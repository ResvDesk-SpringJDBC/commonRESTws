package com.telappoint.commonrestws.support.constants;

/**
 * 
 * @author Murali
 * 
 */

public enum SupportPriorityConstants {
	
	SUPPORT_TICKET_PRIOROTY_LOW(1,"Low"),
	SUPPORT_TICKET_PRIOROTY_MEDIUM(5,"Medium"),
	SUPPORT_TICKET_PRIOROTY_HIGH(10,"High"),
	SUPPORT_TICKET_PRIOROTY_CRITICAL(15,"Critical");
	
	private int ticketPriority;
	private String priority;

	private SupportPriorityConstants(int ticketPriority,String priority) {
		this.ticketPriority = ticketPriority;
		this.priority = priority;
	}

	public int getTicketPriority() {
		return ticketPriority;
	}

	public String getPriority() {
		return priority;
	}
	
	public static class SupportPriorityConstantsMessage {
		public static String getPriority(int ticketStatus) {
			SupportPriorityConstants[] keys = SupportPriorityConstants.values();
			for (SupportPriorityConstants key : keys) {
				if (key.getTicketPriority() == ticketStatus) {
					return key.getPriority();
				}
			}
			return "";
		}
	}
	
	public static void main(String[] args) {
		String result = SupportPriorityConstantsMessage.getPriority(1);
		System.out.println(result);
	}
	
}
