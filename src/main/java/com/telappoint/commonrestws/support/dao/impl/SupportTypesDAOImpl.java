package com.telappoint.commonrestws.support.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.support.dao.SupportTypesDAO;
import com.telappoint.commonrestws.support.domain.SupportTypes;

/**
 * @author Murali
 * 
 */
@Repository
public class SupportTypesDAOImpl extends BaseDao<SupportTypes, Integer> implements SupportTypesDAO {
	
	@Override
	public Map<Integer, String> getSupportTypes() {
		StringBuilder sql = new StringBuilder();
		sql.append("select supportTypes.id, supportTypes.type");
		sql.append(" from  support_types  supportTypes ");
		sql.append(" order by supportTypes.type asc");
		return getNativeMap(sql.toString());
	}
}
