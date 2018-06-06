package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="sla")
@org.hibernate.annotations.Proxy(lazy=false)
public class Sla implements Serializable {
	
	private static final long serialVersionUID = 2348836336997852537L;

	public Sla() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51DD05027")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51DD05027", strategy="identity")	
	private int id;
	
	@Column(name="client_id", nullable=false)	
	@org.hibernate.annotations.Index(name="client_id")	
	private int client_id;
	
	@Column(name="skip_sla", nullable=false, length=1)	
	private char skip_sla = 'N';
	
	@Column(name="display_sla", nullable=false, length=1)	
	private char display_sla = 'N';
	
	@Column(name="sla_text", nullable=true)	
	private String sla_text;
	
	@Column(name="create_date", nullable=false)	
	private java.sql.Timestamp create_date;
	
	@Column(name="delete_flag", nullable=false, length=1)	
	private char delete_flag = 'N';
	
	@Column(name="user_agree", nullable=false, length=1)	
	private char user_agree = 'N';
	
	@Column(name="user_id", nullable=true)	
	@org.hibernate.annotations.Index(name="user_id")	
	private Integer user_id;
	
	@Column(name="user_initials", nullable=true, length=10)	
	private String user_initials;
	
	@Column(name="ip_address", nullable=true, length=50)	
	private String ip_address;
	
	@Column(name="accept_date", nullable=false)	
	private java.sql.Timestamp accept_date;
	
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
	 * Client ID
	 */
	public void setClient_id(int value) {
		this.client_id = value;
	}
	
	/**
	 * Client ID
	 */
	public int getClient_id() {
		return client_id;
	}
	
	public void setDisplay_sla(char value) {
		this.display_sla = value;
	}
	
	public char getDisplay_sla() {
		return display_sla;
	}
	
	public void setSla_text(String value) {
		this.sla_text = value;
	}
	
	public String getSla_text() {
		return sla_text;
	}
	
	public void setCreate_date(java.sql.Timestamp value) {
		this.create_date = value;
	}
	
	public java.sql.Timestamp getCreate_date() {
		return create_date;
	}
	
	public void setDelete_flag(char value) {
		this.delete_flag = value;
	}
	
	public char getDelete_flag() {
		return delete_flag;
	}
	
	public void setUser_agree(char value) {
		this.user_agree = value;
	}
	
	public char getUser_agree() {
		return user_agree;
	}
	
	public void setUser_id(int value) {
		setUser_id(new Integer(value));
	}
	
	public void setUser_id(Integer value) {
		this.user_id = value;
	}
	
	public Integer getUser_id() {
		return user_id;
	}
	
	public void setUser_initials(String value) {
		this.user_initials = value;
	}
	
	public String getUser_initials() {
		return user_initials;
	}
	
	public void setIp_address(String value) {
		this.ip_address = value;
	}
	
	public String getIp_address() {
		return ip_address;
	}
	
	public void setAccept_date(java.sql.Timestamp value) {
		this.accept_date = value;
	}
	
	public java.sql.Timestamp getAccept_date() {
		return accept_date;
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
			sb.append("Sla[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Client_id=").append(getClient_id()).append(" ");
			sb.append("Display_sla=").append(getDisplay_sla()).append(" ");
			sb.append("Sla_text=").append(getSla_text()).append(" ");
			sb.append("Create_date=").append(getCreate_date()).append(" ");
			sb.append("Delete_flag=").append(getDelete_flag()).append(" ");
			sb.append("User_agree=").append(getUser_agree()).append(" ");
			sb.append("User_id=").append(getUser_id()).append(" ");
			sb.append("User_initials=").append(getUser_initials()).append(" ");
			sb.append("Ip_address=").append(getIp_address()).append(" ");
			sb.append("Accept_date=").append(getAccept_date()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

	public char getSkip_sla() {
		return skip_sla;
	}

	public void setSkip_sla(char skip_sla) {
		this.skip_sla = skip_sla;
	}
	
}
