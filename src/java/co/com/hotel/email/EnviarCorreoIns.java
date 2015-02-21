package co.com.hotel.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase con la cual se pueden enviar correos a los usuarios
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author nicolas
 */
public class EnviarCorreoIns {

    private Properties config;
    private Session session;
    private String mensaje;
    private String Asunto;

    /**
     * Constructor de la clase en la cual se inicializan todos las propiedades
     * necesarias para la configuracion de envio de correos.
     *
     * @since 1.0.0
     */
    public EnviarCorreoIns() {
        this.config = new Properties();
        this.config.put("mail.smtp.host", "smtp.gmail.com");
        this.config.put("mail.smtp.socketFactory.port", "465");
        this.config.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        this.config.put("mail.smtp.auth", "true");
        this.config.put("mail.smtp.port", "465");
    }

    /**
     * Funcion la cual se encarga de logear el correo desde el cual se va enviar
     * los correos
     *
     * @since 1.0.0
     */
    public void logginCorreo() {
        this.session = Session.getDefaultInstance(this.config,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("jnsierrac@gmail.com", "flaco1030585312");
                    }
                });
    }

    /**
     * Función desde la cual se envian los correos al usuario que se le desea
     * enviar.
     *
     * @param correo String correo del usuario al cual se le desea enviar el
     * @return Mensaje von el cual se informa que se envio el correo  correctamente
     */

    public String envioCorreoIns(String correo) {
        String rta = "Correo enviado correctamente-Ok";
        try {

            Message message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress("from@no-spam.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject(this.Asunto);
            message.setText(this.mensaje);

            Transport.send(message);

            System.out.println("Mensaje enviado");

        } catch (MessagingException e) {
            rta = "Error al enviar correo-Err";
            throw new RuntimeException(e);
        }
        return rta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

}
