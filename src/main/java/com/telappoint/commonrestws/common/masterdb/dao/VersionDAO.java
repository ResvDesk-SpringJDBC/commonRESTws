package com.telappoint.commonrestws.common.masterdb.dao;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.common.masterdb.domain.Version;


public interface VersionDAO extends IBaseDao<Version, Integer> {

	public Version getAppVersionByAppName(String appName);
		
}
