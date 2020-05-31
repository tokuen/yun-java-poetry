package com.yun.springboot.model.result;


public class RetResult<T> {

	public int return_code;

	public String return_message;

	private T data;

	public RetResult<T> setCode(ErrorCode errorCode) {
		this.return_code = errorCode.code;
		return this;
	}

	public RetResult<T> setCode(int code) {
		this.return_code = code;
		return this;
	}

	public RetResult<T> setMsg(String msg) {
		this.return_message = msg;
		return this;
	}

	public T getData() {
		return data;
	}

	public RetResult<T> setData(T data) {
		this.data = data;
		return this;
	}
}
