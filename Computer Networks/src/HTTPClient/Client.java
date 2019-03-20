package HTTPClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class Client {
	private HTTPCommand command;
	private String URI;
	private String host;
	private int port;
	private HTTPVersion version;
	
	public Client (String command, String URI, int port) throws UnknownHostException, IOException, URISyntaxException {
		this(command,URI,port,"HTTP/1.1");
	}
	
	public Client (String command, String URI, int port, String version) throws UnknownHostException, IOException, URISyntaxException {
		if (HTTPCommand.fromString(command)==null) {
			throw new IllegalArgumentException(command + " is not recognized");
		}
		setCommand(command);
		setURI(URI);
		//setHost(URI);
		//setHost (URI.substring(0, URI.indexOf("/")));
		setPort(port);
		this.version = new HTTPVersion(version);
		if (getVersion().getMajor()!=1 || getVersion().getMinor()!=1) {
			throw new IllegalArgumentException("unsupported version");
		}
		HTTPConnection connection = new HTTPConnection(URI, port);
		connection.openConnection();
		HTTPRequest request = new HTTPRequest(getCommand(), getURI(), this);
		System.out.println(request.toString());
		HTTPResponse response = connection.sendRequest(request);
		
	}
	
	public HTTPVersion getVersion() {
		return this.version;
	}
	
	public void setVersion(HTTPVersion version) {
		getVersion().setMajor(version.getMajor());
		getVersion().setMinor(version.getMinor());
	}
	
	public void setVersion(int major, int minor) {
		getVersion().setMajor(major);
		getVersion().setMinor(minor);
	}

	public int getPort () {
		return port;
	}
	
	private void setPort(int port) {
		this.port=port;
	}

	public String getURI () {
		return URI;
	}
	
	private void setURI(String URI) {
		this.URI = URI;
	}
	
	public HTTPCommand getCommand () {
		return command;
	}
	
	private void setCommand(String command) {
		this.command = HTTPCommand.fromString(command);
	}
	
}