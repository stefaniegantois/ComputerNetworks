package HTTPClient;

import java.io.BufferedReader;
import java.net.Socket;

import HTTPServer.HTTPResponse;

public class HTTPConnection {
	
	private final String host;
	private final int port;
	
	private Socket socket;
	
	public HTTPConnection (String host) {
		this.host=host;
		this.port=80;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getHost() {
		return host;
	}
	
	public void openConnection() {
		socket = new Socket(getHost(),getPort());
	}
	
	public void closeConnection() {
		socket.close();
	}
	
	public HTTPResponse sendRequest(HTTPRequest request) {
		return new HTTPResponse(request);
	}

}