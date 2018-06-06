package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="sms_request")
@org.hibernate.annotations.Proxy(lazy=false)
public class SMSRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	public SMSRequest() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B6D1404E7A641203E0E")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B6D1404E7A641203E0E", strategy="identity")	
	private long id;
	
	@Column(name="sms_phone", nullable=false, length=30)	
	private String sms_phone;
	
	@ManyToOne(cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })	
	@JoinColumns({ @JoinColumn(name="client_id", referencedColumnName="id") })	
	@org.hibernate.annotations.Index(name="client_id")	
	@Basic(fetch=FetchType.LAZY)	
	private Client client;
	
	@Column(name="resource_id", nullable=false)	
	private int resource_id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSms_phone() {
		return sms_phone;
	}

	public void setSms_phone(String sms_phone) {
		this.sms_phone = sms_phone;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getResource_id() {
		return resource_id;
	}

	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}
	
	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getId());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("Notify[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Sms_phone=").append(getSms_phone()).append(" ");
			if (getClient() != null)
				sb.append("Client.Persist_ID=").append(getClient().toString(true)).append(" ");
			else
				sb.append("Client=null ");		
			sb.append("resource_id=").append(getResource_id()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

	}
