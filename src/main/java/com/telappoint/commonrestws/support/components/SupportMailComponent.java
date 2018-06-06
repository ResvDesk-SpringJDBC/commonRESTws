package com.telappoint.commonrestws.support.components;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginNewDAO;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginNew;
import com.telappoint.commonrestws.support.constants.SupportPriorityConstants.SupportPriorityConstantsMessage;
import com.telappoint.commonrestws.support.constants.SupportStatusConstants;
import com.telappoint.commonrestws.support.dao.SupportEmailTemplatesDAO;
import com.telappoint.commonrestws.support.domain.SupportEmailTemplates;
import com.telappoint.commonrestws.support.domain.Ticket;
import com.telappoint.commonrestws.support.model.assembler.TicketAssembler;
import com.telappoint.commonrestws.support.utils.EmailContants;
import com.telappoint.commonrestws.support.utils.EmailDetailsTO;
import com.telappoint.commonrestws.support.utils.EmailUtils;

@Component("mailComponent")
public class SupportMailComponent {
	
	private Logger logger = Logger.getLogger(SupportMailComponent.class);
	@Autowired
	private AdminLoginNewDAO adminLoginNewDAO;
	@Autowired
	private SupportEmailTemplatesDAO supportEmailTemplatesDAO;
	@Autowired
	public EmailUtils emailUtils;
		
	public void sendNewTicketSupportMail(Ticket ticket){
		try{			
			EmailDetailsTO emailDetailsTO = populateEmailDetails(ticket);			
			SupportEmailTemplates template = supportEmailTemplatesDAO.getSupportEmailTemplates(EmailContants.SUPPORT_EMAIL_TEMPLATE_TYPE_NEW_TICKET.getValue());

			emailDetailsTO.setSubject(populateEmailSubject(ticket, template));
						
			String mailBody = template.getBody();
			mailBody = mailBody.replaceAll("%FIRSTNAME%", emailDetailsTO.getFirstName());
			mailBody = mailBody.replaceAll("%LASTNAME%", emailDetailsTO.getLastName());
			
			mailBody = populateCommonMailBodyDetails(ticket, mailBody);			
			emailDetailsTO.setMailBody(mailBody);	
			
			emailUtils.sendEmail(emailDetailsTO);
		}catch(Exception e){
			logger.error("Error : "+e.getMessage(),e);
		}			
	}

	private String populateCommonMailBodyDetails(Ticket ticket, String mailBody) {
		//Common Section
		mailBody = mailBody.replaceAll("%CLIENTCODE-TICKETNUMBER%", ticket.getClient_code()+" - "+ticket.getId());
		mailBody = mailBody.replaceAll("%SUBJECT%", ticket.getSubject());
		mailBody = mailBody.replaceAll("%TYPE%",ticket.getSupportTypes()!=null ? ticket.getSupportTypes().getType() : "");
		mailBody = mailBody.replaceAll("%STATUS%",ticket.getSupportStatus()!=null ? ticket.getSupportStatus().getStatus() : "");
		mailBody = mailBody.replaceAll("%PRIORITY%",SupportPriorityConstantsMessage.getPriority(ticket.getPriority()));
		
		return mailBody;
	}

	private String populateEmailSubject(Ticket ticket,SupportEmailTemplates template) {
		//%CLIENTCODE-TICKETNUMBER%:%SUBJECT%
		String subject = template.getSubject();
		subject = subject.replaceAll("%CLIENTCODE-TICKETNUMBER%", ticket.getClient_code()+" - "+ticket.getId());
		subject = subject.replaceAll("%SUBJECT%",ticket.getSubject());
		return subject;
	}

