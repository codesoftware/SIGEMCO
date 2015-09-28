/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.email;

import co.com.hotel.dto.Empresa;
import co.com.hotel.logica.empresa.Emp_EmpresaLogica;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    private String mensaje;
    private String destinatario;
    private String asunto;

    public SendMail(String mensaje, String destinatario, String asunto) {
        this.mensaje = mensaje;
        this.destinatario = destinatario;
        this.asunto = asunto;
    }

    /**
     * Función que envia correos desde una cuenta GMAIL
     * @return true si envia bien el correo
     */
    public boolean send() {
        //obtiene los datos de la empresa para el envío del correo
         Emp_EmpresaLogica logicaEmp = new Emp_EmpresaLogica();
        Empresa empresa = logicaEmp.obtieneDatosEmpresa();
        ResourceBundle rb = ResourceBundle.getBundle("co.com.sigemco.alfa.archivos.MAIL");
        try {
            String host = rb.getString("HOST").trim();
            String starttls = rb.getString("STARTLS").trim();
            String port = rb.getString("PORT").trim();
            String usuarioCorreo = rb.getString("USER").trim();
            String auth = rb.getString("AUTH").trim();
            String password = rb.getString("PASSWORD").trim();
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.setProperty("mail.smtp.starttls.enable", starttls);
            props.setProperty("mail.smtp.port", port);
            props.setProperty("mail.smtp.user", usuarioCorreo);
            props.setProperty("mail.smtp.auth", auth);

            Session session = Session.getInstance(props, null);
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuarioCorreo));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(destinatario));
            message.setSubject(empresa.getNombre()+"---"+asunto);
            message.setContent(multiParte);

            Transport t = session.getTransport("smtp");
            t.connect(usuarioCorreo, password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

}
