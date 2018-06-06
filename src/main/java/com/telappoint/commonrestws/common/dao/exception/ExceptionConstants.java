package com.telappoint.commonrestws.common.dao.exception;

public class ExceptionConstants {
	
	/** The Constant FILENOTFOUND_ERROR. */
	public static final Integer FILENOTFOUND_ERROR = 1002;
	
	/** The Constant EXCEPTION_PROPFILE. */
	public static final String EXCEPTION_PROPFILE = "ExceptionDescription.properties";
	
	/** The Constant TELAPPOINTDB_EXCEPTION. */
	public static final Integer TELAPPOINTDB_EXCEPTION = 100;	
	
	/**  Saving Entity   */
	public static final Integer TELAPPOINTDB_SAVING_ROLLBACK_EXCEPTION = 101;
	public static final Integer TELAPPOINTDB_SAVING_TRANSACTION_REQUIRED_EXCEPTION = 102;
	public static final Integer TELAPPOINTDB_SAVING_ENTITY_EXISTS_EXCEPTION = 103;
	public static final Integer TELAPPOINTDB_SAVING_ENTITY_NOT_FOUND_EXCEPTION = 104;
	public static final Integer TELAPPOINTDB_SAVING_PERSISTENCE_EXCEPTION = 105;
	
	
	/**  QUERING Entity   */
	public static final Integer TELAPPOINTDB_QUERING_NONUNIQUERESULTFOUND_EXCEPTION = 111;
	public static final Integer TELAPPOINTDB_QUERING_NORESULTFOUND_EXCEPTION = 112;
	public static final Integer TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION = 113;
	public static final Integer TELAPPOINTDB_QUERING_SQL_EXCEPTION = 114;
	
	/**  UPDATING Entity   */
	public static final Integer TELAPPOINTDB_UPDATING_ROLLBACK_EXCEPTION = 121;
	public static final Integer TELAPPOINTDB_UPDATING_TRANSACTION_REQUIRED_EXCEPTION = 122;
	public static final Integer TELAPPOINTDB_UPDATING_ENTITY_EXISTS_EXCEPTION = 123;
	public static final Integer TELAPPOINTDB_UPDATING_ENTITY_NOT_FOUND_EXCEPTION = 124;
	public static final Integer TELAPPOINTDB_UPDATING_PERSISTENCE_EXCEPTION = 125;
	
	
	/**  DELETING Entity   */
	public static final Integer TELAPPOINTDB_DELETING_ROLLBACK_EXCEPTION = 131;
	public static final Integer TELAPPOINTDB_DELETING_TRANSACTION_REQUIRED_EXCEPTION = 132;
	public static final Integer TELAPPOINTDB_DELETING_ENTITY_EXISTS_EXCEPTION = 133;
	public static final Integer TELAPPOINTDB_DELETING_ENTITY_NOT_FOUND_EXCEPTION = 134;
	public static final Integer TELAPPOINTDB_DELETING_PERSISTENCE_EXCEPTION = 135;
	
}
