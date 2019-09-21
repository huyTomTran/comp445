package lab1;

import java.util.*;
import java.net.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		String param ="";
		
		System.out.println("command");
		
		param = scanner.nextLine();
		while(!param.equals("C"))
		{
				try 
				{
					ParmaReader parmaReader = new ParmaReader(param);
					parmaReader.getRequest();
				}catch(Exception e) {
					 System.out.println("Something went wrong.");
		}
				System.out.println("command");
				param = scanner.nextLine();
		}
		System.out.println("Exit");
		
//		
//		String parma [] = scanner.nextLine().split(" ");
//		
//		for(int i=0;i<parma.length;i++) 
//		{
//			if(parma[0]!="httpc") 
//			{
//				httpcCheck = true;
//			}
//		}
//		
//		if(httpcCheck == true) 
//		{
//			//GetRequest getRequest = new GetRequest();
//			//getRequest.sendRequest();	
//		}
// hihisa

	}

}
