package com.telappoint.commonrestws.support.model.assembler;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;

import com.telappoint.commonrestws.common.constants.CommonDateContants;
import com.telappoint.commonrestws.common.utils.DateUtils;
import com.telappoint.commonrestws.common.utils.FileUtils;
import com.telappoint.commonrestws.support.constants.SupportPriorityConstants.SupportPriorityConstantsMessage;
import com.telappoint.commonrestws.support.domain.Ticket;
import com.telappoint.commonrestws.support.model.to.TicketTO;

public class TicketAssembler {
	
	private Logger logger = Logger.getLogger(TicketAssembler.class);

	private static TicketAssembler instance__ = new TicketAssembler();

	private TicketAssembler() {
	}

	public static TicketAssembler getInstance() {
		return instance__;
	}

	public TicketTO getTicketTO(Ticket ticket,boolean isEdit) {

		TicketTO ticketTO = null;
		try {
			ticketTO = new TicketTO();
			
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(ticketTO, ticket);
			
			//ThreadLocal<DateFormat> tlTime = DateUtils.getSimpleDateFormat(CommonDateContants.TIME_FORMAT_TWELVE_HRS.getValue());
			ThreadLocal<DateFormat> tlDate = DateUtils.getSimpleDateFormat(CommonDateContants.DATE_FORMAT_MMDDYYYY.getValue());
			
			ticketTO.setTimestamp_str(ticket.getTimestamp()!=null ? tlDate.get().format(ticket.getTimestamp()): "");
			ticketTO.setDate_opened_str(ticket.getDate_opened()!=null ? tlDate.get().format(ticket.getDate_opened()): "");
			ticketTO.setDate_updated_str(ticket.getDate_updated()!=null ? tlDate.get().format(ticket.getDate_updated()): "");
			
			ticketTO.setTimestamp(null);
			ticketTO.setDate_opened(null);
			ticketTO.setDate_updated(null);
			
			String workPhone = ticket.getContact_phone();
			
			if(workPhone!=null && workPhone!=""  && !workPhone.isEmpty()){
				if(workPhone.contains("-")){
					String workPhones[] = workPhone.split("-");
					if(workPhones!=null && workPhones.length>=3){
						ticketTO.setContact_phone_1(workPhones[0]);
						ticketTO.setContact_phone_2(workPhones[1]);
						ticketTO.setContact_phone_3(workPhones[2]);
					}
				} else {
					ticketTO.setContact_phone_1(workPhone.substring(0,3));
					ticketTO.setContact_phone_2(workPhone.substring(3,6));
					ticketTO.setContact_phone_3(workPhone.substring(6));
				}
			}
			
			ticketTO.setType_str(ticket.getSupportTypes()!=null ? ticket.getSupportTypes().getType() : "");
			ticketTO.setPriority_str(SupportPriorityConstantsMessage.getPriority(ticket.getPriority()));
			ticketTO.setStatus_str(ticket.getSupportStatus()!=null ? ticket.getSupportStatus().getStatus() : "");
			
			ticketTO.setFile_attachment_1(ticket.getFile_attachment_1());
			ticketTO.setFile_attachment_2(ticket.getFile_attachment_2());
			ticketTO.setFile_attachment_3(ticket.getFile_attachment_3());
			ticketTO.setFile_attachment_4(ticket.getFile_attachment_4());
			ticketTO.setFile_attachment_5(ticket.getFile_attachment_5());
			
			if(isEdit){
				String displayable_client_response = "0";
				if(ticket.getClient_response_1_enable()=='Y'){
					ticketTO.setItfd_response_1(ticket.getItfd_response_1());
					ticketTO.setClient_response_1_enable(ticket.getClient_response_1_enable());
					ticketTO.setItfd_response_1(ticket.getItfd_response_1());
					displayable_client_response = "1";
				}
				if(ticket.getClient_response_2_enable()=='Y'){
					ticketTO.setItfd_response_2(ticket.getItfd_response_2());
					ticketTO.setClient_response_2_enable(ticket.getClient_response_2_enable());
					ticketTO.setItfd_response_2(ticket.getItfd_response_2());
					displayable_client_response = "2";
				}
				if(ticket.getClient_response_3_enable()=='Y'){
					ticketTO.setItfd_response_3(ticket.getItfd_response_3());
					ticketTO.setClient_response_3_enable(ticket.getClient_response_3_enable());
					ticketTO.setItfd_response_3(ticket.getItfd_response_3());
					displayable_client_response = "3";
				}
				if(ticket.getClient_response_4_enable()=='Y'){
					ticketTO.setItfd_response_4(ticket.getItfd_response_4());
					ticketTO.setClient_response_4_enable(ticket.getClient_response_4_enable());
					ticketTO.setItfd_response_4(ticket.getItfd_response_4());
					displayable_client_response = "4";
				}
				if(ticket.getClient_response_5_enable()=='Y'){
					ticketTO.setItfd_response_5(ticket.getItfd_response_5());
					ticketTO.setClient_response_5_enable(ticket.getClient_response_5_enable());
					ticketTO.setItfd_response_5(ticket.getItfd_response_5());
					displayable_client_response = "5";
				}				
				ticketTO.setDisplayable_client_response(displayable_client_response);
			}
			
		} catch (Exception e) {
			logger.error("Error:" + e, e);
		}
		return ticketTO;
	}
	
