/*
 * Author: Cody Martz
 * Class: CMSC 495 6381
 * Date: 01/26/2018
 * Description: This class defines how to email a message to a defined user.
 */
package encryptionproject;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SecureEmail {
    
  public static void emailMessage(final String secureMessage, final String userEmail) {
    // Define email config for gmail account
    Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("sdevtestuser@gmail.com", "test123!@#");
        }});
    
    try {
      // set encoding
      MimeMessage message = new MimeMessage(session);
      // set sender
      message.setFrom(new InternetAddress("sdevtestuser@gmail.com"));
      // set receipient
      message.addRecipient(Message.RecipientType.TO,
          new InternetAddress(userEmail));
      // set subject line
      message.setSubject("Secret Message");
      // set message body
      message.setText("The secret message is: \n" + secureMessage);
      // attempt to send email
      Transport.send(message);

    } catch (MessagingException me) {
      System.out.println("EMail Error: " + me.toString());
    }
  } // End of emailMessage method
} // End of SecureEmail class
