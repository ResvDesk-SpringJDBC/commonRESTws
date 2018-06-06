package com.telappoint.commonrestws.support.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.support.constants.SupportStatusConstants;
import com.telappoint.commonrestws.support.dao.TicketDAO;
import com.telappoint.commonrestws.support.domain.Ticket;

/**
 * @author Murali
 * 
 */
@Repository
public class TicketDAOImpl extends BaseDao<Ticket, Integer> implements TicketDAO {

	private static Logger logger = Logger.getLogger(TicketDAOImpl.class);
	
	@Override
	public List<Ticket> getTickets(String clientCode, boolean closedOnlly) {
		List<Ticket> tickets = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" from  Ticket  ticket where ticket.client_code='").append(clientCode).append("'");
		sql.append(" and ticket.supportStatus.value ");
		if(closedOnlly){
			sql.append(" in ");
		}else{
			sql.append(" not in ");
		}
		sql.append(" ( ").append(SupportStatusConstants.SUPPORT_TICKET_STATUS_COMPLETE.getTicketStatus()).append(" )");
		sql.append(" order by ticket.date_opened desc");
		
		tickets = findByQuery(sql.toString());
		logger.info(" tickets : " + tickets);

		return tickets;
	}

	@Override
	public Ticket addTicket(Ticket ticket) throws Exception {
		try {
			return update(ticket);
		} catch (Exception telappoint) {
			throw telappoint;
		}
	}

	@Override
	public Ticket getTicketById(int ticketId) {
		return findByPropertySingleRow("id", ticketId);
	}

	@Override
	public Ticket updateTicket(Ticket ticket) throws Exception {
		try {
			Ticket loc = update(ticket);
			return loc;
		} catch (Exception telAppoint) {
			throw telAppoint;
		}
	}
	
	@Override
	public List<Ticket> getTicketsWaitingForClientResponse(String clientCode,String userName) {
		List<Ticket> tickets = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" from  Ticket  ticket where ticket.client_code='").append(clientCode).append("'");
		sql.append(" and ticket.username='").append(userName).append("'");
		sql.append(" and ( ");
		sql.append(" ticket.client_response_1_enable='Y' or ");
		sql.append(" ticket.client_response_2_enable='Y' or ");
		sql.append(" ticket.client_response_3_enable='Y' or ");
		sql.append(" ticket.client_response_4_enable='Y' or ");
		sql.append("ticket.client_response_5_enable='Y' ");
		sql.append(" )");
		//System.out.println("SQL ::: "+sql.toString());
		tickets = findByQuery(sql.toString());
		logger.info(" tickets : " + tickets);

		return tickets;
	}
}
