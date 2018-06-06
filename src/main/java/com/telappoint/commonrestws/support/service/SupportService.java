package com.telappoint.commonrestws.support.service;

import java.util.Map;

import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.support.model.to.SupportResponseTO;
import com.telappoint.commonrestws.support.model.to.TicketTO;

/**
 * 
 * @author Murali
 */
public interface SupportService {
	
	public SupportResponseTO getSupportResponse(String clientCode) throws TelAppointException;
	public TicketTO getTicketById(String ticketId);
	public TicketTO addTicket(TicketTO ticketTO);
	public boolean deleteTicket(String id);
	public boolean updateTicket(TicketTO ticketTO);
	public Map<Integer, String> getSupportTypes() throws TelAppointException;
	public boolean isAnyTicketWaitingForClientResponse(String clientCode,String userName) throws TelAppointException;
	public Map<Integer, String> getSupportStatus() throws TelAppointException;
	
}
