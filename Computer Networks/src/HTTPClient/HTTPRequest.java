package HTTPClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class HTTPRequest {
	
	private HTTPCommand command;
	private URI uri;
	private Map<String, String> headers = new HashMap<>();
	private Object body;
	private Client client;
	
	
	public HTTPRequest (HTTPCommand command, String path) throws URISyntaxException {
		this(command, path, null,null);
	}
	
	public HTTPRequest (HTTPCommand command, String path, Client client) throws URISyntaxException {
		this(command, path, client,null);
		
	}

	public HTTPRequest (HTTPCommand command, String URI, Client client, Map<String, String> headers) throws URISyntaxException{
		setCommand(command);
		setURI(URI);
		setClient(client);
		setHeaders(headers);
		putHeader("HOST",getHost()+":"+getClient().getPort());
		putHeader("User-Agent","HTTPClientVM/1.1"); //what's this?
	}
	
	public Client getClient() {
		return this.client;
	}
	
	private void setClient(Client client) {
		this.client = client;
	}
	
	//Headers
	public String getHeaderValue (String name) {
		return this.headers.get(name);
	}
	
	public Map<String,String> getHeaders() {
		return this.headers;
	}
	
	private void putHeader(String name, String value) {
		this.headers.put(name, value);
	}

	private void setHeaders(Map<String, String> headers) {
		this.headers = headers;
		
	}
	
	//URI
	public String getPath() {
		return uri.getPath();
	}
	
	public String getHost() {
		return uri.getHost();
	}

	private void setURI(String URI) throws URISyntaxException {
		this.uri = new URI(URI);
	}
	
	//Command
	public HTTPCommand getCommand() {
		return command;
	}

	private void setCommand(HTTPCommand command) {
		this.command = command;
	}
	
	@Override
	public String toString () {
		String string = "";
		string += getCommand() + " ";
		
		String path = getPath();
		if (path == null) {
			path = "/";
		}
		string += getPath() + " ";
		
		if(getClient()==null) {
			string += "HTTP/1.1\n";
		} else {
			string += getClient().getVersion().toString() + "\n";
		}
		
		for (String name: getHeaders().keySet()) {
			string += name +": " + getHeaderValue(name) + "\n";
		}
		
		string += "\n";
				
		return string;
	}
	
}
