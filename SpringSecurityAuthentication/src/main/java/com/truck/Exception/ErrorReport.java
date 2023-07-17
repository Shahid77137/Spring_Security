package com.truck.Exception;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
public class ErrorReport {

	private int code;
	private String msg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
