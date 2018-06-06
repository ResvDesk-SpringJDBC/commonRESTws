package com.telappoint.commonrestws.common.utils;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	
	//private static Logger logger = Logger.getLogger(MailSender.class);	
	private static MailSender mailSender = null;
	public static final String EMAIL_DELIMETER_SEMICOLON = ";";
	
	private MailSender(){			
	}
	
	public static MailSender getInstance(){		
	      if (mailSender == null) {
	    	  mailSender = new MailSender();
	      }           
	     return mailSender;
	}
	
	
	  public synchronized boolean sendMail(String toAddress,String username,String url){
		  
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
		  String subject = mailProperties.getProperty("mail.subject");
		  String text = mailProperties.getProperty("mail.bodymessage");
		  text = text.replaceAll("@usernameeParam@", username);
		  //text = text.replaceAll("@loginusernameParam@", loginusername);
		  //text = text.replaceAll("@passwordParam@", password);
		 /* String url = mailProperties.getProperty("resetpassword.reuest.link");
		  url = url.replaceAll("@HOST_NAME@", hostName);
		  url = url.replaceAll("@PORT@", hostport);
		  url = url.replaceAll("@APPLICATION_NAME@", applicationName);*/
		  
		  System.out.println("url : "+url);
		  text = text.replaceAll("@RESET_PASSWORD_LINK@", url);
		  
		  String signature = mailProperties.getProperty("mail.signature");
		  text = text+signature;		  
		  
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

	  try {	
	       Session session = Session.getDefaultInstance(props, null);
	       session.setDebug(debug);
	       MimeMessage msg = new MimeMessage(session);
	       msg.setText(text);
	       msg.setSubject(subject);
	       msg.setFrom(new InternetAddress(fromAddress));
	       InternetAddress[] replyAddresses = new InternetAddress[1];
	       replyAddresses[0] = new InternetAddress(replyAddress);
	       msg.setReplyTo(replyAddresses);
	       msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
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
	  }  catch (Exception mex){
	         mex.printStackTrace();
	         return false;
	  }
  }
}