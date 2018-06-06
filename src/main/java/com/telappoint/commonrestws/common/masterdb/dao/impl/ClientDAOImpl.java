package com.telappoint.commonrestws.common.masterdb.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.exception.TelAppointDBException;
import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.ClientDAO;
import com.telappoint.commonrestws.common.masterdb.domain.Client;

/**
 * @author Murali
 * @author Balaji
 */

@Repository
public class ClientDAOImpl extends BaseDao<Client, Integer> implements ClientDAO {
	private Logger logger = Logger.getLogger(ClientDAOImpl.class);

	@Override
	public int getClientId(String clientCode) {
		int clientId = 0;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select c.id from client c,client_deployment_config d ");
			sb.append("where c.client_code ='" + clientCode + "' and c.id = d.client_id ");
			sb.append("and c.delete_flag = 'N' and active = 'Y' ");
			sb.append("and locked = 'N' " + "and d.user_expiry_date_time > now()");
			clientId = (Integer) findByNativeQuerySingleRow(sb.toString());
		} catch (Exception e) {
			logger.error("Error in getClientId:" + e, e);
		}
		return clientId;
	}

	public Client getClientInfo(int clientId) {
		Client client = null;
		try {
			client = findById(clientId);
		} catch (Exception e) {
			logger.error("Error in getClientInfo:" + e, e);
		}
		return client;
	}

	public Client getClient(String clientCode) {
		Client client = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" from Client c");
		sql.append(" where c.delete_flag = 'N' and c.active = 'Y' and c.client_code = '" + clientCode + "'");

		try {
			List<Client> clientList = findByQuery(sql.toString());
			if (clientList != null && clientList.size() > 0) {
				client = clientList.get(0);
			}
		} catch (Exception e) {
			logger.error("Error in getClientInfo:" + e, e);
		}
		return client;
	}
	
	
	@Override
	public List<Client> getClientListBySearchKey(String searchKey) throws TelAppointException {
		logger.info("## getClientListBySearchKey method - Start ##");
		try {
			return findByQuery("from Client c where upper(c.client_name) like upper('%" + searchKey + "%')");
		} catch (TelAppointDBException exception) {
			logger.error("ERROR: " + exception, exception);
			throw new TelAppointException(exception.getExceptionCode());
		} finally {
			logger.info("## getClientListBySearchKey method - End ##");
		}

	}
	
	@Override
	public List<Client> getClientInfo(String clientCode) throws TelAppointException {
		logger.info("## getClientListBySearchKey method - Start ##");
		try {
			return findByQuery("from Client c where upper(c.client_code) = upper('" + clientCode + "')");
		} catch (TelAppointDBException exception) {
			logger.error("ERROR: " + exception, exception);
			throw new TelAppointException(exception.getExceptionCode());
		} finally {
			logger.info("## getClientListBySearchKey method - End ##");
		}

	}

	@Override
	public List<Client> getMobileDemoClientList(Set<Integer> clientIds) {
		return null;
	}

}
