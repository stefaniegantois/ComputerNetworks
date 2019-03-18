package HTTPClient;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		for (String arg: args) {
			System.out.println(arg);
		}
		if (args.length==0||args.length>4) {
			throw new IllegalArgumentException("Unvalid arguments");
		}
		String command = args[0];
		String URI = args[1];
		int port = Integer.parseInt(args[2]);
		if (args.length==4) {
			String version = args[3];
			new Client (command, URI, port, version);
		} else {
			new Client (command, URI, port);
		}
	}
}