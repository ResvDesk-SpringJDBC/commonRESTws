package com.telappoint.commonrestws.common.masterdb.dao;

import java.util.List;
import java.util.Map;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginNew;

public interface AdminLoginNewDAO extends IBaseDao<AdminLoginNew, Integer>
{

	/**
	 * @param admin_login
	 * @return
	 */
	public boolean addUserDetails(AdminLoginNew admin_login);

	/**
	 * @param userId
	 * @return
	 */
	public boolean deleteUserDetails(AdminLoginNew admin_login);

	/**
	 * Fetch user by userId
	 * @param id
	 * @return
	 */
	public AdminLoginNew getUserDetailsByuserId(String id);

	/**
	 * Fetches all users
	 * @return
	 */
	public List<AdminLoginNew> getUserList(String clientId);

	/**
	 * Method to update User
	 * @param adminLoginNew
	 * @return
	 */
	public boolean updateUserDetails(AdminLoginNew adminLoginNew);

	public AdminLoginNew getUserByUserName(String clientId);

	public Map<String, String> getAdminNames();

	public List<AdminLoginNew> getAdminLogins(String username,String id);
}
