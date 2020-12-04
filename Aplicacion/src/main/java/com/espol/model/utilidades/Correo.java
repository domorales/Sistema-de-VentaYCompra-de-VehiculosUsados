/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.model.utilidades;

import com.espol.exceptionsP.ProblemaMensajeException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dilan
 */
public class Correo {

    private static String remitente;  //Para la dirección nomcuenta@gmail.com
    private static String contraseña;
    private static String port;
    private static String url;
//procede a enviar el correo al destinatario

    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) throws ProblemaMensajeException {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        try (InputStream archivo = new FileInputStream("archivo.properties");) {
            Properties props = new Properties();
            props.load(archivo);
            remitente = props.getProperty("db.usuario");
            contraseña = props.getProperty("db.password");
            port = props.getProperty("db.port");
            url = props.getProperty("db.url");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Properties props = System.getProperties();
        props.put("mail.smtp.host", url);  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", contraseña);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", port); //El puerto SMTP seguro de Google
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            javax.mail.Transport transport = session.getTransport("smtp");
            transport.connect(url, remitente, contraseña);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            System.out.println("no se envio mensaje");;//Si se produce un error     
        }
    }

}
