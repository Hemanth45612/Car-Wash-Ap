//package com.cg.exception;
//
//import java.time.LocalDate;
//
//import org.springframework.http.HttpStatus;
//
//public class ExceptionResponse {
//	
//	   private String message;
//	   private String details;
//	   private HttpStatus httpCode;
//	   private LocalDate timestamp;
//	   
//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
//	public String getDetails() {
//		return details;
//	}
//	public void setDetails(String details) {
//		this.details = details;
//	}
//	public HttpStatus getHttpCode() {
//		return httpCode;
//	}
//	public void setHttpCode(HttpStatus httpCode) {
//		this.httpCode = httpCode;
//	}
//	public LocalDate getTimestamp() {
//		return timestamp;
//	}
//	public void setTimestamp(LocalDate timestamp) {
//		this.timestamp = timestamp;
//	}
//	public ExceptionResponse(String message, String details, HttpStatus httpCode, LocalDate timestamp) {
//		super();
//		this.message = message;
//		this.details = details;
//		this.httpCode = httpCode;
//		this.timestamp = timestamp;
//	}
//	   
//	   
//	   
//
//}


package com.cg.exception;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;

public class ExceptionResponse {

    private String message;
    private String details;
    private HttpStatus httpCode;
    private LocalDate timestamp;

    public ExceptionResponse(String message, String details, HttpStatus httpCode, LocalDate timestamp) {
        this.message = message;
        this.details = details;
        this.httpCode = httpCode;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}

