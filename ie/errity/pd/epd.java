/*******************************************************************************
 * Copyright (c) 2018Copyright (C)  Ameneh Deljoo based on the Code by Andrew Errity
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *******************************************************************************/
package ie.errity.pd;

import ie.errity.pd.graphics.*;
//Import GUI components
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.plaf.metal.*;

import com.sun.net.ssl.internal.www.protocol.https.Handler;


/**
 *MAIN class. Contains code which launches an instance of the Evolutionary 
 *Prisoner's Dilemma application.
 *@author Andrew Errity 99086921
 */
public class epd
{	
	/**
	 *MAIN class. Contains code which launches an instance of the Evolutionary 
	 *Prisoner's Dilemma application.
	 *@param args 	command line agruments (not processed)
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
	  	//Make sure we have window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);
	//	 Logger logger = Logger.getLogger("MyLog", null);  
	//	    FileHandler fh;
		 Logger logger = Logger.getLogger("my.log");

		    Handler handler = null;
			
		//Create a frame and container for the game panels.
		MenuFrame progFrame = new MenuFrame("Digital Prisoner's Dilemma");
		//setBackground(Color.black);

//getContentPane().setBackground(Color.black);

       	// THEMES
		// user selected theme
		MetalTheme theme = new EmeraldTheme();  
		// set the chosen theme
		MetalLookAndFeel.setCurrentTheme(theme);	
		try
		{
		    UIManager.setLookAndFeel(
			UIManager.getCrossPlatformLookAndFeelClassName());
		    SwingUtilities.updateComponentTreeUI(progFrame);
		}
		catch(Exception e){}	
		
		
	    // Exit when the window is closed.
	    progFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		// Show the program
		progFrame.pack();
		progFrame.setVisible(true);
		//  public log (String message) throws IOException { 
		   //   PrintWriter out = new PrintWriter(new FileWriter("output.txt", true), true);
		 //     out.write(message);
		   //   out.close();
		    
		}

	/*PrintWriter out = null;{
		try {
		    out = new PrintWriter(new FileWriter("Desktop:\\testing.txt"));
		    } catch (IOException e) {
		            e.printStackTrace();
		    }
		out.println("output");
		out.close();
		
	}	*/
}

