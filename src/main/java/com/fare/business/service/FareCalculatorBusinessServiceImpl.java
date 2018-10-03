package com.fare.business.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fare.business.FareCalculatorBusinessServiceConstants;
import com.fare.business.proxy.FareCalculatorBusinessProxy;
import com.fare.business.response.builder.FareCalculatorBusinessResponseBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * This class return the fare details along with origin and destination details
 * 
 * @author santhosh
 *
 */
@Service
public class FareCalculatorBusinessServiceImpl implements FareCalculatorBusinessService {

	private final FareCalculatorBusinessProxy fareCalculatorBusinessProxy;
	private final FareCalculatorBusinessResponseBuilder fareCalculatorBusinessResponseBuilder;

	/**
	 * Autowiring the objects
	 * 
	 * @param fareCalculatorBusinessProxy
	 */
	@Autowired
	public FareCalculatorBusinessServiceImpl(final FareCalculatorBusinessProxy fareCalculatorBusinessProxy,
			final FareCalculatorBusinessResponseBuilder fareCalculatorBusinessResponseBuilder) {
		this.fareCalculatorBusinessProxy = fareCalculatorBusinessProxy;
		this.fareCalculatorBusinessResponseBuilder = fareCalculatorBusinessResponseBuilder;
	}

	/**
	 * Method used to return fare details in case mockservice is not available
	 */

	public String errorMessage() {
		return "System is down";
	}

	/**
	 * Method to call proxy class which inturn calls mock service which returns only
	 * origin/destination codes
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Override
	@HystrixCommand(fallbackMethod = "errorMessage")
	public String calculateFare(Map<String, String> requestHeader, Map<String, String> requestParam, String origin,
			String destination) throws InterruptedException, ExecutionException {
		if (requestParam.get(FareCalculatorBusinessServiceConstants.AUTHORIZATION_TOKEN) == null) {

			CompletableFuture<String> mockServiceResponse = fareCalculatorBusinessProxy.callMock();

			String response = fareCalculatorBusinessResponseBuilder.buildResponse(mockServiceResponse.get(),
					requestParam);
			return response;
		} else
			return FareCalculatorBusinessServiceConstants.USER_NOT_AUTHENTICATED;

	}

	/**
	 * Method to call proxy class callMockToRetrieveOrigin method to retrieve origin
	 * details
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Override
	public String retrieveOriginDetails(Map<String, String> requestHeader, Map<String, String> requestParam)
			throws InterruptedException, ExecutionException {
		CompletableFuture<String> mockServiceResponse = fareCalculatorBusinessProxy.callMockToRetrieveOrigin();
		if (requestParam.get(FareCalculatorBusinessServiceConstants.AUTHORIZATION_TOKEN) != null) {

			return mockServiceResponse.get();
		} else
			return FareCalculatorBusinessServiceConstants.USER_NOT_AUTHENTICATED;

	}

	/**
	 * Method to call proxy class callMockToRetrieveDestination method to retrieve
	 * destination details
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Override
	public String retrieveDestinationDetails(Map<String, String> requestHeader, Map<String, String> requestParam)
			throws InterruptedException, ExecutionException {
		CompletableFuture<String> mockServiceResponse = fareCalculatorBusinessProxy.callMockToRetrieveDestination();

		if (requestParam.get(FareCalculatorBusinessServiceConstants.AUTHORIZATION_TOKEN) != null) {

			return mockServiceResponse.get();
		} else
			return FareCalculatorBusinessServiceConstants.USER_NOT_AUTHENTICATED;
	}
}
