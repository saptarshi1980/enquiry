package in.net.dpl.utility;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

  


  
 
	 
	 


		 import java.util.Properties;

		 import javax.mail.Message;
		 import javax.mail.MessagingException;
		 import javax.mail.PasswordAuthentication;
		 import javax.mail.Session;
		 import javax.mail.Transport;
		 import javax.mail.internet.InternetAddress;
		 import javax.mail.internet.MimeMessage;

		 public class SendMail {
		   
			 String recipient;
			 public String getRecipient() {
				return recipient;
			}



			public void setRecipient(String recipient) {
				this.recipient = recipient;
			}



			public String getRefNo() {
				return refNo;
			}



			public void setRefNo(String refNo) {
				this.refNo = refNo;
			}



			String refNo;
			 
			 
			 
			 public void send(String recipient,String refNo){
		       // Recipient's email ID needs to be mentioned.
		       //change accordingly

		       // Sender's email ID needs to be mentioned
		       String from = "itcelldpl@gmail.com";//change accordingly
		       final String username = "itcelldpl";//change accordingly
		       final String password = "itcell@123";//change accordingly

		       // Assuming you are sending email through relay.jangosmtp.net
		       String host = "smtp.gmail.com";

		       Properties props = new Properties();
		       props.put("mail.smtp.auth", "true");
		       props.put("mail.smtp.starttls.enable", "true");
		       props.put("mail.smtp.host", host);
		       props.put("mail.smtp.port", "587");

		       // Get the Session object.
		       Session session = Session.getInstance(props,
		       new javax.mail.Authenticator() {
		          protected PasswordAuthentication getPasswordAuthentication() {
		             return new PasswordAuthentication(username, password);
		          }
		       });

		       try {
		          // Create a default MimeMessage object.
		          Message message = new MimeMessage(session);

		          // Set From: header field of the header.
		          message.setFrom(new InternetAddress(from));

		          // Set To: header field of the header.
		          message.setRecipients(Message.RecipientType.TO,
		          InternetAddress.parse(recipient));

		          // Set Subject: header field
		          message.setSubject("Online Electricity Payment Receipt");
		          String newline = System.getProperty("line.separator");


		          // Now set the actual message
		          message.setText("Hello,"+newline+newline+" Please find the link for Receipt of Online Payment for Electricty Bill."+newline+newline+" https://thedpl.in/dplcrm/PaymentStatus.dpl?ref_no="+refNo+ ""+newline+newline+" Regards,"+newline+newline+"Revenue Section,"+newline+"The Durgapur Projects Limited. ");

		          // Send message
		          Transport.send(message);

		          System.out.println("Sent message successfully....");

		       } catch (MessagingException e) {
		             throw new RuntimeException(e);
		       }
		    }
		 }
	 

