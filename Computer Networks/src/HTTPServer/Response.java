package HTTPServer;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Response {
	
	String initial;
	Map<String, String> headers = new HashMap<String, String>();
	ArrayList<String> body = new ArrayList<String>();
	Method command;
	Request req = new Request();
	String head = "";
	String info = "";
	byte[] infoBytes= null;
	String path;

	
	public Response(Map<String, String> headers, ArrayList<String> body, Method command, String path) {
		this.command = command;
		this.headers = headers;
		this.body = body;
		this.path = path;
		initial = "";
	}
	
	public byte[] getResponse(boolean error) {
		if(error || command == Method.ERROR) {
			initial = "HTTP/1.1 500 SERVER ERROR\n";
			setHead();
			return (initial + head).getBytes();
		}
		if(! req.correctRequest(headers, command)) {
			initial = "400 BAD REQUEST\n";
			setHead();
			return (initial + head).getBytes();
		}
		if(command == Method.GET) {
			setInfo();
			setHead();
			byte[] header = (initial + head).getBytes();
			int lenHeader = header.length;
			if(infoBytes != null) {
			byte[] totalInfo = new byte[lenHeader + infoBytes.length];
			for(int i = 0; i < lenHeader; i++) {
				totalInfo[i] = header[i];
			}
			for(int j = 0; j < infoBytes.length; j++) {
				totalInfo[j+lenHeader] = infoBytes[j];
			}
			return totalInfo;
			}
			else
				return (initial+head).getBytes();
		}
		else if(command == Method.HEAD) {
			setInfo();
			setHead();
			info = "";
			return (initial + head).getBytes();
		}
		else if(command == Method.POST || command == Method.PUT) {
			saveInfo();
			setHead();
			return (initial + head).getBytes();
		}
		else {
			initial = "HTTP/1.1 400 BAD REQUEST\n";
			return initial.getBytes();
		}
		
	}
	
	private void setInfo() {
		try {
			File file = new File(this.path);
			Date date = null;
			Date modifiedDate = new Date();
			SimpleDateFormat dsf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			if(headers.containsKey("if-modified-since")) {
				date = getDate();
				modifiedDate = getModifiedDate(dsf.format(file.lastModified()));

			}
			if(date == null || date.before(modifiedDate)){
				if(path.substring(path.length()-4).equalsIgnoreCase("html")){
						FileReader fr = new FileReader(file);
						BufferedReader reader = new BufferedReader(fr);
						String nextLine = reader.readLine();
						while(nextLine != null) {
							info += (nextLine + "\n");
							nextLine = reader.readLine();
						}
						infoBytes = info.getBytes();
						info = "";
						initial = "HTTP/1.1 200 OK\n";
						reader.close();
				}
				else if(path.substring(path.length()-4).equalsIgnoreCase(".jpg")) {
					BufferedImage image = ImageIO.read(new File(this.path));
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					ImageIO.write(image, "jpg", bos);
					byte[] data = bos.toByteArray();
//					String str = new String(data);
//					System.out.println(data);
//					System.out.println(str);
					infoBytes = data;
					initial = "HTTP/1.1 200 OK\n";
					bos.close();
//					initial = "HTTP/1.1 500 SERVER ERROR";
//					info = "";
				}
			}
			else {
				initial = "HTTP/1.1 304 NOT MODIFIED\n";
				info = "";
			}
//			
		}
		catch(FileNotFoundException exc) {
			initial = "HTTP/1.1 404 FILE NOT FOUND\n";
			info = "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			initial = "HTTP/1.1 500 SERVER ERROR\n";
			info = "";
			System.out.println("Failed setInfo");
		}
	}
	
	private void saveInfo() {
		try {
			System.out.println(body);
			if(command == Method.PUT) {
				File file = new File(this.path);
				if(file.exists()) {
					file.delete();
//					initial = "HTTP/1.1 400 BAD REQUEST\n";
				}
//				else {
					FileWriter fw = new FileWriter(this.path, true);
					BufferedWriter bw = new BufferedWriter(fw);
					for(int i = 0; i < body.size(); i++) {
						bw.write(body.get(i));
						bw.newLine();
					}
					bw.close();
					fw.close();
					initial = "HTTP/1.1 200 OK\n";
				}
//			}
			else if(command == Method.POST) {
				FileWriter fw = new FileWriter(this.path, true);
				BufferedWriter bw = new BufferedWriter(fw);
				for(int i = 0; i < body.size(); i++) {
					bw.write(body.get(i));
					bw.newLine();
				}
				bw.close();
				fw.close();
				initial = "HTTP/1.1 200 OK\n";
			}
			
		} catch (IOException e) {
			initial = "HTTP/1.1 500 SERVER ERROR\n";
			info = "";
		}
	}
	
	private void setHead() {
		Date date = new Date();
		head += "Date: " + date + "\n";
		if(infoBytes != null && infoBytes.length > 0) {
			head += "Content-Length: " + infoBytes.length + "\n";
			if (path.substring(path.length()-4).equalsIgnoreCase("html")) {
				head += "Content-Type: text/html\n";
			}
			else if(path.substring(path.length()-4).equalsIgnoreCase(".jpg")) {
				head += "Content-Type: image/jpeg\n";
			}
		}
		head += "\n";
	}
	
	private Date getDate() {
		System.out.println(headers.get("if-modified-since"));
		String[] parsedDate = headers.get("if-modified-since").split(" ");
		Date date = new Date();
		switch(parsedDate[1]) {
		case "Jan":
			date.setMonth(0);
			break;
		case "Feb":
			date.setMonth(1);
			break;
		case "Mar":
			date.setMonth(2);
			break;
		case "Apr":
			date.setMonth(3);
			break;
		case "May":
			date.setMonth(4);
			break;
		case "Jun":
			date.setMonth(5);
			break;
		case "Jul":
			date.setMonth(6);
			break;
		case "Aug":
			date.setMonth(7);
			break;
		case "Sep":
			date.setMonth(8);
			break;
		case "Oct":
			date.setMonth(9);
			break;
		case "Nov":
			date.setMonth(10);
			break;
		case "Dec":
			date.setMonth(11);
			break;
		default:
			date.setMonth(0);
			break;
		}
		date.setDate(Integer.parseInt(parsedDate[2]));
		String[] parsedTime = parsedDate[3].split(":");
		date.setHours(Integer.parseInt(parsedTime[0]));
		date.setMinutes(Integer.parseInt(parsedTime[1]));
		date.setSeconds(Integer.parseInt(parsedTime[2]));
		date.setYear(Integer.parseInt(parsedDate[4].substring(0, 4))-1900);
		return date;
	}
	
	private Date getModifiedDate(String str) {
		Date date = new Date();
		String [] parsedFormat = str.split(" ");
		String[] parsedDate = parsedFormat[0].split("/");
		String[] parsedTime = parsedFormat[1].split(":");
		date.setSeconds(Integer.parseInt(parsedTime[2]));
		date.setMinutes(Integer.parseInt(parsedTime[1]));
		date.setHours(Integer.parseInt(parsedTime[0]));
		date.setDate(Integer.parseInt(parsedDate[1]));
		date.setMonth(Integer.parseInt(parsedDate[0])-1);
		date.setYear(Integer.parseInt(parsedDate[2]) - 1900);
		return date;
 	}
}
