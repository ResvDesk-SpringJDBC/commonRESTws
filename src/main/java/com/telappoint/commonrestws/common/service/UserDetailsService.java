package com.telappoint.commonrestws.common.service;

import java.util.List;

import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.model.to.AdminLoginNewTO;

/**
 * 
 * @author rajeev
 * 
 */
public interface UserDetailsService
{
	/**
	 * 
	 * @return
	 * @throws TelAppointException
	 */
	public List<AdminLoginNewTO> getUserList(String clientId) throws TelAppointException;

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public AdminLoginNewTO findUsersById(String userId);

	/**
	 * save admin login to database
	 * @param adminLoginNewTO
	 * @return
	 */
	public boolean addUserDetals(AdminLoginNewTO adminLoginNewTO);

	/**
	 * delete user by user id
	 * @param id
	 * @return
	 */
	public boolean deleteUserDetails(String id);

	/**
	 * 
	 * @param adminLoginNewTO
	 * @return
	 */
	public boolean updateUserDetails(AdminLoginNewTO adminLoginNewTO);

	public boolean updateUserDetailsByAdmin(AdminLoginNewTO adminLoginNewTO);

	public boolean isValidateUserName(String username,String id);
	
	public void sendEmailIPNotAllowed(String clientCode, String ipaddress, String methodName);
}
