package com.telappoint.commonrestws.sms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telappoint.commonrestws.common.controller.CommonController;
import com.telappoint.commonrestws.common.controller.json.JsonDataHandler;
import com.telappoint.commonrestws.sms.model.SMSClientResponse;
import com.telappoint.commonrestws.sms.service.SMSNotifyService;



/**
 * 
 * @author Murali
 * 
 */
@Controller
@RequestMapping("/smsnotify")
public class SMSNtifyController extends CommonController {

	private static final Logger logger = Logger.getLogger(SMSNtifyController.class);
	
	@Autowired
	private SMSNotifyService smsNotifyService;
	
	
	@RequestMapping(value="sendSMS",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody JsonDataHandler sendSMS(@RequestParam("fromNumber") String fromNumber,
			@RequestParam("toNumber") String toNumber,@RequestParam("message") String message) {
		logger.info("sendSMS method has entered.");
		try {
			String ststusResponse = smsNotifyService.sendSMS(fromNumber, toNumber, message);
			return populateJDHSuccessData(ststusResponse);
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/receiveSMS", produces = {"text/html", "application/xml", "text/xml" })
	public @ResponseBody String receiveSMS(
			@RequestParam("AccountSid") String AccountSid,
			@RequestParam("ApiVersion") String ApiVersion,
			@RequestParam("Body") String Body,
			@RequestParam("From") String From,
			@RequestParam("FromCity") String FromCity,
			@RequestParam("FromCountry") String FromCountry,
			@RequestParam("FromState") String FromState,
			@RequestParam("FromZip") String FromZip,
			@RequestParam("SmsMessageSid") String SmsMessageSid,
			@RequestParam("SmsSid") String SmsSid,
			@RequestParam("SmsStatus") String SmsStatus,
			@RequestParam("To") String To,
			@RequestParam("ToCity") String ToCity,
			@RequestParam("ToCountry") String ToCountry,
			@RequestParam("ToState") String ToState,
			@RequestParam("ToZip") String ToZip) {			
		
		logger.info("receiveSMS method has entered.");
		System.out.println("receiveSMS method has entered.");
		logger.info("AccountSid:"+AccountSid);
		logger.info("From:"+From);
		logger.info("Body:"+Body);
		
		System.out.println("AccountSid:"+AccountSid);
		System.out.println("From:"+From);
		System.out.println("Body:"+Body);
		
		
		logger.debug("AccountSid:"+AccountSid);
		logger.debug("From:"+From);
		logger.debug("Body:"+Body);
		
		String str = "";
		if("1".equals(Body.trim())){
			str = "<?"+"xml version=\"1.0\" encoding=\"UTF-8\" ?><Response><Sms>Thank you for your response. This is message 1 of 2.</Sms></Response"+">";
		}else if("2".equals(Body.trim())){
			str = "<?"+"xml version=\"1.0\" encoding=\"UTF-8\" ?><Response><Sms>Thank you for your response. This is message 2 of 2.</Sms></Response"+">";
		}else{
			str = "<?"+"xml version=\"1.0\" encoding=\"UTF-8\" ?><Response><Sms>Thank you for your response. But system is unable to read your data. Please try again!!</Sms></Response"+">";
		}
		
		 
		
		//return populateJDHSuccessData(str);
		return str;
	}
	
	
}
