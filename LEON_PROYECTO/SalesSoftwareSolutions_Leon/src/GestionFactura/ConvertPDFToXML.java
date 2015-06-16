/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFactura;

import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import org.xml.sax.helpers.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//   Clase que toma un PDF existente y lo convierte en extension .xml.
public class ConvertPDFToXML {

    static StreamResult streamResult;
    static TransformerHandler handler;
    static AttributesImpl atts;

//    Método que localiza la ruta de la factura solicitada.
//    Tiene como parametro el folio de la factura realizada recientemente.
//    Llama al metodo initXML.
    public ConvertPDFToXML(String folio) {
        try {
            Document document = new Document();
            document.open();
            PdfReader reader = new PdfReader("C:/Facturas/" + folio + "/" + folio + ".pdf");
            PdfDictionary page = reader.getPageN(1);
            PRIndirectReference objectReference = (PRIndirectReference) page
                    .get(PdfName.CONTENTS);
            PRStream stream = (PRStream) PdfReader
                    .getPdfObject(objectReference);
            byte[] streamBytes = PdfReader.getStreamBytes(stream);
            PRTokeniser tokenizer = new PRTokeniser(streamBytes);

            StringBuffer strbufe = new StringBuffer();
            while (tokenizer.nextToken()) {
                if (tokenizer.getTokenType() == PRTokeniser.TK_STRING) {
                    strbufe.append(tokenizer.getStringValue());
                }
            }
            String test = strbufe.toString();
            streamResult = new StreamResult("C:/Facturas/" + folio + "/" + folio + ".xml");
            initXML();
            process(test);
            closeXML();
            document.add(new Paragraph(".."));
            document.close();
        } catch (Exception e) {
        }
    }

//    Metodo que realiza las conversiones del archivo PDF.
//    En el handler permite dar formato de inicio del .xml.
//    Es la parte de header del .XML.
    public static void initXML() throws ParserConfigurationException,
            TransformerConfigurationException, SAXException {
        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory
                .newInstance();

        handler = tf.newTransformerHandler();
        Transformer serializer = handler.getTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        serializer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "4");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        handler.setResult(streamResult);
        handler.startDocument();
        atts = new AttributesImpl();
        handler.startElement("Sales", "", "SalesSolutions:Comprobante", atts);
    }

//    Meto que reliza la separacion de datos (cadenas de texto del PDF).
//    Es la parte de desarrollo del .xml
    public static void process(String s) throws SAXException {
        String[] elements = s.split("\\|");
        atts.clear();
        handler.startElement("", "", "Information", atts);
        handler.characters(elements[0].toCharArray(), 0, elements[0].length());
        handler.endElement("", "", "Information");
    }

//    Cierra la parte final del .xml. (Footer).
    public static void closeXML() throws SAXException {
        handler.endElement("", "", "SalesSolutions:Comprobante");
        handler.endDocument();
    }
}
