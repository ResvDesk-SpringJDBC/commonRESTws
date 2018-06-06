package com.telappoint.commonrestws.support.constants;


/**
 * 
 * @author Murali
 * 
 */

public enum SupportStatusConstants {
	
	/*
	 * 0=Pending, 
		1=In Progress, 
		3=Need More Info, 
		4=Waiting Info from Client, 
		5=Deferred, 
		6=On Hold, 
		7=Will be fixed in new release, 
		8=Resubmit, 
		9=Escalation, 
		15=Fixed
		19=Delete
		20=Complete
	 */
	SUPPORT_TICKET_STATUS_PENDING(0,"Pending"),
	SUPPORT_TICKET_STATUS_IN_PROGRESS(1,"In Progress"),
	SUPPORT_TICKET_STATUS_NEED_MORE_INFO(3,"Need More Info"),
	SUPPORT_TICKET_STATUS_WAITING_FOR_INFO_FROM_CLIENT(4,"Waiting Info from Client"),
	SUPPORT_TICKET_STATUS_DEFERRED(5,"Deferred"),
	SUPPORT_TICKET_STATUS_ON_HOLD(6,"On Hold"),
	SUPPORT_TICKET_STATUS_WILL_BE_FIXED_IN_NEW_RELEASE(7,"Will be fixed in new release"),
	SUPPORT_TICKET_STATUS_RESUBMIT(8,"Resubmit"),
	SUPPORT_TICKET_STATUS_ESCALATION(9,"Escalation"),
	SUPPORT_TICKET_STATUS_FIXED(15,"Fixed"),
	SUPPORT_TICKET_STATUS_DELETE(19,"Delete"),
	SUPPORT_TICKET_STATUS_COMPLETE(20,"Complete");
	
	private int ticketStatus;
	private String status;

	private SupportStatusConstants(int ticketStatus,String status) {
		this.ticketStatus = ticketStatus;
		this.status = status;
	}

	public int getTicketStatus() {
		return ticketStatus;
	}

	public String getStatus() {
		return status;
	}
	
	public static class SupportStatusConstantsMessage {
		public static String getStatus(int ticketStatus) {
			SupportStatusConstants[] keys = SupportStatusConstants.values();
			for (SupportStatusConstants key : keys) {
				if (key.getTicketStatus() == ticketStatus) {
					return key.getStatus();
				}
			}
			return "";
		}
	}
	
	public static void main(String[] args) {
		String result = SupportStatusConstantsMessage.getStatus(1);
		System.out.println(result);
	}
	
}
