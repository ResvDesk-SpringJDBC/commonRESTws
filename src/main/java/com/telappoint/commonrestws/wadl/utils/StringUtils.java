package com.telappoint.commonrestws.wadl.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author RajeevS
 * 
 */
public class StringUtils
{

	private static Log log = LogFactory.getLog("com.telappoint.commonrestws.wadl.utils.StringUtils");

	public static String formatPhoneNumber(String phone)
	{
		StringBuffer buf = new StringBuffer("");

		if (isNotEmpty(phone))
		{
			try
			{
				buf.append(phone.substring(0, 3));
				buf.append("-");
				buf.append(phone.substring(3, 6));
				buf.append("-");
				buf.append(phone.substring(6, 10));
			}
			catch (Exception e)
			{
				log.error("Phone number should be of length eleven ");
			}
		}

		return buf.toString();
	}

	/**
	 * Returns true if string is empty or null
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string)
	{
		return (string != null && string.length() > 0) ? false : true;
	}

	/**
	 * Returns true if string is not empty and not null
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isNotEmpty(String input)
	{
		return !isEmpty(input);
	}
}
