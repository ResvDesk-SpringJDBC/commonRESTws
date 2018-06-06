package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="auto_pay")
@org.hibernate.annotations.Proxy(lazy=false)
public class AutoPay implements Serializable {
	
	private static final long serialVersionUID = -4545311651491079872L;

	public AutoPay() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51BE05021")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51BE05021", strategy="identity")	
	private int id;
	
	@Column(name="client_id", nullable=true)	
	@org.hibernate.annotations.Index(name="FK_auto_pay_client_id")	
	private Integer client_id;
	
	@Column(name="payment_engine", nullable=true, length=1)	
	private Character payment_engine;
	
	@Column(name="display", nullable=true, length=1)	
	private Character display;
	
	@Column(name="status", nullable=true, length=1)	
	private Character status;
	
	@Column(name="max_amount", nullable=true, length=10)	
	private String max_amount;
	
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
		setClient_id(new Integer(value));
	}
	
	/**
	 * Client ID
	 */
	public void setClient_id(Integer value) {
		this.client_id = value;
	}
	
	/**
	 * Client ID
	 */
	public Integer getClient_id() {
		return client_id;
	}
	
	/**
	 * Payment Engine. values: p = paypal, a = authorize.net etc
	 */
	public void setPayment_engine(char value) {
		setPayment_engine(new Character(value));
	}
	
	/**
	 * Payment Engine. values: p = paypal, a = authorize.net etc
	 */
	public void setPayment_engine(Character value) {
		this.payment_engine = value;
	}
	
	/**
	 * Payment Engine. values: p = paypal, a = authorize.net etc
	 */
	public Character getPayment_engine() {
		return payment_engine;
	}
	
	/**
	 * Display AutoPay button in admin site? values: Y or N
	 */
	public void setDisplay(char value) {
		setDisplay(new Character(value));
	}
	
	/**
	 * Display AutoPay button in admin site? values: Y or N
	 */
	public void setDisplay(Character value) {
		this.display = value;
	}
	
	/**
	 * Display AutoPay button in admin site? values: Y or N
	 */
	public Character getDisplay() {
		return display;
	}
	
	/**
	 * Status - not yet used
	 */
	public void setStatus(char value) {
		setStatus(new Character(value));
	}
	
	/**
	 * Status - not yet used
	 */
	public void setStatus(Character value) {
		this.status = value;
	}
	
	/**
	 * Status - not yet used
	 */
	public Character getStatus() {
		return status;
	}
	
	/**
	 * Maximum Amount which can be billed
	 */
	public void setMax_amount(String value) {
		this.max_amount = value;
	}
	
	/**
	 * Maximum Amount which can be billed
	 */
	public String getMax_amount() {
		return max_amount;
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
			sb.append("Auto_pay[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Client_id=").append(getClient_id()).append(" ");
			sb.append("Payment_engine=").append(getPayment_engine()).append(" ");
			sb.append("Display=").append(getDisplay()).append(" ");
			sb.append("Status=").append(getStatus()).append(" ");
			sb.append("Max_amount=").append(getMax_amount()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}
