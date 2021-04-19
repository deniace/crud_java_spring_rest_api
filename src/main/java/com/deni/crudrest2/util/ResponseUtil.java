package com.deni.crudrest2.util;

import com.deni.crudrest2.dto.ResponseDto;

public class ResponseUtil {
	public static ResponseDto responseConstruct(boolean status, Object data, String message) {
		ResponseDto res = new ResponseDto();
		res.setStatus(status);
		res.setMessage(message);
		res.setData(data);
		return res;
	}
}
