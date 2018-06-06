package com.telappoint.commonrestws.common.masterdb.dao;

import java.util.List;
import java.util.Set;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.masterdb.domain.Client;

/**
 * @author Murali
 */

public interface ClientDAO extends IBaseDao<Client, Integer> {
	
	public int getClientId(String clientCode);
	public Client getClientInfo(int clientId);
	public Client getClient(String clientCode);
	public List<Client> getClientListBySearchKey(String searchKey) throws TelAppointException;
	public List<Client> getMobileDemoClientList(Set<Integer> clientIds);
	public List<Client> getClientInfo(String clientCode) throws TelAppointException;
}
