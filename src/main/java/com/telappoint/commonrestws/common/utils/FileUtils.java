package com.telappoint.commonrestws.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.telappoint.commonrestws.common.dao.exception.ExceptionConstants;
import com.telappoint.commonrestws.common.dao.exception.TelAppointException;

/**
 * 
 * @author Murali
 * 
 */
public class FileUtils {
	private static final Logger logger = Logger.getLogger(FileUtils.class);

	private static Map<String, Properties> propsMap = new HashMap<String, Properties>();
	public static final String MAIL_PROPFILE = "Mail.properties";
	public static final String COMMON_PROPFILE = "commonproperties.properties";
	public static final String COMMON_PROP_FILE = "common.properties";
	public static final String COMMON_PROP_FILE_FILE_PATH = "/apps/properties/common.properties";
	public static final String MAIL_PROPFILE_FILE_PATH = "/apps/properties/supportmail.properties";
	
	public static final String APPTDESK_RESTWS_APP_PROPFILE = "apptdeskRESTws.properties";
	public static final String APPTDESK_RESTWS_APP_PROPFILE_FILE_PATH = "/apps/properties/apptdeskRESTws.properties";
	
	/**
	 * @desc Creates an InputStream Object from a given file name.
	 * @param fileName
	 * @return An Inputstream object
	 * @throws TelAppointException
	 *             if specified file not found
	 */
	public static InputStream getResourceAsStream(String fileName) throws TelAppointException {
		InputStream propsIn = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if (propsIn == null) {
			propsIn = FileUtils.class.getResourceAsStream(fileName);
		}
		if (propsIn == null) {
			propsIn = ClassLoader.getSystemResourceAsStream(fileName);
		}

		if (propsIn == null) {
			logger.error(fileName + " not found");

			throw new TelAppointException(ExceptionConstants.FILENOTFOUND_ERROR);
		}
		return propsIn;
	}

	/**
	 * @desc Creates a Properties Object from a given file name.
	 * @param fileName
	 * @return A Properties object
	 * @throws TelAppointException
	 */
	public static Properties getProperties(String fileName) throws TelAppointException {

		Properties properties = propsMap.get(fileName);
		if (properties != null) {
			return properties;
		}

		try {
			properties = new Properties();
			properties.load(getResourceAsStream(fileName));
			propsMap.put(fileName, properties);
		} catch (IOException e) {
			logger.error(e);

			throw new TelAppointException(ExceptionConstants.FILENOTFOUND_ERROR);
		}

		return properties;
	}

	/**
	 * @desc Refreshes a Properties Object from a given file name by assigning
	 *       it to null.
	 * @param fileName
	 * @return A Properties object
	 * @throws TelAppointException
	 */
	public static Properties refreshProperties(String fileName) throws TelAppointException {
		Properties properties = new Properties();
		try {
			properties.load(getResourceAsStream(fileName));
			propsMap.put(fileName, null);
		} catch (IOException e) {
			logger.error(e);
			throw new TelAppointException(ExceptionConstants.FILENOTFOUND_ERROR);
		}
		return properties;
	}

	public static String getValueFromProperties(String key, String fileName) throws TelAppointException {
		Properties properties = getProperties(fileName);
		return (String) properties.get(key);
	}

	public static String getErrorMessage(Integer statusCode) {
		Properties properties = new Properties();
		if (propsMap.get("errorProperties") == null) {
			try {
				properties = getProperties(ExceptionConstants.EXCEPTION_PROPFILE);
				propsMap.put("errorProperties", properties);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			properties = propsMap.get("errorProperties");

		String errorMessage = properties.getProperty(String.valueOf(statusCode));
		return errorMessage;
	}
	
	public static Properties getMailProperties() {
		Properties properties = new Properties();
		if (propsMap.get("mailProperties") == null) {
			try {
				properties.load(new FileInputStream(MAIL_PROPFILE_FILE_PATH));
			} catch (Exception e) {
				logger.error("Error:"+e,e);
				try {
					properties = getProperties(MAIL_PROPFILE);					
				} catch (Exception ex) {
					logger.error("Error:"+ex,ex);
				}
			}
			propsMap.put("mailProperties", properties);
		} else{
			properties = propsMap.get("mailProperties");
		}			
		return properties;
	}
	
	public static Properties getCommonProperties() {
		Properties properties = new Properties();
		if (propsMap.get("commonproperties") == null) {
			try {
				properties = getProperties(COMMON_PROPFILE);
				propsMap.put("commonproperties", properties);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else{
			properties = propsMap.get("commonproperties");
		}			
		return properties;
	}
	
	public static String getPropertyValueFromCommonProperties(String key) {
		Properties properties = new Properties();
		if (propsMap.get("commonprops") == null) {
			try {
				properties.load(new FileInputStream(COMMON_PROP_FILE_FILE_PATH));
			} catch (Exception e) {
				logger.error("Error:"+e,e);
				try {
					properties = getProperties(COMMON_PROP_FILE);					
				} catch (Exception ex) {
					logger.error("Error:"+ex,ex);
				}
			}
			propsMap.put("commonprops", properties);
		} else{
			properties = propsMap.get("commonprops");
		}			
		return (String) properties.get(key);
	}
	
	public static Properties getApptDeskRestWSAppProperties() {
		Properties properties = new Properties();
		if (propsMap.get("apptDeskRestWSAppProperties") == null) {
			try {
				properties.load(new FileInputStream(APPTDESK_RESTWS_APP_PROPFILE_FILE_PATH));
			} catch (Exception e) {
				logger.error("Error:"+e,e);
				try {
					properties = getProperties(APPTDESK_RESTWS_APP_PROPFILE);					
				} catch (Exception ex) {
					logger.error("Error:"+ex,ex);
				}
			}
			propsMap.put("apptDeskRestWSAppProperties", properties);
		} else{
			properties = propsMap.get("apptDeskRestWSAppProperties");
		}			
		return properties;
	}
	
	public static String getSupportErrorFilePath() {
		Properties properties = getApptDeskRestWSAppProperties();
		String path = properties.getProperty("support_ticket_file_path");
		return path;
	}
	
	/*public static String getPropertyValueFromCommonProperties(String key) {
		Properties properties = new Properties();
		if (propsMap.get("commonprops") == null) {
			try {
				properties = getProperties(COMMON_PROP_FILE);					
			} catch (Exception ex) {
				logger.error("Error:"+ex,ex);
			}
			propsMap.put("commonprops", properties);
		} else{
			properties = propsMap.get("commonprops");
		}			
		return (String) properties.get(key);
	}*/
}
