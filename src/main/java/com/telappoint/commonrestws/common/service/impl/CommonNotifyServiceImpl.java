package com.telappoint.commonrestws.common.service.impl;

import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telappoint.commonrestws.common.assembler.ClientAssembler;
import com.telappoint.commonrestws.common.components.CommonLoginComponent;
import com.telappoint.commonrestws.common.core.AdminInstance;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginConfigDAO;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginNewDAO;
import com.telappoint.commonrestws.common.masterdb.dao.ClientDAO;
import com.telappoint.commonrestws.common.masterdb.dao.VersionDAO;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginNew;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginResetRequest;
import com.telappoint.commonrestws.common.masterdb.domain.Client;
import com.telappoint.commonrestws.common.masterdb.domain.Version;
import com.telappoint.commonrestws.common.model.ClientTO;
import com.telappoint.commonrestws.common.model.to.ChangePasswordTO;
import com.telappoint.commonrestws.common.model.to.ResetPasswordTO;
import com.telappoint.commonrestws.common.service.CommonNotifyService;
import com.telappoint.commonrestws.common.utils.CoreUtils;
import com.telappoint.commonrestws.common.utils.DateUtils;
import com.telappoint.commonrestws.common.utils.FileUtils;
import com.telappoint.commonrestws.common.utils.MailSender;

/**
 * 
 * @author rajeev
 * 
 */
@Service
public class CommonNotifyServiceImpl implements CommonNotifyService
{
	Logger logger = Logger.getLogger(CommonNotifyServiceImpl.class.getName());
	
	@Autowired
	ClientDAO clientDAO;
	@Autowired
	AdminLoginConfigDAO adminLoginConfigDAO;
	@Autowired
	AdminLoginNewDAO adminLoginNewDAO;
	@Autowired
	CommonLoginComponent commonLoginComponent;
	@Autowired
	VersionDAO versionDAO;
	@Override
	public ClientTO findClientById(String clientCode)
	{
		ClientAssembler clientAssembler = ClientAssembler.getInstance();
		Client client = clientDAO.getClient(clientCode);
		ClientTO clientTO = clientAssembler.getClientTO(client);
		return clientTO;
	}
	
	@Override
	public String getPasswordComplexityLogic(String clientCode) {
		String passwordComplexity = adminLoginConfigDAO.getPasswordComplexityLogic(clientCode);
		return passwordComplexity;
	}
	
	@Override
	public String getPasswordComplexityLogicByUserName(String userName) {
		String passwordComplexity = adminLoginConfigDAO.getPasswordComplexityLogicByUserName(userName);
		return passwordComplexity;
	}
	
	/*@Override
	public String resetPassword(String usename) {
		Properties mailProperties = FileUtils.getMailProperties();
		String response = mailProperties.getProperty("resetpassword.failuremessage");
		try{			  
			AdminLoginNew  adminLoginNew  = adminLoginNewDAO.getUserByUserName(usename);
			if(adminLoginNew!=null){
				String email = adminLoginNew.getContact_email();
				String password = adminLoginNew.getPassword();
				System.out.println("usename --------------> "+usename);
				System.out.println("email --------------> "+email);
				System.out.println("password --------------> "+password);
				String welcomeName = adminLoginNew.getFirst_name();
				if(adminLoginNew.getLast_name()!=null && adminLoginNew.getLast_name()!=""){
					welcomeName = welcomeName+" "+adminLoginNew.getLast_name();
				}
	
				String hostName = "localhost";
				String hostport = "8082";
				String applicationName = "adminnotify";
				
				boolean sendSuccessfully = MailSender.getInstance().sendMail(adminLoginNew.getContact_email(),welcomeName,hostName,hostport,applicationName);
				if(sendSuccessfully){
					response = mailProperties.getProperty("resetpassword.successmessage");
				}else{
					response = mailProperties.getProperty("resetpassword.failuremessage");
				}
			}else{
				response = mailProperties.getProperty("resetpassword.invalidusername");
			}
		}catch(Exception e){
			System.out.println("Exception while sending mail ");
		}
		return response;
	}*/

