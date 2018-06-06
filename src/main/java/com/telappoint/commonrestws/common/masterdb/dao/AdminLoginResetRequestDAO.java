package com.telappoint.commonrestws.common.masterdb.dao;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginResetRequest;

public interface AdminLoginResetRequestDAO extends IBaseDao<AdminLoginResetRequest, Integer> {

	public AdminLoginResetRequest getAdminLoginResetRequestByToken(String token);

}
