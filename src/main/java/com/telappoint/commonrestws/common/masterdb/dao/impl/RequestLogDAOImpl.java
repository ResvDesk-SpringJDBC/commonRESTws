package com.telappoint.commonrestws.common.masterdb.dao.impl;

import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.RequestLogDAO;
import com.telappoint.commonrestws.common.masterdb.domain.RequestLog;

@Repository
public class RequestLogDAOImpl  extends BaseDao<RequestLog, Integer>  implements RequestLogDAO {

    
  
	@Override
	public void logRequest(String deviceType, String uuid, String searchTerm)
			throws TelAppointException {
		
	}
}
