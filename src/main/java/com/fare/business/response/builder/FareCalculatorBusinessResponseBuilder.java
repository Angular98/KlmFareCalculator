package com.fare.business.response.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fare.business.FareCalculatorBusinessServiceConstants;
import com.fare.business.domain.model.FareDetails;
import com.fare.business.domain.model.Faredetail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Component
public class FareCalculatorBusinessResponseBuilder {

	public String buildResponse(String response, Map<String, String> requestQueryParam) {
		List<FareDetails> fareDetails = new ArrayList<FareDetails>();
		String originRequested = requestQueryParam.get(FareCalculatorBusinessServiceConstants.ORIGIN);
		String destinationRequested = requestQueryParam.get(FareCalculatorBusinessServiceConstants.DESTINATION);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			final JsonNode rootNode = objectMapper.readTree(response);
			final ArrayNode fareDetailsArrayNode = (ArrayNode) rootNode.get("faredetails");
			Iterator<JsonNode> fareDetailsIterator = fareDetailsArrayNode.elements();
			while (fareDetailsIterator.hasNext()) {
				FareDetails fareDetailsResponse = new FareDetails();
				final JsonNode fareDetailsNode = fareDetailsIterator.next();
				final JsonNode journeyDetailsNode = fareDetailsNode.path("journeydetails");
				final JsonNode origin = journeyDetailsNode.path("origin");
				final JsonNode destination = journeyDetailsNode.path("destination");
				if (originRequested.equals(origin) && destinationRequested.equals(destination)) {
					fareDetailsResponse.setOrigin(origin.asText());
					fareDetailsResponse.setDestination(destination.asText());
					linkBuilder(fareDetailsResponse);
					fareDetails.add(fareDetailsResponse);
				}

			}
		} catch (IOException e) {
			throw new HttpMessageNotReadableException("Marshalling Failed", e);
		}
		return fareDetails.toString();

	}

	private void linkBuilder(FareDetails fareDetailsResponse) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host("www.klm.com")
				.path("/bookflight").build();
		fareDetailsResponse.addLink(uriComponents.toString(), "bookflight");

	}

}
