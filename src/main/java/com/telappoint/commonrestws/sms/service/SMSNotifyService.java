package com.telappoint.commonrestws.sms.service;

/**
 * 
 * @author Murali
 */
public interface SMSNotifyService {
	
	public String sendSMS(String fromNumber, String toNumber, String message);

}
