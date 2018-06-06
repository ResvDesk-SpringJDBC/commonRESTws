package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="client")
@org.hibernate.annotations.Proxy(lazy=false)
public class Client implements Serializable {
	
	private static final long serialVersionUID = 4837450175371886561L;

	public Client() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51BE05022")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51BE05022", strategy="identity")	
	private int id;
	
	@Column(name="timestamp", nullable=false)	
	private java.sql.Timestamp timestamp;
	
	@Column(name="client_name", nullable=false, length=100)	
	private String client_name;
	
	@Column(name="client_code", nullable=false, length=8)	
	private String client_code;
	
	@Column(name="contact_person", nullable=false, length=60)	
	private String contact_person;
	
	@Column(name="contact_phone", nullable=false, length=15)	
	private String contact_phone;
	
	@Column(name="contact_email", nullable=true, length=80)	
	private String contact_email;
	
	@Column(name="website", nullable=true, length=200)	
	private String website;
	
	@Column(name="phone_transfer_number", nullable=true, length=15)	
	private String phone_transfer_number;
	
	@Column(name="fax", nullable=true, length=15)	
	private String fax;
	
	@Column(name="address", nullable=true, length=80)	
	private String address;
	
	@Column(name="address2", nullable=true, length=80)	
	private String address2;
	
	@Column(name="city", nullable=true, length=60)	
	private String city;
	
	@Column(name="state", nullable=true, length=60)	
	private String state;
	
	@Column(name="zip", nullable=true, length=60)	
	private String zip;
	
	@Column(name="country", nullable=true, length=60)	
	private String country;
	
	@Column(name="appt_link", nullable=true, length=200)	
	private String appt_link;
	
	@Column(name="forward_url", nullable=true, length=200)	
	private String forward_url;
	
	@Column(name="business_type", nullable=true, length=80)	
	private String business_type;
	
	@Column(name="appcode", nullable=false, length=20)	
	private String appcode;
	
	@Column(name="db_server", nullable=true, length=100)	
	private String db_server;
	
	@Column(name="db_name", nullable=false, length=25)	
	private String db_name;
	
	@Column(name="license_key", nullable=false, length=500)	
	private String license_key;
	
	@Column(name="date_subscribed", nullable=false)	
	private java.util.Date date_subscribed;
	
	@Column(name="date_cancelled", nullable=true)	
	private java.util.Date date_cancelled;
	
	@Column(name="delete_flag", nullable=true, length=1)	
	private Character delete_flag = new Character('N');
	
	@Column(name="active", nullable=true, length=1)	
	private Character active = new Character('Y');
	
	@Column(name="locked", nullable=true, length=1)	
	private Character locked = new Character('N');
	
	@Column(name="group_account", nullable=true, length=60)	
	private String group_account;
	
	@Column(name="channel_partner_id", nullable=true, length=60)	
	private String channel_partner_id;
	
	@Column(name="sales_rep", nullable=true, length=60)	
	private String sales_rep;
	
	@Column(name="billable", nullable=true, length=1)	
	private Character billable = new Character('N');
	
	@Column(name = "allow_online_appt", nullable = true, length = 1)
	private Character allow_online_appt = new Character('N');

	@Column(name = "allow_ivr_appt", nullable = true, length = 1)
	private Character allow_ivr_appt = new Character('N');

	@Column(name = "allow_mobile_appt", nullable = true, length = 1)
	private Character allow_mobile_appt = new Character('N');

	@Column(name = "allow_mobile_admin", nullable = true, length = 1)
	private Character allow_mobile_admin = new Character('N');

	@Column(name = "allow_ivr_notify", nullable = true, length = 1)
	private Character allow_ivr_notify = new Character('N');

	@Column(name = "allow_sms_notify", nullable = true, length = 1)
	private Character allow_sms_notify = new Character('N');

	@Column(name = "allow_email_notify", nullable = true, length = 1)
	private Character allow_email_notify = new Character('N');
	
	@Column(name="client_dnis_1", nullable=true, length=11)	
	private String client_dnis_1;
	
	@Column(name="client_dnis_2", nullable=true, length=11)	
	private String client_dnis_2;
	
	@Column(name="client_dnis_3", nullable=true, length=11)	
	private String client_dnis_3;
	
	@Column(name="extension", nullable=true, length=11)	
	private String extension;
	
	@Column(name="redirect_url", nullable=true, length=11)	
	private String redirect_url;
	
	@Column(name="direct_access_number", nullable=true, length=11)	
	private String direct_access_number;
	
	public Character getAllow_online_appt() {
		return allow_online_appt;
	}

	public void setAllow_online_appt(Character allow_online_appt) {
		this.allow_online_appt = allow_online_appt;
	}

	public Character getAllow_ivr_appt() {
		return allow_ivr_appt;
	}

	public void setAllow_ivr_appt(Character allow_ivr_appt) {
		this.allow_ivr_appt = allow_ivr_appt;
	}

	public Character getAllow_mobile_appt() {
		return allow_mobile_appt;
	}

	public void setAllow_mobile_appt(Character allow_mobile_appt) {
		this.allow_mobile_appt = allow_mobile_appt;
	}

	public Character getAllow_mobile_admin() {
		return allow_mobile_admin;
	}

	public void setAllow_mobile_admin(Character allow_mobile_admin) {
		this.allow_mobile_admin = allow_mobile_admin;
	}

	public Character getAllow_ivr_notify() {
		return allow_ivr_notify;
	}

	public void setAllow_ivr_notify(Character allow_ivr_notify) {
		this.allow_ivr_notify = allow_ivr_notify;
	}

	public Character getAllow_sms_notify() {
		return allow_sms_notify;
	}

	public void setAllow_sms_notify(Character allow_sms_notify) {
		this.allow_sms_notify = allow_sms_notify;
	}

	public Character getAllow_email_notify() {
		return allow_email_notify;
	}

	public void setAllow_email_notify(Character allow_email_notify) {
		this.allow_email_notify = allow_email_notify;
	}

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
	 * Client Name
	 */
	public void setClient_name(String value) {
		this.client_name = value;
	}
	
	/**
	 * Client Name
	 */
	public String getClient_name() {
		return client_name;
	}
	
	/**
	 * Unique Client Code - 8 character
	 */
	public void setClient_code(String value) {
		this.client_code = value;
	}
	
	/**
	 * Unique Client Code - 8 character
	 */
	public String getClient_code() {
		return client_code;
	}
	
	/**
	 * Contact Person
	 */
	public void setContact_person(String value) {
		this.contact_person = value;
	}
	
	/**
	 * Contact Person
	 */
	public String getContact_person() {
		return contact_person;
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
	 * Business Website
	 */
	public void setWebsite(String value) {
		this.website = value;
	}
	
	/**
	 * Business Website
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * Phone number which will be transferred to our IVR server
	 */
	public void setPhone_transfer_number(String value) {
		this.phone_transfer_number = value;
	}
	
	/**
	 * Phone number which will be transferred to our IVR server
	 */
	public String getPhone_transfer_number() {
		return phone_transfer_number;
	}
	
	/**
	 * Business Fax
	 */
	public void setFax(String value) {
		this.fax = value;
	}
	
	/**
	 * Business Fax
	 */
	public String getFax() {
		return fax;
	}
	
	/**
	 * Address Line1
	 */
	public void setAddress(String value) {
		this.address = value;
	}
	
	/**
	 * Address Line1
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Address Line2
	 */
	public void setAddress2(String value) {
		this.address2 = value;
	}
	
	/**
	 * Address Line2
	 */
	public String getAddress2() {
		return address2;
	}
	
	/**
	 * City
	 */
	public void setCity(String value) {
		this.city = value;
	}
	
	/**
	 * City
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * State/Province
	 */
	public void setState(String value) {
		this.state = value;
	}
	
	/**
	 * State/Province
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Zip/Postal Code
	 */
	public void setZip(String value) {
		this.zip = value;
	}
	
	/**
	 * Zip/Postal Code
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * Country
	 */
	public void setCountry(String value) {
		this.country = value;
	}
	
	/**
	 * Country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Appointment Scheduler Link
	 */
	public void setAppt_link(String value) {
		this.appt_link = value;
	}
	
	/**
	 * Appointment Scheduler Link
	 */
	public String getAppt_link() {
		return appt_link;
	}
	
	/**
	 * website which will link to our online scheduler
	 */
	public void setForward_url(String value) {
		this.forward_url = value;
	}
	
	/**
	 * website which will link to our online scheduler
	 */
	public String getForward_url() {
		return forward_url;
	}
	
	/**
	 * Business Type
	 */
	public void setBusiness_type(String value) {
		this.business_type = value;
	}
	
	/**
	 * Business Type
	 */
	public String getBusiness_type() {
		return business_type;
	}
	
	/**
	 * Application Code - apptdesk, reminddesk, labresultdesk, resvdesk
	 */
	public void setAppcode(String value) {
		this.appcode = value;
	}
	
	/**
	 * Application Code - apptdesk, reminddesk, labresultdesk, resvdesk
	 */
	public String getAppcode() {
		return appcode;
	}
	
	/**
	 * Database Server Address or IP Address.
	 */
	public void setDb_server(String value) {
		this.db_server = value;
	}
	
	/**
	 * Database Server Address or IP Address.
	 */
	public String getDb_server() {
		return db_server;
	}
	
	/**
	 * database name
	 */
	public void setDb_name(String value) {
		this.db_name = value;
	}
	
	/**
	 * database name
	 */
	public String getDb_name() {
		return db_name;
	}
	
	/**
	 * License Key meant for appliance
	 */
	public void setLicense_key(String value) {
		this.license_key = value;
	}
	
	/**
	 * License Key meant for appliance
	 */
	public String getLicense_key() {
		return license_key;
	}
	
	/**
	 * Date of subscription
	 */
	public void setDate_subscribed(java.util.Date value) {
		this.date_subscribed = value;
	}
	
	/**
	 * Date of subscription
	 */
	public java.util.Date getDate_subscribed() {
		return date_subscribed;
	}
	
	/**
	 * Date of cancellation
	 */
	public void setDate_cancelled(java.util.Date value) {
		this.date_cancelled = value;
	}
	
	/**
	 * Date of cancellation
	 */
	public java.util.Date getDate_cancelled() {
		return date_cancelled;
	}
	
	/**
	 * Soft Delete Flag. Y = Delete. N = Active
	 */
	public void setDelete_flag(char value) {
		setDelete_flag(new Character(value));
	}
	
	/**
	 * Soft Delete Flag. Y = Delete. N = Active
	 */
	public void setDelete_flag(Character value) {
		this.delete_flag = value;
	}
	
	/**
	 * Soft Delete Flag. Y = Delete. N = Active
	 */
	public Character getDelete_flag() {
		return delete_flag;
	}
	
	/**
	 * Is account active? values: Y or N
	 */
	public void setActive(char value) {
		setActive(new Character(value));
	}
	
	/**
	 * Is account active? values: Y or N
	 */
	public void setActive(Character value) {
		this.active = value;
	}
	
	/**
	 * Is account active? values: Y or N
	 */
	public Character getActive() {
		return active;
	}
	
	/**
	 * Is account locked? values: Y or N
	 */
	public void setLocked(char value) {
		setLocked(new Character(value));
	}
	
	/**
	 * Is account locked? values: Y or N
	 */
	public void setLocked(Character value) {
		this.locked = value;
	}
	
	/**
	 * Is account locked? values: Y or N
	 */
	public Character getLocked() {
		return locked;
	}
	
	/**
	 * Group Account Code. Example: catapult, bci etc
	 */
	public void setGroup_account(String value) {
		this.group_account = value;
	}
	
	/**
	 * Group Account Code. Example: catapult, bci etc
	 */
	public String getGroup_account() {
		return group_account;
	}
	
	/**
	 * Channel Partner ID - not yet used
	 */
	public void setChannel_partner_id(String value) {
		this.channel_partner_id = value;
	}
	
	/**
	 * Channel Partner ID - not yet used
	 */
	public String getChannel_partner_id() {
		return channel_partner_id;
	}
	
	/**
	 * Sales Rep for this account
	 */
	public void setSales_rep(String value) {
		this.sales_rep = value;
	}
	
	/**
	 * Sales Rep for this account
	 */
	public String getSales_rep() {
		return sales_rep;
	}
	
	/**
	 * Billable Client? Y = yes, N = no
	 */
	public void setBillable(char value) {
		setBillable(new Character(value));
	}
	
	/**
	 * Billable Client? Y = yes, N = no
	 */
	public void setBillable(Character value) {
		this.billable = value;
	}
	
	/**
	 * Billable Client? Y = yes, N = no
	 */
	public Character getBillable() {
		return billable;
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
			sb.append("Client[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Timestamp=").append(getTimestamp()).append(" ");
			sb.append("Client_name=").append(getClient_name()).append(" ");
			sb.append("Client_code=").append(getClient_code()).append(" ");
			sb.append("Contact_person=").append(getContact_person()).append(" ");
			sb.append("Contact_phone=").append(getContact_phone()).append(" ");
			sb.append("Contact_email=").append(getContact_email()).append(" ");
			sb.append("Website=").append(getWebsite()).append(" ");
			sb.append("Phone_transfer_number=").append(getPhone_transfer_number()).append(" ");
			sb.append("Fax=").append(getFax()).append(" ");
			sb.append("Address=").append(getAddress()).append(" ");
			sb.append("Address2=").append(getAddress2()).append(" ");
			sb.append("City=").append(getCity()).append(" ");
			sb.append("State=").append(getState()).append(" ");
			sb.append("Zip=").append(getZip()).append(" ");
			sb.append("Country=").append(getCountry()).append(" ");
			sb.append("Appt_link=").append(getAppt_link()).append(" ");
			sb.append("Forward_url=").append(getForward_url()).append(" ");
			sb.append("Business_type=").append(getBusiness_type()).append(" ");
			sb.append("Appcode=").append(getAppcode()).append(" ");
			sb.append("Db_server=").append(getDb_server()).append(" ");
			sb.append("Db_name=").append(getDb_name()).append(" ");
			sb.append("License_key=").append(getLicense_key()).append(" ");
			sb.append("Date_subscribed=").append(getDate_subscribed()).append(" ");
			sb.append("Date_cancelled=").append(getDate_cancelled()).append(" ");
			sb.append("Delete_flag=").append(getDelete_flag()).append(" ");
			sb.append("Active=").append(getActive()).append(" ");
			sb.append("Locked=").append(getLocked()).append(" ");
			sb.append("Group_account=").append(getGroup_account()).append(" ");
			sb.append("Channel_partner_id=").append(getChannel_partner_id()).append(" ");
			sb.append("Sales_rep=").append(getSales_rep()).append(" ");
			sb.append("Billable=").append(getBillable()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public String getDirect_access_number() {
		return direct_access_number;
	}

	public void setDirect_access_number(String direct_access_number) {
		this.direct_access_number = direct_access_number;
	}

	public String getClient_dnis_1() {
		return client_dnis_1;
	}

	public void setClient_dnis_1(String client_dnis_1) {
		this.client_dnis_1 = client_dnis_1;
	}

	public String getClient_dnis_2() {
		return client_dnis_2;
	}

	public void setClient_dnis_2(String client_dnis_2) {
		this.client_dnis_2 = client_dnis_2;
	}

	public String getClient_dnis_3() {
		return client_dnis_3;
	}

	public void setClient_dnis_3(String client_dnis_3) {
		this.client_dnis_3 = client_dnis_3;
	}
	
}
