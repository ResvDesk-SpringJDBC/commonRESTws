package com.telappoint.commonrestws.common.masterdb.dao;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginConfig;
import com.telappoint.commonrestws.common.model.to.ResetPasswordTO;

public interface AdminLoginConfigDAO extends IBaseDao<AdminLoginConfig, Integer> {

	public String getPasswordComplexityLogic(String clientCode);

	public ResetPasswordTO getPasswordComplexityLogic(int clientId);

	public String getPasswordComplexityLogicByUserName(String username);
	
}
