package lab1;

import java.io.*;
import java.net.*;
import java.util.*;



public class GetRequest {
	private Socket socket;


	public GetRequest() {
		
	}
	
	public void sendGetRequest(String url, String param, Boolean isVerbose) 
	{
		try {
			String tempURL = url;
			Socket socket = new Socket(url, 80);
			

			PrintWriter wtr = new PrintWriter(socket.getOutputStream());
	        //Prints the request string to the output stream
			String query ="";
			
			if(param!=null) 
			{
				query = "Get /get?" + param +" HTTP/1.1\r\n"+ "Host: " + tempURL +"\r\n";  
			}
			else
			{
				query = "Get / HTTP/1.1\r\n"+ "Host: " + tempURL +"\r\n";  
			}
			
			if(ParmaReader.hasHead)
			{
				for(String key : ParmaReader.contentMap.keySet()) 
				{
					query = query + key + ": " + ParmaReader.contentMap.get(key) + "\r\n";
				}
				query = query +"\r\n"; 
			}
			else
			{
				query = query +"\r\n"; 
			}
	
	        //Create a server request	
	       // wtr.println(get);
	        //wtr.println(host);
			wtr.println(query);
	        wtr.println("");
	        wtr.flush();
	        
	        //observe the request on console
	        if(isVerbose)
	        {
		        System.out.print(query+"\r\n");
		       
		        System.out.println("");
	        }


	        //Creates a BufferedReader that contains the server response
	        BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String outStr;
	        if(isVerbose) {
		        //Prints each line of the response 
		        while((outStr = bufRead.readLine()) != null){
		            System.out.println(outStr);
	        }
	        }
	        else
	        {
		        //Prints each line of the response 
		        while((outStr = bufRead.readLine()) != null){
		        	if((outStr.contains("{")||outStr.contains("\"")||outStr.contains("}"))) 
		        	{
			            System.out.println(outStr);
				            if(outStr.equals("0")||outStr.equals("</body>"))
				            {
				            	break;
				            }
			        	}
		        }
	        }
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
	
	
	public void sendRequest (String str) {
		try {
			socket = new Socket("www.google.com", 80);
			

			PrintWriter wtr = new PrintWriter(socket.getOutputStream());
	        //Prints the request string to the output stream
			
			String get = "GET / HTTP/1.1";
			String host = "Host: www.google.com";
			
	        //Create a server request			
	        wtr.println(get);
	        wtr.println(host);
	        wtr.println("");
	        wtr.flush();
	        
	        //observe the request on console
	        System.out.print(get+"\r\n");
	        System.out.print(host+"\r\n");
	        System.out.print("\r\n");

	        //Creates a BufferedReader that contains the server response
	        BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String outStr;

	        //Prints each line of the response 
	        while((outStr = bufRead.readLine()) != null){
	            System.out.println(outStr);
	            if(outStr.equals("0")||outStr.equals("</body>"))
	            {
	            	break;
	            }
	        }
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
