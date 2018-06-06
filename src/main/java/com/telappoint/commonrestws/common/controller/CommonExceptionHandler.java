package com.telappoint.commonrestws.common.controller;

import static com.telappoint.commonrestws.common.controller.json.JsonDataHandler.EXCEPTION_RESPONSE;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telappoint.commonrestws.common.controller.json.JsonDataHandler;
import com.telappoint.commonrestws.common.controller.json.StatusCodes;
import com.telappoint.commonrestws.common.dao.exception.TelAppointDBException;
import com.telappoint.commonrestws.common.dao.exception.TelAppointException;

/**
 * 
 * @link ApptExceptionHandler} is a handler where all the exception are handled.
 *       This class builds the failure response in
 * @link JsonDataHandler} and returns as JSON response. This class has to be
 *       extended with the controllers.
 * 
 */
public class CommonExceptionHandler {
	private static final Logger logger = Logger.getLogger(CommonExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public @ResponseBody
	JsonDataHandler handleException(Exception e) {
		logger.error("Error:"+e,e);
		
		if (e instanceof TelAppointDBException) {
			TelAppointDBException telappe = (TelAppointDBException)e;
			logger.error("DB Error:\n ExceptionCode" + telappe.getExceptionCode()+"\n"+telappe.getExceptionDesc(), e);
			return EXCEPTION_RESPONSE(StatusCodes.NINETY_NINE.getStatusCode());
		} else if(e instanceof TelAppointException){
			e.printStackTrace();
			TelAppointException telappe = (TelAppointException)e;
			logger.error("Internal Error:\n ExceptionCode" + telappe.getExceptionCode()+"\n"+telappe.getExceptionDesc(), e);
			return EXCEPTION_RESPONSE(StatusCodes.NINETY_EIGHT.getStatusCode());
		} else {
			logger.error("Error:"+e,e);
			return EXCEPTION_RESPONSE(StatusCodes.NINETY_NINE.getStatusCode());
		}
	}
}
