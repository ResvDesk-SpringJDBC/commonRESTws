package com.telappoint.commonrestws.support.dao;

import java.util.Map;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.support.domain.SupportTypes;

/**
 * @author Murali
 * 
 */

public interface SupportTypesDAO extends IBaseDao<SupportTypes, Integer> {

	public Map<Integer, String> getSupportTypes();
	
}
