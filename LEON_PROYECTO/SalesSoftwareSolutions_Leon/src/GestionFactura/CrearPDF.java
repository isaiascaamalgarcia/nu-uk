/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFactura;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfPTable;
import ModelDB.conexion;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//Clase que permite generar el archivo .pdf, de acuerdo a los datos proporcionados.
public class CrearPDF {

//    Estilos de formato para las cadenas de texto utilizadas.
    private String folio = "";
    private conexion con = new conexion();
    private static Font guindaFont = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.BOLD, new BaseColor(116, 16, 30));
    private static Font Font1 = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.BOLD, BaseColor.BLACK);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 8,
            Font.NORMAL, BaseColor.BLACK);
    private static Font subFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.BLACK);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD);
    private static Font smallBoldC = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD, new BaseColor(116, 16, 30));
    private static Font smallBoldW = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD, BaseColor.WHITE);
    private static PdfPTable tabla, tabla1, tabla2;
    private static PdfPCell cell;

//    Variables que se utilizan para la separacion de datos.
    String URL = "";
    String[] cantidad;
    String[] unidad;
    String[] concepto;
    String[] precio;
    String[] importe;
    String[] informacionC;
    String[] cadena;
    String[] cadena1;
    String columnas = "";
    int max = 0;

//    Método inicial para crear el archivo .pdf.
//    Tiene como parametro el folio de la factura a generar.
//    Llama a los métodos carpeta() y informacionBD().
    public CrearPDF(String folio) {
        this.folio = folio;
        carpeta();
        informacionBD();
    }

//    Método que crea en el disco local C de la computadora las carpetas con
//    las ubicaciones establecidas.
    public void carpeta() {
        File folder;
        try {
            folder = new File("C:/Facturas");
            folder.mkdir();
            folder = new File("C:/Facturas/" + this.folio);
            folder.mkdir();
        } catch (Exception e) {
        }
    }

//    Método que contiene la informacion del archivo PDF
    private void informacionPdf(Document document) {
        document.addTitle("Factura Electronica");
        document.addAuthor("Shary Chuc");
        document.addCreator("Sales Solutions");
    }

//    Método que obtiene la información necesaria de la BD para generar la factura.
//    Con los datos obtenidos se elebora la factura.
    public void informacionBD() {
        try {
            columnas = "infoCliente,cantidad,unidad,concepto,precio,"
                    + "importe,subtotal,iva,total,toLetra,"
                    + "fecha,hora";
            con.busqueda("factura", "folio", columnas, folio);
            System.out.println(con.registro_busqueda);
            cadena = con.registro_busqueda.split(",");

            columnas = "rfc,empresa,direccion,email";
            con.busqueda("empresa", "id", columnas, "1");
            System.out.println(con.registro_busqueda);
            cadena1 = con.registro_busqueda.split(",");

            generarQR(cadena, cadena1);
            creaFactura(cadena, cadena1);
        } catch (Exception e) {
        }
    }

//    Método que llama a la clase QR, genera la imagen con la información de
//    los vectores proporcionados.
//    Cadena corresponde a toda la información del cliente y cadena1 a el rfc y empresa del emisor.
    public void generarQR(String[] cadena, String[] cadena1) {
        try {
            QR GenerarQr = new QR(folio, cadena1[0], cadena1[1], cadena[0]);
        } catch (Exception e) {
        }
    }

//    Método que llama a la clase ConvertPDFToXML.
//    Permite convertir el archivo .pdf en .xml.
    public void generarXML() {
        try {
            System.out.println("VERIFICANDO FOLIO" + this.folio);
            ConvertPDFToXML GenerarXml = new ConvertPDFToXML(this.folio);
        } catch (Exception e) {
        }
    }

//    Método que se utiliza para poner la información de cabecera (header) del archivo pdf.
    public void header(Document documento) {
        try {
            tabla = new PdfPTable(1);
            tabla.getDefaultCell().setBorder(0);
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int k = 0; k < cadena1.length; k++) {
                tabla.addCell(new Phrase(cadena1[k], guindaFont));
            }

            tabla1 = new PdfPTable(1);
            tabla1.getDefaultCell().setBorder(3);
            tabla1.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla1.addCell(tabla);
            documento.add(tabla1);
        } catch (Exception e) {
        }

    }

