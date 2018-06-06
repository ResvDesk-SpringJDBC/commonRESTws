package com.telappoint.commonrestws.support.controller;

import static com.telappoint.commonrestws.common.controller.json.JsonDataHandler.FAILURE_RESPONSE;
import static com.telappoint.commonrestws.common.controller.json.JsonDataHandler.SUCCESS_RESPONSE;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telappoint.commonrestws.common.controller.CommonController;
import com.telappoint.commonrestws.common.controller.json.JsonDataHandler;
import com.telappoint.commonrestws.common.controller.json.MessageType;
import com.telappoint.commonrestws.common.controller.json.StatusCodes;
import com.telappoint.commonrestws.support.model.to.SupportResponseTO;
import com.telappoint.commonrestws.support.model.to.TicketTO;
import com.telappoint.commonrestws.support.service.SupportService;

/**
 * 
 * @author Murali
 * 
 */
@Controller
@RequestMapping("/support")
public class SupportController extends CommonController {	

	private static final Logger logger = Logger.getLogger(SupportController.class);
	
	@Autowired
	private SupportService supportService;

	@RequestMapping(method = RequestMethod.GET, value = "/getSupportResponse", produces = "application/json")
	public @ResponseBody JsonDataHandler getSupportResponse(@RequestParam("clientCode") String clientCode){
		try {
			SupportResponseTO supportResponse = supportService.getSupportResponse(clientCode);
			return populateJDHSuccessData(supportResponse);
		} catch (Exception e){	
			logger.error("Error : "+e);
			return handleException(e);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getTicketById", produces = "application/json")
	public @ResponseBody JsonDataHandler getTicketById(@RequestParam("ticketId") String ticketId) {
		try {
			TicketTO ticketTO = supportService.getTicketById(ticketId);
			return populateJDHSuccessData(ticketTO);
		} catch (Exception e) {
			logger.error("Error : "+e);
			return handleException(e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "addTicket", consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonDataHandler addTicket(@RequestBody TicketTO ticketTO) {
		try {			
			ticketTO = supportService.addTicket(ticketTO);
			return populateJDHSuccessData(ticketTO);
		} catch (Exception e) {
			logger.error("Error : "+e);
			return handleException(e);
		}
	}
		
	@RequestMapping(method = RequestMethod.GET, value = "deleteTicket", produces = "application/json")
	public @ResponseBody JsonDataHandler deleteTicket(@RequestParam String ticketId) {
		boolean isDeleted = false;
		try {
			isDeleted = supportService.deleteTicket(ticketId);
			if (isDeleted) {
				return SUCCESS_RESPONSE();
			} else {
				return FAILURE_RESPONSE(MessageType.ERROR.getMessageType(), StatusCodes.EIGHTY_FOUR.getStatusCode());
			}
		} catch (Exception e) {
			logger.error("Error : "+e);
			return handleException(e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "updateTicket", consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonDataHandler updateTicket(@RequestBody TicketTO ticketTO) {
		boolean isUpdated = false;
		try {
			isUpdated = supportService.updateTicket(ticketTO);
			if (isUpdated) {
				return SUCCESS_RESPONSE();
			} else {
				return FAILURE_RESPONSE(MessageType.ERROR.getMessageType(), StatusCodes.EIGHTY_FOUR.getStatusCode());
			}
		} catch (Exception e) {
			logger.error("Error : "+e);
			return handleException(e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getSupportTypes", produces = "application/json")
	public @ResponseBody JsonDataHandler getSupportTypes() {
		try {
			return populateJDHSuccessData(supportService.getSupportTypes());
		} catch (Exception e) {
			logger.error("Error : "+e);
			return handleException(e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getSupportStatus", produces = "application/json")
	public @ResponseBody JsonDataHandler getSupportStatus() {
		try {
			return populateJDHSuccessData(supportService.getSupportStatus());
		} catch (Exception e) {
			logger.error("Error : "+e);
			return handleException(e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "ticketWaitingForClientResponse", produces = "application/json")
	public @ResponseBody JsonDataHandler ticketWaitingForClientResponse(@RequestParam String clientCode,@RequestParam String userName) {
		boolean isWaiting = false;
		try {
			isWaiting = supportService.isAnyTicketWaitingForClientResponse(clientCode,userName);
			if (isWaiting) {
				return SUCCESS_RESPONSE();
			} else {
				return FAILURE_RESPONSE(MessageType.ERROR.getMessageType(), StatusCodes.EIGHTY_FOUR.getStatusCode());
			}
		} catch (Exception e) {
			logger.error("Error : "+e);
			return handleException(e);
		}
	}
	
}
