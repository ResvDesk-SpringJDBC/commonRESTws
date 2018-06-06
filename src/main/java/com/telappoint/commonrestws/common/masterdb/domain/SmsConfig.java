package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="sms_config")
//@org.hibernate.annotations.Proxy(lazy=false)
public class SmsConfig implements Serializable {
	
	private static final long serialVersionUID = 5646312566077392555L;

	public SmsConfig() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140E250035101309")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140E250035101309", strategy="identity")	
	private long id;
	
	@ManyToOne(cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })	
	@JoinColumns({ @JoinColumn(name="client_id", referencedColumnName="id") })	
	@org.hibernate.annotations.Index(name="client_id")	
	@Basic(fetch=FetchType.LAZY)	
	private Client client;
	
	@Column(name="account_sid", nullable=true, length=50)	
	private String account_sid = "AC4458b0b635416cba4238effa76e78e84";
	
	@Column(name="auth_token", nullable=true, length=50)	
	private String auth_token = "31056ad9bdd97443ec55231d6c966881";
	
	@Column(name="sms_phone", nullable=false, length=15)	
	private String sms_phone;
	
	@Column(name="resource_id", nullable=true)	
	private Integer resource_id;
	
	@Column(name="location_id", nullable=true)	
	private Integer location_id;
	
	public long getId() {
		return id;
	}
	
	public long getORMID() {
		return getId();
	}
	
	public void setAccount_sid(String value) {
		this.account_sid = value;
	}
	
	public String getAccount_sid() {
		return account_sid;
	}
	
	public void setAuth_token(String value) {
		this.auth_token = value;
	}
	
	public String getAuth_token() {
		return auth_token;
	}
	
	public void setSms_phone(String value) {
		this.sms_phone = value;
	}
	
	public String getSms_phone() {
		return sms_phone;
	}
	
	public void setResource_id(int value) {
		setResource_id(new Integer(value));
	}
	
	public void setResource_id(Integer value) {
		this.resource_id = value;
	}
	
	public Integer getResource_id() {
		return resource_id;
	}
	
	public void setLocation_id(int value) {
		setLocation_id(new Integer(value));
	}
	
	public void setLocation_id(Integer value) {
		this.location_id = value;
	}
	
	public Integer getLocation_id() {
		return location_id;
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
			sb.append("Sms_config[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Client=").append(getClient()).append(" ");
			sb.append("Account_sid=").append(getAccount_sid()).append(" ");
			sb.append("Auth_token=").append(getAuth_token()).append(" ");
			sb.append("Sms_phone=").append(getSms_phone()).append(" ");
			sb.append("Resource_id=").append(getResource_id()).append(" ");
			sb.append("Location_id=").append(getLocation_id()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
