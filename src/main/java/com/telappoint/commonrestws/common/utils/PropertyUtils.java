package com.telappoint.commonrestws.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Balaji
 * 
 */
public class PropertyUtils {
	private static final Log logger = LogFactory.getLog(PropertyUtils.class);

	private static Map<String, Properties> propsMap = new TreeMap<String, Properties>();
	public static final String MAIL_PROPFILE = "Mail.properties";

	/**
	 * @desc Creates an InputStream Object from a given file name.
	 * @param fileName
	 * @return An Inputstream object
	 * @throws OMSException
	 *             if specified file not found
	 */
	public static InputStream getResourceAsStream(String fileName) throws Exception {
		InputStream propsIn = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if (propsIn == null) {
			propsIn = getResourceAsStream(fileName);
		}
		if (propsIn == null) {
			propsIn = ClassLoader.getSystemResourceAsStream(fileName);
		}

		if (propsIn == null) {
			logger.error(fileName + " not found");
			throw new Exception(fileName + " file is not found");
		}
		return propsIn;
	}

	/**
	 * @desc Creates a Properties Object from a given file name.
	 * @param fileName
	 * @return A Properties object
	 * @throws OMSException
	 */
	public static Properties getProperties(String fileName) throws Exception {

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
			throw new Exception(fileName + " file is not found");
		}

		return properties;
	}

	/**
	 * @desc Refreshes a Properties Object from a given file name by assigning
	 *       it to null.
	 * @param fileName
	 * @return A Properties object
	 * @throws OMSException
	 */
	public static Properties refreshProperties(String fileName) throws Exception {
		Properties properties = new Properties();
		try {
			properties.load(getResourceAsStream(fileName));
			propsMap.put(fileName, null);
		} catch (IOException e) {
			logger.error(e);
			throw new Exception(fileName + " file is not found");
		}
		return properties;
	}

	public static String getValueFromProperties(String key, String fileName) throws Exception {
		Properties properties = getProperties(fileName);
		return (String) properties.get(key);
	}

	
	public static Properties getMailProperties() {
		Properties properties = new Properties();
		if (propsMap.get("mailProperties") == null) {
			try {
				properties = getProperties(MAIL_PROPFILE);
				propsMap.put("mailProperties", properties);
			} catch (Exception e) {
				logger.error("Error:"+e,e);
			}
		} else{
			properties = propsMap.get("mailProperties");
		}			
		return properties;
	}
}
