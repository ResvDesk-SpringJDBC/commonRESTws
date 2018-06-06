package com.telappoint.commonrestws.common.service;

import java.util.Map;

import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.model.to.PageClicksTO;
import com.telappoint.commonrestws.common.model.to.RequestResponseTimeLogTO;

/**
 * 
 * @author Murali G
 * 
 */
public interface CommonFunctionalityService {
	
	public Map<String,PageClicksTO> getActivityPageClickDetails(String clientCode) throws TelAppointException ;
	public boolean writeRequestResponseTimeLog(RequestResponseTimeLogTO reqResTimeLogTO)throws TelAppointException;

}
