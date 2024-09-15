package com.cg.ExceptionResponse;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleExpection extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	ResponseEntity<Object> handleAllException(Exception ex,WebRequest request){
		
		ExceptionResponse response=new ExceptionResponse(request.getDescription(false),ex.getMessage(),"500 Internal Server Error",LocalDate.now());
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	HttpHeaders headers, HttpStatusCode status, WebRequest request) {

	BindingResult bs= ex.getBindingResult();
	List <String> erros=bs.getFieldErrors().stream().map(t -> t.getField()+":"+t.getDefaultMessage()).toList();                          
	ExceptionResponse response=new ExceptionResponse(request.getDescription(false) , erros.toString(),"Not Valid",LocalDate.now());
	return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
}
 
}
