package lab1;

import java.util.*;

public class ParmaReader {
 
	private String parma[];
	private boolean httpcCheck = false;
	private GetRequest getRequest;
	
	public ParmaReader(String str) {
		super();
		this.parma = str.split(" ");
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
				if(parma[0]!="httpc") 
				{
					httpcCheck = true;
				}
			}
			
			/*
			 processing
			 */
			if(httpcCheck == true) 
			{
				getRequest.sendRequest();	
			}
			else 
			{
				System.out.println("Command line Error");
			}
		}
		
		

	}

}