	@Override
	public String sendResetPasswordRequestToken(ResetPasswordTO resetPasswordTO) {
		Properties commonProperties = FileUtils.getCommonProperties();
		String response = commonProperties.getProperty("resetpassword.tokensend.failuremessage");
		try{			  
			AdminLoginNew  adminLoginNew  = adminLoginNewDAO.getUserByUserName(resetPasswordTO.getUserName());
			if(adminLoginNew!=null){				
				String welcomeName = adminLoginNew.getFirst_name();
				if(adminLoginNew.getLast_name()!=null && adminLoginNew.getLast_name()!=""){
					welcomeName = welcomeName+" "+adminLoginNew.getLast_name();
				}
				String url = FileUtils.getMailProperties().getProperty("resetpassword.reuest.link");
				url = url.replaceAll("@HOST_NAME@", resetPasswordTO.getHostName());
				url = url.replaceAll("@PORT@", resetPasswordTO.getHostport());
				url = url.replaceAll("@APPLICATION_NAME@", resetPasswordTO.getApplicationName());
				AdminLoginResetRequest adminLoginResetRequest = commonLoginComponent.insertAdminLoginResetRequestToken(adminLoginNew.getId(), adminLoginNew.getFirst_name(), adminLoginNew.getContact_email(), 
																		url, adminLoginNew.getClient_id());
				url = url.replaceAll("@TOKEN_PARAM@", adminLoginResetRequest.getReset_password_token());				
				boolean sendSuccessfully = MailSender.getInstance().sendMail(adminLoginNew.getContact_email(),welcomeName,url);
				System.out.println("Mail Send Sucessfully : "+sendSuccessfully);
				if(sendSuccessfully){
					response = commonProperties.getProperty("resetpassword.tokensend.successmessage");
				}else{
					response = commonProperties.getProperty("resetpassword.tokensend.failuremessage");
				}
			}else{
				response = commonProperties.getProperty("resetpassword.tokensend.failuremessage");
			}
		}catch(Exception e){
			logger.error("Exception while sending mail ",e);
		}
		return response;
	}
	
	@Override
	public ResetPasswordTO resetPasswordRequest(String token) {
		ResetPasswordTO resetPasswordTO = null;
		try{
			int login_id =  commonLoginComponent.isTokenValid(token);
			/*AdminLoginNew  adminLoginNew  = adminLoginNewDAO.findById(login_id);
			if(adminLoginNew!=null){
				Client client = clientDAO.findById(adminLoginNew.getClient_id());
				if(client!=null){
					resetPasswordTO = adminLoginConfigDAO.getPasswordComplexityLogic(client.getId(),resetPasswordTO);
					resetPasswordTO = new ResetPasswordTO();
					resetPasswordTO.setUserName(adminLoginNew.getUsername());
					resetPasswordTO.setClientCode(client.getClient_code());
					//resetPasswordTO.setPasswordComplexity(passwordComplexity);
				}
				
			}*/
			if(login_id!=0){
				resetPasswordTO = new ResetPasswordTO();
				resetPasswordTO = adminLoginConfigDAO.getPasswordComplexityLogic(login_id);
				
			}
		}catch(Exception e){
			logger.error("Error in resetPasswordRequest ", e);
		}
		return resetPasswordTO;
	}
	
	@Override
	@Transactional
	public String updatePassword(ResetPasswordTO resetPasswordTO) {
		Properties commonProperties = FileUtils.getCommonProperties();
		String response = commonProperties.getProperty("resetpassword.update.failuremessage");
		try{			  
			AdminLoginNew  adminLoginNew  = adminLoginNewDAO.getUserByUserName(resetPasswordTO.getUserName());
			if(adminLoginNew!=null){
				adminLoginNew.setPassword(AdminInstance.getInstance().encrypt(resetPasswordTO.getNewpassword()));	
				adminLoginNew.setPassword_last_update_date(new Date());
				adminLoginNew.setExpire_date(DateUtils.getTimestampFromString("2020-01-01 10:10:10"));
				adminLoginNewDAO.update(adminLoginNew);
				response = commonProperties.getProperty("resetpassword.update.successmessage");
			}
		}catch(Exception e){
			logger.error("Exception while updatePassword ",e);
		}
		return response;
	}
	
