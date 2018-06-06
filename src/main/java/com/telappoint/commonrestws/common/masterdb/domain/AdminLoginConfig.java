package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_login_config")
@org.hibernate.annotations.Proxy(lazy=false)
public class AdminLoginConfig implements Serializable {
	
	private static final long serialVersionUID = 5970753940653164653L;

	public AdminLoginConfig() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51BE05020")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51BE05020", strategy="identity")	
	private int id;
	
	@Column(name="client_id", nullable=false)	
	@org.hibernate.annotations.Index(name="client_id")	
	private int client_id;
	
	@Column(name="password_expire_days", nullable=false, length=10)	
	private int password_expire_days=-1;
	
	@Column(name="password_expiry_warning_days", nullable=false, length=10)	
	private int password_expiry_warning_days=-1;
	
	@Column(name="password_reset_algorithm", nullable=true, length=100)	
	private String password_reset_algorithm;
	
	@Column(name="password_reset_method", nullable=false, length=100)	
	private String password_reset_method="SMS";
	
	@Column(name="password_reset_temp_storage_hours", nullable=false, length=10)	
	private int password_reset_temp_storage_hours=1;
	
	@Column(name="password_complexity", nullable=false, length=1000)	
	private String password_complexity="6min,10max,1upper,1lower,1digit,1letter,1splchar,no_username";
	
	@Column(name="user_restrict_ips", nullable=true, length=100)	
	private String user_restrict_ips;
	
	@Column(name="max_wrong_login_attempts", nullable=true, length=10)	
	private int max_wrong_login_attempts;
	
	/*@Column(name="wrong_login_max_attempt_locked", nullable=false, length=1000)	
	private char wrong_login_max_attempt_locked='N';
	*/
	@Column(name="max_wrong_login_lock_reset_mins", nullable=true, length=10)	
	private int max_wrong_login_lock_reset_mins;

	@Column(name="placeholder1", nullable=true, length=100)	
	private String placeholder1;
	
	@Column(name="placeholder2", nullable=true, length=100)	
	private String placeholder2;
	
	@Column(name="placeholder3", nullable=true, length=100)	
	private String placeholder3;
	
	@Column(name="placeholder4", nullable=true, length=100)	
	private String placeholder4;
	
	@Column(name="placeholder5", nullable=true, length=100)	
	private String placeholder5;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getPassword_expire_days() {
		return password_expire_days;
	}

	public void setPassword_expire_days(int password_expire_days) {
		this.password_expire_days = password_expire_days;
	}

	public int getPassword_expiry_warning_days() {
		return password_expiry_warning_days;
	}

	public void setPassword_expiry_warning_days(int password_expiry_warning_days) {
		this.password_expiry_warning_days = password_expiry_warning_days;
	}

	public String getPassword_reset_algorithm() {
		return password_reset_algorithm;
	}

	public void setPassword_reset_algorithm(String password_reset_algorithm) {
		this.password_reset_algorithm = password_reset_algorithm;
	}

	public String getPassword_reset_method() {
		return password_reset_method;
	}

	public void setPassword_reset_method(String password_reset_method) {
		this.password_reset_method = password_reset_method;
	}

	public int getPassword_reset_temp_storage_hours() {
		return password_reset_temp_storage_hours;
	}

	public void setPassword_reset_temp_storage_hours(
			int password_reset_temp_storage_hours) {
		this.password_reset_temp_storage_hours = password_reset_temp_storage_hours;
	}

	public String getPassword_complexity() {
		return password_complexity;
	}

	public void setPassword_complexity(String password_complexity) {
		this.password_complexity = password_complexity;
	}

	public int getMax_wrong_login_lock_reset_mins() {
		return max_wrong_login_lock_reset_mins;
	}

	public void setMax_wrong_login_lock_reset_mins(
			int max_wrong_login_lock_reset_mins) {
		this.max_wrong_login_lock_reset_mins = max_wrong_login_lock_reset_mins;
	}

	public String getUser_restrict_ips() {
		return user_restrict_ips;
	}

	public void setUser_restrict_ips(String user_restrict_ips) {
		this.user_restrict_ips = user_restrict_ips;
	}

	public int getMax_wrong_login_attempts() {
		return max_wrong_login_attempts;
	}

	public void setMax_wrong_login_attempts(int max_wrong_login_attempts) {
		this.max_wrong_login_attempts = max_wrong_login_attempts;
	}

	public String getPlaceholder1() {
		return placeholder1;
	}

	public void setPlaceholder1(String placeholder1) {
		this.placeholder1 = placeholder1;
	}

	public String getPlaceholder2() {
		return placeholder2;
	}

	public void setPlaceholder2(String placeholder2) {
		this.placeholder2 = placeholder2;
	}

	public String getPlaceholder3() {
		return placeholder3;
	}

	public void setPlaceholder3(String placeholder3) {
		this.placeholder3 = placeholder3;
	}

	public String getPlaceholder4() {
		return placeholder4;
	}

	public void setPlaceholder4(String placeholder4) {
		this.placeholder4 = placeholder4;
	}

	public String getPlaceholder5() {
		return placeholder5;
	}

	public void setPlaceholder5(String placeholder5) {
		this.placeholder5 = placeholder5;
	}
	
		
}
