package com.telappoint.commonrestws.common.model.assembler;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

import com.telappoint.commonrestws.common.constants.CommonDateContants;
import com.telappoint.commonrestws.common.core.AdminInstance;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginNew;
import com.telappoint.commonrestws.common.model.to.AdminLoginNewTO;
import com.telappoint.commonrestws.common.utils.DateUtils;

/**
 * 
 * @author rajeev
 * 
 */
public class AdminLoginNewAssembler
{
	private static ThreadLocal<DateFormat> tltimestamp = DateUtils
			.getSimpleDateFormat(CommonDateContants.DATETIME_FORMAT_YYYYMMDDHHMMSS_CAP.getValue());

	public static AdminLoginNewTO getAdminInfoTO(AdminLoginNew adminLogin)
	{

		AdminLoginNewTO adminInfoTO = new AdminLoginNewTO();
		if (adminLogin.getId() != 0){
			adminInfoTO.setId(adminLogin.getId());
		}
		adminInfoTO.setAccessLevel(adminLogin.getAccess_level());
		adminInfoTO.setContactEmail(adminLogin.getContact_email());
		adminInfoTO.setContactPhone(adminLogin.getContact_phone());
		adminInfoTO.setExpireDate(tltimestamp.get().format(adminLogin.getExpire_date()));
		adminInfoTO.setFirstName(adminLogin.getFirst_name());
		adminInfoTO.setLastName(adminLogin.getLast_name());
		adminInfoTO.setLocationIds(adminLogin.getLocation_ids());
		adminInfoTO.setMasterPassword(adminLogin.getPassword());
		try {
			adminInfoTO.setPassword(AdminInstance.getInstance().decrypt(adminLogin.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		adminInfoTO.setResourceIds(adminLogin.getResource_ids());
		adminInfoTO.setStartDate(tltimestamp.get().format(adminLogin.getStart_date()));
		adminInfoTO.setUserName(adminLogin.getUsername());
		
		adminInfoTO.setLocked(adminLogin.getWrong_login_max_attempt_locked());
		adminInfoTO.setSuspend(adminLogin.getSuspend());
		
		//adminInfoTO.setPasswordComplexity(adminLogin.getpasswordc);
		
		return adminInfoTO;
	}

	public static AdminLoginNew getAdminLogin(AdminLoginNewTO adminInfoTO,boolean isUpdate) {
		AdminLoginNew adminLoginNew = new AdminLoginNew();
		if (adminInfoTO.getId() != 0) {
			adminLoginNew.setId(adminInfoTO.getId());
		}
		if(!isUpdate){
			adminLoginNew.setStart_date(new Timestamp(new Date().getTime()));
			//adminLoginNew.setExpire_date(new Timestamp(new Date().getTime()));
			adminLoginNew.setExpire_date(DateUtils.getTimestampFromString("2020-01-01 10:10:10"));
		}else{
			adminLoginNew.setExpire_date(DateUtils.getTimestampFromString("2020-01-01 10:10:10"));
		}
		adminLoginNew.setUsername(adminInfoTO.getUserName());
		if(adminInfoTO.getPassword()!=null && adminInfoTO.getPassword()!="") {
			try {
				adminLoginNew.setPassword(AdminInstance.getInstance().encrypt(adminInfoTO.getPassword()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			adminLoginNew.setPassword_last_update_date(new Date());
		}
		adminLoginNew.setClient_id(adminInfoTO.getClientId());
		adminLoginNew.setFirst_name(adminInfoTO.getFirstName());
		adminLoginNew.setLast_name(adminInfoTO.getLastName());
		adminLoginNew.setContact_phone(adminInfoTO.getContactPhone());
		adminLoginNew.setContact_email(adminInfoTO.getContactEmail());
		adminLoginNew.setLocation_ids(adminInfoTO.getLocationIds());
		adminLoginNew.setResource_ids(adminInfoTO.getResourceIds());
		//adminLoginNew.setStart_date(DateUtils.getTimestampFromString(adminInfoTO.getStartDate()));
		//adminLoginNew.setExpire_date(DateUtils.getTimestampFromString(adminInfoTO.getExpireDate()));
		//System.out.println("AccessLevel  --------------> "+adminInfoTO.getAccessLevel());
		if(adminInfoTO.getAccessLevel()!="" && adminInfoTO.getAccessLevel()!=null){
			adminLoginNew.setAccess_level(adminInfoTO.getAccessLevel());
		}else{
			adminLoginNew.setAccess_level("Super User");
		}
			
		//adminLoginNew.setSuspend('N');		
		//adminLoginNew.setPassword_expire_days(-1);
		//adminLoginNew.setPassword_expiry_warning_days(-1);
		//adminLoginNew.setExpire_date(DateUtils.getTimestampFromString("2020-01-01 10:10:10"));
		
		return adminLoginNew;
	}
	
	public static AdminLoginNew getUpdatedAdminLogin(AdminLoginNewTO adminInfoTO,AdminLoginNew adminLoginNew) {
		
		adminLoginNew.setUsername(adminInfoTO.getUserName());
		/*try {
			adminLoginNew.setPassword(AdminInstance.getInstance().encrypt(adminInfoTO.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		adminLoginNew.setPassword_last_update_date(new Date());*/
	
		adminLoginNew.setFirst_name(adminInfoTO.getFirstName());
		adminLoginNew.setLast_name(adminInfoTO.getLastName());
		adminLoginNew.setContact_email(adminInfoTO.getContactEmail());
		adminLoginNew.setExpire_date(DateUtils.getTimestampFromString("2020-01-01 10:10:10"));		
		if(adminInfoTO.getAccessLevel()!="" && adminInfoTO.getAccessLevel()!=null){
			adminLoginNew.setAccess_level(adminInfoTO.getAccessLevel());
			adminLoginNew.setLocation_ids(adminInfoTO.getLocationIds());
			adminLoginNew.setResource_ids(adminInfoTO.getResourceIds());
		}
		
		
		return adminLoginNew;
	}
	
	
	public static AdminLoginNew updateUserDetailsByAdmin(AdminLoginNewTO adminInfoTO,AdminLoginNew adminLoginNew) {	
		try {
			adminLoginNew.setPassword(AdminInstance.getInstance().encrypt(adminInfoTO.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		adminLoginNew.setPassword_last_update_date(new Date());
	
		adminLoginNew.setContact_email(adminInfoTO.getContactEmail());
		adminLoginNew.setExpire_date(DateUtils.getTimestampFromString(adminInfoTO.getExpireDate(),CommonDateContants.DATE_FORMAT_MMDDYYYY.getValue()));		
		
		adminLoginNew.setWrong_login_max_attempt_locked(adminInfoTO.getLocked());
		adminLoginNew.setSuspend(adminInfoTO.getSuspend());
				
		return adminLoginNew;
	}
	
	public static AdminLoginNew getPasswordUpdatedAdminLogin(AdminLoginNewTO adminInfoTO,AdminLoginNew adminLoginNew) {		
		try {
			adminLoginNew.setPassword(AdminInstance.getInstance().encrypt(adminInfoTO.getPassword()));
			adminLoginNew.setPassword_last_update_date(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminLoginNew;
	}

}
