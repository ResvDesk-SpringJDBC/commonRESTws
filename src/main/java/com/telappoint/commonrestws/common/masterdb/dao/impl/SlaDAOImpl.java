package com.telappoint.commonrestws.common.masterdb.dao.impl;

import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.SlaDAO;
import com.telappoint.commonrestws.common.masterdb.domain.Sla;

@Repository
public class SlaDAOImpl extends BaseDao<Sla, Integer> implements SlaDAO {

}
