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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;

/**
 *Iterated Prisoner's Dilemma Tournament
 *<BR>In such a tournament every player plays the IPD against every other player
 *and themselves.
 *@author Andrew Errity 99086921
 *@author AH 
 */
public class Tournament
{
	private Prisoner Players[];
	private int numPlayers;
	private int Results[];
	private double Trust;
	private double ResultTrust[];
	private Rules rules;
	boolean done;
	
	/**
	 *Create new Iterated Prisoner's Dilemma Tournament
	 *@param plyrs an array containing the competitors
	 *@param r the rules to govern the Tournament
	 *@param Trust 
	 */
	public Tournament(Prisoner plyrs[], Rules r)
	{
		Players = plyrs;
		rules = r;
		numPlayers = Players.length;
	//	Trust = ResultTrust.length;
		Results = new int[numPlayers];
		//ResultTrust= new double[(int) Trust];
		for(int i = 0; i < numPlayers; i++)
			Results[i] = 0;
		/*for(int j = 0; j <Trust ; j++)
			ResultTrust[j] = 0;*/
		done = false;
	}
	
	/**
	 *Play the tournament
	 *@return the Tournament results, an array of payoffs corresponding to the 
	 *player's scores
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public int[] Play() throws UnsupportedEncodingException, FileNotFoundException, IOException
	{ 
	//	double j;
		int i;
		Game g;
		Prisoner Clone[] = new Prisoner[numPlayers];
		
		//Every player plays themselves and every other player	
		for(i = 0; i < numPlayers; i++)
		{
			Clone[i] = (Prisoner)Players[i].clone();
			Clone[i].setScore(0);
			
			for(int j = 0; j < numPlayers; j++)
			{
				g = new Game(Clone[i],Players[j],rules);
				g.Play();
			}
			Results[i] = Clone[i].getScore();
			
		}
		//The total pay-offs each player recieved are returned
		Players = Clone;
		done = true;
		return Results;
		//return Trust;
	}
	
	/**
	 *Returns the Tournament results
	 *@return the Tournament results
	 */
	public int[] getResults(){return Results;}
	
	
	/**
	 *Returns index of lowest scoring individual
	 *@return index of lowest scoring individual
	 */
	public int minResult() 
	{
		int index = -1;
		if(done)
		{
			int min = Results[0];
			index = 0;
			for(int i = 1; i < numPlayers; i++)
				if(Results[i] < min)
				{
					min = Results[i];
					index = i;
				}
		}
		return index;
	}
	
	/**
	 *Returns index of highest scoring individual
	 *@return index of highest scoring individual
	 */
	public int maxResult()
	{
		int index = -1;
		if(done)
		{
			int max = Results[0];
			index = 0;
			for(int i = 1; i < numPlayers; i++)
				if(Results[i] > max)
				{
					max = Results[i];
					index = i;
				}
		}
		return index;
	}
	
	/**
	 *Returns the average score
	 *@return the average score
	 */
	public double meanResult()
	{
		double mean = 0;
		if(done)
		{
			int total = 0;
			for(int i = 0; i < numPlayers; i++)
				total += Results[i];
			mean = total/numPlayers;
		}	
		return mean;
	}
	
	
}