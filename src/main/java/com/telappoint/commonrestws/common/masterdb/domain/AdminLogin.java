package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="admin_login")
@org.hibernate.annotations.Proxy(lazy=false)
public class AdminLogin implements Serializable {
	
	private static final long serialVersionUID = -7508898773574497307L;

	public AdminLogin() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51AE0501F")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51AE0501F", strategy="identity")	
	private int id;
	
	@Column(name="client_id", nullable=false)	
	@org.hibernate.annotations.Index(name="FK_admin_login_client_id")	
	private int client_id;
	
	@Column(name="username", nullable=false, length=30)	
	private String username;
	
	@Column(name="password", nullable=false, length=100)	
	private String password;
	
	@Column(name="master_password", nullable=false, length=100)	
	private String master_password;
	
	@Column(name="first_name", nullable=false, length=30)	
	private String first_name;
	
	@Column(name="last_name", nullable=false, length=30)	
	private String last_name;
	
	@Column(name="contact_phone", nullable=true, length=15)	
	private String contact_phone;
	
	@Column(name="contact_email", nullable=true, length=80)	
	private String contact_email;
	
	@Column(name="location_ids", nullable=false, length=100)	
	private String location_ids;
	
	@Column(name="resource_ids", nullable=false, length=100)	
	private String resource_ids;
	
	@Column(name="start_date", nullable=false)	
	private java.sql.Timestamp start_date;
	
	@Column(name="expire_date", nullable=false)	
	private java.sql.Timestamp expire_date;
	
	@Column(name="locked", nullable=false, length=1)	
	private char locked;
	
	@Column(name="access_level", nullable=false, length=30)	
	private String access_level;
	
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
	 * Admin Login Master Password (encrypted)
	 */
	public void setMaster_password(String value) {
		this.master_password = value;
	}
	
	/**
	 * Admin Login Master Password (encrypted)
	 */
	public String getMaster_password() {
		return master_password;
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
	 * Is this user locked? values: Y or N
	 */
	public void setLocked(char value) {
		this.locked = value;
	}
	
	/**
	 * Is this user locked? values: Y or N
	 */
	public char getLocked() {
		return locked;
	}
	
	/**
	 * Access Level for this user. values: 9 = super user, 1 = read only appt calendar, 2 = etc
	 */
	public void setAccess_level(String value) {
		this.access_level = value;
	}
	
	/**
	 * Access Level for this user. values: 9 = super user, 1 = read only appt calendar, 2 = etc
	 */
	public String getAccess_level() {
		return access_level;
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
			sb.append("Admin_login[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Client_id=").append(getClient_id()).append(" ");
			sb.append("Username=").append(getUsername()).append(" ");
			sb.append("Password=").append(getPassword()).append(" ");
			sb.append("Master_password=").append(getMaster_password()).append(" ");
			sb.append("First_name=").append(getFirst_name()).append(" ");
			sb.append("Last_name=").append(getLast_name()).append(" ");
			sb.append("Contact_phone=").append(getContact_phone()).append(" ");
			sb.append("Contact_email=").append(getContact_email()).append(" ");
			sb.append("Location_ids=").append(getLocation_ids()).append(" ");
			sb.append("Resource_ids=").append(getResource_ids()).append(" ");
			sb.append("Start_date=").append(getStart_date()).append(" ");
			sb.append("Expire_date=").append(getExpire_date()).append(" ");
			sb.append("Locked=").append(getLocked()).append(" ");
			sb.append("Access_level=").append(getAccess_level()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}
