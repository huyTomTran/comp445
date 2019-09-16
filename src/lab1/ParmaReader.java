package lab1;

import java.util.*;

public class ParmaReader {
 
	private String parma[];
	private boolean isHttpcCheck = false;
	private boolean isView = false;
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
				
				if(parma[i].equals("-v"))
				{
					isView = true;
					URL = parma[i+1];
				}
				else
				{
					URL = parma[i];
				}
			}
			
			/*
			 processing
			 */
			if(isHttpcCheck == true && URL != null && isView == true) 
			{
				getRequest.sendRequest(URL);	
			}
			else 
			{
				System.out.println("Command line Error");
			}
		}
		
		

	}

}