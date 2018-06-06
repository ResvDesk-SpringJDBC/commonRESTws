package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="request_log")
@org.hibernate.annotations.Proxy(lazy=false)
public class RequestLog implements Serializable {
	
	private static final long serialVersionUID = 533994355945381568L;

	public RequestLog() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51CE05026")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51CE05026", strategy="identity")	
	private int id;
	
	@Column(name="timestamp", nullable=false)	
	private java.sql.Timestamp timestamp;
	
	@Column(name="device", nullable=true, length=20)	
	private String device;
	
	@Column(name="uuid", nullable=true, length=20)	
	private String uuid;
	
	@Column(name="mobile_search_term", nullable=true, length=100)	
	private String mobile_search_term;
	
	@Column(name="called_phone_number", nullable=true, length=20)	
	private String called_phone_number;
	
	@Column(name="caller_id", nullable=true, length=20)	
	private String caller_id;
	
	@Column(name="ipaddress", nullable=true, length=100)	
	private String ipaddress;
	
	@Column(name="admin_username", nullable=true, length=20)	
	private String admin_username;
	
	@Column(name="admin_password", nullable=true, length=100)	
	private String admin_password;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	/**
	 * Timestamp of when the record was created
	 */
	public void setTimestamp(java.sql.Timestamp value) {
		this.timestamp = value;
	}
	
	/**
	 * Timestamp of when the record was created
	 */
	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}
	
	/**
	 * device type. values: online, phone, sms, iphone, android, ipad
	 */
	public void setDevice(String value) {
		this.device = value;
	}
	
	/**
	 * device type. values: online, phone, sms, iphone, android, ipad
	 */
	public String getDevice() {
		return device;
	}
	
	/**
	 * UUID of iPhone
	 */
	public void setUuid(String value) {
		this.uuid = value;
	}
	
	/**
	 * UUID of iPhone
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * Search term used by mobile users
	 */
	public void setMobile_search_term(String value) {
		this.mobile_search_term = value;
	}
	
	/**
	 * Search term used by mobile users
	 */
	public String getMobile_search_term() {
		return mobile_search_term;
	}
	
	/**
	 * Dialed phone number
	 */
	public void setCalled_phone_number(String value) {
		this.called_phone_number = value;
	}
	
	/**
	 * Dialed phone number
	 */
	public String getCalled_phone_number() {
		return called_phone_number;
	}
	
	/**
	 * Caller ID of the user
	 */
	public void setCaller_id(String value) {
		this.caller_id = value;
	}
	
	/**
	 * Caller ID of the user
	 */
	public String getCaller_id() {
		return caller_id;
	}
	
	/**
	 * IP Address of the user accessing our scheduler
	 */
	public void setIpaddress(String value) {
		this.ipaddress = value;
	}
	
	/**
	 * IP Address of the user accessing our scheduler
	 */
	public String getIpaddress() {
		return ipaddress;
	}
	
	/**
	 * Admin User Name tried by the user
	 */
	public void setAdmin_username(String value) {
		this.admin_username = value;
	}
	
	/**
	 * Admin User Name tried by the user
	 */
	public String getAdmin_username() {
		return admin_username;
	}
	
	/**
	 * Admin Password tried by the user
	 */
	public void setAdmin_password(String value) {
		this.admin_password = value;
	}
	
	/**
	 * Admin Password tried by the user
	 */
	public String getAdmin_password() {
		return admin_password;
	}
	
	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getId());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("Request_log[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Timestamp=").append(getTimestamp()).append(" ");
			sb.append("Device=").append(getDevice()).append(" ");
			sb.append("Uuid=").append(getUuid()).append(" ");
			sb.append("Mobile_search_term=").append(getMobile_search_term()).append(" ");
			sb.append("Called_phone_number=").append(getCalled_phone_number()).append(" ");
			sb.append("Caller_id=").append(getCaller_id()).append(" ");
			sb.append("Ipaddress=").append(getIpaddress()).append(" ");
			sb.append("Admin_username=").append(getAdmin_username()).append(" ");
			sb.append("Admin_password=").append(getAdmin_password()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}
