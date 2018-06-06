package com.telappoint.commonrestws.common.masterdb.dao.impl;

import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.RequestResponseTimeLogDAO;
import com.telappoint.commonrestws.common.masterdb.domain.RequestResponseTimeLog;

@Repository
public class RequestResponseTimeLogDAOImpl extends BaseDao<RequestResponseTimeLog,Integer> implements RequestResponseTimeLogDAO {

}
