package org.generation.ecommercedb.model;

public class Token {
	
	private final String accessToken;

	//constructor
	public Token(String accessToken) {
		this.accessToken = accessToken;
	}//constructor

	
	//getter
	public String getAccessToken() {
		return accessToken;
	}
}
