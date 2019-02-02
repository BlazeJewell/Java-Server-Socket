/**
File name: Server.java
Author: Jacob Jewell, 0408667244
Course: CST8221 – JAP, Lab Section: 302
Assignment: 2
Date: April 20 2018
Professor:  Svillen Ranev
Purpose:The Server class is responsible for creating a server socket and starting a service thread responsible for serving each individual client.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
The main method creates a server socket and starting a service thread responsible for serving each individual client..
@author Jacob Jewell
@version 1.0
@see default package
@since the version number (1.8.0_161)
 */
public class Server {
	/**
  	The main method sets the port number, then creates a TCP/IP server socket, then in an endless loop the method calls 
  	accept() on the server socket instance, then creates an instance of serverSocketRunnable class...
	 */
	public static void main(String[] args) {

		int port = 65535; // The server must use 65535 as a default port number.
		ServerSocket serverSocket;

		// If command line string is supplied at launch
		if(0 < args.length) {
			// The method converts the string to an integer port number.
			if(!args[0].matches("([0-9]*)"))
				System.out.println("Invalid port: " + args[0]);
			else {
				port = Integer.parseInt(args[0]);
				System.out.println("Using port: " + port);
			} // End of else statement
		} // End of if statement
		else 
			System.out.println("Using default port: " + port);

		try {
			serverSocket = new ServerSocket(port);

			// In an endless loop
			for(;;) {

				// The method calls accept() on the server socket instance.
				Socket socket = serverSocket.accept();

				// The main() method prints an appropriate message on the console screen and proceeds
				System.out.printf("Connecting to a client Socket[addr=%s, port=%d, localport=%d]\n",
								   socket.getInetAddress(), socket.getPort(), socket.getLocalPort());

				// main() method creates an instance of ServerSocketRunnable class passing the socket instance to its constructor.
				ServerSocketRunnable serverRunnable = new ServerSocketRunnable(socket);
				Thread thread = new Thread(serverRunnable);
				thread.start();		

			} // End of for loop
		} catch (IOException e) { 
			System.out.println("Failed to accept a new connection");
		}// End of catch statement
	}// End of main method
}// End of server class
