package com.telappoint.commonrestws.common.components;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.telappoint.commonrestws.common.assembler.ClientAssembler;
import com.telappoint.commonrestws.common.constants.CommonDateContants;
import com.telappoint.commonrestws.common.constants.LoginConstants;
import com.telappoint.commonrestws.common.core.AdminInstance;
import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginConfigDAO;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginNewDAO;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginResetRequestDAO;
import com.telappoint.commonrestws.common.masterdb.dao.ClientDAO;
import com.telappoint.commonrestws.common.masterdb.dao.ClientDeploymentConfigDAO;
import com.telappoint.commonrestws.common.masterdb.dao.LoginAttemptsDAO;
import com.telappoint.commonrestws.common.masterdb.dao.SlaDAO;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginConfig;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginNew;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginResetRequest;
import com.telappoint.commonrestws.common.masterdb.domain.Client;
import com.telappoint.commonrestws.common.masterdb.domain.ClientDeploymentConfig;
import com.telappoint.commonrestws.common.masterdb.domain.LoginAttempts;
import com.telappoint.commonrestws.common.masterdb.domain.Sla;
import com.telappoint.commonrestws.common.model.JSONLoginResponse;
import com.telappoint.commonrestws.common.utils.CoreUtils;
import com.telappoint.commonrestws.common.utils.DateUtils;
import com.telappoint.commonrestws.wadl.utils.StringUtils;

/**
 * 
 * @author Murali
 * 
 */

@Component("commonLoginComponent")
@Transactional(propagation = Propagation.SUPPORTS)
public class CommonLoginComponent
{

	private static final Logger logger = Logger.getLogger(CommonLoginComponent.class);

	@Autowired
	private AdminLoginNewDAO adminLoginNewDAO;
	@Autowired
	private ClientDAO clientDAO;
	@Autowired
	private LoginAttemptsDAO loginAttemptsDAO;
	@Autowired
	private SlaDAO slaDAO;
	@Autowired
	private AdminLoginResetRequestDAO  adminLoginResetRequestDAO;
	@Autowired
	private AdminLoginConfigDAO adminLoginConfigDAO;
	@Autowired
	private ClientDeploymentConfigDAO clientDeploymentConfigDAO;
	
	@Transactional
	public String authenticateLogin(String username, String password, String ipAddress) throws TelAppointException
	{
		try
		{
			List<AdminLoginNew> loginList = adminLoginNewDAO.findByProperty("username", username.trim());
			
			if (loginList != null && loginList.size() > 0)
			{
				AdminLoginNew adminLoginNew = loginList.get(0);
				List<AdminLoginConfig> adminLoginConfigList = adminLoginConfigDAO.findByProperty("client_id", adminLoginNew.getClient_id());
				if(adminLoginConfigList!=null && adminLoginConfigList.size()>0) 
				{
					Client client = clientDAO.findById(adminLoginNew.getClient_id());
					AdminLoginConfig adminLoginConfig = adminLoginConfigList.get(0);
					if (client.getDelete_flag() != 'Y' && client.getActive() != 'N' && client.getLocked() != 'Y')
					{
						LoginAttempts loginAttempts = addLoginAttempts(adminLoginNew.getId(), ipAddress);
						boolean valid = validateUserRestrictIPs(adminLoginConfig.getUser_restrict_ips(), ipAddress);
						if (valid)
						{
							if (validateUserPassword(adminLoginNew, password, loginAttempts,adminLoginConfig))
							{
								if (adminLoginNew.getSuspend() != 'Y')
								{
									if (!isPassWordExpired(adminLoginNew,adminLoginConfig))
									{
										if (adminLoginNew.getWrong_login_max_attempt_locked() != 'Y')
										{   if (new Date(adminLoginNew.getStart_date().getTime()).compareTo(new Date(adminLoginNew.getExpire_date().getTime())) != 0)
											{
												if (new Date().compareTo(new Date(adminLoginNew.getExpire_date().getTime())) <= 0)
												{
													return LoginConstants.LOGIN_SUCESSES_RESPONSE.getValue();
												}
												else {
													return LoginConstants.LOGIN_PASSWORD_EXPIRED_RESPONSE.getValue();												
												}
											}else{
												return LoginConstants.LOGIN_CHANGE_PASSWORD_RESPONSE.getValue();
											}
											
										} else {
											return LoginConstants.LOGIN_WRONG_LOGIN_ATTEMPTS_EXCEEDED_RESPONSE.getValue();
										}
									} else {
										return LoginConstants.LOGIN_PASSWORD_EXPIRED_RESPONSE.getValue();
									}
								} else {
									return LoginConstants.LOGIN_INVALID_PASSWORD_RESPONSE.getValue();
								}
							} else {
								return LoginConstants.LOGIN_PASSWORD_EXPIRED_RESPONSE.getValue();
							}
						} else {
							return LoginConstants.LOGIN_RESTRICT_IPS_RESPONSE.getValue();
						}
					} else {
						return LoginConstants.LOGIN_USER_LOCKED_RESPONSE.getValue();
					}
				}
			}else{
				return LoginConstants.LOGIN_FAILURE_RESPONSE.getValue();
			}
		}
		catch (Exception e)
		{
			logger.error("Error while authenticating username - " + username, e);
		}
		return LoginConstants.LOGIN_FAILURE_RESPONSE.getValue();
	}

