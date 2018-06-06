package com.telappoint.commonrestws.common.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telappoint.commonrestws.common.components.CommonLoginComponent;
import com.telappoint.commonrestws.common.constants.LoginConstants;
import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginNewDAO;
import com.telappoint.commonrestws.common.masterdb.dao.ClientDAO;
import com.telappoint.commonrestws.common.masterdb.dao.LoginAttemptsDAO;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginNew;
import com.telappoint.commonrestws.common.model.JSONLoginResponse;
import com.telappoint.commonrestws.common.service.LoginService;
import com.telappoint.commonrestws.common.utils.CoreUtils;

/**
 * 
 * This is login service implementation for Login Authentication
 * 
 * @author Murali
 * 
 */

@Service
public class LoginServiceImpl implements LoginService {
	
	private Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private AdminLoginNewDAO adminLoginNewDAO;
	@Autowired
	private ClientDAO clientDAO;
	@Autowired
	private LoginAttemptsDAO loginAttemptsDAO;
	@Autowired
	private CommonLoginComponent commonLoginComponent;
	
	@Override
	public String authenticateLogin(String username,String password,String ipAddress) throws TelAppointException {
		try{
			return commonLoginComponent.authenticateLogin(username, password, ipAddress);
		}catch(Exception e){
			logger.error("Error while authenticating username - "+username, e);
		}
		
		return LoginConstants.LOGIN_FAILURE_RESPONSE.getValue();
	}
	
	@Override
	public JSONLoginResponse getLoginResponse(String username,String response,String ip)throws TelAppointException {
		JSONLoginResponse loginResponse = null;
		try{
			 List<AdminLoginNew> loginList = adminLoginNewDAO.findByProperty("username", username);
			 if(loginList!=null && loginList.size()>0){
				 loginResponse = commonLoginComponent.createLoginResponse(loginList.get(0), response, ip);
			 }
			logger.info("Sucessfully LoginResponse created for usernmae - "+username);
		}catch(Exception e){
			logger.error("Error while creating LoginResponse for username - "+username, e);
		}
		
		return loginResponse;
	}
	
	public void sendEmailIPNotAllowed(String clientCode, String ipaddress, String methodName) {
		StringBuilder body = new StringBuilder("IP Not Allowed :" + ipaddress);
		body.append("<br/><br/>");
		body.append("Exception: ");
		body.append("<br/>");
		body.append(methodName);
		try {
			CoreUtils.sendErrorEmail("IPNotAllowed - clientCode:"+clientCode, body.toString());
		} catch (Exception ex) {
			logger.error("Error: Unable to send application error email!" + ex, ex);
			ex.printStackTrace();
		}
	}
	
}
