/**
File name: Client.java
Author: Jacob Jewell, 0408667244
Course: CST8221 – JAP, Lab Section: 302
Assignment: 2
Date: April 20 2018
Professor:  Svillen Ranev
Purpose: This file is responsible for launching the application.
 */
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
The main method must call EventQueue.invokeLater() method.
@author Jacob Jewell
@version 1.0
@see default package
@since the version number (1.8.0_161)
 */
public class Client {

	/**
	Calls the EvenQueue to run the frame
	@param command line argument
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			/**
			creates a JFrame object; sets the frame title and minimum size; sets the default close operation
			for the frame; sets the content pane of the frame to a ClientView object; sets the
			application location at launch; and finally, makes the frame visible.
			 */
			public void run() {

				ClientView clientObj = new ClientView();

				JFrame panel = new JFrame();
				// set the frame title
				panel.setTitle("Jake's Client");
				// set the frame minimum size (the minimum size of the application frame must be (320, 520))
				panel.setMinimumSize(new Dimension(600, 550));  
				// set the default close operation for the frame
				panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// set the content pane of the frame to a ClientView object
				panel.setContentPane(clientObj);
				// set the application location at launch
				panel.setLocationByPlatform(true);  
				// finally, make the frame visible
				panel.setVisible(true);
			} // End of method
		});		
	} // End of main method
} // End of class