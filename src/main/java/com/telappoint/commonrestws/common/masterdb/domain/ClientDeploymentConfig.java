package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="client_deployment_config")
@org.hibernate.annotations.Proxy(lazy=false)
public class ClientDeploymentConfig implements Serializable {
	
	private static final long serialVersionUID = -7930089076876698761L;

	public ClientDeploymentConfig() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51CE05023")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51CE05023", strategy="identity")	
	private int id;
	
	@ManyToOne(cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })	
	@JoinColumns({ @JoinColumn(name="client_id", referencedColumnName="id") })	
	@Basic(fetch=FetchType.LAZY)	
	private Client client;
	
	
	@Column(name="block_time_in_mins", nullable=false)	
	private int block_time_in_mins;
	
	@Column(name="resource_calendar_months", nullable=false, length=3)	
	private int resource_calendar_months;
	
	@Column(name="user_expiry_date_time", nullable=false)	
	private java.sql.Timestamp user_expiry_date_time;
	
	@Column(name="day_start_time", nullable=false)	
	private java.sql.Time day_start_time;
	
	@Column(name="day_end_time", nullable=false)	
	private java.sql.Time day_end_time;
	
	@Column(name="integrated_reminddesk", nullable=true, length=1)	
	private Character integrated_reminddesk;
	
	@Column(name="server_flag", nullable=true, length=1)	
	private Character server_flag;
	
	@Column(name="auto_file_upload", nullable=true, length=1)	
	private Character auto_file_upload;
	
	@Column(name="same_multiloc_resource", nullable=false, length=1)	
	private char same_multiloc_resource;
	
	@Column(name="time_zone", nullable=false, length=100)	
	private String time_zone;
	
	@Column(name="date_format", nullable=true, length=30)	
	private String date_format;
	
	@Column(name="date_yyyy_format", nullable=true, length=30)	
	private String date_yyyy_format;
	
	@Column(name="full_date_format", nullable=true, length=30)	
	private String full_date_format;
	
	@Column(name="full_datetime_format", nullable=true, length=30)	
	private String full_datetime_format;
	
	@Column(name="popup_calendardate_format", nullable=true, length=30)	
	private String popup_calendardate_format;
	
	@Column(name="time_format", nullable=true, length=30)	
	private String time_format;
	
	@Column(name="time_withsec_format", nullable=true, length=30)	
	private String time_withsec_format;
	
	@Column(name="phone_format", nullable=true, length=10)	
	private String phone_format;
	
	@Column(name="country_code", nullable=true, length=10)	
	private String country_code;
	
	@Column(name="full_textualday_format", nullable=true, length=60)	
	private String full_textualday_format;
	
	@Column(name="call_center_logic", nullable=true, length=60)	
	private String call_center_logic;
	
	@Column(name="notify_phone_lead_time", nullable=true)	
	private int notify_phone_lead_time;
	
	@Column(name="notify_phone_lag_time", nullable=true)	
	private int notify_phone_lag_time;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * Minimum Block Time for calendar display - may be obsolete
	 */
	public void setBlock_time_in_mins(int value) {
		this.block_time_in_mins = value;
	}
	
	/**
	 * Minimum Block Time for calendar display - may be obsolete
	 */
	public int getBlock_time_in_mins() {
		return block_time_in_mins;
	}
	
	/**
	 * How many months calendar is required?
	 */
	public void setResource_calendar_months(int value) {
		this.resource_calendar_months = value;
	}
	
	/**
	 * How many months calendar is required?
	 */
	public int getResource_calendar_months() {
		return resource_calendar_months;
	}
	
	/**
	 * Account Expiry Date
	 */
	public void setUser_expiry_date_time(java.sql.Timestamp value) {
		this.user_expiry_date_time = value;
	}
	
	/**
	 * Account Expiry Date
	 */
	public java.sql.Timestamp getUser_expiry_date_time() {
		return user_expiry_date_time;
	}
	
	/**
	 * Day hour start time for calendar display - may be obsolete
	 */
	public void setDay_start_time(java.sql.Time value) {
		this.day_start_time = value;
	}
	
	/**
	 * Day hour start time for calendar display - may be obsolete
	 */
	public java.sql.Time getDay_start_time() {
		return day_start_time;
	}
	
	/**
	 * Day hour end time for calendar display - may be obsolete
	 */
	public void setDay_end_time(java.sql.Time value) {
		this.day_end_time = value;
	}
	
	/**
	 * Day hour end time for calendar display - may be obsolete
	 */
	public java.sql.Time getDay_end_time() {
		return day_end_time;
	}
	
	/**
	 * Integrated ReminderDesl? values: Y or N
	 */
	public void setIntegrated_reminddesk(char value) {
		setIntegrated_reminddesk(new Character(value));
	}
	
	/**
	 * Integrated ReminderDesl? values: Y or N
	 */
	public void setIntegrated_reminddesk(Character value) {
		this.integrated_reminddesk = value;
	}
	
	/**
	 * Integrated ReminderDesl? values: Y or N
	 */
	public Character getIntegrated_reminddesk() {
		return integrated_reminddesk;
	}
	
	/**
	 * Is it Server? values: Y or N
	 */
	public void setServer_flag(char value) {
		setServer_flag(new Character(value));
	}
	
	/**
	 * Is it Server? values: Y or N
	 */
	public void setServer_flag(Character value) {
		this.server_flag = value;
	}
	
	/**
	 * Is it Server? values: Y or N
	 */
	public Character getServer_flag() {
		return server_flag;
	}
	
	/**
	 * Auto File Upload Feature Enabled?  - may be obsolete
	 */
	public void setAuto_file_upload(char value) {
		setAuto_file_upload(new Character(value));
	}
	
	/**
	 * Auto File Upload Feature Enabled?  - may be obsolete
	 */
	public void setAuto_file_upload(Character value) {
		this.auto_file_upload = value;
	}
	
	/**
	 * Auto File Upload Feature Enabled?  - may be obsolete
	 */
	public Character getAuto_file_upload() {
		return auto_file_upload;
	}
	
	/**
	 * Same Resource works in multiple Locations? values: Y or N - may be obsolete
	 */
	public void setSame_multiloc_resource(char value) {
		this.same_multiloc_resource = value;
	}
	
	/**
	 * Same Resource works in multiple Locations? values: Y or N - may be obsolete
	 */
	public char getSame_multiloc_resource() {
		return same_multiloc_resource;
	}
	
	/**
	 * Time Zone in Java format
	 */
	public void setTime_zone(String value) {
		this.time_zone = value;
	}
	
	/**
	 * Time Zone in Java format
	 */
	public String getTime_zone() {
		return time_zone;
	}
	
	/**
	 * Date Format
	 */
	public void setDate_format(String value) {
		this.date_format = value;
	}
	
	/**
	 * Date Format
	 */
	public String getDate_format() {
		return date_format;
	}
	
	/**
	 * Date Year Format
	 */
	public void setDate_yyyy_format(String value) {
		this.date_yyyy_format = value;
	}
	
	/**
	 * Date Year Format
	 */
	public String getDate_yyyy_format() {
		return date_yyyy_format;
	}
	
	/**
	 * Full Date Format
	 */
	public void setFull_date_format(String value) {
		this.full_date_format = value;
	}
	
	/**
	 * Full Date Format
	 */
	public String getFull_date_format() {
		return full_date_format;
	}
	
	/**
	 * Full Date Time Format
	 */
	public void setFull_datetime_format(String value) {
		this.full_datetime_format = value;
	}
	
	/**
	 * Full Date Time Format
	 */
	public String getFull_datetime_format() {
		return full_datetime_format;
	}
	
	/**
	 * Format for Pop-up Calendar
	 */
	public void setPopup_calendardate_format(String value) {
		this.popup_calendardate_format = value;
	}
	
	/**
	 * Format for Pop-up Calendar
	 */
	public String getPopup_calendardate_format() {
		return popup_calendardate_format;
	}
	
	/**
	 * Time Format
	 */
	public void setTime_format(String value) {
		this.time_format = value;
	}
	
	/**
	 * Time Format
	 */
	public String getTime_format() {
		return time_format;
	}
	
	/**
	 * Time with Seconds format
	 */
	public void setTime_withsec_format(String value) {
		this.time_withsec_format = value;
	}
	
	/**
	 * Time with Seconds format
	 */
	public String getTime_withsec_format() {
		return time_withsec_format;
	}
	
	/**
	 * Phone Format
	 */
	public void setPhone_format(String value) {
		this.phone_format = value;
	}
	
	/**
	 * Phone Format
	 */
	public String getPhone_format() {
		return phone_format;
	}
	
	/**
	 * Country Code
	 */
	public void setCountry_code(String value) {
		this.country_code = value;
	}
	
	/**
	 * Country Code
	 */
	public String getCountry_code() {
		return country_code;
	}
	
	/**
	 * Full Textual Date Format
	 */
	public void setFull_textualday_format(String value) {
		this.full_textualday_format = value;
	}
	
	/**
	 * Full Textual Date Format
	 */
	public String getFull_textualday_format() {
		return full_textualday_format;
	}
	
	public void setClient(Client value) {
		this.client = value;
	}
	
	public Client getClient() {
		return client;
	}
	
	public String getCall_center_logic() {
		return call_center_logic;
	}

	public void setCall_center_logic(String call_center_logic) {
		this.call_center_logic = call_center_logic;
	}

	/**
	 * @return the notify_phone_lead_time
	 */
	public int getNotify_phone_lead_time() {
		return notify_phone_lead_time;
	}

	/**
	 * @return the notify_phone_lag_time
	 */
	public int getNotify_phone_lag_time() {
		return notify_phone_lag_time;
	}

	/**
	 * @param notify_phone_lead_time the notify_phone_lead_time to set
	 */
	public void setNotify_phone_lead_time(int notify_phone_lead_time) {
		this.notify_phone_lead_time = notify_phone_lead_time;
	}

	/**
	 * @param notify_phone_lag_time the notify_phone_lag_time to set
	 */
	public void setNotify_phone_lag_time(int notify_phone_lag_time) {
		this.notify_phone_lag_time = notify_phone_lag_time;
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
			sb.append("Client_deployment_config[ ");
			sb.append("Id=").append(getId()).append(" ");
			if (getClient() != null)
				sb.append("Client.Persist_ID=").append(getClient().toString(true)).append(" ");
			else
				sb.append("Client=null ");
			sb.append("Block_time_in_mins=").append(getBlock_time_in_mins()).append(" ");
			sb.append("Resource_calendar_months=").append(getResource_calendar_months()).append(" ");
			sb.append("User_expiry_date_time=").append(getUser_expiry_date_time()).append(" ");
			sb.append("Day_start_time=").append(getDay_start_time()).append(" ");
			sb.append("Day_end_time=").append(getDay_end_time()).append(" ");
			sb.append("Integrated_reminddesk=").append(getIntegrated_reminddesk()).append(" ");
			sb.append("Server_flag=").append(getServer_flag()).append(" ");
			sb.append("Auto_file_upload=").append(getAuto_file_upload()).append(" ");
			sb.append("Same_multiloc_resource=").append(getSame_multiloc_resource()).append(" ");
			sb.append("Time_zone=").append(getTime_zone()).append(" ");
			sb.append("Date_format=").append(getDate_format()).append(" ");
			sb.append("Date_yyyy_format=").append(getDate_yyyy_format()).append(" ");
			sb.append("Full_date_format=").append(getFull_date_format()).append(" ");
			sb.append("Full_datetime_format=").append(getFull_datetime_format()).append(" ");
			sb.append("Popup_calendardate_format=").append(getPopup_calendardate_format()).append(" ");
			sb.append("Time_format=").append(getTime_format()).append(" ");
			sb.append("Time_withsec_format=").append(getTime_withsec_format()).append(" ");
			sb.append("Phone_format=").append(getPhone_format()).append(" ");
			sb.append("Country_code=").append(getCountry_code()).append(" ");
			sb.append("Full_textualday_format=").append(getFull_textualday_format()).append(" ");
			sb.append("call_center_logic=").append(getCall_center_logic()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}