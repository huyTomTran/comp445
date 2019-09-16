package lab1;

import java.io.*;
import java.net.*;

public class GetRequest {
	public GetRequest() {
		
	}
	
	
	
	
	
	
	public void sendRequest (String str) {
		
		try {
			Socket socket = new Socket(str, 80);
			PrintWriter wtr = new PrintWriter(socket.getOutputStream());
	        //Prints the request string to the output stream
			
			String get = "GET / HTTP/1.1";
			String host = "Host: "+str;
			
	        //Create a server request			
	        wtr.println(get);
	        wtr.println(host);
	        wtr.println("");
	        wtr.flush();
	        
	        //observe the request on console
	        System.out.println(get);
	        System.out.println(host);
	        System.out.println("");

	        //Creates a BufferedReader that contains the server response
	        BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String outStr;

	        //Prints each line of the response 
	        while((outStr = bufRead.readLine()) != null){
	            System.out.println(outStr);
	            if(outStr.equals("0"))
	            {
	            	break;
	            }
	        }
	        //System.out.println("train is big");
	        //Closes out buffer and writer
	        bufRead.close();
	        wtr.close();
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}
	
	
	
	
}
