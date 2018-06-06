package com.telappoint.commonrestws.support.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.telappoint.commonrestws.common.utils.FileUtils;

/**
 * @author Murali
 * 
 * 
 */

@Service("emailUtils")
public class EmailUtils {

	private static Logger log = Logger.getLogger(EmailUtils.class);

	@Async("supportMailExecutor")
	public void sendEmail(EmailDetailsTO emailDetailsTO) {
		sendSupportRequestMail(emailDetailsTO);
	}

	 public boolean sendSupportRequestMail(EmailDetailsTO emailDetailsTO){	
		try {	
			  Properties mailProperties = FileUtils.getMailProperties();		  
			  String userName = mailProperties.getProperty("mail.smtp.user");
			  String passWord = mailProperties.getProperty("mail.smtp.password"); 
			  String fromAddress = mailProperties.getProperty("mail.fromaddress");
			  String ccAddress = mailProperties.getProperty("mail.ccaddress");
			  String host = mailProperties.getProperty("mail.smtp.hostname");
			  String port = mailProperties.getProperty("mail.smtp.port");
			  String starttls = mailProperties.getProperty("mail.smtp.starttls.enable");
			  String auth = mailProperties.getProperty("mail.smtp.auth");
			  String replyAddress = mailProperties.getProperty("mail.replayaddress");
			  boolean debug = true;
			  String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
			  String fallback = "false";
			 
			  String subject = emailDetailsTO.getSubject();
			  String text = emailDetailsTO.getMailBody();
			  
			  Properties props = new Properties();
	          //Properties props=System.getProperties();
	          props.put("mail.smtp.user", userName);
	          props.put("mail.smtp.host", "localhost");
	
	          if(!"".equals(port))
	       	    props.put("mail.smtp.port", port);
	          if(!"".equals(starttls))
	       	    props.put("mail.smtp.starttls.enable",starttls);
	         	props.put("mail.smtp.auth", auth);
	         if(debug){
	       	  	props.put("mail.smtp.debug", "true");
	         }else{
	       	  	props.put("mail.smtp.debug", "false");
	         }
	         if(!"".equals(port))
	        	 props.put("mail.smtp.socketFactory.port", port);
	         props.put("mail.smtp.socketFactory.class",socketFactoryClass);
	         props.put("mail.smtp.socketFactory.fallback", fallback);
	         
		     Session session = Session.getDefaultInstance(props, null);
		     session.setDebug(debug);
		     MimeMessage msg = new MimeMessage(session);
		     //msg.setText(text, "text/html; charset=utf-8");
		     msg.setContent(text, "text/html");
		     msg.setSubject(subject);
		     msg.setFrom(new InternetAddress(fromAddress));
		     InternetAddress[] replyAddresses = new InternetAddress[1];
		     replyAddresses[0] = new InternetAddress(replyAddress);
		     msg.setReplyTo(replyAddresses);
		     msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDetailsTO.getToMailId()));
		     msg.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));   
		     // MimeMultipart mpRoot = getMultiPart(text,clientRequestInfo,requester,clientNature,clientName);
		     //msg.setContent(mpRoot);
		     //msg.saveChanges();
			 // session.setDebug(true);
		     Transport transport = session.getTransport("smtp");
		     transport.connect(host, userName, passWord);
		     transport.sendMessage(msg, msg.getAllRecipients());
		     transport.close();
		     return true;
		  }catch (Exception ex){
			 log.error("Error : "+ex.getMessage(),ex);
		     return false;
		  }
	 }
}
