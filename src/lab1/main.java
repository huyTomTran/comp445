package lab1;
import java.io.IOException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		// variables declaration & initialization
		String param="";

		// get input from user
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your command line");
		param = scanner.nextLine();
		while (!param.equals("q")){
			try
			{
				ParmaReader parmaReader = new ParmaReader(param);
				parmaReader.getRequest();
			}
			catch(Exception e)
			{
				System.out.println("Oops! Something went wrong! Please try again !");
			}
			System.out.println("\nPlease enter your command line");
			param = scanner.nextLine();
		} 	
		scanner.close();

		System.out.print("Exit. Program is terminated succesfully!");


	}

}
