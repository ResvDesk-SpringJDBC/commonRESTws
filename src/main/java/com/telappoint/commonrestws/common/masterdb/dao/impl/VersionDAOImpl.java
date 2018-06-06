package com.telappoint.commonrestws.common.masterdb.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.VersionDAO;
import com.telappoint.commonrestws.common.masterdb.domain.Version;

@Repository
public class VersionDAOImpl extends BaseDao<Version, Integer> implements VersionDAO {
	
	private Logger logger = Logger.getLogger(VersionDAOImpl.class);
	
	@Override
	public Version getAppVersionByAppName(String app_code) {
		Version version = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" from Version v where v.app_code='");
			sql.append(app_code);
			sql.append("' order by timestamp desc");
			
			 List<Version> versions = findByQuery(sql.toString());
			 if(versions!=null && versions.size()>0){
				 version =  versions.get(0);
			 }
		}catch (Exception e) {
			logger.error("Error:" + e, e);
		}
		return version;
	}
	
}
