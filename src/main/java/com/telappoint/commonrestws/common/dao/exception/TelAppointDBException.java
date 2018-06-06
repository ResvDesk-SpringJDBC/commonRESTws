package com.telappoint.commonrestws.common.dao.exception;

import com.telappoint.commonrestws.common.utils.ExceptionUtils;


/**
 * 
 * @author Murali
 * 
 */
public class TelAppointDBException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer exceptionCode;
	private String exceptionDesc;

	public TelAppointDBException(Integer exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public TelAppointDBException(Integer exceptionCode, String exceptionDesc) {
		this.exceptionCode = exceptionCode;
		this.exceptionDesc = exceptionDesc;
	}

	public String getExceptionDesc(Integer exceptionCode) {
		return ExceptionUtils.getExceptionDescription(exceptionCode);
	}

	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}

	public String getExceptionDesc() {
		return exceptionDesc;
	}

	public void setExceptionCode(Integer exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public Integer getExceptionCode() {
		return exceptionCode;
	}
}
