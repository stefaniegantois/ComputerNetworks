package HTTPClient;

import java.util.HashMap;
import java.util.Map;


public class HTTPRequest {
	
	private HTTPCommand command;
	private String path;
	private Map<String, String> headers = new HashMap<>();
	private Object body;
	
	
	public HTTPRequest (String command, String path) {
		this(command, path, null, null);
	}
	
	public HTTPRequest (String command, String path, Map <String, String> headers, Object body) {
		setCommand(command);
		setPath(path);
		setHeaders(headers);
		setBody(body);
	}
	
	public Object getBody() {
		return body;
	}
	
	private void setBody(Object body) {
		// TODO Auto-generated method stub
		
	}
	
	public Map<String,String> getHeaders() {
		return headers;
	}

	private void setHeaders(Map<String, String> headers) {
		// TODO Auto-generated method stub
		
	}
	
	public String getPath() {
		return path;
	}

	private void setPath(String path) {
		// TODO Auto-generated method stub
		
	}
	
	public HTTPCommand getCommand() {
		return command;
	}

	private void setCommand(String command) {
		// TODO Auto-generated method stub
		
	}
}
