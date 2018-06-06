package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_login_reset_request")
@org.hibernate.annotations.Proxy(lazy=false)
public class AdminLoginResetRequest implements Serializable {
	
	private static final long serialVersionUID = 5970753940653164653L;

	public AdminLoginResetRequest() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51BE05020")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51BE05020", strategy="identity")	
	private int id;
	
	@Column(name="login_id", nullable=false, length=11)	
	private int login_id;
	
	@Column(name="sent_timestamp", nullable=false)	
	private java.sql.Timestamp sent_timestamp;
	
	@Column(name="expire_timestamp", nullable=false)	
	private java.sql.Timestamp expire_timestamp;
	
	@Column(name="reset_password_token", nullable=true, length=50)	
	private String reset_password_token;
	
	@Column(name="temp_password", nullable=true, length=100)	
	private String temp_password;
	
	@Column(name="email_reset_url", nullable=true, length=2000)	
	private String email_reset_url;
	
	@Column(name="sms_reset_code", nullable=true, length=20)	
	private String sms_reset_code;
	
	@Column(name="sms_phone", nullable=true, length=20)	
	private String sms_phone;
	
	@Column(name="email", nullable=true, length=80)	
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLogin_id() {
		return login_id;
	}

	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}

	public java.sql.Timestamp getSent_timestamp() {
		return sent_timestamp;
	}

	public void setSent_timestamp(java.sql.Timestamp sent_timestamp) {
		this.sent_timestamp = sent_timestamp;
	}

	public java.sql.Timestamp getExpire_timestamp() {
		return expire_timestamp;
	}

	public void setExpire_timestamp(java.sql.Timestamp expire_timestamp) {
		this.expire_timestamp = expire_timestamp;
	}

	public String getTemp_password() {
		return temp_password;
	}

	public void setTemp_password(String temp_password) {
		this.temp_password = temp_password;
	}

	public String getEmail_reset_url() {
		return email_reset_url;
	}

	public void setEmail_reset_url(String email_reset_url) {
		this.email_reset_url = email_reset_url;
	}

	public String getSms_reset_code() {
		return sms_reset_code;
	}

	public void setSms_reset_code(String sms_reset_code) {
		this.sms_reset_code = sms_reset_code;
	}

	public String getSms_phone() {
		return sms_phone;
	}

	public void setSms_phone(String sms_phone) {
		this.sms_phone = sms_phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReset_password_token() {
		return reset_password_token;
	}

	public void setReset_password_token(String reset_password_token) {
		this.reset_password_token = reset_password_token;
	}
	
	
	
}
