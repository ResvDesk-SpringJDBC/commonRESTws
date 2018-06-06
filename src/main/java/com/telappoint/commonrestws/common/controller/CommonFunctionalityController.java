package com.telappoint.commonrestws.common.controller;

import static com.telappoint.commonrestws.common.controller.json.JsonDataHandler.FAILURE_RESPONSE;
import static com.telappoint.commonrestws.common.controller.json.JsonDataHandler.SUCCESS_RESPONSE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telappoint.commonrestws.common.controller.json.JsonDataHandler;
import com.telappoint.commonrestws.common.controller.json.MessageType;
import com.telappoint.commonrestws.common.controller.json.StatusCodes;
import com.telappoint.commonrestws.common.model.to.RequestResponseTimeLogTO;
import com.telappoint.commonrestws.common.service.CommonFunctionalityService;
import com.telappoint.commonrestws.common.service.UserDetailsService;

/**
 * 
 * @author Murali G
 * 
 */
@Controller
@RequestMapping("/commonfunctionality")
public class CommonFunctionalityController extends CommonController {
	private final String iptoCheck = "127.0.0.1";
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private CommonFunctionalityService commonFunSer;

	@RequestMapping(method = RequestMethod.GET, value = "/getActivityPageClickDetails", produces = "application/json")
	public @ResponseBody
	JsonDataHandler getActivityPageClickDetails(HttpServletRequest request,@RequestParam("appName") String appName, @RequestParam("clientCode") String clientCode) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(clientCode, ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			return populateJDHSuccessData(commonFunSer.getActivityPageClickDetails(appName));
		} catch (Exception e) {
			e.printStackTrace();
			return handleException(e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/writeRequestResponseTimeLog" , consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonDataHandler writeRequestResponseTimeLog(HttpServletRequest request,@RequestBody RequestResponseTimeLogTO reqResTimeLogTO) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(reqResTimeLogTO.getClient_code(), ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			boolean isAdded = commonFunSer.writeRequestResponseTimeLog(reqResTimeLogTO);
			if (isAdded) {
				return SUCCESS_RESPONSE();
			} else {
				return FAILURE_RESPONSE(MessageType.ERROR.getMessageType(), StatusCodes.EIGHTY_FOUR.getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return handleException(e);
		}
	}
}
