/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFactura;

import java.awt.Desktop;
import java.io.File;
import com.barcodelib.barcode.QRCode;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//Clase que genera una imagen.gif
//con la informacion del emisor y receptor de la factura solicitada.
public class QR {

//    Variables estaticas que dan formato a la imagen.
    private static int udm = 0;
    private static int resolucion = 72;
    private static float margenIzq = 0.000f;
    private static float margenDer = 0.000f;
    private static float margenSup = 0.000f;
    private static float margenInf = 0.000f;
    private static int rotacion = 0;
    private static float tamanoModulo = 5.000f;

    QRCode codigoQR = null;
    String mensajeQR = "";
    Desktop aplicacion = null;
    File archivoFile = null;
    String rutaSalida = "";

    /**
     * @param args the command line arguments
     */
//     Constructor: Realiza la imagen.gif con las variables de formato y los parametros del folio de la factura,
//     rfc y empresa del emisor, y toda la información del cliente.
    public QR(String folio, String rfcE, String nombreE, String rfcR) {
        try {
            rutaSalida = "C:/Facturas/" + folio + "/QR" + folio + ".gif";
            codigoQR = new QRCode();
            mensajeQR = folio + "\n" + rfcE + "\n" + nombreE + "\n" + rfcR;
            codigoQR.setData(mensajeQR);
            codigoQR.setDataMode(QRCode.MODE_BYTE);
            codigoQR.setUOM(udm);
            codigoQR.setLeftMargin(margenIzq);
            codigoQR.setResolution(resolucion);
            codigoQR.setRightMargin(margenDer);
            codigoQR.setTopMargin(margenSup);
            codigoQR.setBottomMargin(margenInf);
            codigoQR.setRotate(rotacion);
            codigoQR.setModuleSize(tamanoModulo);

            codigoQR.renderBarcode(rutaSalida);
            aplicacion.getDesktop();
            aplicacion.open(new File(rutaSalida));

        } catch (Exception e) {
        }
    }

}
