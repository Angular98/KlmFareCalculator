package com.fare.business.domain.model;

import java.util.ArrayList;
import java.util.List;

public class FareDetails {
	private String origin;
	private String destination;
	private String fare;
	private ErrorResponse errorResponse;
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	private List<Link> links = new ArrayList<>();
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setRel(rel);
		link.setUrl(url);
		
	}
	

}
