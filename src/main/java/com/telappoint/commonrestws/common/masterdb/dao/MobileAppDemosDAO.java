package com.telappoint.commonrestws.common.masterdb.dao;

import java.util.Set;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.common.masterdb.domain.MobileAppDemos;

/**
 * 
 * @author Murali
 * 
 */
public interface MobileAppDemosDAO extends IBaseDao<MobileAppDemos, Integer> {
	
	public Set<Integer> getClientDemoIds();
}