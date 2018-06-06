package com.telappoint.commonrestws.common.masterdb.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.ClientDeploymentConfigDAO;
import com.telappoint.commonrestws.common.masterdb.domain.ClientDeploymentConfig;

/**
 * 
 * @author Balaji
 * 
 */
@Repository
public class ClientDeploymentConfigDAOImpl extends BaseDao<ClientDeploymentConfig, Integer> implements ClientDeploymentConfigDAO {
	private Logger logger = Logger.getLogger(ClientDeploymentConfigDAOImpl.class);

	public ClientDeploymentConfig getClientDeploymentConfig(int clientId) {
		logger.info("");
		return findByPropertySingleRow("client.id", clientId);
	}
}
