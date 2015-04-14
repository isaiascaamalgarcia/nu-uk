/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFactura;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//Clase que envia al correo electronico del cliente su factura.
public class JavaMail {

    static String nombre_factura = "";
    static String correo_destino = "";

//    Constructor: Tiene como parametros el nombre de la factura y el correo al que se necesita enviar.
    public JavaMail(String factura, String correo) {
        this.nombre_factura = factura;
        this.correo_destino = correo;
        System.out.println("CONTRUCTOR");
    }

//    Método que da inicio al proceso de envio
    public void run() {
        System.out.println(nombre_factura);
        System.out.println(correo_destino);
        try {
//            Protocolos necesarios para el envio.
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "softwaresalessolutions@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);

            // Se compone la parte del texto que recibira el cliente.
            BodyPart texto = new MimeBodyPart();
            texto.setText("A quien corresponda:\nLe informamos que su factura se realizó satisfactoriamente.\nNumero de folio " + nombre_factura + "."
                    + "\nPara mayor informacion visite la sucursal Sales Solutions.\n\n"
                    + "Le agradecemos su preferencia.\n\n\n\n\n" + "ATTE:\n" + "Sales Solutions");

            // Se compone el adjunto del archivo .pdf
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(
                    new DataHandler(new FileDataSource("C:/Facturas/" + nombre_factura + "/"
                                    + nombre_factura + ".pdf")));
            // Se compone el adjunto del archivo .xml
            BodyPart adjunto1 = new MimeBodyPart();
            adjunto1.setDataHandler(
                    new DataHandler(new FileDataSource("C:/Facturas/" + nombre_factura + "/"
                                    + nombre_factura + ".xml")));
            adjunto.setFileName(nombre_factura + ".pdf");
            adjunto1.setFileName(nombre_factura + ".xml");

            // Una MultiParte para agrupar texto y archivos.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            multiParte.addBodyPart(adjunto1);

            // Se compone el correo, dando to, from, subject y el contenido.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("softwaresalessolutions@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(correo_destino));
            message.setSubject("Factura Sales Solutions");
            message.setContent(multiParte);

            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            t.connect("softwaresalessolutions@gmail.com", "sales12345678");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (Exception e) {
        }
    }
}
