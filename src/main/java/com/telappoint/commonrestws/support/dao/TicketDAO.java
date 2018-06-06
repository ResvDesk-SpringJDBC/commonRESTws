package com.telappoint.commonrestws.support.dao;

import java.util.List;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.support.domain.Ticket;

/**
 * @author Murali
 * 
 */

public interface TicketDAO extends IBaseDao<Ticket, Integer> {

	public List<Ticket> getTickets(String clientCode, boolean closedOnlly);
	public Ticket getTicketById(int ticketId);
	public Ticket addTicket(Ticket ticket) throws Exception;
	public Ticket updateTicket(Ticket ticket) throws Exception;
	public List<Ticket> getTicketsWaitingForClientResponse(String clientCode,String userName);
	
}
