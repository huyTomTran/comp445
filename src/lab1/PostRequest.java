package lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Stack;

public class PostRequest {
	
	public PostRequest() 
	{
		
	}
	
	public void sendPostRequest(String url, String param, Boolean isVerbose) 
	{

	/*
		//delete http:// for socket
		String tempURL = url.substring(7, url.length());
		if(tempURL.substring(tempURL.length()-1).equals("/")) 
		{
			tempURL=tempURL.substring(0, tempURL.length()-1);
			System.out.println(tempURL);
		}
		*/
		try {
			String tempURL = url;
			Socket socket = new Socket(tempURL, 80);
			

			PrintWriter wtr = new PrintWriter(socket.getOutputStream());
	        //Prints the request string to the output stream

			String query = "Post /post?" + param +" HTTP/1.1\r\n"+ "Host: " + tempURL +"\r\n";  
			
			
			
			if(ParmaReader.hasHead)
			{
				for(String key : ParmaReader.contentMap.keySet()) 
				{
					query = query + key + ": " + ParmaReader.contentMap.get(key) + "\r\n";
				}
				//query = query +"\r\n"; 
			}
			
			if(ParmaReader.hasData||ParmaReader.hasFile) 
			{
				if(ParmaReader.hasFile)
				{
					query = query + ("Content-Length: " + ParmaReader.tempdata.length() + "\r\n\r\n" + ParmaReader.tempdata + "\r\n\r\n");
				}
				else
				{
					ParmaReader.tempdata =  ParmaReader.tempdata.substring(0,ParmaReader.tempdata.length()-1);
					query = query + ("Content-Length: " + ParmaReader.tempdata.length() + "\r\n\r\n" + ParmaReader.tempdata + "\r\n\r\n");
				}
			}
			else
			{
				query = query +("/r/n");
			}
			
	        //Create a server request	
	        //wtr.println(method);
	        //wtr.println(host);
	        wtr.println(query);
	        wtr.println("");
	        wtr.flush();
	        



	        //Creates a BufferedReader that contains the server response
	        BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String outStr;
	        if(isVerbose) 
	        {
		        //Prints each line of the response 
		        while((outStr = bufRead.readLine()) != null){
		            System.out.println(outStr);
		            if(outStr.equals("0")||outStr.equals("</body>"))
		            {
		            	break;
		            }
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