//    Método principal para el desarrollo de la factura en .pdf
//    En este Método se almacena toda la información obtenida de la BD de la 
//    tabla de "factura" y se imprime en el .pdf creado.
    public void creaFactura(String[] cadena, String[] cadena1) {
        try {
            URL = "C:/Facturas/" + this.folio + "/" + this.folio + ".pdf";

            // Se crea el documento
            Document documento = new Document();

            // Se crea el OutputStream para el fichero donde se guardara el pdf.
            FileOutputStream ficheroPdf = new FileOutputStream(URL);
            // Se asocia el documento al OutputStream y se indica que el espaciado entre
            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
            documento = new Document(PageSize.LETTER, 30, 30, 30, 30);
            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);

            // Se abre el documento.
            documento.open();

//            Llamada de los métodos de informacion
            informacionPdf(documento);
            header(documento);

//            Llenado de informacion a las variables respectivas.
            informacionC = cadena[0].split("[|]");
            cantidad = cadena[1].split("[|]");
            unidad = cadena[2].split("[|]");
            concepto = cadena[3].split("[|]");
            precio = cadena[4].split("[|]");
            importe = cadena[5].split("[|]");

//            Tabla en el .pdf con la informacion del cliente.
            tabla1 = new PdfPTable(3);
            tabla1.getDefaultCell().setBorder(0);
            tabla1.setHorizontalAlignment(Element.ALIGN_CENTER);
            max = informacionC.length;

            tabla = new PdfPTable(1);
            cell = new PdfPCell(new Phrase("Datos del cliente", smallBoldW));
            cell.setBackgroundColor(new BaseColor(116, 16, 30));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.getDefaultCell().setBorder(0);
            tabla.addCell(cell);

            for (int i = 0; i < max; i++) {
                tabla.addCell(new Phrase(informacionC[i], smallBold));
            }
//            Se agrega nueva linea (salto).
            documento.add(Chunk.NEWLINE);
//            Tabla que contiene informacion de la factura, folio, dia, fecha.
            tabla2 = new PdfPTable(1);
            tabla2.getDefaultCell().setBorder(0);

//            Agregado de información.
            cell = new PdfPCell(new Phrase("Factura Folio", smallBoldW));
            cell.setBackgroundColor(new BaseColor(116, 16, 30));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla2.addCell(cell);

            cell = new PdfPCell(new Phrase(folio, smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tabla2.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha de emisión", smallBoldW));
            cell.setBackgroundColor(new BaseColor(116, 16, 30));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla2.addCell(cell);

            cell = new PdfPCell(new Phrase(cadena[10], smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tabla2.addCell(cell);

            cell = new PdfPCell(new Phrase("Hora de emisión", smallBoldW));
            cell.setBackgroundColor(new BaseColor(116, 16, 30));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla2.addCell(cell);

            cell = new PdfPCell(new Phrase(cadena[11], smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tabla2.addCell(cell);

            tabla1.addCell(tabla);
            tabla1.addCell(" ");
            tabla1.addCell(tabla2);
//            Se añade al documento .pdf la tabla creads.
            documento.add(tabla1);
            documento.add(Chunk.NEWLINE);

//            Tabla principal con la información de la compra del cliente.
            tabla = new PdfPTable(5);
            tabla.getDefaultCell().setBorder(3);
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.getDefaultCell().setBorderColor(new BaseColor(116, 16, 30));

//            Llenado de información.
            for (int j = 0; j <= cantidad.length; j++) {
                if (j == 0) {
                    PdfPCell cell1 = new PdfPCell(new Paragraph("CANTIDAD", smallBoldW));
                    cell1.setBackgroundColor(new BaseColor(116, 16, 30));
                    PdfPCell cell2 = new PdfPCell(new Paragraph("UNIDAD", smallBoldW));
                    cell2.setBackgroundColor(new BaseColor(116, 16, 30));
                    PdfPCell cell3 = new PdfPCell(new Paragraph("CONCEPTO", smallBoldW));
                    cell3.setBackgroundColor(new BaseColor(116, 16, 30));
                    PdfPCell cell4 = new PdfPCell(new Paragraph("PRECIO", smallBoldW));
                    cell4.setBackgroundColor(new BaseColor(116, 16, 30));
                    PdfPCell cell5 = new PdfPCell(new Paragraph("IMPORTE", smallBoldW));
                    cell5.setBackgroundColor(new BaseColor(116, 16, 30));
                    tabla.addCell(cell1);
                    tabla.addCell(cell2);
                    tabla.addCell(cell3);
                    tabla.addCell(cell4);
                    tabla.addCell(cell5);

                } else {
                    tabla.addCell(new Phrase(cantidad[j - 1], subFont));
                    tabla.addCell(new Phrase(unidad[j - 1], subFont));
                    tabla.addCell(new Phrase(concepto[j - 1], subFont));
                    tabla.addCell(new Phrase("$" + precio[j - 1], subFont));
                    tabla.addCell(new Phrase("$" + importe[j - 1], subFont));
                }

            }

//            Se añade al documento .pdf la tabla creads.
            documento.add(tabla);
            documento.add(Chunk.NEWLINE);

//            Tabla con el contenido de pago del cliente.
            tabla = new PdfPTable(1);
            tabla.getDefaultCell().setBorder(0);

            cell = new PdfPCell(new Phrase("Subtotal:", smallBoldW));
            cell.setBackgroundColor(new BaseColor(116, 16, 30));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell);

            cell = new PdfPCell(new Phrase("$" + cadena[6], subFont1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tabla.addCell(cell);

            cell = new PdfPCell(new Phrase("IVA:", smallBoldW));
            cell.setBackgroundColor(new BaseColor(116, 16, 30));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell);

            cell = new PdfPCell(new Phrase("$" + cadena[7], subFont1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tabla.addCell(cell);

            cell = new PdfPCell(new Phrase("Total:", smallBoldW));
            cell.setBackgroundColor(new BaseColor(116, 16, 30));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell);

            cell = new PdfPCell(new Phrase("$" + cadena[8], subFont1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tabla.addCell(cell);

//            Agregado de la imagen codigo QR
            Image foto = Image.getInstance("C:/Facturas/" + this.folio + "/QR" + this.folio + ".gif");
            foto.scaleToFit(100, 100);
            foto.setAlignment(Chunk.ALIGN_MIDDLE);

            tabla2 = new PdfPTable(4);
            tabla2.getDefaultCell().setBorder(0);
            tabla2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla2.addCell(foto);
            tabla2.addCell("");
            tabla2.addCell("");
            tabla2.addCell(tabla);
            documento.add(tabla2);
            documento.add(Chunk.NEWLINE);

//            Tabla que contiene el total a pagar en letras.
            tabla1 = new PdfPTable(1);
            tabla1.getDefaultCell().setBorder(0);

            cell = new PdfPCell(new Phrase("Total en letras:", smallBoldW));
            cell.setBackgroundColor(new BaseColor(116, 16, 30));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla1.addCell(cell);

            cell = new PdfPCell(new Phrase(cadena[9], subFont1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla1.addCell(cell);
            documento.add(tabla1);
            documento.add(Chunk.NEWLINE);

//            Tabla que contiene el footer del .pdf
            PdfPTable tabla3 = new PdfPTable(1);
            tabla3.getDefaultCell().setBorder(0);
            tabla3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell = new PdfPCell(new Phrase("Método de pago: NO IDENTIFICADO", smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tabla3.addCell(cell);

            cell = new PdfPCell(new Phrase("Forma de pago: PAGO EN UNA SOLA EXHIBICION", smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tabla3.addCell(cell);

            cell = new PdfPCell(new Phrase("ESTE DOCUMENTO ES UNA REPRESENTACIÓN IMPRESA DE UN CFDI", Font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tabla3.addCell(cell);

            cell = new PdfPCell(new Phrase("Régimen fiscal: RÉGIMEN DE ACTIVIDADES EMPRESARIALES Y PROFESIONALES", smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tabla3.addCell(cell);
            documento.add(tabla3);

//            Se cierra el documento.
            documento.close();
//            Llama el método generarXML para la eleboración del .xml.
            generarXML();

        } catch (Exception e) {
        }
    }
}
