package com.hpicorp.core.helper;

import java.util.List;
import java.util.Optional;

import com.hpicorp.core.config.ResponseStatus;
import com.hpicorp.core.exception.BusinessErrorException;

public class OptionalHelper {

	private OptionalHelper() {
	}

	public static <T> void checkOptional(Optional<T> optional) throws BusinessErrorException {
		if (!optional.isPresent()) {
			// if not exist send error response
			BusinessErrorException be = new BusinessErrorException(ResponseStatus.ERROR_INVALID_FORMAT);
			be.setData("無此 LINK ID 資訊");
			throw be;
		}
	}

	public static <T> void checkList(List<T> list) throws BusinessErrorException {
		if (list == null || list.isEmpty()) {
			// if not exist send error response
			BusinessErrorException be = new BusinessErrorException(ResponseStatus.ERROR_INVALID_FORMAT);
			be.setData("無此ID資訊 or 陣列為空");
			throw be;
		}
	}

}