	public Ticket getTicket(TicketTO ticketTO) {
		Ticket ticket = null;
		try {
			Timestamp timestamp = new Timestamp(new Date().getTime());
			ticket = new Ticket();
			ticket.setTimestamp(timestamp);
			ticket.setSubject(ticketTO.getSubject());
			ticket.setDate_opened(timestamp);
			ticket.setDate_updated(timestamp);
			ticket.setClient_code(ticketTO.getClient_code());
			ticket.setClient_name(ticketTO.getClient_name());
			ticket.setUsername(ticketTO.getUsername());
			ticket.setPriority(ticketTO.getPriority());
			//ticket.setStatus(ticketTO.getStatus());
			ticket.setContact_email(ticketTO.getContact_email());
			ticket.setComment(ticketTO.getComment());
			
			String phone = ticketTO.getContact_phone_1();		
			if(null!=phone && !"".equals(phone) && !" ".equals(phone)){
				phone = phone+"-";
			}
			phone = phone+ticketTO.getContact_phone_1();
			if(null!=phone && !"".equals(phone) && !" ".equals(phone)){
				phone = phone+"-";
			}
			phone = phone+ticketTO.getContact_phone_1();	
			ticket.setContact_phone(phone);	
			
		} catch (Exception e) {
			logger.error("Error:" + e, e);
		}
		return ticket;
	}
	
	public Ticket getUpdatedTicketWithFilePath(TicketTO ticketTO,Ticket ticket) {
		String path = "";
		int ticketNum = ticket.getId();
		if(null!=ticketTO.getFile_attachment_1() && !"".equals(ticketTO.getFile_attachment_1())){
			path = getSupportErrorFilePath(getSupportErrorFileName(ticketTO.getClient_code(),ticketTO.getFile_attachment_1(),ticketNum,1));
			ticket.setFile_attachment_1(path);
		}
		if(null!=ticketTO.getFile_attachment_2() && !"".equals(ticketTO.getFile_attachment_2())){
			path = getSupportErrorFilePath(getSupportErrorFileName(ticketTO.getClient_code(),ticketTO.getFile_attachment_2(),ticketNum,2));
			ticket.setFile_attachment_2(path);
		}
		if(null!=ticketTO.getFile_attachment_3() && !"".equals(ticketTO.getFile_attachment_3())){
			path = getSupportErrorFilePath(getSupportErrorFileName(ticketTO.getClient_code(),ticketTO.getFile_attachment_3(),ticketNum,3));
			ticket.setFile_attachment_3(path);
		}
		if(null!=ticketTO.getFile_attachment_4() && !"".equals(ticketTO.getFile_attachment_4())){
			path = getSupportErrorFilePath(getSupportErrorFileName(ticketTO.getClient_code(),ticketTO.getFile_attachment_4(),ticketNum,4));
			ticket.setFile_attachment_4(path);
		}
		if(null!=ticketTO.getFile_attachment_5() && !"".equals(ticketTO.getFile_attachment_5())){
			path = getSupportErrorFilePath(getSupportErrorFileName(ticketTO.getClient_code(),ticketTO.getFile_attachment_5(),ticketNum,5));
			ticket.setFile_attachment_5(path);
		}
		return ticket;		
	}
		
