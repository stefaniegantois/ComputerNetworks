package HTTPServer;

import java.util.Map;

public class Request {
	
	public Method getCommand(String initial) {
		int index = initial.indexOf(" ");
		String command = initial.substring(0, index);
		if(command.equalsIgnoreCase("get"))
			return Method.GET;
		else if(command.equalsIgnoreCase("head"))
			return Method.HEAD;
		else if(command.equalsIgnoreCase("put"))
			return Method.PUT;
		else if(command.equalsIgnoreCase("post"))
			return Method.POST;
		else
			return Method.ERROR;
	}
	
	public String getAdress(String initial) {
		//int firstIndex = initial.indexOf(" ");
		//int secondIndex = initial.indexOf(" ", firstIndex + 1) + firstIndex;
		String[] parsedInitial = initial.split(" ");
		if(parsedInitial[1].equalsIgnoreCase("/"))
			return "webpage/index.html";
		return ("webpage/" + parsedInitial[1].substring(1));
	}
	
	public String getProtocol(String initial) {
		String[] parsedInitial = initial.split(" ");
		return parsedInitial[2];
	}
	
	public boolean checkCommand(Method command) {
		if(command == Method.ERROR)
			return false;
		return true;
	}
	
	public boolean correctRequest(Map<String, String> headers, Method command) {
		if(command != Method.ERROR && headers.containsKey("host"))
			return true;
		return false;
	}
	

}

