package com.telappoint.commonrestws.support.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telappoint.commonrestws.common.dao.exception.TelAppointException;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginNewDAO;
import com.telappoint.commonrestws.support.components.SupportMailComponent;
import com.telappoint.commonrestws.support.constants.SupportStatusConstants;
import com.telappoint.commonrestws.support.dao.SupportStatusDAO;
import com.telappoint.commonrestws.support.dao.SupportTypesDAO;
import com.telappoint.commonrestws.support.dao.TicketDAO;
import com.telappoint.commonrestws.support.domain.SupportStatus;
import com.telappoint.commonrestws.support.domain.SupportTypes;
import com.telappoint.commonrestws.support.domain.Ticket;
import com.telappoint.commonrestws.support.model.assembler.TicketAssembler;
import com.telappoint.commonrestws.support.model.to.SupportResponseTO;
import com.telappoint.commonrestws.support.model.to.TicketTO;
import com.telappoint.commonrestws.support.service.SupportService;

@Service
public class SupportServiceImpl implements SupportService {

	@Autowired
	private TicketDAO ticketDAO;
	
	@Autowired
	private SupportTypesDAO supportTypesDAO;
	
	@Autowired
	private SupportStatusDAO supportStatusDAO;
	
	@Autowired
	private SupportMailComponent supportMailComponent;
	
	@Autowired
	private AdminLoginNewDAO adminLoginNewDAO;
	
	@Override
	public SupportResponseTO getSupportResponse(String clientCode) throws TelAppointException {
		SupportResponseTO supportResponseTO = new SupportResponseTO();
		TicketAssembler assembler = TicketAssembler.getInstance();
		TicketTO ticketTO = null;
		Map<String, String> adminNames = adminLoginNewDAO.getAdminNames();
		//Fetching pending and In-Progress tickets
		List<TicketTO> ticketTOList = new ArrayList<TicketTO>();
				
		List<Ticket> ticketList = ticketDAO.getTickets(clientCode,false);
		for (Ticket ticket : ticketList) {
			ticketTO = assembler.getTicketTO(ticket,false);
			ticketTO.setName(adminNames.get(ticket.getUsername()));
			ticketTOList.add(ticketTO);
		}
		supportResponseTO.setTicketTOList(ticketTOList);
		
		//Fetching completed/closed
		List<TicketTO> closedTicketTOList = new ArrayList<TicketTO>();
		
		List<Ticket> closedTicketList = ticketDAO.getTickets(clientCode,true);
		for (Ticket ticket : closedTicketList) {
			ticketTO = assembler.getTicketTO(ticket,false);
			ticketTO.setName(adminNames.get(ticket.getUsername()));
			closedTicketTOList.add(ticketTO);
		}	
		supportResponseTO.setClosedTicketTOList(closedTicketTOList);
		
		return supportResponseTO;		
	}

	@Override
	public TicketTO getTicketById(String ticketId) {
		Ticket ticket = ticketDAO.findById(Integer.parseInt(ticketId));
		return TicketAssembler.getInstance().getTicketTO(ticket,true);
	}

	@Override
	//@Transactional
	public TicketTO addTicket(TicketTO ticketTO){
		SupportTypes supportTypes = supportTypesDAO.findById(ticketTO.getType_id());
		SupportStatus supportStatus = supportStatusDAO.findByPropertySingleRow("value",SupportStatusConstants.SUPPORT_TICKET_STATUS_PENDING.getTicketStatus());
		Ticket ticket = TicketAssembler.getInstance().getTicket(ticketTO);
		ticket.setSupportTypes(supportTypes);
		ticket.setSupportStatus(supportStatus);
		ticket = ticketDAO.update(ticket);
		//This is to get the ticked id to append with file path
		ticket = ticketDAO.update(TicketAssembler.getInstance().getUpdatedTicketWithFilePath(ticketTO,ticket));
		if(null!=ticket){
			supportMailComponent.sendNewTicketSupportMail(ticket);
			return TicketAssembler.getInstance().getTicketTO(ticket,false);
		}else{
			return null;
		}
	}

	@Override
	public boolean deleteTicket(String id){
		ticketDAO.deleteById(Integer.parseInt(id));
		return true;
	}

	@Override
	public boolean updateTicket(TicketTO ticketTO){
		Ticket ticket = ticketDAO.findById(ticketTO.getId());
		ticket = TicketAssembler.getInstance().getUpdatedClientResponseTicket(ticketTO,ticket);
		boolean isTicketClosed = false;
		if(ticketTO.getStatus()>0){
			isTicketClosed = true;
			SupportStatus supportStatus = supportStatusDAO.findByPropertySingleRow("value",ticketTO.getStatus());
			ticket.setSupportStatus(supportStatus);
		}
		ticket = ticketDAO.update(ticket);
		if(null!=ticket){
			supportMailComponent.sendClientResponseUpdatedSupportMail(ticket,isTicketClosed);
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public Map<Integer, String> getSupportTypes() throws TelAppointException {		
		return supportTypesDAO.getSupportTypes();		
	}
	
	@Override
	public Map<Integer, String> getSupportStatus() throws TelAppointException {		
		return supportStatusDAO.getSupportStatus();		
	}
	
	@Override
	public boolean isAnyTicketWaitingForClientResponse(String clientCode,String userName) throws TelAppointException {		
		List<Ticket> tickets = ticketDAO.getTicketsWaitingForClientResponse(clientCode,userName);

		if(null!=tickets && tickets.size()>0){
			return true;
		}else{
			return false;
		}	
	}
}
