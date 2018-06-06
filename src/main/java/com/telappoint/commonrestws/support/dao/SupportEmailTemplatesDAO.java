package com.telappoint.commonrestws.support.dao;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.support.domain.SupportEmailTemplates;

/**
 * @author Murali
 * 
 */

public interface SupportEmailTemplatesDAO extends IBaseDao<SupportEmailTemplates, Integer> {

	public SupportEmailTemplates getSupportEmailTemplates(String type);
	
}