	private EmailDetailsTO populateEmailDetails(Ticket ticket) {
		AdminLoginNew adminLoginNew = adminLoginNewDAO.getUserByUserName(ticket.getUsername());
		EmailDetailsTO emailDetailsTO = new EmailDetailsTO();
		emailDetailsTO.setFirstName(adminLoginNew.getFirst_name());
		emailDetailsTO.setLastName(adminLoginNew.getLast_name());
		emailDetailsTO.setUserName(ticket.getUsername());			
		emailDetailsTO.setClientCode(ticket.getClient_code());	
		emailDetailsTO.setTicketId(String.valueOf(ticket.getId()));
		emailDetailsTO.setToMailId(ticket.getContact_email());
		//emailDetailsTO.setCcMailId(ccMailId);
		//emailDetailsTO.setFromMailId(fromMailId);
		//emailDetailsTO.setReplytoMailId(replytoMailId);
		return emailDetailsTO;
	}
	
	public void sendClientResponseUpdatedSupportMail(Ticket ticket,boolean isTicketClosed){
		try{			
			EmailDetailsTO emailDetailsTO = populateEmailDetails(ticket);
			SupportEmailTemplates template = null;	
			if(!isTicketClosed){
				if(ticket.getEscalate_request()=='Y'){
					template = supportEmailTemplatesDAO.getSupportEmailTemplates(EmailContants.SUPPORT_EMAIL_TEMPLATE_TYPE_CLIENT_ESCALATED_TICKET.getValue());
				}else{
					template = supportEmailTemplatesDAO.getSupportEmailTemplates(EmailContants.SUPPORT_EMAIL_TEMPLATE_TYPE_CLIENT_UPDATED_RESPONSE.getValue());
				}
			}else{
				template = supportEmailTemplatesDAO.getSupportEmailTemplates(EmailContants.SUPPORT_EMAIL_TEMPLATE_TYPE_CLIENT_CLOSED_TICKET.getValue());
			}
			emailDetailsTO.setSubject(populateEmailSubject(ticket, template));
						
			String mailBody = template.getBody();
			mailBody = mailBody.replaceAll("%FIRSTNAME%", emailDetailsTO.getFirstName());
			mailBody = mailBody.replaceAll("%LASTNAME%", emailDetailsTO.getLastName());
			
			mailBody = mailBody.replaceAll("%CLIENT_RESPONSE%",TicketAssembler.getInstance().getClientResponse(ticket));
			
			mailBody = populateCommonMailBodyDetails(ticket, mailBody);			
			emailDetailsTO.setMailBody(mailBody);	
			
			emailUtils.sendEmail(emailDetailsTO);			
		}catch(Exception e){
			logger.error("Error : "+e.getMessage(),e);
		}
	}
	
	public void sendITFDResponseUpdatedSupportMail(Ticket ticket){
		try{
			EmailDetailsTO emailDetailsTO = populateEmailDetails(ticket);
			SupportEmailTemplates template = null;			
			if(SupportStatusConstants.SUPPORT_TICKET_STATUS_COMPLETE.getTicketStatus()== (ticket.getSupportStatus()!=null ? ticket.getSupportStatus().getValue() : 0)){
				template = supportEmailTemplatesDAO.getSupportEmailTemplates(EmailContants.SUPPORT_EMAIL_TEMPLATE_TYPE_STATUS_UPDATED_TO_FIXED_BY_ITFD.getValue());
			}else{
				template = supportEmailTemplatesDAO.getSupportEmailTemplates(EmailContants.SUPPORT_EMAIL_TEMPLATE_TYPE_STATUS_UPDATED_BY_ITFD.getValue());
			}			
			emailDetailsTO.setSubject(populateEmailSubject(ticket, template));
						
			String mailBody = template.getBody();
			mailBody = mailBody.replaceAll("%FIRSTNAME%", emailDetailsTO.getFirstName());
			mailBody = mailBody.replaceAll("%LASTNAME%", emailDetailsTO.getLastName());
			
			mailBody = mailBody.replaceAll("%%ITFD_STATUS_UPDATED_RESPONSE%%",TicketAssembler.getInstance().getItfdResponse(ticket));
			
			mailBody = populateCommonMailBodyDetails(ticket, mailBody);			
			emailDetailsTO.setMailBody(mailBody);	
			
			emailUtils.sendEmail(emailDetailsTO);
		}catch(Exception e){
			logger.error("Error : "+e.getMessage(),e);
		}
	}

}
