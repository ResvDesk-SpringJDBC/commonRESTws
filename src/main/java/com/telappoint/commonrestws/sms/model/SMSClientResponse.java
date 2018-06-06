package com.telappoint.commonrestws.sms.model;

public class SMSClientResponse {

	private String accountSid;
	private String fromPhone;
	private String toPhone;
	private String body;	
	private String smsSid;
	
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getFromPhone() {
		return fromPhone;
	}
	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}
	public String getToPhone() {
		return toPhone;
	}
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSmsSid() {
		return smsSid;
	}
	public void setSmsSid(String smsSid) {
		this.smsSid = smsSid;
	}
	
	
	
	
	
	
}
