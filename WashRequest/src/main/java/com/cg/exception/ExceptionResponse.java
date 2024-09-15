package com.cg.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

	private String message;
	private HttpStatus httpStatus;
	private String detail;
	
	public ExceptionResponse(String message, HttpStatus httpStatus, String detail) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.detail = detail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
