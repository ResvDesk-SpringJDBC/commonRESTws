package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.telappoint.commonrestws.common.utils.DateUtils;

@Entity
@Table(name="admin_login_new")
@org.hibernate.annotations.Proxy(lazy=false)
public class AdminLoginNew implements Serializable {
	
	private static final long serialVersionUID = 5970753940653164653L;

	public AdminLoginNew() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51BE05020")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51BE05020", strategy="identity")	
	private int id;
	
	@Column(name="client_id", nullable=false)	
	@org.hibernate.annotations.Index(name="client_id")	
	private int client_id;
	
	@Column(name="username", nullable=false, length=30)	
	private String username;
	
	@Column(name="password", nullable=false, length=100)	
	private String password;
	
	@Column(name="first_name", nullable=false, length=30)	
	private String first_name;
	
	@Column(name="last_name", nullable=false, length=30)	
	private String last_name;
	
	@Column(name="contact_phone", nullable=true, length=15)	
	private String contact_phone;
	
	@Column(name="contact_email", nullable=false, length=80)	
	private String contact_email;
	
	@Column(name="location_ids", nullable=true, length=100)	
	private String location_ids;
	
	@Column(name="resource_ids", nullable=true, length=100)	
	private String resource_ids;
	
	@Column(name="start_date", nullable=false)	
	private java.sql.Timestamp start_date;
	
	@Column(name="expire_date", nullable=false)	
	private java.sql.Timestamp expire_date = DateUtils.getTimestampFromString("2020-01-01 10:10:10");
	
	@Column(name="suspend", nullable=false, length=1)	
	private char suspend='N';
	
	@Column(name="access_level", nullable=false, length=50)	
	private String access_level;
	
	@Column(name="password_last_update_date", nullable=true)	
	private java.util.Date password_last_update_date;
	
	/*@Column(name="password_expire_days", nullable=false)	
	private int password_expire_days = -1;
	
	@Column(name="password_expiry_warning_days", nullable=false)	
	private int password_expiry_warning_days = -1;*/
	
	@Column(name="password_reset_security_question_1", nullable=true, length=1000)	
	private String password_reset_security_question_1;
	
	@Column(name="password_reset_security_answer_1", nullable=true, length=1000)	
	private String password_reset_security_answer_1;
	
	@Column(name="password_reset_security_question_2", nullable=true, length=1000)	
	private String password_reset_security_question_2;
	
	@Column(name="password_reset_security_answer_2", nullable=true, length=1000)	
	private String password_reset_security_answer_2;
	
	@Column(name="password_reset_security_question_3", nullable=true, length=1000)	
	private String password_reset_security_question_3;
	
	@Column(name="password_reset_security_answer_3", nullable=true, length=1000)	
	private String password_reset_security_answer_3;
	
	@Column(name="password_reset_algorithm", nullable=true, length=100)	
	private String password_reset_algorithm;
	
	@Column(name="password_reset_sms_phone", nullable=true, length=10)	
	private String password_reset_sms_phone;
	
	/*@Column(name="password_complexity", nullable=true, length=1000)	
	private String password_complexity;
	
	@Column(name="user_restrict_ips", nullable=true, length=100)	
	private String user_restrict_ips;
	
	@Column(name="max_wrong_login_attempts", nullable=true)	
	private Integer max_wrong_login_attempts;*/
	
	@Column(name="wrong_login_max_attempt_locked", nullable=true, length=1)	
	private Character wrong_login_max_attempt_locked = new Character('N');
	
	/*@Column(name="max_wrong_login_lock_reset_mins", nullable=true)	
	private Integer max_wrong_login_lock_reset_mins;*/
	
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
	
	/**
	 * Admin Login Username
	 */
	public void setUsername(String value) {
		this.username = value;
	}
	
	/**
	 * Admin Login Username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Admin Login Password (encrypted)
	 */
	public void setPassword(String value) {
		this.password = value;
	}
	
	/**
	 * Admin Login Password (encrypted)
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * First Name
	 */
	public void setFirst_name(String value) {
		this.first_name = value;
	}
	
	/**
	 * First Name
	 */
	public String getFirst_name() {
		return first_name;
	}
	
	/**
	 * Last Name
	 */
	public void setLast_name(String value) {
		this.last_name = value;
	}
	
	/**
	 * Last Name
	 */
	public String getLast_name() {
		return last_name;
	}
	
	/**
	 * Contact Phone
	 */
	public void setContact_phone(String value) {
		this.contact_phone = value;
	}
	
	/**
	 * Contact Phone
	 */
	public String getContact_phone() {
		return contact_phone;
	}
	
	/**
	 * Contact Email
	 */
	public void setContact_email(String value) {
		this.contact_email = value;
	}
	
	/**
	 * Contact Email
	 */
	public String getContact_email() {
		return contact_email;
	}
	
	/**
	 * Location Privilege - Location ID's which are assigned to this user
	 */
	public void setLocation_ids(String value) {
		this.location_ids = value;
	}
	
	/**
	 * Location Privilege - Location ID's which are assigned to this user
	 */
	public String getLocation_ids() {
		return location_ids;
	}
	
	/**
	 * Resource Privilege - Resource ID's which are assigned to this user
	 */
	public void setResource_ids(String value) {
		this.resource_ids = value;
	}
	
	/**
	 * Resource Privilege - Resource ID's which are assigned to this user
	 */
	public String getResource_ids() {
		return resource_ids;
	}
	
	/**
	 * User Start Date
	 */
	public void setStart_date(java.sql.Timestamp value) {
		this.start_date = value;
	}
	
	/**
	 * User Start Date
	 */
	public java.sql.Timestamp getStart_date() {
		return start_date;
	}
	
	/**
	 * User Login Expiry Date
	 */
	public void setExpire_date(java.sql.Timestamp value) {
		this.expire_date = value;
	}
	
	/**
	 * User Login Expiry Date
	 */
	public java.sql.Timestamp getExpire_date() {
		return expire_date;
	}
	
	/**
	 * Is this user suspended? values: Y or N
	 */
	public void setSuspend(char value) {
		this.suspend = value;
	}
	
	/**
	 * Is this user suspended? values: Y or N
	 */
	public char getSuspend() {
		return suspend;
	}
	
	/**
	 * Access Level for this user. example:SUPER,ADMIN,MANAGER,LOCATION,RESOURCE,SCHEDULER,SCHEDULER_LOCATION,SCHEDULER_RESOURCE,READONLY
	 */
	public void setAccess_level(String value) {
		this.access_level = value;
	}
	
	/**
	 * Access Level for this user. example:SUPER,ADMIN,MANAGER,LOCATION,RESOURCE,SCHEDULER,SCHEDULER_LOCATION,SCHEDULER_RESOURCE,READONLY
	 */
	public String getAccess_level() {
		return access_level;
	}
	
	public void setPassword_last_update_date(java.util.Date value) {
		this.password_last_update_date = value;
	}
	
	public java.util.Date getPassword_last_update_date() {
		return password_last_update_date;
	}
	
	public void setPassword_reset_security_question_1(String value) {
		this.password_reset_security_question_1 = value;
	}
	
	public String getPassword_reset_security_question_1() {
		return password_reset_security_question_1;
	}
	
	public void setPassword_reset_security_answer_1(String value) {
		this.password_reset_security_answer_1 = value;
	}
	
	public String getPassword_reset_security_answer_1() {
		return password_reset_security_answer_1;
	}
	
	public void setPassword_reset_security_question_2(String value) {
		this.password_reset_security_question_2 = value;
	}
	
	public String getPassword_reset_security_question_2() {
		return password_reset_security_question_2;
	}
	
	public void setPassword_reset_security_answer_2(String value) {
		this.password_reset_security_answer_2 = value;
	}
	
	public String getPassword_reset_security_answer_2() {
		return password_reset_security_answer_2;
	}
	
	public void setPassword_reset_security_question_3(String value) {
		this.password_reset_security_question_3 = value;
	}
	
	public String getPassword_reset_security_question_3() {
		return password_reset_security_question_3;
	}
	
	public void setPassword_reset_security_answer_3(String value) {
		this.password_reset_security_answer_3 = value;
	}
	
	public String getPassword_reset_security_answer_3() {
		return password_reset_security_answer_3;
	}
	
	public void setPassword_reset_algorithm(String value) {
		this.password_reset_algorithm = value;
	}
	
	public String getPassword_reset_algorithm() {
		return password_reset_algorithm;
	}
	
	public void setPassword_reset_sms_phone(String value) {
		this.password_reset_sms_phone = value;
	}
	
	public String getPassword_reset_sms_phone() {
		return password_reset_sms_phone;
	}
	
	public void setWrong_login_max_attempt_locked(char value) {
		setWrong_login_max_attempt_locked(new Character(value));
	}
	
	public void setWrong_login_max_attempt_locked(Character value) {
		this.wrong_login_max_attempt_locked = value;
	}
	
	public Character getWrong_login_max_attempt_locked() {
		return wrong_login_max_attempt_locked;
	}
	
	public void setMax_wrong_login_lock_reset_mins(int value) {
		setMax_wrong_login_lock_reset_mins(new Integer(value));
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
			sb.append("Admin_login_new[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Client_id=").append(getClient_id()).append(" ");
			sb.append("Username=").append(getUsername()).append(" ");
			sb.append("Password=").append(getPassword()).append(" ");
			sb.append("First_name=").append(getFirst_name()).append(" ");
			sb.append("Last_name=").append(getLast_name()).append(" ");
			sb.append("Contact_phone=").append(getContact_phone()).append(" ");
			sb.append("Contact_email=").append(getContact_email()).append(" ");
			sb.append("Location_ids=").append(getLocation_ids()).append(" ");
			sb.append("Resource_ids=").append(getResource_ids()).append(" ");
			sb.append("Start_date=").append(getStart_date()).append(" ");
			sb.append("Expire_date=").append(getExpire_date()).append(" ");
			sb.append("Suspend=").append(getSuspend()).append(" ");
			sb.append("Access_level=").append(getAccess_level()).append(" ");
			sb.append("Password_last_update_date=").append(getPassword_last_update_date()).append(" ");
			//sb.append("Password_expire_days=").append(getPassword_expire_days()).append(" ");
			//sb.append("Password_expiry_warning_days=").append(getPassword_expiry_warning_days()).append(" ");
			sb.append("Password_reset_security_question_1=").append(getPassword_reset_security_question_1()).append(" ");
			sb.append("Password_reset_security_answer_1=").append(getPassword_reset_security_answer_1()).append(" ");
			sb.append("Password_reset_security_question_2=").append(getPassword_reset_security_question_2()).append(" ");
			sb.append("Password_reset_security_answer_2=").append(getPassword_reset_security_answer_2()).append(" ");
			sb.append("Password_reset_security_question_3=").append(getPassword_reset_security_question_3()).append(" ");
			sb.append("Password_reset_security_answer_3=").append(getPassword_reset_security_answer_3()).append(" ");
			sb.append("Password_reset_algorithm=").append(getPassword_reset_algorithm()).append(" ");
			sb.append("Password_reset_sms_phone=").append(getPassword_reset_sms_phone()).append(" ");
			//sb.append("Password_complexity=").append(getPassword_complexity()).append(" ");
			//sb.append("User_restrict_ips=").append(getUser_restrict_ips()).append(" ");
			//sb.append("Max_wrong_login_attempts=").append(getMax_wrong_login_attempts()).append(" ");
			sb.append("Wrong_login_max_attempt_locked=").append(getWrong_login_max_attempt_locked()).append(" ");
			//sb.append("Max_wrong_login_lock_reset_mins=").append(getMax_wrong_login_lock_reset_mins()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}
