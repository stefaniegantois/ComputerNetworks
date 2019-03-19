package HTTPClient;

import java.io.BufferedReader;

public  class HTTPResponse {
	
	private int statusCode;
	
	public HTTPResponse(HTTPRequest request) {
		
	}
	
	public HTTPResponse(BufferedReader in, HTTPRequest request) {
		// TODO Auto-generated constructor stub
	}

	public String getStatus () {
		switch(getStatusCode()) {
		case 200:
			return "OK";
		case 304:
			return "Not Modified";
		case 400:
			return "Bad Request";
		case 404:
			return "Not Found";
		case 500:
			return "Server Error";
		default: 
			return "Unknow Status Code";
		}
	}

	private int getStatusCode() {
		return statusCode;
	}
}