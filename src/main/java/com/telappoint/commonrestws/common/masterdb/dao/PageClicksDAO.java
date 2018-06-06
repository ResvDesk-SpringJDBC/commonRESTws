package com.telappoint.commonrestws.common.masterdb.dao;

import java.util.List;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.common.masterdb.domain.PageClicks;

public interface PageClicksDAO extends IBaseDao<PageClicks, Integer> {

	public List<PageClicks> getActivityPageClickDetails(String appName);
	
}
