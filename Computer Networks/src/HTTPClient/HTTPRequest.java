package HTTPClient;

import java.util.HashMap;
import java.util.Map;


public class HTTPRequest {
	
	private HTTPCommand command;
	private String path;
	private Map<String, String> headers = new HashMap<>();
	private Object body;
	
	private int major;
	private int minor;
	
	public HTTPRequest (HTTPCommand command, String path) {
		this(command, path, null, null);
	}
	
	public HTTPRequest (HTTPCommand command, String path, Map <String, String> headers, Object body) {
		setCommand(command);
		setPath(path);
		setHeaders(headers);
		setBody(body);
	}
	
	public int getMajor() {
		return major;
	}
	
	public void setMajor(int major) {
		this.major=major;
	}
	
	public int getMinor() {
		return minor;
	}
	
	public void setMinor(int minor) {
		this.minor=minor;
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
		if (path == null)
			this.path="/";
		else this.path=path;
	}
	
	public HTTPCommand getCommand() {
		return command;
	}

	private void setCommand(HTTPCommand command) {
		this.command = command;
	}
}
