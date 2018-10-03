package com.fare.business.proxy;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fare.business.FareCalculatorBusinessServiceConstants;
import com.fare.business.error.handler.FareCalculatorRestWebServiceCallResponseErrorHandler;

/**
 * Class to build a method to call mock service
 * 
 * @author santhosh
 *
 */
@Component
public class FareCalculatorBusinessProxy {
	private final RestTemplate restTemplate = new RestTemplate();
	private final FareCalculatorRestWebServiceCallResponseErrorHandler restWebServiceCallResponseErrorHandler;

	@Value("${mock.fare-calculation.uri}")
	private String fareDetailsUrl;

	@Value("${mock.retrieve-origin.uri}")
	private String retrieveOriginDetailsUrl;

	@Value("${mock.retrieve-destination.uri}")
	private String retrieveDestinationDetailsUrl;

	public FareCalculatorBusinessProxy(
			final FareCalculatorRestWebServiceCallResponseErrorHandler restWebServiceCallResponseErrorHandler) {
		this.restWebServiceCallResponseErrorHandler = restWebServiceCallResponseErrorHandler;
	}

	/**
	 * Method to call mock service
	 * 
	 * @return
	 */
	@Async
	public CompletableFuture<String> callMock() {
		// Custom Error handler which handes error during rest ful call by rest template

		restTemplate.setErrorHandler(restWebServiceCallResponseErrorHandler);

		ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
		};
		ResponseEntity<String> response = restTemplate.exchange(
				FareCalculatorBusinessServiceConstants.FARE_DETAILS_HTTP_URL + fareDetailsUrl, HttpMethod.GET, null, responseType);
		return CompletableFuture.completedFuture(response.getBody());

	}

	/**
	 * Method to call mock service to retrieveorigin details
	 * 
	 * @return
	 */
	@Async
	public CompletableFuture<String> callMockToRetrieveOrigin() {

		// Custom Error handler which handes error during rest ful call by rest template

		restTemplate.setErrorHandler(restWebServiceCallResponseErrorHandler);
		ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
		};

		ResponseEntity<String> response = restTemplate.exchange(
				FareCalculatorBusinessServiceConstants.HTTP_URL + retrieveOriginDetailsUrl, HttpMethod.GET, null,
				responseType);

		return CompletableFuture.completedFuture(response.getBody());
	}

	/**
	 * Method to call mock service to retrieve destination details
	 * 
	 * @return
	 */
	@Async
	public CompletableFuture<String> callMockToRetrieveDestination() {

		// Custom Error handler which handes error during rest ful call by rest template

		restTemplate.setErrorHandler(restWebServiceCallResponseErrorHandler);
		ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
		};

		ResponseEntity<String> response = restTemplate.exchange(
				FareCalculatorBusinessServiceConstants.HTTP_URL + retrieveDestinationDetailsUrl, HttpMethod.GET, null,
				responseType);

		return CompletableFuture.completedFuture(response.getBody());
	}
}
