package com.hpicorp.core.config;

public enum ResponseStatus {

	REQUEST_SUCCESS(true, "success"),
	
	SYSTEM_ERROR(false, "system error"),
	
	REQUEST_ERROR(false, "fail"),
	
	ERROR_INVALID_FORMAT(false, "invalid format"),
	
	ERROR_PUSH_MSG_TYPE(false, "invalid sendPushMsgType"),
	
	ERROR_VERIFY_TOKEN(false, "verify token fail"),

	ERROR_VERIFY_TOKEN_EXPIRED(false, "verify token expired"),
	
	ERROR_INVALID_CONFIRM_PASSWORD(false, "password confirm check fail")
	
	,ERROR_PASSWORD(false, "password check fail")
	
	,ERROR_UNKNOW_USER(false, "user account not found")
	
	,ERROR_LOGIN(false, "user account or password error")

	,ERROR_DATA_NOT_FOUND(false, "data not exists")

	,ERROR_REFRESH_TOKEN_FALED(false, "refresh token failed, please re-login")
	
	,ERROR_FILE_UPLOAD(false, "file upload unexcepted error")

	,ERROR_OVER_SCHEDULE(false, "over schedule time error")
	
	,ERROR_INVALID_PARAMETER(false, "invalid Parameter")
	
	,ERROR_LINE_CHANNEL_INFO(false, "line channel info error")
	
	,ERROR_DUPLICATE_ACCOUNT(false, "duplicate account error")

	,ERROR_DUPLICATE_CHANNEL_ID(false, "duplicate channel id error")
	
	,ERROR_LINE_API(false, "line api error")
	
	,ERROR_IP_ADDR(false, "is not from the same origin")
	
	,ERROR_DUPLICATE_PROD_CODE(false, "duplicate prod code error")
	
	,ERROR_OVER_LIMIT(false, "over limit error")
	
	,ERROR_LINE_LOGIN_CHANNEL_INFO(false, "line login channel info error")
	
	;
	
	private Boolean isSuccess;
	
	private String message;
	
	private ResponseStatus(Boolean isSuccess, String message) {
		this.isSuccess = isSuccess;
		this.message = message;
	}
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	
	public String getMessage() {
		return message;
	}
	
}
