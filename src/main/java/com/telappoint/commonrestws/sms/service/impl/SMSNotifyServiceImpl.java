package com.telappoint.commonrestws.sms.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import twilio.client.*;
import com.telappoint.commonrestws.common.masterdb.dao.SMSRequestDAO;
import com.telappoint.commonrestws.sms.constants.SMSConstants;
import com.telappoint.commonrestws.sms.service.SMSNotifyService;

/**
 * 
 * 
 * @author Murali
 * 
 */

@Service
public class SMSNotifyServiceImpl implements SMSNotifyService {
	
	private Logger logger = Logger.getLogger(SMSNotifyServiceImpl.class);
	
	@Autowired
	private SMSRequestDAO smsRequestDAO;
	@Override
	public String sendSMS(String fromNumber, String toNumber, String message) {
		try{
			/*String accountSid=FileUtils.getValueFromProperties(SMSConstants.SMS_ACCOUNT_SID_PROP_KEY.getValue(), SMSConstants.SMS_ACCOUNT_DETAILS_PROP_FILE.getValue());
			String authToken=FileUtils.getValueFromProperties(SMSConstants.SMS_AUTH_TOKEN_PROP_KEY.getValue(), SMSConstants.SMS_ACCOUNT_DETAILS_PROP_FILE.getValue());
			TwilioClient client = new TwilioClient(accountSid,authToken); 
			SMSMessage smsMessage = client.sendSmsMessage(fromNumber,toNumber, message);
			return smsMessage.getStatus();*/
			return "Fail";
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception while sending SMS to - "+toNumber, e);
		}
		return SMSConstants.SMS_FAILURE_RESPONSE.getValue();
	}

}
