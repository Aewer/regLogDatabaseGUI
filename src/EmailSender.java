import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class EmailSender {
    String code = Recover.code;

    public EmailSender() throws SQLException {
    }

    public void emailSender(String email)
    {
        String to = email;
        String from = "aewer321@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator()
        {

            protected PasswordAuthentication getPasswordAuthentication()
            {
                //return new PasswordAuthentication("recovery23728@gmail.com", "eqjzdpxkkobavcvm");
                return new PasswordAuthentication("aewer321@gmail.com", "xojhroigbipfvaom");
            }

        });
        session.setDebug(false);


        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Password recovery");
            message.setText("The recovery code is:" + code);
            Transport.send(message);
        }
        catch (MessagingException mex) {
            throw new RuntimeException(mex);
        }
    }
}
