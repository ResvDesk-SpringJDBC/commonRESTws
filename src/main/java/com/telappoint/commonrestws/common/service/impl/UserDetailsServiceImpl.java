package com.telappoint.commonrestws.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginConfigDAO;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginNewDAO;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginConfig;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginNew;
import com.telappoint.commonrestws.common.model.assembler.AdminLoginNewAssembler;
import com.telappoint.commonrestws.common.model.to.AdminLoginNewTO;
import com.telappoint.commonrestws.common.service.UserDetailsService;
import com.telappoint.commonrestws.common.utils.CoreUtils;

/**
 * 
 * @author rajeev
 * 
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	private Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private AdminLoginNewDAO adminLoginNewDAO;
	
	@Autowired
	private AdminLoginConfigDAO adminLoginConfigDAO;

	@Override
	public List<AdminLoginNewTO> getUserList(String clientId) throws TelAppointException {
		String passwordComplexity = "";
		try {
			List<AdminLoginConfig> adminLoginConfigList = adminLoginConfigDAO.findByPropertyWithOrder("client_id",Integer.parseInt(clientId),"id","ASC");
			if(adminLoginConfigList!=null && adminLoginConfigList.size()>0){
				AdminLoginConfig adminLoginConfig = adminLoginConfigList.get(0);
				passwordComplexity = adminLoginConfig.getPassword_complexity();			
			}
		}catch(Exception e){
			logger.error("Error while getting password complexity : "+e.getMessage(), e);
		}
		
		List<AdminLoginNew> adminLogins = adminLoginNewDAO.getUserList(clientId);
		List<AdminLoginNewTO> adminInfoTOs = new ArrayList<AdminLoginNewTO>();
		for (AdminLoginNew adminLogin : adminLogins)
		{
			AdminLoginNewTO adminInfoTO = AdminLoginNewAssembler.getAdminInfoTO(adminLogin);
			adminInfoTO.setPasswordComplexity(passwordComplexity);
			adminInfoTOs.add(adminInfoTO);
		}
		return adminInfoTOs;
	}

	@Override
	public AdminLoginNewTO findUsersById(String userId)
	{
		AdminLoginNew adminLogin = adminLoginNewDAO.getUserDetailsByuserId(userId);
		AdminLoginNewTO adminInfoTO = AdminLoginNewAssembler.getAdminInfoTO(adminLogin);
		return adminInfoTO;
	}

	@Override
	public boolean addUserDetals(AdminLoginNewTO adminLoginNewTO)
	{
		boolean isSavedOrUpdated = false;
		AdminLoginNew admin_login = AdminLoginNewAssembler.getAdminLogin(adminLoginNewTO,false);
		isSavedOrUpdated = adminLoginNewDAO.addUserDetails(admin_login);
		return isSavedOrUpdated;
	}

	@Override
	public boolean deleteUserDetails(String id)
	{
		boolean isDelete = false;
		try
		{
			AdminLoginNew admin_login = adminLoginNewDAO.findById(Integer.parseInt(id));
			return isDelete = adminLoginNewDAO.deleteUserDetails(admin_login);
		}
		catch (Exception e)
		{
			logger.error("ERROR: " + e, e);
		}
		return isDelete;
	}

	@Override
	public boolean updateUserDetails(AdminLoginNewTO adminLoginNewTO)
	{
		boolean isSavedOrUpdated = false;
		AdminLoginNew admin_login = adminLoginNewDAO.findById(adminLoginNewTO.getId());
		admin_login = AdminLoginNewAssembler.getUpdatedAdminLogin(adminLoginNewTO,admin_login);
		isSavedOrUpdated = adminLoginNewDAO.updateUserDetails(admin_login);
		return isSavedOrUpdated;
	}
	
	@Override
	public boolean updateUserDetailsByAdmin(AdminLoginNewTO adminLoginNewTO) {
		boolean isUpdated = false;
		AdminLoginNew admin_login = adminLoginNewDAO.findById(adminLoginNewTO.getId());
		admin_login = AdminLoginNewAssembler.updateUserDetailsByAdmin(adminLoginNewTO,admin_login);
		isUpdated = adminLoginNewDAO.updateUserDetails(admin_login);
		return isUpdated;
	}
	
	@Override
	public boolean isValidateUserName(String username,String id) {
		boolean isValid = false;
		List<AdminLoginNew>  users = adminLoginNewDAO.getAdminLogins(username,id) ;
		if(users!=null && users.size()>0){
			isValid = false;
		}else{
			isValid = true;
		}
		return isValid;
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
