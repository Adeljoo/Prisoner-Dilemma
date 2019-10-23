/*******************************************************************************
 * Copyright (c) 2018Copyright (C)  Ameneh Deljoo based on the Code by Ingrid Nunes
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

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.BitSet;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import com.sun.istack.internal.logging.Logger;


/**
 *A game of the Iterated Prisoner's Dilemma between two 
 *{@link  ie.errity.pd.Prisoner  Prisoners}
 *@author	Andrew Errity 99086921
 */
public class Game
{
	//Two Players
	private Prisoner P1;
	private Prisoner P2;
//	 Logger logger = Logger.getLogger("MyLog", null);  
//	    FileHandler fh; 
	//Histories from each players view
	private BitSet P1History;
	private BitSet P2History;
	
	//Payoffs recieved
	private int P1Score;
	private int P2Score;

	//Rules for the IPD
	private Rules rules;
	private double ConfidenceP1,ConfidenceP2;
	private double Confidence;
	static final String dataFile = "invoicedata";
	/**
	 *Create a new game of the Iterated Prisoner's Dilemma between two players
	 *@param p1 player 1
	 *@param p2 player 2
	 *@param r 	the rules which govern the game
	 */
	public Game(Prisoner p1, Prisoner p2, Rules r)
	{
		P1 = p1;
		P2 = p2;
		rules = r;	
	}
	
	
	/**Play a game of IPD according to the rules
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException */
	public void Play() throws UnsupportedEncodingException, FileNotFoundException, IOException
	{
		//Init
		int length = rules.getIterations();
		int iteration = 0;
		P1Score = 0;
		P2Score = 0;
		ConfidenceP1 = 0.5;
		ConfidenceP2 = 0;
		BitSet P1History = new BitSet();
		BitSet P2History = new BitSet();
		boolean P1move, P2move;
		
		//Play the specified number of PD games
		for(iteration = 0; iteration < length; iteration++)
		{	
			//Get each players move
			P1move = P1.play(iteration,P1History);
			P2move = P2.play(iteration,P2History);
			
			//Update scores according to payoffs
			if(P1move && P2move) //CC
			{
				P1Score += rules.getR();
				P2Score += rules.getR();
				ConfidenceP1+= rules.getConfidence() + 0.1*0.08;
			    System.out.println("ConfidenceP1=");
				ConfidenceP2 += ConfidenceP1 + 0.01*0.08;
				System.out.println("ConfidenceP2=");
			}
			else if(P1move && !P2move) //CD
			{
				P1Score += rules.getS();
				P2Score += rules.getT();
				ConfidenceP1+= rules.getConfidence()-0.01*0.08;
				ConfidenceP2 += ConfidenceP1-0.1*0.08;
			}
			else if(!P1move && P2move) //DC
			{
				P1Score += rules.getT();
				P2Score += rules.getS();
				ConfidenceP1+= rules.getConfidence()+0.05*0.08;
				ConfidenceP2 += ConfidenceP1 +0.01*0.08;
			}
			else if(!P1move && !P2move) //DD
			{
				P1Score += rules.getP();
				P2Score += rules.getP();
				ConfidenceP1+= rules.getConfidence()-0.05*0.08;
				ConfidenceP2 += ConfidenceP1-0.1*0.08;
			}
					
			//Update player histories
			if(P1move)
			{
				P1History.set(iteration*2);
				P2History.set((iteration*2)+1);
			}
			else
			{
				P1History.clear(iteration*2);
				P2History.clear((iteration*2)+1);
			}
			if(P2move)
			{			
				P1History.set((iteration*2)+1);
				P2History.set((iteration*2));
			}
			else
			{
				P1History.clear((iteration*2)+1);
				P2History.clear((iteration*2));
			}							
		}
		//Update each players score
		P1.updateScore(P1Score);
		P2.updateScore(P2Score);
		P1.updateConfidence(ConfidenceP1);
		P2.updateConfidence(ConfidenceP2);
	}
	
	/**
	 *Get game results
	 *@return array containing player 1 and player 2's score - [p1,p2]
	 */
	public int[] getScores()
	{ 
		int	[] scores = {P1Score, P2Score};
		return scores;
	}
	/**
	 *Get game results
	 *@return array containing player 1 and player 2's Confidence - [p1,p2]
	 */
	public double[] getConfidences()
	{ 
		double	[] Confidences = {ConfidenceP1, ConfidenceP2};
		return Confidences;
	}
/*	{
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new BufferedOutputStream(
			    new FileOutputStream(dataFile)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    try {
			out.writeDouble(TrustP1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
*/
	
}