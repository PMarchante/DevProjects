/*
 * Author: Cody Martz
 * Class: CMSC 495 6381
 * Date: 01/26/2018
 * Description: This class defines how to email a message to a defined user.
 */
package encryptionproject;

import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SecureEmail {
    
  public void emailMessage(final String secureMessage, final String toUserEmail, final String oauthToken) {
    // Email Application constants
    final String FROM_USER_EMAIL = "sdevtestuser@gmail.com";
    final String FROM_USER_FULLNAME = "Simple Encryptor/Decryptor";
    final String SMTP_SERVER_HOST = "smtp.gmail.com";
    Session session = null;
    MimeMessage message = null;
    
    // Establish SMTP protocol settings
    try {
      // Don't use file keystore; create one per class usage
      MailSSLSocketFactory socketFactory = new MailSSLSocketFactory();
      socketFactory = new MailSSLSocketFactory();
      // Setup smtp properties
      Properties props = System.getProperties();
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtp.port", "587");
      props.put("mail.smtp.ssl.trust", "*");
      props.put("mail.smtp.socketFactory.class", socketFactory);
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.auth.mechanisms", "XOAUTH2");

      // Apply smtp properties to the session
      session = Session.getDefaultInstance(props);
      // Turn on debugging for smtp connection process
      session.setDebug(true);
      // Trust all new web host certs
      socketFactory.setTrustAllHosts(true);
      
    } catch (GeneralSecurityException ex) {
      Logger.getLogger(SecureEmail.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    // Define email settings and content
    try {
      // Set encoding
      message = new MimeMessage(session);
      // Set from and to fields
      message.setFrom(new InternetAddress(FROM_USER_EMAIL, FROM_USER_FULLNAME));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUserEmail));
      // Set subject line
      message.setSubject("Secret Message");
      // Set message body
      message.setText("The secret message is: \n" + secureMessage);

    } catch (Exception ex) {
      Logger.getLogger(SecureEmail.class.getName()).log(Level.SEVERE, null, ex);
    } 
    
    // Attempt to connect and send email
    try {
      
      SMTPTransport transport = new SMTPTransport(session, null);
      // Connect to smtp host with hardcoded email account
      transport.connect(SMTP_SERVER_HOST, FROM_USER_EMAIL, null);
      // Send Oauth token for authentication purposes
      transport.issueCommand("AUTH XOAUTH2 " + new String(BASE64EncoderStream.encode(String.format(
          "user=%s\1auth=Bearer %s\1\1", FROM_USER_EMAIL, oauthToken).getBytes())), 235);
      // Send the message to the destined recipients 
      transport.sendMessage(message, message.getAllRecipients());
      
    } catch(Exception ex1) {
      Logger.getLogger(SecureEmail.class.getName()).log(Level.SEVERE, null, ex1);
    }
    
  } // End of emailMessage method
} // End of SecureEmail class
