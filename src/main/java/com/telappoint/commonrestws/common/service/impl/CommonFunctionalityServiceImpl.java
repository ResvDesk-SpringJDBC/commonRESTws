package com.telappoint.commonrestws.common.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.masterdb.dao.PageClicksDAO;
import com.telappoint.commonrestws.common.masterdb.dao.RequestResponseTimeLogDAO;
import com.telappoint.commonrestws.common.masterdb.domain.PageClicks;
import com.telappoint.commonrestws.common.masterdb.domain.RequestResponseTimeLog;
import com.telappoint.commonrestws.common.model.to.PageClicksTO;
import com.telappoint.commonrestws.common.model.to.RequestResponseTimeLogTO;
import com.telappoint.commonrestws.common.service.CommonFunctionalityService;

/**
 * 
 * @author Murali G
 * 
 */
@Service
public class CommonFunctionalityServiceImpl implements CommonFunctionalityService {
	Logger logger = Logger.getLogger(CommonFunctionalityServiceImpl.class.getName());
	
	@Autowired
	private PageClicksDAO pageClicksDAO;
	@Autowired
	private RequestResponseTimeLogDAO reqResTimeLogDAO;
	

	@Override
	public Map<String,PageClicksTO> getActivityPageClickDetails(String appName) throws TelAppointException {
		Map<String, PageClicksTO> pageClickDetails = new HashMap<String, PageClicksTO>();
		List<PageClicks> pageClicksList = pageClicksDAO.getActivityPageClickDetails(appName);
		if(pageClicksList!=null && pageClicksList.size()>0){
			PageClicksTO pageClicksTO = null;
			for(PageClicks pageClicks : pageClicksList){
				pageClicksTO = new PageClicksTO();
				
				pageClicksTO.setId(pageClicks.getId());
				pageClicksTO.setApp_name(pageClicks.getApp_name());
				pageClicksTO.setFront_end_method_name(pageClicks.getFront_end_method_name());
				pageClicksTO.setPage_id(pageClicks.getPage_id());
				pageClicksTO.setPage_name(pageClicks.getPage_name());
				pageClicksTO.setClick_id(pageClicks.getClick_id());
				pageClicksTO.setClick_name(pageClicks.getClick_name());
				
				pageClickDetails.put(pageClicks.getFront_end_method_name(),pageClicksTO);
			}
		}
		return pageClickDetails;
	}
	
	@Override
	public boolean writeRequestResponseTimeLog(RequestResponseTimeLogTO reqResTimeLogTO) throws TelAppointException {
		try{
			RequestResponseTimeLog reqResTimeLog = new RequestResponseTimeLog();
			reqResTimeLog.setTimestamp(new Timestamp(new Date().getTime()));
			reqResTimeLog.setApi(reqResTimeLogTO.getApi());
			reqResTimeLog.setApp_name(reqResTimeLogTO.getApp_name());
			reqResTimeLog.setClient_code(reqResTimeLogTO.getClient_code());
			reqResTimeLog.setSessionid(reqResTimeLogTO.getSessionid());
			reqResTimeLog.setDuration_ms(reqResTimeLogTO.getDuration_ms());
			reqResTimeLog.setJson_request(reqResTimeLogTO.getJson_request());
			reqResTimeLog.setJson_response(reqResTimeLogTO.getJson_response());
			reqResTimeLog.setMethod_type(reqResTimeLogTO.getMethod_type());
			reqResTimeLog.setPage(reqResTimeLogTO.getPage());
			reqResTimeLog.setUrl(reqResTimeLogTO.getUrl());
			reqResTimeLog.setUserid(reqResTimeLogTO.getUserid());
			
			reqResTimeLogDAO.save(reqResTimeLog);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
}
