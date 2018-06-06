package com.telappoint.commonrestws.support.utils;

public class EmailDetailsTO {
	
	private String firstName;
	private String lastName;
	private String userName;
	
	private String fromMailId;
	private String toMailId;
	private String replytoMailId;
	private String subject;
	private String mailBody;
	private String clientCode;
	private String ticketId;
	private String ccMailId;
	
	public String getCcMailId() {
		return ccMailId;
	}
	public void setCcMailId(String ccMailId) {
		this.ccMailId = ccMailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	public String getFromMailId() {
		return fromMailId;
	}
	public void setFromMailId(String fromMailId) {
		this.fromMailId = fromMailId;
	}
	public String getToMailId() {
		return toMailId;
	}
	public void setToMailId(String toMailId) {
		this.toMailId = toMailId;
	}
	public String getReplytoMailId() {
		return replytoMailId;
	}
	public void setReplytoMailId(String replytoMailId) {
		this.replytoMailId = replytoMailId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}		
}
