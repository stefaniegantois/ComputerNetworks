package HTTPClient;

public class Client {
	private String command;
	private String URI;
	private String host;
	private int port;
	
	public Client (String command, String URI, int port) {
		setCommand (command);
		setURI (URI);
		//setHost (URI.substring(0, URI.indexOf("/")));
		setPort (port);
		
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