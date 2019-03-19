package HTTPTest;

import java.net.*;
import java.io.*;

public class TestClient {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Socket server = new Socket("localhost", 80);
		BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
		String req = "PUT / HTTP/1.1\nHost: localhost\nConnection: close\n\nTest\nFile\n4\n\n";
		DataOutputStream output = new DataOutputStream(server.getOutputStream());
		output.writeBytes(req);
		String next = reader.readLine();
		while(reader.ready()) {
			System.out.println(next);
			next=reader.readLine();
		}
		reader.close();
		output.close();
		server.close();
	}

}
