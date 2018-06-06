package com.telappoint.commonrestws.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telappoint.commonrestws.common.controller.json.JsonDataHandler;
import com.telappoint.commonrestws.common.utils.PropertyUtils;

/**
 * 
 * @author Balaji
 * 
 */
public class CoreUtils {

	private static Logger logger = Logger.getLogger(CoreUtils.class);

	/**
	 * 
	 * @param serviceId
	 *            - ServiceId which is selected by customer.
	 * @param serviceList
	 *            - Service list based on location
	 * @return
	 */
	/*public static int getServiceBlocks(int serviceId, List<ServiceTO> serviceList) {
		int serId = -1;
		int blocks = 1;
		for (int i = 0; i < serviceList.size(); i++) {
			ServiceTO serviceTO = (ServiceTO) serviceList.get(i);
			serId = serviceTO.getId();
			if (serId == serviceId) {
				blocks = serviceTO.getBlocks();
				break;
			}
		}
		return blocks;
	}*/

	public static String getIpAddress() {
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
			String ipAddress = inetAddress.getHostAddress();
			return ipAddress;
		} catch (UnknownHostException unhoste) {
			logger.error("Error:" + unhoste, unhoste);
		}
		return null;
	}

	public static String getProperField(String fieldName) {
		StringBuilder sb = new StringBuilder();
		String tempStr = fieldName;
		String split[] = tempStr.split("_");
		for (int i = 0; i < split.length; i++) {
			if (i == 0) {
				sb.append(split[i]);
			} else {
				sb.append(Character.toUpperCase(split[i].charAt(0)));
				sb.append(split[i].substring(1, split[i].length()));
			}
		}
		return sb.toString();
	}

	/*public static Object getPropertyValue(Object object, String fieldName) throws NoSuchFieldException {
		try {
			BeanInfo info = Introspector.getBeanInfo(object.getClass());
			for (PropertyDescriptor pd : info.getPropertyDescriptors())
				if (pd.getName().equals(fieldName)) {
					Method getter = pd.getReadMethod();
					if (getter != null) {
						getter.setAccessible(true);
						return getter.invoke(object, null);
					}

				}
		} catch (Exception e) {
			throw new NoSuchFieldException(object.getClass() + " has no field " + fieldName);
		}
		return "";
	}*/

	public static void setPropertyValue(Object object, String propertyName, Object propertyValue) throws Exception {
		try {
			BeanInfo bi = Introspector.getBeanInfo(object.getClass());
			PropertyDescriptor pds[] = bi.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				if (pd.getName().equals(propertyName)) {
					Method setter = pd.getWriteMethod();
					if (setter != null) {
						setter.invoke(object, new Object[] { propertyValue });
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public String getConfNumber() {
		Calendar cal = Calendar.getInstance();
		long milliSeconds = cal.get(Calendar.MILLISECOND);
		return String.valueOf(milliSeconds);
	}

	/*public static int getDisplayMaxTimeSlots(ApptSysConfigTO apptSysConfigTO, String deviceType) {
		int maxTimeSlots = 10;
		if (CommonApptDeskContants.ONLINE.getValue().equalsIgnoreCase(deviceType)) {
			maxTimeSlots = apptSysConfigTO.getMax_display_time_slots_online();
		} else if (CommonApptDeskContants.MOBILE.getValue().equalsIgnoreCase(deviceType)) {
			maxTimeSlots = apptSysConfigTO.getMax_display_time_slots_mobile();
		} else if (CommonApptDeskContants.IVRTTS.getValue().equalsIgnoreCase(deviceType)) {
			maxTimeSlots = apptSysConfigTO.getMax_display_time_slots_ivr();
		} else if (CommonApptDeskContants.IVRAUDIO.getValue().equalsIgnoreCase(deviceType)) {
			maxTimeSlots = apptSysConfigTO.getMax_display_time_slots_ivr();
		} else if (CommonApptDeskContants.SMS.getValue().equalsIgnoreCase(deviceType)) {
			maxTimeSlots = apptSysConfigTO.getMax_display_time_slots_sms();
		}
		return maxTimeSlots;
	}*/

	public static void printJSONResponse(JsonDataHandler jsonDataHandler,String methodName) {
		Gson gson = new GsonBuilder().create();
		String response = gson.toJson(jsonDataHandler);
		//TODO change to debug - before moving to production.
		logger.error(methodName + " method response:" + response);
		System.out.println(methodName + " method response:" + response);
	}
	
	public static String getToken(String appendedStr) {
		String token = UUID.nameUUIDFromBytes(appendedStr.getBytes()).toString();
		return token;
	}
	
	public static Date addMinsToCurrentTime(int mins) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, mins);
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		StringBuilder appendedStr = new StringBuilder();
		appendedStr.append("DEMOMEDG");
		appendedStr.append("1");
		appendedStr.append(new Timestamp(System.currentTimeMillis()));
		
		
		System.out.println("token = " + CoreUtils.getToken(appendedStr.toString()));
	}
	
	public static void closeEntityManager(EntityManager entityManager) {
		if(entityManager != null) {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}
	
    public static void sendErrorEmail(String subject,String body) {
    	logger.debug("sendEmail method entered.");
		Properties mailProperties = PropertyUtils.getMailProperties();
		String sendMail = mailProperties.getProperty("error.mail.send");

		if (sendMail != null && "yes".equalsIgnoreCase(sendMail)) {
			String userName = mailProperties.getProperty("mail.smtp.user");
			String passWord = mailProperties.getProperty("mail.smtp.password");
			String host = mailProperties.getProperty("mail.smtp.hostname");

			String fromAddress = mailProperties.getProperty("mail.fromaddress");
			String toAddress = mailProperties.getProperty("error.mail.to");
			String ccAddress = mailProperties.getProperty("error.mail.cc");
			String replyAddress = mailProperties.getProperty("mail.replayaddress");

			Properties props = getMailSettings(mailProperties);

			try {
				Session session = Session.getDefaultInstance(props, null);
				MimeMessage msg = new MimeMessage(session);
				msg.setSubject(subject);
				msg.setFrom(new InternetAddress(fromAddress));
				InternetAddress[] replyAddresses = new InternetAddress[1];
				replyAddresses[0] = new InternetAddress(replyAddress);
				msg.setReplyTo(replyAddresses);

				// This part for CC Address Details
				if (ccAddress != null && !"".equals(ccAddress) && ccAddress.length() > 0) {
					InternetAddress[] inetAddress = getInetAddress(ccAddress);
					msg.setRecipients(Message.RecipientType.CC, inetAddress);
				}
				// This part for TO Address Details
				if (toAddress != null && !"".equals(toAddress) && toAddress.length() > 0) {
					InternetAddress[] inetAddress = getInetAddress(toAddress);
					msg.setRecipients(Message.RecipientType.TO, inetAddress);
				}

				msg.setContent(body, "text/html");
				msg.saveChanges();
				Transport transport = session.getTransport("smtp");
				transport.connect(host, userName, passWord);
				transport.sendMessage(msg, msg.getAllRecipients());
				transport.close();
			} catch (AddressException ade) {
				logger.error("Error:" + ade, ade);
			} catch (MessagingException me) {
				logger.error("Error:" + me, me);
			}
		}
	}
    
    private static Properties getMailSettings(Properties mailProperties) {
    	logger.debug("getMailSettings method entered.");
		String debug = mailProperties.getProperty("mail.smtp.debug");
		String userName = mailProperties.getProperty("mail.smtp.user");
		String port = mailProperties.getProperty("mail.smtp.port");
		String starttls = mailProperties.getProperty("mail.smtp.starttls.enable");
		String auth = mailProperties.getProperty("mail.smtp.auth");
		String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
		String fallback = "false";

		Properties props = new Properties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", "localhost");

		if (!"".equals(port)) {
			props.put("mail.smtp.port", port);
		}
		if (!"".equals(starttls)) {
			props.put("mail.smtp.starttls.enable", starttls);
		}
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.debug", debug);

		if (!"".equals(port)) {
			props.put("mail.smtp.socketFactory.port", port);
		}
		props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		props.put("mail.smtp.socketFactory.fallback", fallback);
		logger.debug("getMailSettings method exit.");
		return props;
	}
    
    private static InternetAddress[] getInetAddress(String address) throws AddressException {
		InternetAddress[] inetAddress = null;
		StringTokenizer tokens = new StringTokenizer(address, ",");
		if (tokens != null && tokens.hasMoreTokens()) {
			inetAddress = new InternetAddress[tokens.countTokens()];
			int i = 0;
			while (tokens.hasMoreTokens()) {
				String token = tokens.nextToken();
				inetAddress[i++] = new InternetAddress(token.trim());
			}
		}
		return inetAddress;
	}
    
    public static String encrypt(String value) {
		String ecryptedValue=null;
		String keyValue = FileUtils.getPropertyValueFromCommonProperties("PASSWORD_ENCRYPT_DECRYPT_KEY_VALUE");
		try {
			ecryptedValue = new com.telappoint.commonrestws.common.core.AESEncryptor(keyValue).encrypt(value);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ecryptedValue;
	}
	
	public static String decrypt(String encryptedValue) {
		String decryptedValue = null;
		String keyValue = FileUtils.getPropertyValueFromCommonProperties("PASSWORD_ENCRYPT_DECRYPT_KEY_VALUE");
		try {	
			decryptedValue = new com.telappoint.commonrestws.common.core.AESEncryptor(keyValue).decrypt(encryptedValue);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return decryptedValue;
	}
}
