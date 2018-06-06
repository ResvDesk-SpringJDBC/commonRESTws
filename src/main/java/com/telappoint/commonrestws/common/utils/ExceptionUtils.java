package com.telappoint.commonrestws.common.utils;
/**
 * 
 * @author Balaji
 *
 */
public class ExceptionUtils {
	public static String getExceptionDescription(Integer exceptionCode) {
		String exceptionDescription = FileUtils.getErrorMessage(exceptionCode);
		return exceptionDescription;
	}
}
