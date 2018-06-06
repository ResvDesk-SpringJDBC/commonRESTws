package com.telappoint.commonrestws.common.controller;

import org.springframework.stereotype.Controller;

import static com.telappoint.commonrestws.common.controller.json.JsonDataHandler.FAILURE_RESPONSE;
import static com.telappoint.commonrestws.common.controller.json.JsonDataHandler.SUCCESS_RESPONSE;
import static com.telappoint.commonrestws.common.controller.json.JsonDataHandler.TOKEN_EXPIRED;


import com.telappoint.commonrestws.common.controller.json.JsonDataHandler;
import com.telappoint.commonrestws.common.utils.CoreUtils;

/**
 * 
 * @author Murali
 * 
 */
@Controller
public class CommonController extends CommonExceptionHandler {

	public JsonDataHandler populateJDHSuccessData(Object data) {
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();
		String callerMethodName = elements[1].getMethodName();
		JsonDataHandler jsonDataHandler = SUCCESS_RESPONSE();
		jsonDataHandler.withData(data);
		CoreUtils.printJSONResponse(jsonDataHandler,callerMethodName);
		return jsonDataHandler;
	}
	
	public JsonDataHandler populateJDHFailureData(Object data) {
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();
		String callerMethodName = elements[1].getMethodName();
		JsonDataHandler jsonDataHandler = FAILURE_RESPONSE();
		jsonDataHandler.withData(data);
		CoreUtils.printJSONResponse(jsonDataHandler,callerMethodName);
		return jsonDataHandler;
	}
	
	public JsonDataHandler populateJDHSuccessCollection(Object data) {
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();
		String callerMethodName = elements[1].getMethodName();
		JsonDataHandler jsonDataHandler = SUCCESS_RESPONSE();
		jsonDataHandler.withCollection(data);
		CoreUtils.printJSONResponse(jsonDataHandler,callerMethodName);
		return jsonDataHandler;
	}
	
	public JsonDataHandler populateTokenExpired() {
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();
		String callerMethodName = elements[1].getMethodName();
		JsonDataHandler jsonDataHandler = TOKEN_EXPIRED();
		CoreUtils.printJSONResponse(jsonDataHandler,callerMethodName);
		return jsonDataHandler;
	}
	
	public JsonDataHandler populateJDBFailureData(Object data,String messageType,String statusCode) {
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();
		String callerMethodName = elements[1].getMethodName();
		JsonDataHandler jsonDataHandler = FAILURE_RESPONSE(messageType,statusCode);
		jsonDataHandler.withData(data);
		CoreUtils.printJSONResponse(jsonDataHandler,callerMethodName);
		return jsonDataHandler;
	}
}
