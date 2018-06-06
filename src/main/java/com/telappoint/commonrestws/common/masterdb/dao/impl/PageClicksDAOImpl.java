package com.telappoint.commonrestws.common.masterdb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.PageClicksDAO;
import com.telappoint.commonrestws.common.masterdb.domain.PageClicks;

@Repository
public class PageClicksDAOImpl extends BaseDao<PageClicks, Integer> implements PageClicksDAO {

	@Override
	public List<PageClicks> getActivityPageClickDetails(String appName){
		return findByProperty("app_name", appName);
	}
}
