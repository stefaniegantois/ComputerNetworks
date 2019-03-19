package HTTPServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Handler implements Runnable{

	Socket socket;
	String initial;
	Map<String, String> headers = new HashMap<String, String>();
	ArrayList<String> body = new ArrayList<String>();
	Method command;
	String path;
	String protocol;
	Request req = new Request();
	boolean openSocket = true;
	boolean errorOccured = false;
	
	public Handler(Socket socket) {
		this.socket = socket;
		
	}
	
	//Creates buffered reader and data output stream. Controls flow of one connection.
	public void run(){
		BufferedReader inReader = null;
		DataOutputStream output = null;
		try {
			inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new DataOutputStream(socket.getOutputStream());
			while(openSocket) {
				boolean newRequest = parseRequest(inReader);
				if(newRequest) {
					Response resp = new Response(headers, body, command, path);
					byte[] outBytes = resp.getResponse(errorOccured);
					output.write(outBytes);
					if(headers.containsKey("connection") && "close".equals(headers.get("connection")))
						openSocket = false;
				}
				if(inReader == null)
					break;
				this.errorOccured = false;
				initial = "";
				command = null;
				headers.clear();
				body.clear();
				protocol = "";
			}
			inReader.close();
			output.close();
			socket.close();
			System.out.println("Done.");
			
		}
		catch(IOException exc){
			this.errorOccured = true;
			System.out.println("Failed run");
		}
		finally {
			if (inReader != null) {
				try {
					inReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(output != null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private boolean parseRequest(BufferedReader reader) {
		try {
			initial = reader.readLine();
			if(initial != null && initial.length() > 3) {
				command = req.getCommand(initial);
				path = req.getAdress(initial);
				protocol = req.getProtocol(initial);
				String next = reader.readLine();
				while(next.contains(":")) {
					parseHeader(next);
					next = reader.readLine();
				}
				if(command == Method.POST || command == Method.PUT) {
					//next = reader.readLine();
					while(reader.ready()) {
						next = reader.readLine();
						body.add(next);
						System.out.println(next);
					}
					
				}
				return true;
			}
			return false;
		}
		catch(IOException exc) {
			this.errorOccured = true;
			System.out.println("Failed parseRequest");
			return true;
		}
		catch(NullPointerException exc) {
			this.errorOccured = true;
			return true;
		}
	}
	
	private void parseHeader(String head) {
		int index = head.indexOf(":");
		try {
			headers.put(head.substring(0, index).toLowerCase(), head.substring(index+2));
		}
		catch(IndexOutOfBoundsException exc) {
			System.out.println("Invalid Header: " + head);
			this.errorOccured = true;
			System.out.println("Failed parseHeader");
		}
	}
	

	
	
}
