package com.telappoint.commonrestws.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telappoint.commonrestws.common.constants.LoginConstants;
import com.telappoint.commonrestws.common.controller.json.JsonDataHandler;
import com.telappoint.commonrestws.common.model.JSONLoginResponse;
import com.telappoint.commonrestws.common.model.to.UserLoginTO;
import com.telappoint.commonrestws.common.service.LoginService;

/**
 * 
 * @author Murali
 * 
 */
@Controller
@RequestMapping("/authenticateLogin")
public class LoginAuthenticationController extends CommonController {
	
	@Autowired
	private LoginService loginService;
	private final String iptoCheck = "127.0.0.1";
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonDataHandler authenticateLogin(HttpServletRequest request,@RequestBody UserLoginTO userLoginTO) {
		String remoteIpAddress = request.getRemoteAddr();
		if (!remoteIpAddress.equals(iptoCheck)) {
			Exception e = new Exception("IP Not Allowed :" + remoteIpAddress);
			loginService.sendEmailIPNotAllowed("masterdb", remoteIpAddress, Thread.currentThread().getStackTrace()[1].getMethodName());
			return handleException(e);
		}
		
		try {
			String username = userLoginTO.getUsername() ;
			String password = userLoginTO.getPassword();
			String ipAddress = userLoginTO.getIpAddress();
						
			String response = loginService.authenticateLogin(username, password, ipAddress);
			JSONLoginResponse loginResponse = null;
						
			if(response!=null && LoginConstants.LOGIN_SUCESSES_RESPONSE.getValue().equals(response)){
				loginResponse = loginService.getLoginResponse(username, response, ipAddress);
				return populateJDHSuccessData(loginResponse);
			}else{ 			
				if(response==null){
					response = LoginConstants.LOGIN_FAILURE_RESPONSE.getValue();
				}
				loginResponse = new JSONLoginResponse();
				loginResponse.setLogin(response);
				return populateJDHFailureData(loginResponse);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
}