	@Override
	@Transactional
	public String validateoldpassword(ChangePasswordTO changePasswordTO) {
		Properties commonProperties = FileUtils.getCommonProperties();
		String response = "";
		try{			  
			AdminLoginNew  adminLoginNew  = adminLoginNewDAO.getUserByUserName(changePasswordTO.getUserName());
			if(adminLoginNew!=null){
				if(adminLoginNew.getPassword()!=null && adminLoginNew.getPassword()!="" &&
					changePasswordTO.getOldpassword()!=null && changePasswordTO.getOldpassword()!="" &&
							AdminInstance.getInstance().decrypt(adminLoginNew.getPassword()).equals(changePasswordTO.getOldpassword())){
					System.out.println("Valid Old Password");
				}else{
					response = commonProperties.getProperty("changepassword.invalidoldpassword");
				}
			}
		}catch(Exception e){
			response = commonProperties.getProperty("changepassword.invalidoldpassword");
			logger.error("Exception while validateoldpassword ",e);
		}
		return response;
	}
	
	@Override
	@Transactional
	public String updatechangepassword(ChangePasswordTO changePasswordTO) {
		Properties commonProperties = FileUtils.getCommonProperties();
		String response = commonProperties.getProperty("changepassword.update.failuremessage");
		try{			  
			AdminLoginNew  adminLoginNew  = adminLoginNewDAO.getUserByUserName(changePasswordTO.getUserName());
			if(adminLoginNew!=null){
				if(adminLoginNew.getPassword()!=null && adminLoginNew.getPassword()!="" &&
					changePasswordTO.getOldpassword()!=null && changePasswordTO.getOldpassword()!="" &&
					AdminInstance.getInstance().decrypt(adminLoginNew.getPassword()).equals(changePasswordTO.getOldpassword())){
					
					adminLoginNew.setPassword(AdminInstance.getInstance().encrypt(changePasswordTO.getNewpassword()));	
					adminLoginNew.setPassword_last_update_date(new Date());
					adminLoginNew.setExpire_date(DateUtils.getTimestampFromString("2020-01-01 10:10:10"));
					adminLoginNewDAO.update(adminLoginNew);
					response = commonProperties.getProperty("changepassword.update.successmessage");
				}else{
					response = commonProperties.getProperty("changepassword.invalidoldpassword");
				}
			}
		}catch(Exception e){
			logger.error("Exception while updatechangepassword ",e);
		}
		return response;
	}
	
	@Override
	@Transactional
	public String updatePasswordChangedByAdmin(ResetPasswordTO resetPasswordTO) {
		Properties commonProperties = FileUtils.getCommonProperties();
		String response = commonProperties.getProperty("resetpassword.update.failuremessage");
		try{			  
			AdminLoginNew  adminLoginNew  = adminLoginNewDAO.findById(resetPasswordTO.getUserId());
			if(adminLoginNew!=null){
				adminLoginNew.setPassword(AdminInstance.getInstance().encrypt(resetPasswordTO.getNewpassword()));	
				adminLoginNew.setPassword_last_update_date(new Date());
				adminLoginNew.setExpire_date(DateUtils.getTimestampFromString("2020-01-01 10:10:10"));
				adminLoginNewDAO.update(adminLoginNew);
				response = commonProperties.getProperty("resetpassword.update.successmessage");
			}
		}catch(Exception e){
			logger.error("Exception while updatePassword ",e);
		}
		return response;
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
	
	@Override
	@Transactional
	public String getAppVersion(String app_code,String clientCode) {
		StringBuilder appVersion = new StringBuilder();//6.0.0
		try{	
			Version version = versionDAO.getAppVersionByAppName(app_code);
			if(version!=null){
				appVersion.append(version.getMajor_version()).append(".").append(version.getMinor_version()).append(".").append(version.getPatch_version());
			}
		}catch(Exception e){
			logger.error("Exception while updatePassword ",e);
		}
		return appVersion.toString();
	}
}
