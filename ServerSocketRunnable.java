/**
File name: ServerSocketRunnable.java
Author: Jacob Jewell, 0408667244
Course: CST8221 – JAP, Lab Section: 302
Assignment: 2
Date: April 20 2018
Professor:  Svillen Ranev
Purpose: The ServerSocketRunnable class is responsible for communicating with the client and responding to the command strings sent by the client.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
The class ServerSocketRunnable has a single constructor taking a reference to a Socket object as a parameter.
@author Jacob Jewell
@version 2.0
@see default package
@since the version number (1.8.0_161)
 */
public class ServerSocketRunnable implements Runnable {
	private String request; /** To store the user's command */
	private Socket socket; /** socket used to connect to the server */
	private ObjectOutputStream output; /** To write primitive data types and graphs of Java objects to an OutputStream */
	private ObjectInputStream input; /** To adeserializes primitive data and objects previously written using an ObjectOutputStream. */
	private final SimpleDateFormat TIME; /** To hold the current time */
	private final SimpleDateFormat DATE; /** To hold the current date */
	
	/**
	 * The class must have a single constructor taking a reference to a Socket object as a parameter.
	 * @param socket
	 */
	public ServerSocketRunnable(Socket socket) { 
		this.socket = socket;
		DATE = new SimpleDateFormat("dd MMMMM yyyy");
		TIME = new SimpleDateFormat("hh:mm:ss aaa");
	}// End of default constructor 

	/**
	 * It opens input and output streams, in a loop that repeats until the end command is sent by the client, the run()
	   method first reads the command string sent by the client, The method extracts the COMMAND from the command string and depending
	   on the COMMAND it writes back to the client and finally writes back a string depending on the command
	 */
	public void run() {
		try {
			// It opens input and output streams.
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());

			// In a loop that repeats until the end command is sent by the client
			for(;;) {
				// The method extracts the COMMAND from the command string
				request = (String) input.readObject(); 

				// If the COMMAND is echo
				if (request.startsWith("-echo")) { 
					output.writeObject("SERVER>"+ request.replaceFirst("-echo-", "Echo:"));
					
				} 
				// If the COMMAND is time
				else if (request.startsWith("-time"))  
					output.writeObject("SERVER>TIME: " + TIME.format(new Date()));

				// If the COMMAND is date,
				else if (request.startsWith("-date")) 
					output.writeObject("SERVER>DATE: " + DATE.format(new Date()));	

				// If the COMMAND is help
				else if (request.startsWith("-help")) 
					output.writeObject("SERVER>AVAILABLE SERVICES: \nend \necho \ntime \ndate \nhelp \ncld \n");	

				// If the COMMAND is cld
				else if (request.startsWith("-cld")) 
					output.writeObject("CLD:");

				// If the COMMAND is end
				else if (request.startsWith("-end")) { 
					System.out.println("Server Socket: Closing client connection...");
					output.writeObject("SERVER>Connection Closed.");
					
					//closes the streams and the client socket, and terminates
					input.close();
					output.close();
					socket.close();
					break; // the method exits the loop	
				} 

				// If the COMMAND is invalid
				else 
					output.writeObject("SERVER>ERROR: Unrecognized command");	
				Thread.sleep(100); // At the end of each of the loop iterations, the tread sleeps for 100 milliseconds.
			} // End of for loop
			
		} catch ( IOException | InterruptedException e) {
			System.out.println("Server Socket: Closing client connection...");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}// End of catch statement
	}// End of method
}// End of class