package com.telappoint.commonrestws.support.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.support.dao.SupportStatusDAO;
import com.telappoint.commonrestws.support.domain.SupportStatus;

/**
 * @author Murali
 * 
 */
@Repository
public class SupportStatusDAOImpl extends BaseDao<SupportStatus, Integer> implements SupportStatusDAO {
	
	@Override
	public Map<Integer, String> getSupportStatus() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select supportStatus.id,supportStatus.status ");
		sql.append(" from support_status supportStatus ");
		sql.append(" order by supportStatus.id asc");
		return getNativeMap(sql.toString());
	}
}
