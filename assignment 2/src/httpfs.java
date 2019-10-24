/*
 * httpfs.java
 * Copyright (C) 2019 sebastien <sebastien@sebver>
 *
 * Distributed under terms of the MIT license.
 */


import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.nio.file.*;


public class httpfs
{	
	public static void main(String[] args) throws IOException{
		int server_port = 8080; // Defines at which port the server will be listening at
		String path = ".";
		boolean isHelp = false;
		boolean isError = false;
		boolean isVerbose = false;

		

		for(int i = 0; i < args.length; i++) 
		{
			if(args[i].equalsIgnoreCase("-v")) 
			{
				isVerbose = true;
			}
			//port
			if(args[i].equalsIgnoreCase("-p")) 
			{
				try
				{
					server_port = Integer.parseInt(args[i+1]);
				}
				catch(Exception e) 
				{
					System.out.println("port has to a number");
					isError = false;
				}		
			}
			//path
			if(args[i].equalsIgnoreCase("-d"))
			{
				path = args[i+1];
			}
			//help
			if(args[0].equalsIgnoreCase("help"))
			{
				isHelp = true;
			}

		}

		if (server_port>=1024 && server_port<=65535)
		{
			if(!isHelp && !isError)
			{
				System.out.println("Server has been instantiated at port " + server_port);
				System.out.println("Path has been binding at " + path);
				// if -v
				if(isVerbose)
				{
					System.out.println("\n Prints debugging messages");
					fileserver fs = new fileserver(server_port,path);
					fs.run();
					isVerbose=false;
				}
				// if not -v
				else
				{
					try {
						System.setOut(new PrintStream(new FileOutputStream("log.txt")));
						fileserver fs = new fileserver(server_port,path);
						fs.run();
					} catch(Exception e) {}
				
				}		
			}
			else 
			{
				if(isError)
				{
					System.out.println("The query is wrong");
				}
				else
				{
					System.out.println("httpfs is a simple file server.\r\n" + "usage: httpfs [-v] [-p PORT] [-d PATH-TO-DIR]\r\n"
							+ " -v Prints debugging messages.\r\n"
							+ " -p Specifies the port number that the server will listen and serve at.\r\n"
							+ " Default is 8080.\r\n" + " -d Specifies the directory that the server will use to read/write\r\n"
							+ "requested files. Default is the current directory when launching the\r\n" + "application.");
				}
			}
		}
		else
		{
			System.out.println("\nERROR!!! Port range has to be [1024,65535]");
		}

	}
}