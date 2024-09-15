//package com.cg.exception;
//
//import java.net.http.HttpHeaders;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Stream;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//	
//	
//	@ExceptionHandler(Exception.class)
//		public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//		    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),"500 Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR,
//		    		LocalDate.now());
//		    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	   
//	   @Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//		   
//		   BindingResult bs=ex.getBindingResult();
//		    List<FieldError> fieldErrors = bs.getFieldErrors();
//		    Stream<FieldError> stream = fieldErrors.stream();
//		    Function<FieldError,String > fun=((errors)->errors.getField()+":"+errors.getDefaultMessage());
//		    Stream<String> map = stream.map(fun);
//		    List<String> list = map.toList();
//			ExceptionResponse response=new ExceptionResponse( list.toString(),"Validastion Error", HttpStatus.INTERNAL_SERVER_ERROR,LocalDate.now());
//			
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	   
//		
//	}



package com.cg.exception;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),
                "500 Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, LocalDate.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @Override
 	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
 			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
 		   
 		   BindingResult bs=ex.getBindingResult();
 		    List<FieldError> fieldErrors = bs.getFieldErrors();
 		    Stream<FieldError> stream = fieldErrors.stream();
 		    Function<FieldError,String > fun=((errors)->errors.getField()+":"+errors.getDefaultMessage());
 		    Stream<String> map = stream.map(fun);
 		    List<String> list = map.toList();
 			ExceptionResponse response=new ExceptionResponse( list.toString(),"Validastion Error", HttpStatus.INTERNAL_SERVER_ERROR,LocalDate.now());
 			
 			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 	}
}

