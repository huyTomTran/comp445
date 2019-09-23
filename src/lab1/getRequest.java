package lab1;

import java.io.*;
import java.net.*;
import java.util.*;



public class GetRequest {
	private Socket socket;


	public GetRequest() {
		
	}
	
	public void _sendRequest(String url, String parma, Boolean isView) 
	{

		//delete http:// for socket
		String tempURL = url.substring(7, url.length());
		
		try {
			Socket socket = new Socket(tempURL, 80);
			

			PrintWriter wtr = new PrintWriter(socket.getOutputStream());
	        //Prints the request string to the output stream
				String get = "GET / HTTP/1.1";
				String host = "Host: "+tempURL;
	
			
	        //Create a server request	
	        wtr.println(get);
	        wtr.println(host);
	        wtr.println("");
	        wtr.flush();
	        
	        //observe the request on console
	        if(isView)
	        {
		        System.out.println(get);
		        System.out.println(host);
		        System.out.println("");
	        }


	        //Creates a BufferedReader that contains the server response
	        BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String outStr;
	        if(isView) {
		        //Prints each line of the response 
		        while((outStr = bufRead.readLine()) != null){
		            System.out.println(outStr);
		            if(outStr.equals("0")||outStr.equals("</body>"))
		            {
		            	break;
		            }
	        }
	        }
	        //System.out.println("train is big");
	        //Closes out buffer and writer
	        bufRead.close();
	        wtr.close();
			if(!parma.equals(""))  
			{
				String str [] = parma.split("&");
				Stack <String> stack = new Stack <String>();
				for(String a : str) {
					stack.push(a);
				}			
				System.out.println(
				"{\n"
				+"  \"arg\": {");	
					while(!stack.empty())
					{
						String temp [] = stack.pop().split("=");
						System.out.println("    \""+temp[0]+"\" : "+"\""+temp[1]+"\"");
					}
				System.out.println("  },");
	        	System.out.println("\"headers\": {");
	        	System.out.println("  "+host + "\n  },\n  \"url\": "+url+"?"+parma+"\n}");
			}
			
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
	        System.out.println(get);
	        System.out.println(host);
	        System.out.println("");

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
