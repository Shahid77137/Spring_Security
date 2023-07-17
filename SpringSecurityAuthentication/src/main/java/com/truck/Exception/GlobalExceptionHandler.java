
package com.truck.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorReport> userExceptionHandler(UserException user, WebRequest req) {

		ErrorReport details = new ErrorReport();
		details.setCode(HttpStatus.NOT_FOUND.value());
		details.setMsg(user.getMessage());
		
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(TruckException.class)
	public ResponseEntity<ErrorReport> truckExceptionHandler(TruckException user, WebRequest req) {

		ErrorReport details = new ErrorReport();
		details.setCode(HttpStatus.NOT_FOUND.value());
		details.setMsg(user.getMessage());
		
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<ErrorReport> invalidExceptionHandler(InvalidCredentialException user, WebRequest req) {

		ErrorReport details = new ErrorReport();
		details.setCode(HttpStatus.UNAUTHORIZED.value());
		details.setMsg(user.getMessage());
		
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);

	}
	
}
