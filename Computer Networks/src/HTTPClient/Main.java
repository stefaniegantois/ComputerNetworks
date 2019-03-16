package HTTPClient;

public class Main {
	
	public static void main(String[] args) {
		for (String arg: args) {
			System.out.println(arg);
		}
		String command = args[0];
		String URI = args[1];
		int port = Integer.parseInt(args[2]);
		new Client (command, URI, port);
	}
}