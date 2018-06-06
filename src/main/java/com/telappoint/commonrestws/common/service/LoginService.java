package com.telappoint.commonrestws.common.service;

import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.model.JSONLoginResponse;

/**
 * 
 * @author Murali
 */

public interface LoginService {
	
	public String authenticateLogin(String username,String password,String ipAddress) throws TelAppointException;
	public JSONLoginResponse getLoginResponse(String username,String response,String ip) throws TelAppointException;
	public void sendEmailIPNotAllowed(String clientCode, String ipaddress, String methodName);
	
}
