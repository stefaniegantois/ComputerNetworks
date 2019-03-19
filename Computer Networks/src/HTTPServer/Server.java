package HTTPServer;

import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ServerSocket welcomeSocket = new ServerSocket(80);
		System.out.println("Server listening on port 80");
		int conn = 0;
		while(true) {
			Socket client = welcomeSocket.accept();
			if(client != null) {
				conn += 1;
				System.out.println("Made Connection." + conn);
				Handler server = new Handler(client);
				Thread thread = new Thread(server);
				thread.start();
			}
		}
	}

}

