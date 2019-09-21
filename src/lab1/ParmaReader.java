package lab1;

import java.util.*;

public class ParmaReader {
 
	private String parma[];
	private boolean isHttpcCheck = false;
	private boolean isView = false;
	private boolean isHelp = false;
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
						else if(parma[index+1].equals("post"))
						{
							System.out.println("help get");
						}
						else 
						{
							isHelp = true;
							Help.postHelpMenu();
						}
						
					}
				}

			}
			
			/*
			 processing
			 */
			if(isHttpcCheck == true && URL != null && isView == true && isHelp == false) 
			{
				//getRequest.sendRequest(URL); 
			}
			else if(isHelp == true)
			{
				isHelp = false;
			}
			else 
			{
				System.out.println("Command line Error");
			}
		}
		
		

	}

}