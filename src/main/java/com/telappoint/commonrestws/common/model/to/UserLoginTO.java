package com.telappoint.commonrestws.common.model.to;

/**
 * Class used to Transfer User login information
 * 
 * @author Murali
 * 
 */
public class UserLoginTO
{

	public UserLoginTO(String username, String password, String ipAddress)
	{
		super();
		this.username = username;
		this.password = password;
		this.ipAddress = ipAddress;
	}

	public UserLoginTO()
	{
		super();
	}

	private String username;

	private String password;

	private String ipAddress;

	/*private String confirmNewPassword;
	private String newPassword;	
	private String oldPassword;*/
	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
}
