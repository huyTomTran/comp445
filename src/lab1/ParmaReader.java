package lab1;

import java.util.*;

public class ParmaReader {
 
	private String parma[];
	private boolean isHttpcCheck = false;
	private boolean isView = false;
	private boolean isHelp = false;
	private boolean hasKey = false;
	private boolean isGet = false;
	private boolean isPost = false;
	private String URL;
	private GetRequest getRequest;
	
	public ParmaReader(String str) {
		super();
		this.parma = str.split("\\s+");
		getRequest = new GetRequest();
	}

	public void getRequest() 
	{
		if(parma.length<0) 
		{
			System.out.println("you missing the command line");
		}
		else
		{
			//read the parma
			for(int i=0;i<parma.length;i++) 
			{
				if(parma[0].equals("httpc"))
				{
					isHttpcCheck = true;
				}
	
				if(parma[i].equals("help"))
				{
					int index = i;
					int x = parma.length;
					if(parma.length<=index+1) 
					{
						//System.out.println("just help");
						isHelp = true;
						Help.helpMenu();
					}
					else 
					{
						if(parma[index+1].equals("get"))
						{
							isHelp = true;
							Help.getHelpMenu();
						}
						else 
						{
							isHelp = true;
							Help.postHelpMenu();
						}
					}
				}
				
				if(parma[i].equals("get")) 
				{
					isGet = true;					
				}
				if(parma[i].equals("post")) 
				{
					isPost = true;					
				}
				
				if(parma[i].contains("http://")){
					URL = parma[i];
				}
				if(parma[i].equals("-v"))
				{
					isView = true;
				}
			}
			
			/*
			 processing
			 */
			if(isHttpcCheck == true && isGet==true && isHelp == false && isPost == false) 
			{					
				String url = "";
				String parmaa = "";
				int temp =0;
				for(int i =0 ; i < URL.length();i++) 
				{
					if(URL.charAt(i)=='?') {
						temp = i;
						url = URL.substring(0,temp);
						parmaa = URL.substring(temp+1,URL.length());
						break;
					}
					else 
					{
						url =URL;
						parmaa = "";
					}
				}
				getRequest._sendRequest(url,parmaa,isView); 
				//getRequest.sendRequest(URL); 
			}
			else if(isHttpcCheck == true && isPost ==true && isHelp == false && isGet == false)
			{
				System.out.println("it's post");
			}
			else if(isHelp == true)
			{
				isHelp = false;
			}
			else 
			{
				System.out.println("Command line Error or missing http");
			}
		}
		
		

	}

}