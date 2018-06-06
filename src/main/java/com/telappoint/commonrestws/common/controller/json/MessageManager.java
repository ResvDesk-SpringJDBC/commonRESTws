package com.telappoint.commonrestws.common.controller.json;

import java.util.ResourceBundle;

import com.telappoint.commonrestws.common.constants.CommonContants;
import com.telappoint.commonrestws.common.utils.StringUtils;


/**
 * @author Rajin
 * @author Balaji
 * 
 */
public class MessageManager {
	private static ResourceBundle bundle;
	
	private MessageManager() {
	}

	private static void loadBundle(String statusType) {
		MessageManager.bundle = getbundleType(statusType);
	}

	public static String getProperty(String statusType, String errorCode) {
		loadBundle(statusType);
		if (StringUtils.isEmpty(errorCode)) {
			return CommonContants.EMPTY_STRING.getValue();
		}
		return (String) MessageManager.bundle.getString(errorCode);
	}

	private static ResourceBundle getbundleType(String statusType) {
		ResourceBundle bundle = null;
		int status = (MessageType.INFO.getMessageType()).equals(statusType)?1:0;
		if(status ==0) status = (MessageType.WARNING.getMessageType()).equals(statusType)?2:0;
		if(status ==0) status = (MessageType.ERROR.getMessageType()).equals(statusType)?3:0;
		switch (status) {
		case 1:
			bundle = ResourceBundle.getBundle("appt-info");
			break;
		case 2:
			bundle = ResourceBundle.getBundle("appt-warn");
			break;
		case 3:
			bundle = ResourceBundle.getBundle("appt-error");
			break;
		}
		return bundle;
	}

}
