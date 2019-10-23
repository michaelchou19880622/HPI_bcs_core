package com.hpicorp.core.exception;

import com.hpicorp.core.config.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessErrorException extends RuntimeException {
	
	private static final long serialVersionUID = -6651262405626297138L;

	private Object data;
	
	private ResponseStatus responseStatus;
	
	public BusinessErrorException(ResponseStatus responseStatus) {
        super(responseStatus.getMessage());
        this.responseStatus=responseStatus;
    }
	
}
