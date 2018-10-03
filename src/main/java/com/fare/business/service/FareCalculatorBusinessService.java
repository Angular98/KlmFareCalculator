package com.fare.business.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface FareCalculatorBusinessService {

	String calculateFare(Map<String, String> requestHeader, Map<String, String> requestParam, String origin,
			String destination) throws InterruptedException, ExecutionException;

	String retrieveOriginDetails(Map<String, String> requestHeader, Map<String, String> requestParam)
			throws InterruptedException, ExecutionException;

	String retrieveDestinationDetails(Map<String, String> requestHeader, Map<String, String> requestParam)
			throws InterruptedException, ExecutionException;

}