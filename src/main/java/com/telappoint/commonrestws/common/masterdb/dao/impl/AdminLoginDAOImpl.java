package com.telappoint.commonrestws.common.masterdb.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.exception.TelAppointDBException;
import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginDAO;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLogin;

@Repository
public class AdminLoginDAOImpl extends BaseDao<AdminLogin, Integer> implements AdminLoginDAO {
	
	private Logger logger = Logger.getLogger(AdminLoginDAOImpl.class);

	@Override
	public boolean addUserDetails(AdminLogin admin_login) {
		boolean isUpdate = false;
		try {
			AdminLogin ser =  update(admin_login);
			if(ser != null) isUpdate = true;
		} catch (TelAppointDBException TELAPPOINT) {
			logger.error("Error:" + TELAPPOINT, TELAPPOINT);
			throw TELAPPOINT;
		}
		return isUpdate;
	}

	
	@Override
	public boolean deleteUserDetails(AdminLogin admin_login) {
		boolean isDelete = false;
		try {
			if(admin_login!=null){
				isDelete = true;
				deleteByQuery(admin_login.getId());
			}
			
		} catch (TelAppointDBException TELAPPOINT) {
			throw TELAPPOINT;
		}
		return isDelete;
	}

	@Override
	public AdminLogin getUserDetailsByuserId(String id) {
		return findById(Integer.parseInt(id));
	}
	
	
}
