package HTTPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class HTTPResponse {
	
	private int statusCode;
	//private String body;
	//private Map <String,String> headers;
    public static final Pattern headerPattern = Pattern.compile("(.*): (.*)");
	
	public HTTPResponse(BufferedReader in, HTTPRequest request) throws IOException {
			String currentLine = in.readLine();
			char currentChar;
			int i;
			
			
			String headerKey = "";
			String headerValue = "";
			Map<String, String> headers = new HashMap<String, String>();
			//System.out.println("Before while");
			Matcher m;
	        while (!"".equals(currentLine = in.readLine())) {
	        	System.out.println(currentLine);
	        	m = headerPattern.matcher(currentLine);
	            if (m.find()) {
	                headers.put(m.group(1), m.group(2));
	            }
	        }
	        this.headers = headers;
	        System.out.println("Headers set");
		
	        i = 0;
	    	String body = "";

	        if(headers.containsKey("Content-Length")) {
	        	int l = Integer.parseInt(headers.get("Content-Length"));
	        	char[] buffer = new char[l];
	        	while (i != l) {
	                i += in.read(buffer, i, l - i);
	        	}
	        	this.body = String.valueOf(buffer);
	        	
	        } else if (false) {
	        	//TODO chunked
	        	
	        }
		    System.out.println(this.body);
		        
		}
		
		@Override
		public String toString() {
			String returnString = "";
			
			returnString += "HTTP/1.1 200  OK\n";
			for (String key : getHeaders()) {
				returnString += key + ": " + getHeader(key) + "\n";
			}
			
			return returnString;
		}
		
		public String getBody() {
			return this.body;
		}

		public String body;
		
		public Map<String,String> headers;
		
		public Set<String> getHeaders(){
			return this.headers.keySet();
		}
		private String getHeader(String header) {
			return this.headers.get(header);
		}
		}
