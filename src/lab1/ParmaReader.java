package lab1;
//https://examples.javacodegeeks.com/core-java/net/socket/send-http-post-request-with-socket/

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.net.URI;
import java.net.URISyntaxException;


>>>>>>> bf03b52... Merge pull request #6 from huyTomTran/test
public class ParmaReader {

	private String parma[];
	private boolean isHttpcCheck = false;
	private boolean isVerbose = false;
	private boolean isHelp = false;
	public static boolean hasFile = false;
	public static boolean hasData = false;
	public static boolean hasHead = false;
	private boolean isGet = false;
	private boolean isPost = false;
	private boolean hasError = false;
	private String url;
	
	public String urlHost;
	public String urlparam;
	public  String contentHeader;
	public GetRequest getRequest;
	public PostRequest postRequest;
	public static HashMap<String, String> dataMap;
	public static ArrayList<String> dataList;
	public static HashMap<String, String> contentMap;
	public static String tempdata;
	
	public ParmaReader(String str) {
		super();
		dataList = new ArrayList<String>();
		dataMap = new HashMap<String, String>();
		contentMap = new HashMap<String, String>();

		this.parma = str.split("\\s+");
		getRequest = new GetRequest();
		postRequest = new PostRequest();
	}


	public void getRequest() throws URISyntaxException 
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
				if(this.parma[0].equals("httpc"))
				{
					isHttpcCheck = true;

					if(parma[i].equals("help"))
					{
						int index = i ;
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
					/*-------END of Help class-------*/


					// GET with Query Parameters	
					else if(parma[i].equals("get")) 
					{
						isGet = true;				

					}

					// POST with Query Parameters	
					else if(parma[i].equals("post")) 
					{
						isPost = true;
					}
					
					if(parma[i].equals("-v"))
					{
						isVerbose = true;
					}
					
					if(parma[i].contains("http://")){
						url = parma[i];
					
										
						char anObject = '"';
						char anotherObject = '\'';
						//remove " in url if "or ' is exit
						while(url.charAt(0) == anObject&&url.charAt(url.length()-1) == anObject||url.charAt(0) == anotherObject&&url.charAt(url.length()-1) == anotherObject)
						{
						     url = url.substring(1, url.length()-1);
						}
						
						URI uri2 = null;

						uri2 = new URI(url);
										
						urlHost = uri2.getHost();
						urlparam =uri2.getQuery();
						
					}//End http
					
					if(parma[i].equals("-h")){
						contentHeader = parma[i+1];
						if(!contentHeader.contains(":")) 
						{
							hasError = true;
						}
						else
						{
							contentHeader.replaceAll("\\s+","");
							String contentArr[] = contentHeader.split(",");
							String contentTempArr[];
							for(int j = 0 ; j < contentArr.length ; j++) {
								contentTempArr = contentArr[j].split(":");
								contentMap.put(contentTempArr[0],contentTempArr[1]);
								hasHead = true;
						}
					}
				}
				
					if(parma[i].equals("-d")&&!hasFile) 
					{					
						String data = parma[i+1];
						
						while(!data.contains("}'")) 
						{
							int k = 1;
							data = data +" "+parma[i+1+k];
							k++;
						}

						tempdata = data;
						hasData = true;
						/*
						char firstAndLastChar ='\'';
						if(data.charAt(0)!=firstAndLastChar|| data.charAt(data.length()-1)!=firstAndLastChar||data.charAt(1)!='{'
								||data.charAt(data.length()-2)!='}')
						{
							hasError = true;
						}
						else			
						{   			
							if(!contentMap.isEmpty()) {
								if(contentMap.containsKey("Content-Type"))
								{
									if(contentMap.get("Content-Type").equals("application/json"))
									{
										data.replaceAll("\\s+","");
										data = data.substring(2,data.length()-2);
										String dataArr[] = data.split(",");
										String dataTempArr[];
										for(int j = 0 ; j < dataArr.length ; j++) 
										{
											dataTempArr = dataArr[j].split(":");
											dataMap.put(dataTempArr[0],dataTempArr[1]);
											hasData = true;
										}
									}
									else 
									{
										dataList.add(data);
									}
								}
								else
								{
									dataList.add(data);
								}

							}
							else
							{
								dataList.add(data);
							}
						}
						*/
						}
					if(parma[i].equals("-f")&&!hasData) 
					{
						// The name of the file to open.
				        String fileName = "test1.txt";

				        // This will reference one line at a time
				        String line = null;

				        try {
				            // FileReader reads text files in the default encoding.
				            FileReader fileReader = 
				                new FileReader(fileName);

				            // Always wrap FileReader in BufferedReader.
				            BufferedReader bufferedReader = 
				                new BufferedReader(fileReader);

				            while((line = bufferedReader.readLine()) != null) {
				                System.out.println(line);
				                tempdata = tempdata + line;
				            }   

				            hasFile = true;
				            // Always close files.
				            bufferedReader.close();         
				        }
				        catch(FileNotFoundException ex) {
				            System.out.println(
				                "Unable to open file '" + 
				                fileName + "'");    
				            	hasError = true;
				        }
				        catch(IOException ex) {
				            System.out.println(
				                "Error reading file '" 
				                + fileName + "'");    
				            	hasError = true;
				            // Or we could just do this: 
				            // ex.printStackTrace();
				        }
					}
					
			}
			}
		}//End else
			/*
			 processing
			 */
			if(hasData&&hasFile) 
			{
				System.out.println("-h & -d cannot happen in the same time");
			}
			else if(isHttpcCheck == true && isGet==true && isHelp == false && isPost == false && !hasError) 
			{	
				getRequest.sendGetRequest(urlHost,urlparam,isVerbose); 
			}//End-if get request
			else if(isHttpcCheck == true && isPost ==true && isHelp == false && isGet == false && !hasError)
			{
				postRequest.sendPostRequest(urlHost,urlparam,isVerbose); 
			}//End-if post request
			else if(isHelp == true && !hasError)
			{
				isHelp = false;
			}
			else if (isHttpcCheck == true && (isGet == false || isPost == false))
			{
				System.out.println("Invalid command! Command must be \"get|post|help\" after the \"httpc\"");
			}
			else 
			{
				System.out.println("Command line Error. Please start with \"httpc\"");
			}


	
	}
}