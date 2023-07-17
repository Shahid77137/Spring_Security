package com.masai.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {

//	@ExceptionHandler(InvalidNumberException.class)
//	public ResponseEntity<MyErrorDetails> myExpHandler1(InvalidNumberException ie,WebRequest req){
//		
//		System.out.println("Inside myExceptionHandler method");
//		
//		MyErrorDetails err = new MyErrorDetails();
//		err.setMessage(ie.getMessage());
//		err.setTimestamp(LocalDateTime.now());
//		err.setDescription(req.getDescription(false));
//		
//		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
//		
//	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExpHandler2(Exception ie,WebRequest req){
		
		System.out.println("Inside myExceptionHandler2 method");
		
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(ie.getMessage());
		err.setTimestamp(LocalDateTime.now());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> myExpHandler3(NoHandlerFoundException ne,WebRequest req){
		
		System.out.println("Inside myExceptionHandler3 method");
		
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(ne.getMessage());
		err.setTimestamp(LocalDateTime.now());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myExpHandler4(MethodArgumentNotValidException me){
		
		System.out.println("Inside myExceptionHandler3 method");
		
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage("Invalid argument");
		err.setTimestamp(LocalDateTime.now());
		err.setDescription(me.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
	}
	
}
