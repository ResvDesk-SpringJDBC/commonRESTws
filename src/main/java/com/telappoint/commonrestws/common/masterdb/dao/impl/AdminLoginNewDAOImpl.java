package com.telappoint.commonrestws.common.masterdb.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.exception.TelAppointDBException;
import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginNewDAO;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginNew;

@Repository
public class AdminLoginNewDAOImpl extends BaseDao<AdminLoginNew, Integer> implements AdminLoginNewDAO
{

	private Logger logger = Logger.getLogger(AdminLoginNewDAOImpl.class);

	@Override
	public boolean addUserDetails(AdminLoginNew adminLoginNew)
	{
		boolean isAdded = false;
		try
		{
			AdminLoginNew savedEntity = update(adminLoginNew);
			if (savedEntity != null)
				isAdded = true;
		}
		catch (TelAppointDBException TELAPPOINT)
		{
			logger.error("Error:" + TELAPPOINT, TELAPPOINT);
			throw TELAPPOINT;
		}
		return isAdded;
	}

	@Override
	public boolean deleteUserDetails(AdminLoginNew admin_login)
	{
		boolean isDelete = false;
		try
		{
			if (admin_login != null)
			{
				isDelete = true;
				deleteByQuery(admin_login.getId());
			}

		}
		catch (TelAppointDBException TELAPPOINT)
		{
			throw TELAPPOINT;
		}
		return isDelete;
	}

	@Override
	public AdminLoginNew getUserDetailsByuserId(String id)
	{
		return findById(Integer.parseInt(id));
	}

	@Override
	public List<AdminLoginNew> getUserList(String clientId)
	{
		List<AdminLoginNew> adminLoginList = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" from AdminLoginNew al where al.client_id="+Integer.parseInt(clientId));
			sql.append(" order by al.first_name asc");
			adminLoginList = findByQuery(sql.toString());
		}
		catch (Exception e) {
			logger.error("Error:" + e, e);
		}
		return adminLoginList;
	}

	@Override
	public boolean updateUserDetails(AdminLoginNew adminLoginNew)
	{
		boolean isUpdate = false;
		try
		{
			AdminLoginNew updatedEntity = update(adminLoginNew);
			if (updatedEntity != null)
				isUpdate = true;
		}
		catch (TelAppointDBException TELAPPOINT)
		{
			logger.error("Error:" + TELAPPOINT, TELAPPOINT);
			throw TELAPPOINT;
		}
		return isUpdate;
	}
	
	@Override
	public AdminLoginNew getUserByUserName(String username)
	{
		AdminLoginNew adminLoginNew = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" from AdminLoginNew al where al.username='");
			sql.append(username);
			sql.append("'");
			
			 List<AdminLoginNew> adminLoginList = findByQuery(sql.toString());
			 if(adminLoginList!=null && adminLoginList.size()>0){
				 adminLoginNew =  adminLoginList.get(0);
			 }
		}
		catch (Exception e) {
			logger.error("Error:" + e, e);
		}
		return adminLoginNew;
	}
	
	@Override
	public Map<String,String> getAdminNames() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select admin.username,TRIM(CONCAT(admin.first_name, ' ', admin.last_name)) as adminName ");
		sql.append(" from admin_login_new admin ");
		sql.append(" order by  admin.username asc");
		
		Map<String, String> adminNames = new LinkedHashMap<String, String>();
		List<Object[]> nativeList = getNativeQueryList(sql.toString());
		if (nativeList != null) {			
			for (Object[] ob : nativeList) {
				adminNames.put((String) ob[0], (String) ob[1]);
			}
		}
		return adminNames;
	}
	
	
	@Override
	public List<AdminLoginNew> getAdminLogins(String username,String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from AdminLoginNew al where al.id!=");
		sql.append(id);
		sql.append(" and al.username='");
		sql.append(username);
		sql.append("'");
		//System.out.println("SQL ---> "+(sql.toString()));
		return findByQuery(sql.toString());
	}
}
