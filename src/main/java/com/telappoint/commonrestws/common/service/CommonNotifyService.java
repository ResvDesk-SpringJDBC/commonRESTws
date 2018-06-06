package com.telappoint.commonrestws.common.service;

import com.telappoint.commonrestws.common.model.ClientTO;
import com.telappoint.commonrestws.common.model.to.ChangePasswordTO;
import com.telappoint.commonrestws.common.model.to.ResetPasswordTO;

/**
 * Write all common method in this interface
 * 
 * @author rajeev
 *
 */
public interface CommonNotifyService {

	public ClientTO findClientById(String clientCode);
	public String getPasswordComplexityLogic(String clientCode);
	public String sendResetPasswordRequestToken(ResetPasswordTO resetPasswordTO);
	public ResetPasswordTO resetPasswordRequest(String token);
	public String updatePassword(ResetPasswordTO ResetPasswordTO);
	public String updatechangepassword(ChangePasswordTO changePasswordTO);
	public String validateoldpassword(ChangePasswordTO changePasswordTO);
	public String getPasswordComplexityLogicByUserName(String userName);
	public String updatePasswordChangedByAdmin(ResetPasswordTO resetPasswordTO);
	public void sendEmailIPNotAllowed(String clientCode, String ipaddress, String methodName);
	public String getAppVersion(String appName, String clientCode);
}
