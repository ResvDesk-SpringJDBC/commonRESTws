package com.telappoint.commonrestws.common.masterdb.dao;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLogin;


public interface AdminLoginDAO extends IBaseDao<AdminLogin, Integer> {
	
	/**
	 * @param admin_login
	 * @return
	 */
	public boolean addUserDetails(AdminLogin admin_login);

	
	/**
	 * @param userId
	 * @return
	 */
	public boolean deleteUserDetails(AdminLogin admin_login);

	/**
	 * @param id
	 * @return
	 */
	public AdminLogin getUserDetailsByuserId(String id);
	
}
