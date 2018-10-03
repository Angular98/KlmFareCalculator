package com.fare.business.error.handler;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fare.business.FareCalculatorBusinessServiceConstants;
import com.fare.business.domain.model.ErrorResponse;
import com.fare.business.domain.model.FareDetails;

@ControllerAdvice(assignableTypes = com.fare.business.controller.FareCalculatorBusinessController.class)
public class ServiceExceptionHandler {
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<FareDetails> handleServiceException(HttpMessageNotReadableException exception) {
		FareDetails fareDetailsErrorResponse = new FareDetails();
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorDescription(FareCalculatorBusinessServiceConstants.ERROR_PARSING_DATA);
		fareDetailsErrorResponse.setErrorResponse(errorResponse);
		return new ResponseEntity<FareDetails>(fareDetailsErrorResponse,HttpStatus.BAD_REQUEST);

	}

}
