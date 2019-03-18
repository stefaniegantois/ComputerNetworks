package HTTPClient;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {
	private String command;
	private String URI;
	private String host;
	private int port;
	private HTTPVersion version;
	
	public Client (String command, String URI, int port) throws UnknownHostException, IOException {
		this(command,URI,port,"HTTP/1.1");
		//setCommand (command);
		//setURI (URI);
		//setHost (URI.substring(0, URI.indexOf("/")));
		setPort (port);
		//new HTTPRequest (HTTPCommand.fromString(command),URI);
		//dit is een test;
	}
	
	public Client (String command, String URI, int port, String version) throws UnknownHostException, IOException {
		if (HTTPCommand.fromString(command)==null) {
			throw new IllegalArgumentException(command + " is not recognized");
		}
		setCommand(command);
		this.version = new HTTPVersion(version);
		if (getVersion().getMajor()!=1 || getVersion().getMinor()!=1) {
			throw new IllegalArgumentException("unsupported version");
		}
		
		new HTTPConnection(host, port).openConnection();
		
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
	
	public String getHost() {
		return host;
	}
	
	private void setHost(String host) {
		System.out.println(host);
		this.host=host;
	}

	public String getURI () {
		return URI;
	}
	
	private void setURI(String URI) {
		this.URI = URI;
	}
	
	public String getCommand () {
		return command;
	}
	
	private void setCommand(String command) {
		this.command = command;
	}
	
}