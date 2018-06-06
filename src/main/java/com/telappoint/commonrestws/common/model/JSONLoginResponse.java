package com.telappoint.commonrestws.common.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 
 * @author Murali
 * 
 */
@JsonIgnoreProperties
@JsonSerialize(include = Inclusion.NON_NULL)
public class JSONLoginResponse
{

	private String login;
	private String clientCode;
	private String accessLevel;
	private String locationAccess;
	private String resourceAccess;
	private String passwordExpiryAlert;
	private String firstName;
	private String lastName;
	private String contactPhone;
	private String contactEmail;
	private String lastLoginDateTime;
	private String lastLoginIP;
	private String displaySLA;
	private String slaText;
	private String slaSkip;
	private String forwardURL;
	private ClientTO clientTO;
	private Integer clientId;
	
	private String username;
	private String passwordResetAlgorithm;
	private String passwordComplexity;
	
	private String locationIds;
	private String resourceIds;
	private int loginUserId;
	
	//private String appVersion;
	
	private String clinetTimeZone;
	private String call_center_logic;
	
	public Integer getClientId()
	{
		return clientId;
	}

	public void setClientId(Integer clientId)
	{
		this.clientId = clientId;
	}

	public ClientTO getClientTO()
	{
		return clientTO;
	}

	public void setClientTO(ClientTO clientTO)
	{
		this.clientTO = clientTO;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getClientCode()
	{
		return clientCode;
	}

	public void setClientCode(String clientCode)
	{
		this.clientCode = clientCode;
	}

	public String getAccessLevel()
	{
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel)
	{
		this.accessLevel = accessLevel;
	}

	public String getLocationAccess()
	{
		return locationAccess;
	}

	public void setLocationAccess(String locationAccess)
	{
		this.locationAccess = locationAccess;
	}

	public String getResourceAccess()
	{
		return resourceAccess;
	}

	public void setResourceAccess(String resourceAccess)
	{
		this.resourceAccess = resourceAccess;
	}

	public String getPasswordExpiryAlert()
	{
		return passwordExpiryAlert;
	}

	public void setPasswordExpiryAlert(String passwordExpiryAlert)
	{
		this.passwordExpiryAlert = passwordExpiryAlert;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getContactPhone()
	{
		return contactPhone;
	}

	public void setContactPhone(String contactPhone)
	{
		this.contactPhone = contactPhone;
	}

	public String getContactEmail()
	{
		return contactEmail;
	}

	public void setContactEmail(String contactEmail)
	{
		this.contactEmail = contactEmail;
	}

	public String getLastLoginDateTime()
	{
		return lastLoginDateTime;
	}

	public void setLastLoginDateTime(String lastLoginDateTime)
	{
		this.lastLoginDateTime = lastLoginDateTime;
	}

	public String getLastLoginIP()
	{
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP)
	{
		this.lastLoginIP = lastLoginIP;
	}

	public String getDisplaySLA()
	{
		return displaySLA;
	}

	public void setDisplaySLA(String displaySLA)
	{
		this.displaySLA = displaySLA;
	}

	public String getSlaText()
	{
		return slaText;
	}

	public void setSlaText(String slaText)
	{
		this.slaText = slaText;
	}

	public String getSlaSkip()
	{
		return slaSkip;
	}

	public void setSlaSkip(String slaSkip)
	{
		this.slaSkip = slaSkip;
	}

	public String getForwardURL()
	{
		return forwardURL;
	}

	public void setForwardURL(String forwardURL)
	{
		this.forwardURL = forwardURL;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordResetAlgorithm() {
		return passwordResetAlgorithm;
	}

	public void setPasswordResetAlgorithm(String passwordResetAlgorithm) {
		this.passwordResetAlgorithm = passwordResetAlgorithm;
	}

	public String getPasswordComplexity() {
		return passwordComplexity;
	}

	public void setPasswordComplexity(String passwordComplexity) {
		this.passwordComplexity = passwordComplexity;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public int getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(int loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getClinetTimeZone() {
		return clinetTimeZone;
	}

	public void setClinetTimeZone(String clinetTimeZone) {
		this.clinetTimeZone = clinetTimeZone;
	}

	public String getCall_center_logic() {
		return call_center_logic;
	}

	public void setCall_center_logic(String call_center_logic) {
		this.call_center_logic = call_center_logic;
	}

	/*public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}*/

}
