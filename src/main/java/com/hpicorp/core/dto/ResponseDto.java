package com.hpicorp.core.dto;

public class ResponseDto {

	Boolean isSuccess;
	String message;
	Object data;
	
	public ResponseDto() {
		super();
	}
	
	public ResponseDto(Boolean isSuccess, String message, Object data) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
		this.data = data;
	}
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseDTO [status=" + isSuccess + ", responseBody=" + data + "]";
	}
	
	
}
