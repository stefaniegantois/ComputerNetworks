package HTTPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import HTTPServer.HTTPResponse;

public class HTTPConnection {
	
	private final String host;
	private final int port;
	
	private Socket socket;
	private PrintWriter out; //To send data through the socket to the server
	private BufferedReader in; //To get the server's response
	
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
	
	public void openConnection() throws UnknownHostException, IOException {
		if (socket == null || socket.isClosed()) {
			socket = new Socket(getHost(),getPort());
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
	}
	
	public void closeConnection() throws IOException {
		if (socket != null) {
			out.close();
			in.close();
			socket.close();
		}
	}
	
	public HTTPResponse sendRequest(HTTPRequest request) {
		return new HTTPResponse(request);
	}

}