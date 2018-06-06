package com.telappoint.commonrestws.support.dao;

import java.util.Map;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.support.domain.SupportStatus;

/**
 * @author Murali
 * 
 */

public interface SupportStatusDAO extends IBaseDao<SupportStatus, Integer> {

	public Map<Integer, String> getSupportStatus();
	
}
