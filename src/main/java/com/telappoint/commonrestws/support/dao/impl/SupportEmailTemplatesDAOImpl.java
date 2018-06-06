package com.telappoint.commonrestws.support.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.support.dao.SupportEmailTemplatesDAO;
import com.telappoint.commonrestws.support.domain.SupportEmailTemplates;

/**
 * @author Murali
 * 
 */
@Repository
public class SupportEmailTemplatesDAOImpl extends BaseDao<SupportEmailTemplates, Integer> implements SupportEmailTemplatesDAO {
	
	private Logger logger = Logger.getLogger(SupportEmailTemplatesDAOImpl.class);
	
	@Override
	public SupportEmailTemplates getSupportEmailTemplates(String type){
		SupportEmailTemplates template = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" from SupportEmailTemplates template where template.type='");
			sql.append(type).append("'");
			//System.out.println("SQL : "+sql.toString());
			 List<SupportEmailTemplates> templates = findByQuery(sql.toString());
			 if(templates!=null && templates.size()>0){
				 template =  templates.get(0);
			 }
		}catch (Exception e) {
			logger.error("Error:" + e, e);
		}
		return template;
	}
}
