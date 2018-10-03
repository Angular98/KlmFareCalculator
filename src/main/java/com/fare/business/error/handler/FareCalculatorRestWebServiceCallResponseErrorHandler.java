package com.fare.business.error.handler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
/**
 * Class handling the error response during restful call where we can implement the custom exception
 * @author santhosh
 *
 */
@Component
public class FareCalculatorRestWebServiceCallResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {

		return false;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {

	}

}
