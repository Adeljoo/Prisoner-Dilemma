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

import java.util.Hashtable;
import java.util.BitSet;

/**
 *Table of the 64 possible histories of a 3 game sequence, 
 *indexed by numbers from 0-63.
 *@author Andrew Errity 99086921
 */
public class Moves
{
	private Hashtable moves;	
	
	/**
 	 *Creates a table of the 64 possible histories of a 3 game sequence, 
 	 *indexed by numbers from 0-63.
 	 */
	public Moves()
	{
		moves = new Hashtable(64);
		String s;
		
		//Build table of all possible moves (3-game history)
     	for(int n = 0; n <64; n++)
     	{
     		s = Integer.toString(n,2);
     		while(s.length() < 6)
     		{
     			s = '0' + s; //pad to length 6
     		}
     		
     		BitSet temp = new BitSet(6);
     		for(int i = 0; i < 6; i++)
     			if(s.charAt(i) == '0')
     				temp.set(i);
     		moves.put(temp,new Integer(n));	
     	} 
	}
	
	/**
	 *Decodes a 3 game history to an index number
	 *@param h 	a 3 game <code>bitset</code> history
	 *@return an index number between 0-63
	 */
	public int get(BitSet h)
	{
		Integer x = (Integer)moves.get(h);
     	return x.intValue();
  	}
	
}