	public String getSupportErrorFilePath(String fileName) {
		String path = FileUtils.getSupportErrorFilePath();
		StringBuffer dirPath = new StringBuffer();
		
		dirPath.append(path);
		String filePath = dirPath.toString().replace('\\', '/');
		File dir = new File(filePath);

		if(!dir.exists()){
			dir.mkdirs();
		}
		filePath = filePath + fileName;

		return filePath;
	}
	
	public String getSupportErrorFileName(String clientCode,String fileName,int ticketNumber,int fileNum) {
		//CLIENTCODE_ticketNumber_fileNum.ext
		StringBuilder appendedFileName = new StringBuilder();
		String extension = ".jpeg";	
		if(null!=fileName && !"".equals(fileName)) {
			if(fileName.contains(".")){
				extension = fileName.substring(fileName.lastIndexOf('.'));
			}
		}
		appendedFileName.append(clientCode);
		appendedFileName.append("_");
		appendedFileName.append(ticketNumber);
		appendedFileName.append("_");
		appendedFileName.append(fileNum);
		appendedFileName.append(extension);
		return appendedFileName.toString();
	}
	
	public Ticket getUpdatedClientResponseTicket(TicketTO ticketTO,Ticket ticket) {
		ticket.setEscalate_request(ticketTO.getEscalate_request());
		String displayable_client_response = ticketTO.getDisplayable_client_response();
		
		if("1".equals(displayable_client_response)){
			ticket.setClient_response_1(ticketTO.getClient_response_1());
			ticket.setClient_response_1_enable('N');
		}
		if("2".equals(displayable_client_response)){
			ticket.setClient_response_2(ticketTO.getClient_response_2());
			ticket.setClient_response_2_enable('N');
		}
		if("3".equals(displayable_client_response)){
			ticket.setClient_response_3(ticketTO.getClient_response_3());
			ticket.setClient_response_3_enable('N');
		}
		if("4".equals(displayable_client_response)){
			ticket.setClient_response_4(ticketTO.getClient_response_4());
			ticket.setClient_response_4_enable('N');
		}
		if("5".equals(displayable_client_response)){
			ticket.setClient_response_5(ticketTO.getClient_response_5());
			ticket.setClient_response_5_enable('N');
		}
		return ticket;		
	}
	
	public String getClientResponse(Ticket ticket) {
		String client_response = "";
		
		if(null!=ticket.getClient_response_1() && !"".equals(ticket.getClient_response_1())){
			client_response = ticket.getClient_response_1();
		}
		if(null!=ticket.getClient_response_2() && !"".equals(ticket.getClient_response_2())){
			client_response = ticket.getClient_response_2();
		}
		if(null!=ticket.getClient_response_3() && !"".equals(ticket.getClient_response_3())){
			client_response = ticket.getClient_response_3();
		}
		if(null!=ticket.getClient_response_4() && !"".equals(ticket.getClient_response_4())){
			client_response = ticket.getClient_response_4();
		}
		if(null!=ticket.getClient_response_5() && !"".equals(ticket.getClient_response_5())){
			client_response = ticket.getClient_response_5();
		}
		return client_response;		
	}
	
	public String getItfdResponse(Ticket ticket) {
		String itfd_response = "";
		
		if(null!=ticket.getItfd_response_1() && !"".equals(ticket.getItfd_response_1())){
			itfd_response = ticket.getItfd_response_1();
		}
		if(null!=ticket.getItfd_response_2() && !"".equals(ticket.getItfd_response_2())){
			itfd_response = ticket.getItfd_response_2();
		}
		if(null!=ticket.getItfd_response_3() && !"".equals(ticket.getItfd_response_3())){
			itfd_response = ticket.getItfd_response_3();
		}
		if(null!=ticket.getItfd_response_4() && !"".equals(ticket.getItfd_response_4())){
			itfd_response = ticket.getItfd_response_4();
		}
		if(null!=ticket.getItfd_response_5() && !"".equals(ticket.getItfd_response_5())){
			itfd_response = ticket.getItfd_response_5();
		}
		return itfd_response;		
	}
}
