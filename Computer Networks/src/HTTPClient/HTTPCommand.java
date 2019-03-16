package HTTPClient;

public enum HTTPCommand {
	HEAD("HEAD"),
	GET("GET"),
	PUT("PUT"),
	POST("POST");
	
	private final String command;
	
	private HTTPCommand (String command) {
		this.command=command;
	}
	
	public static HTTPCommand fromString(String command) {
		switch(command.toUpperCase()) {
		case "HEAD":
			return HTTPCommand.HEAD;
		case "GET":
			return HTTPCommand.GET;
		case "PUT":
			return HTTPCommand.PUT;
		case "POST":
			return HTTPCommand.POST;
		default:
			return null;
		}
	}
	
	public String toString() {
		return this.command;
	}
}

