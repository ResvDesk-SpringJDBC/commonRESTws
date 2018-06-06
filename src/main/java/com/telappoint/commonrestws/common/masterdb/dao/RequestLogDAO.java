package com.telappoint.commonrestws.common.masterdb.dao;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.masterdb.domain.RequestLog;

public interface RequestLogDAO extends IBaseDao<RequestLog, Integer>{

    public void logRequest(String deviceType, String uuid, String searchTerm) throws TelAppointException;
	
}
