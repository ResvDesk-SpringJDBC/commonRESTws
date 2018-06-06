package com.telappoint.commonrestws.common.controller;

import static com.telappoint.commonrestws.common.controller.json.JsonDataHandler.FAILURE_RESPONSE;
import static com.telappoint.commonrestws.common.controller.json.JsonDataHandler.SUCCESS_RESPONSE;

import java.util.List;

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
import com.telappoint.commonrestws.common.model.ClientTO;
import com.telappoint.commonrestws.common.model.to.AdminLoginNewTO;
import com.telappoint.commonrestws.common.model.to.ChangePasswordTO;
import com.telappoint.commonrestws.common.model.to.ResetPasswordTO;
import com.telappoint.commonrestws.common.service.CommonNotifyService;
import com.telappoint.commonrestws.common.service.UserDetailsService;

/**
 * 
 * @author rajeev
 * 
 */
@Controller
@RequestMapping("/commonnotify")
public class CommonNotifyController extends CommonController {
	@Autowired
	UserDetailsService userDetailsService;

	private final String iptoCheck = "127.0.0.1";

	@Autowired
	CommonNotifyService commonNotifyService;

	@RequestMapping(method = RequestMethod.GET, value = "/getUserList", produces = "application/json")
	public @ResponseBody
	JsonDataHandler getUserList(HttpServletRequest request,@RequestParam("clientId") String clientId, @RequestParam("clientCode") String clientCode) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(clientCode, ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			List<AdminLoginNewTO> userList = userDetailsService.getUserList(clientId);
			return populateJDHSuccessData(userList);
		} catch (Exception e) {
			e.printStackTrace();
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findUsersById", produces = "application/json")
	public @ResponseBody
	JsonDataHandler findUsersById(HttpServletRequest request,@RequestParam("userId") String userId) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed("masterdb - userId:"+userId, ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		
		try {
			AdminLoginNewTO users = userDetailsService.findUsersById(userId);
			return populateJDHSuccessData(users);
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "addUserDetails", consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonDataHandler addUserDetails(HttpServletRequest request,@RequestBody AdminLoginNewTO adminLoginNewTO) {
		System.out.println("AddUserDetails starts for clientCode = " + adminLoginNewTO.getClientCode());
		boolean isSaveOrUpdate = false;
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(adminLoginNewTO.getClientCode(), ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			isSaveOrUpdate = userDetailsService.addUserDetals(adminLoginNewTO);
			if (isSaveOrUpdate) {
				return SUCCESS_RESPONSE();
			} else {
				return FAILURE_RESPONSE(MessageType.ERROR.getMessageType(), StatusCodes.EIGHTY_FOUR.getStatusCode());
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findClientById", produces = "application/json")
	public @ResponseBody
	JsonDataHandler findClientById(HttpServletRequest request,@RequestParam("clientCode") String clientCode) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(clientCode, ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			ClientTO clientTO = commonNotifyService.findClientById(clientCode);
			return populateJDHSuccessData(clientTO);
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "deleteUserDetails", produces = "application/json")
	public @ResponseBody
	JsonDataHandler deleteUserDetails(HttpServletRequest request,@RequestParam String userId) {
		boolean isDelete = false;
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed("masterdb", ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			isDelete = userDetailsService.deleteUserDetails(userId);
			if (isDelete) {
				return SUCCESS_RESPONSE();
			} else {
				return FAILURE_RESPONSE(MessageType.ERROR.getMessageType(), StatusCodes.EIGHTY_FOUR.getStatusCode());
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "updateUserDetails", consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonDataHandler updateUserDetails(HttpServletRequest request,@RequestBody AdminLoginNewTO adminLoginNewTO) {
		System.out.println("UpdateUserDetails starts for clientCode = " + adminLoginNewTO.getClientCode());
		boolean isSaveOrUpdate = false;
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(adminLoginNewTO.getClientCode(), ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			isSaveOrUpdate = userDetailsService.updateUserDetails(adminLoginNewTO);
			if (isSaveOrUpdate) {
				return SUCCESS_RESPONSE();
			} else {
				return FAILURE_RESPONSE(MessageType.ERROR.getMessageType(), StatusCodes.EIGHTY_FOUR.getStatusCode());
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getPasswordComplexityLogic", produces = "application/json")
	public @ResponseBody
	JsonDataHandler getPasswordComplexityLogic(HttpServletRequest request,@RequestParam("clientCode") String clientCode) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(clientCode, ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			String passwordComplexity = commonNotifyService.getPasswordComplexityLogic(clientCode);
			return populateJDHSuccessData(passwordComplexity);
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getPasswordComplexityLogicByUserName", produces = "application/json")
	public @ResponseBody
	JsonDataHandler getPasswordComplexityLogicByUserName(HttpServletRequest request,@RequestParam("userName") String userName) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed("masterdb", ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			String passwordComplexity = commonNotifyService.getPasswordComplexityLogicByUserName(userName);
			return populateJDHSuccessData(passwordComplexity);
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendResetPasswordRequestToken", consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonDataHandler sendResetPasswordRequestToken(HttpServletRequest request,@RequestBody ResetPasswordTO resetPasswordTO) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(resetPasswordTO.getClientCode(), ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			String resetPasswordResponse = commonNotifyService.sendResetPasswordRequestToken(resetPasswordTO);
			return populateJDHSuccessData(resetPasswordResponse);
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/resetPasswordRequest", produces = "application/json")
	public @ResponseBody
	JsonDataHandler resetPasswordRequest(HttpServletRequest request,@RequestParam("token") String token) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed("masterdb", ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			ResetPasswordTO resetPasswordTO = commonNotifyService.resetPasswordRequest(token);
			return populateJDHSuccessData(resetPasswordTO);
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePassword", consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonDataHandler updatePassword(HttpServletRequest request,@RequestBody ResetPasswordTO resetPasswordTO) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(resetPasswordTO.getClientCode(), ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			String resetPasswordResponse = commonNotifyService.updatePassword(resetPasswordTO);
			return populateJDHSuccessData(resetPasswordResponse);
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/validateoldpassword", consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonDataHandler validateoldpassword(HttpServletRequest request,@RequestBody ChangePasswordTO changePasswordTO) {
		try {
			String resetPasswordResponse = commonNotifyService.validateoldpassword(changePasswordTO);
			return populateJDHSuccessData(resetPasswordResponse);
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatechangepassword", consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonDataHandler updatechangepassword(HttpServletRequest request,@RequestBody ChangePasswordTO changePasswordTO) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed("masterdb", ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			String resetPasswordResponse = commonNotifyService.updatechangepassword(changePasswordTO);
			return populateJDHSuccessData(resetPasswordResponse);
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "updateUserDetailsByAdmin", consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonDataHandler updateUserDetailsByAdmin(HttpServletRequest request,@RequestBody AdminLoginNewTO adminLoginNewTO) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(adminLoginNewTO.getClientCode(), ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		boolean isUpdated = false;
		try {
			isUpdated = userDetailsService.updateUserDetailsByAdmin(adminLoginNewTO);
			if (isUpdated) {
				return SUCCESS_RESPONSE();
			} else {
				return FAILURE_RESPONSE(MessageType.ERROR.getMessageType(), StatusCodes.EIGHTY_FOUR.getStatusCode());
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "validateUserName", produces = "application/json")
	public @ResponseBody
	JsonDataHandler validateUserName(HttpServletRequest request,@RequestParam String username, @RequestParam("id") String id) {
		boolean isValid = false;
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed("masterdb- username"+username, ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			isValid = userDetailsService.isValidateUserName(username, id);
			if (isValid) {
				return populateJDHSuccessData("YES");
			} else {
				return populateJDHSuccessData("NO");
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePasswordChangedByAdmin", consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonDataHandler updatePasswordChangedByAdmin(HttpServletRequest request,@RequestBody ResetPasswordTO resetPasswordTO) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(resetPasswordTO.getClientCode(), ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			String resetPasswordResponse = commonNotifyService.updatePasswordChangedByAdmin(resetPasswordTO);
			return populateJDHSuccessData(resetPasswordResponse);
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAppVersion", consumes = "application/json", produces = "application/json")
	public @ResponseBody  JsonDataHandler getAppVersion(HttpServletRequest request,@RequestParam("app_code") String app_code,@RequestParam("clientCode") String clientCode) {
		String ipAddress = request.getRemoteAddr();
		if (!ipAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + ipAddress);
			userDetailsService.sendEmailIPNotAllowed(clientCode, ipAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		try {
			String appVersion = commonNotifyService.getAppVersion(app_code,clientCode);
			return populateJDHSuccessData(appVersion);
		} catch (Exception e) {
			return handleException(e);
		}
	}
}
