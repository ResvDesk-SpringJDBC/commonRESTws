package com.telappoint.commonrestws.common.model.to;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 
 * @author rajeev
 *
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class AdminLoginNewTO
{

	private int id;
	private int clientId;
	private String userName;
	private String password;
	private String masterPassword;
	private String firstName;
	private String lastName;
	private String contactPhone;
	private String contactEmail;
	private String locationIds;
	private String resourceIds;
	private String startDate;
	private String expireDate;
	private char locked;
	private String accessLevel;
	private String deviceType;
	private String langCode;
	private String loginType; // optional field using by internal
	private String clientCode;
	private String enforceLogin;
	private String comment;
	private String accessLevelValue;
	private String passwordComplexity;
	private int loginUserId;
	
	private char suspend;
	
	public String getDeviceType()
	{
		return deviceType;
	}

	public void setDeviceType(String deviceType)
	{
		this.deviceType = deviceType;
	}

	public String getLangCode()
	{
		return langCode;
	}

	public void setLangCode(String langCode)
	{
		this.langCode = langCode;
	}

	public String getLoginType()
	{
		return loginType;
	}

	public void setLoginType(String loginType)
	{
		this.loginType = loginType;
	}

	public String getClientCode()
	{
		return clientCode;
	}

	public void setClientCode(String clientCode)
	{
		this.clientCode = clientCode;
	}

	public String getEnforceLogin()
	{
		return enforceLogin;
	}

	public void setEnforceLogin(String enforceLogin)
	{
		this.enforceLogin = enforceLogin;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the masterPassword
	 */
	public String getMasterPassword()
	{
		return masterPassword;
	}

	/**
	 * @param masterPassword
	 *            the masterPassword to set
	 */
	public void setMasterPassword(String masterPassword)
	{
		this.masterPassword = masterPassword;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the contactPhone
	 */
	public String getContactPhone()
	{
		return contactPhone;
	}

	/**
	 * @param contactPhone
	 *            the contactPhone to set
	 */
	public void setContactPhone(String contactPhone)
	{
		this.contactPhone = contactPhone;
	}

	/**
	 * @return the contactEmail
	 */
	public String getContactEmail()
	{
		return contactEmail;
	}

	/**
	 * @param contactEmail
	 *            the contactEmail to set
	 */
	public void setContactEmail(String contactEmail)
	{
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the locationIds
	 */
	public String getLocationIds()
	{
		return locationIds;
	}

	/**
	 * @param locationIds
	 *            the locationIds to set
	 */
	public void setLocationIds(String locationIds)
	{
		this.locationIds = locationIds;
	}

	/**
	 * @return the resourceIds
	 */
	public String getResourceIds()
	{
		return resourceIds;
	}

	/**
	 * @param resourceIds
	 *            the resourceIds to set
	 */
	public void setResourceIds(String resourceIds)
	{
		this.resourceIds = resourceIds;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * @return the expireDate
	 */
	public String getExpireDate()
	{
		return expireDate;
	}

	/**
	 * @param expireDate
	 *            the expireDate to set
	 */
	public void setExpireDate(String expireDate)
	{
		this.expireDate = expireDate;
	}

	/**
	 * @return the accessLevel
	 */
	public String getAccessLevel()
	{
		return accessLevel;
	}

	/**
	 * @param accessLevel
	 *            the accessLevel to set
	 */
	public void setAccessLevel(String accessLevel)
	{
		this.accessLevel = accessLevel;
	}

	/**
	 * @return the locked
	 */
	public char getLocked()
	{
		return locked;
	}

	/**
	 * @param locked
	 *            the locked to set
	 */
	public void setLocked(char locked)
	{
		this.locked = locked;
	}

	public void setClientId(int clientId)
	{
		this.clientId = clientId;
	}

	public int getClientId()
	{
		return clientId;
	}

	public String getAccessLevelValue() {
		return accessLevelValue;
	}

	public void setAccessLevelValue(String accessLevelValue) {
		this.accessLevelValue = accessLevelValue;
	}

	public String getPasswordComplexity() {
		return passwordComplexity;
	}

	public void setPasswordComplexity(String passwordComplexity) {
		this.passwordComplexity = passwordComplexity;
	}

	public int getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(int loginUserId) {
		this.loginUserId = loginUserId;
	}

	public char getSuspend() {
		return suspend;
	}

	public void setSuspend(char suspend) {
		this.suspend = suspend;
	}

}