	@Transactional
	private LoginAttempts addLoginAttempts(int userid, String ipAddress)
	{
		LoginAttempts loginAttempts = new LoginAttempts();
		loginAttempts.setIp_address(ipAddress);
		loginAttempts.setLogin_status(LoginConstants.LOGIN_ATEMPTS_LOGIN_STATUS_SUCESS.getValue());
		loginAttempts.setTimestamp(new Timestamp(new Date().getTime()));
		loginAttempts.setUser_id(userid);
		loginAttemptsDAO.save(loginAttempts);
		return loginAttempts;
	}

	private boolean validateUserRestrictIPs(String user_restrict_ips, String ipAddress)
	{
		boolean valid = false;
		if (user_restrict_ips != null && user_restrict_ips != "")
		{
			List<String> ips = Arrays.asList(user_restrict_ips.split("\\s*,\\s*"));
			valid = ips.contains(ipAddress);
		}
		else
		{
			valid = true;
		}

		return valid;
	}

	private boolean isPassWordExpired(AdminLoginNew adminLoginNew,AdminLoginConfig adminLoginConfig)
	{
		int expireDays = adminLoginConfig.getPassword_expire_days();
		if (expireDays != -1)
		{
			Date lastUpdatedDate = adminLoginNew.getPassword_last_update_date();
			Date expieryDate = DateUtils.addDays(lastUpdatedDate, expireDays);
			if (new Date().compareTo(expieryDate) >= 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	@Transactional
	private boolean validateUserPassword(AdminLoginNew adminLoginNew, String password, LoginAttempts loginAttempts,AdminLoginConfig adminLoginConfig)
	{
		try { 
		// TODO : Actual code - after testing we need to uncomment it
		/*
		 * String queryString =
		 * "select username,first_name from admin_login_new where password=password('"
		 * +password+"') and username='"+adminLoginNew.getUsername()+"'";
		 * List<Object[]> nativeResultList =
		 * adminLoginNewDAO.findByNativeQuery(queryString);
		 * if(nativeResultList!=null && nativeResultList.size()>0){ Object[]
		 * objectArray = nativeResultList.get(0); if(objectArray!=null &&
		 * objectArray.length>0){ if(objectArray[0]!=null){ String username =
		 * (String)objectArray[0]; if(username!=null &&
		 * username.equals(adminLoginNew.getUsername())){ return true; } } }
		 * }else{ loginAttempts.setLogin_status(LoginConstants.
		 * LOGIN_ATEMPTS_LOGIN_STATUS_FAILURE.getValue()); List<Object[]> result
		 * = loginAttemptsDAO.findByNativeQuery(
		 * "SELECT * FROM login_attempts WHERE user_id="+adminLoginNew.getId()+
		 * " and timestamp BETWEEN  now()-INTERVAL 30 MINUTE and now()");
		 * if(result!=null &&
		 * result.size()>adminLoginNew.getMax_wrong_login_attempts()){
		 * adminLoginNew.setWrong_login_max_attempt_locked('Y'); } }
		 */
		/*System.out.println(" DB Password - Encripted - "+adminLoginNew.getPassword());
		System.out.println(" DB Password - Decrypted - "+(new AdminInstance().decrypt(adminLoginNew.getPassword())));
		System.out.println(" Fronnt End Password - "+password);*/
		if (password != null && adminLoginNew.getPassword()!=null && password.equals(AdminInstance.getInstance().decrypt(adminLoginNew.getPassword()))) {
			return true;
		}
		else {
			int allowedWrongAttempts = adminLoginConfig.getMax_wrong_login_attempts();
			loginAttempts.setLogin_status(LoginConstants.LOGIN_ATEMPTS_LOGIN_STATUS_FAILURE.getValue());
			loginAttemptsDAO.update(loginAttempts);
			//System.out.println("allowedWrongAttempts  -----------------> "+allowedWrongAttempts);
			if (allowedWrongAttempts != -1 ) {
				List<Object[]> result = loginAttemptsDAO.findByNativeQuery("SELECT * FROM login_attempts WHERE user_id="
						+ adminLoginNew.getId() + " and timestamp BETWEEN  now()-INTERVAL 30 MINUTE and now()");
				if (result != null && result.size() > allowedWrongAttempts)
				{
					adminLoginNew.setWrong_login_max_attempt_locked('Y');
				}
			}
		}
		}catch(Exception e){
			logger.error("Error while validating user",e);
		}
		return false;
	}

	public JSONLoginResponse createLoginResponse(AdminLoginNew adminLoginNew, String response, String ip)
	{
		JSONLoginResponse loginResponse = new JSONLoginResponse();
		ClientAssembler clientAssembler = ClientAssembler.getInstance();
		loginResponse.setLogin(response);
		Client client = clientDAO.findById(adminLoginNew.getClient_id());
		loginResponse.setClientCode(client.getClient_code());
		loginResponse.setClientTO(clientAssembler.getClientTO(client));
		loginResponse.setClientId(client.getId());
		loginResponse.setAccessLevel(adminLoginNew.getAccess_level());
		loginResponse.setLocationAccess(adminLoginNew.getLocation_ids());
		loginResponse.setResourceAccess(adminLoginNew.getResource_ids());
		//loginResponse.setAppVersion(client.getLicense_key());
		setPasswordDetails(adminLoginNew,loginResponse);
		//loginResponse.setPasswordExpiryAlert(setPasswordDetails(adminLoginNew,loginResponse));
		loginResponse.setFirstName(adminLoginNew.getFirst_name());
		loginResponse.setLastName(adminLoginNew.getLast_name());
		String contactPhone = adminLoginNew.getContact_phone();
		if (StringUtils.isNotEmpty(contactPhone)) {
			loginResponse.setContactPhone(StringUtils.formatPhoneNumber(contactPhone));
		}
		loginResponse.setContactEmail(adminLoginNew.getContact_email());

		// Setting last login details
		setLastLoginDetails(adminLoginNew, loginResponse);

		// setting SLA details
		setSlaDetails(adminLoginNew, loginResponse);

		if ("Y".equals(loginResponse.getDisplaySLA())){
			loginResponse.setForwardURL(LoginConstants.LOGIN_SLA_FORWARD_URL_PATH.getValue());
		} else {
			loginResponse.setForwardURL(client.getForward_url());
		}
		
		loginResponse.setLocationIds(adminLoginNew.getLocation_ids());
		loginResponse.setResourceIds(adminLoginNew.getResource_ids());
		loginResponse.setLoginUserId(adminLoginNew.getId());
				
		try{
			 List<ClientDeploymentConfig>  configList =clientDeploymentConfigDAO.findByProperty("client.id",adminLoginNew.getClient_id());
			 if(null!=configList && configList.size()>0){
				 loginResponse.setClinetTimeZone(configList.get(0).getTime_zone());
				 loginResponse.setCall_center_logic(configList.get(0).getCall_center_logic());
			 }
		}catch(Exception e){
			loginResponse.setClinetTimeZone("");
			loginResponse.setCall_center_logic("N");
		}
		
		return loginResponse;
	}

	private void setPasswordDetails(AdminLoginNew adminLoginNew,JSONLoginResponse loginResponse)
	{
		String passwordExpiryAlert = "No";
		List<AdminLoginConfig> adminLoginConfigList = adminLoginConfigDAO.findByPropertyWithOrder("client_id", adminLoginNew.getClient_id(),"id","ASC");
		if(adminLoginConfigList!=null && adminLoginConfigList.size()>0){
			AdminLoginConfig adminLoginConfig = adminLoginConfigList.get(0);
			loginResponse.setPasswordResetAlgorithm(adminLoginConfig.getPassword_reset_algorithm());
			loginResponse.setPasswordComplexity(adminLoginConfig.getPassword_complexity());
			int warningDays = adminLoginConfig.getPassword_expiry_warning_days();
			if (warningDays != -1)
			{
				Date lastUpdatedDate = adminLoginNew.getPassword_last_update_date();
				Date warningDate = DateUtils.addDays(lastUpdatedDate, warningDays);
				if (new Date().compareTo(warningDate) < 0)
				{
					passwordExpiryAlert =  "Yes";					
				}
			}
		}
		loginResponse.setPasswordExpiryAlert(passwordExpiryAlert);
	}

	private void setSlaDetails(AdminLoginNew adminLoginNew, JSONLoginResponse loginResponse)
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("client_id", adminLoginNew.getClient_id());
		parameters.put("delete_flag", 'N');
		List<Sla> slaList = slaDAO.findByProperties(parameters);
		if (slaList != null && slaList.size() > 0)
		{
			Sla sla = slaList.get(0);
			loginResponse.setDisplaySLA(String.valueOf(sla.getDisplay_sla()));
			loginResponse.setSlaSkip(String.valueOf(sla.getSkip_sla()));
			loginResponse.setSlaText(sla.getSla_text());
		}
	}

	private void setLastLoginDetails(AdminLoginNew adminLoginNew, JSONLoginResponse loginResponse)
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("user_id", adminLoginNew.getId());
		parameters.put("login_status", LoginConstants.LOGIN_ATEMPTS_LOGIN_STATUS_SUCESS.getValue());
		List<LoginAttempts> loginAttemptsList = loginAttemptsDAO.findByPropertiesWithOrder(parameters, "timestamp", "DESC");
		if (loginAttemptsList != null && loginAttemptsList.size() > 0)
		{
			LoginAttempts loginAttempts = loginAttemptsList.get(0);
			if (loginAttemptsList.size() > 2)
			{
				loginAttempts = loginAttemptsList.get(1);
			}
			loginResponse.setLastLoginDateTime(new SimpleDateFormat(CommonDateContants.DATETIME_FORMAT_YYYYMMDDHHMMSS_WITH_AMPM
					.getValue()).format(loginAttempts.getTimestamp()));
			loginResponse.setLastLoginIP(loginAttempts.getIp_address());
		}
	}

	public AdminLoginResetRequest getAdminLoginResetRequestByToken(String token) throws TelAppointException {
		return adminLoginResetRequestDAO.getAdminLoginResetRequestByToken(token);
	}

	public int isTokenValid(String token) throws TelAppointException {
		int login_id = 0;
		AdminLoginResetRequest _token = getAdminLoginResetRequestByToken(token);
		if (_token != null && _token.getExpire_timestamp().after(new Timestamp(System.currentTimeMillis()))) {
			login_id = _token.getLogin_id();
		}
		return login_id;
	}

	public AdminLoginResetRequest insertAdminLoginResetRequestToken(int login_id,String userName,String email,String email_reset_url,int clientId) throws TelAppointException {
		logger.info("insertAdminLoginResetRequestToken");
		int expiryMin = 1 * 60 ; // TODO parameterize
		StringBuilder appendedStr = new StringBuilder();
		appendedStr.append(userName);
		appendedStr.append("|");
		appendedStr.append(clientId);
		appendedStr.append(new Timestamp(System.currentTimeMillis()));
		AdminLoginResetRequest adminLoginResetRequest = new AdminLoginResetRequest();
		
		adminLoginResetRequest.setLogin_id(login_id);
		adminLoginResetRequest.setSent_timestamp(new Timestamp(CoreUtils.addMinsToCurrentTime(0).getTime()));
		adminLoginResetRequest.setExpire_timestamp(new Timestamp(CoreUtils.addMinsToCurrentTime(expiryMin).getTime()));
		adminLoginResetRequest.setReset_password_token(CoreUtils.getToken(appendedStr.toString()));
		adminLoginResetRequest.setEmail(email);
		adminLoginResetRequest.setEmail_reset_url(email_reset_url);
		
		/*adminLoginResetRequest.setTemp_password("");
		adminLoginResetRequest.setSms_phone("");
		adminLoginResetRequest.setSms_reset_code("");*/
		
		
		adminLoginResetRequest = adminLoginResetRequestDAO.update(adminLoginResetRequest);		
		return adminLoginResetRequest;
	}
	
}
