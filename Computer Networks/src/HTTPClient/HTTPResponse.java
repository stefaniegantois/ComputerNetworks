package HTTPClient;

import java.io.BufferedReader;
import java.util.Map;

public  class HTTPResponse {
	
	private int statusCode;
	private String body;
	private Map <String,String> headers;
	
	public HTTPResponse(BufferedReader in, HTTPRequest request) {
		String inputLine = in.readLine();
		
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