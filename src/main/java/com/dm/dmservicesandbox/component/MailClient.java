package com.dm.dmservicesandbox.component;

import com.dm.dmservicesandbox.domain.EmailData;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailClient {

    @Value("${spring.mail.smtp.user}")
    private String sender;

    @Value("${spring.mail.smtp.pwd}")
    private String senderPwd;

    @Value("${spring.mail.smtp.host}")
    private String smtpHost;

    @Value("${spring.mail.smtp.port}")
    private String smtpPort;

    @Value("${spring.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${spring.mail.smtp.starttls.enable}")
    private String mailStarttlsEnable;

    @Value("${spring.mail.smtp.ssl.enable}")
    private String mailSslEnable;

    public void sendMail(EmailData emailData) throws RuntimeException {
        String toAddress = emailData.getReceiver();

        Properties props = new Properties();
        props.put("mail.smtp.auth", mailSmtpAuth);
        props.put("mail.smtp.starttls.enable", mailStarttlsEnable);
        props.put("mail.smtp.ssl.enable", mailSslEnable);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props,
            new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, senderPwd);
                }
            });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            message.setSubject(emailData.getSubject());
            message.setContent(emailData.getMessage(), "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
