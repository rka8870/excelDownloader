/**
 * 
 */
package com.downloader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** Raunak Kumar Agarwal **/
@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(value = FileOperationException.class)
	public ResponseEntity<String> fileOperationExceptionHandler(FileOperationException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
	}
	
	
}
