package com.hpicorp.core.config;

public enum ResponseStatus {

	REQUEST_SUCCESS(0000, "success"),
	
	SYSTEM_ERROR(9000, "system error"),
	
	REQUEST_ERROR(9001, "fail"),
	
	ERROR_INVALID_FORMAT(9002, "invalid format"),
	
	ERROR_PUSH_MSG_TYPE(9003, "invalid sendPushMsgType"),
	
	ERROR_VERIFY_TOKEN(9004, "verify token fail"),

	ERROR_VERIFY_TOKEN_EXPIRED(9005, "verify token expired"),
	
	ERROR_INVALID_CONFIRM_PASSWORD(9006, "password confirm check fail")
	
	,ERROR_PASSWORD(9007, "password check fail")
	
	,ERROR_UNKNOW_USER(9008, "user account not found")
	
	,ERROR_LOGIN(9009, "user account or password error")

	,ERROR_DATA_NOT_FOUND(9010, "data not exists")

	,ERROR_REFRESH_TOKEN_FALED(9011, "refresh token failed, please re-login")
	
	,ERROR_FILE_UPLOAD(9012, "file upload unexcepted error")

	,ERROR_OVER_SCHEDULE(9013, "over schedule time error")
	
	,ERROR_INVALID_PARAMETER(9014, "invalid Parameter")
	
	,ERROR_LINE_CHANNEL_INFO(9015, "line channel info error")
	
	,ERROR_DUPLICATE_ACCOUNT(9016, "duplicate account error")

	,ERROR_DUPLICATE_CHANNEL_ID(9017, "duplicate channel id error")
	
	,ERROR_LINE_API(9018, "line api error")
	
	,ERROR_IP_ADDR(9019, "is not from the same origin")
	
	,ERROR_DUPLICATE_PROD_CODE(9020, "duplicate prod code error")
	
	,ERROR_OVER_LIMIT(9021, "over limit error")
	
	,ERROR_LINE_LOGIN_CHANNEL_INFO(9022, "line login channel info error")
	
	;
	
	private Integer status;
	
	private String message;
	
	private ResponseStatus(Integer status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
}
