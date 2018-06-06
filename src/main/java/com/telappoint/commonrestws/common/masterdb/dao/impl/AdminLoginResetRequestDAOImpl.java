package com.telappoint.commonrestws.common.masterdb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginResetRequestDAO;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginResetRequest;

@Repository
public class AdminLoginResetRequestDAOImpl extends BaseDao<AdminLoginResetRequest, Integer> implements AdminLoginResetRequestDAO {

	@Override
	public AdminLoginResetRequest getAdminLoginResetRequestByToken(String token) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from AdminLoginResetRequest alrr where 1=1");
		sql.append(" and alrr.reset_password_token = '");
		sql.append(token);
		sql.append("'");
		sql.append(" order by alrr.expire_timestamp desc");
		//System.out.println(" sql.toString()  ======================>  "+sql.toString());
		List<AdminLoginResetRequest> tokenList = findByQuery(sql.toString());
		if(tokenList != null && tokenList.size()>0) {
			return tokenList.get(0);
		}
		return null;
	}
}
