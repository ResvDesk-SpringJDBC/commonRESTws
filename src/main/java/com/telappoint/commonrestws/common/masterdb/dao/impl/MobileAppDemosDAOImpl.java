package com.telappoint.commonrestws.common.masterdb.dao.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.MobileAppDemosDAO;
import com.telappoint.commonrestws.common.masterdb.domain.MobileAppDemos;

/**
 * 
 * @author Balaji
 * 
 */
@Repository
public class MobileAppDemosDAOImpl extends BaseDao<MobileAppDemos, Integer> implements MobileAppDemosDAO {
	
	public Set<Integer> getClientDemoIds() {
		Set<Integer> clientIds = new HashSet<Integer>();
		
		return clientIds;
	}
}