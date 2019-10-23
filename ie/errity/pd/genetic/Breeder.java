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
package ie.errity.pd.genetic;

import ie.errity.pd.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.BitSet;
import java.util.Random;


/**
 * Provides a means of evolving a population of 
 *{@link  ie.errity.pd.Prisoner Prisoners} 
 *via a genetic algorithm
 * @author	Andrew Errity 99086921 extend by Ameneh
 */
public class Breeder extends JPanel
{
	private Prisoner curPopulation[];
	private int scaledScores[];	//fitness scaled scores
	private double mutateP, crossP; //mutate, cross probabilities
	private int popSize;
	private Random rand = new Random();
	private int lastparent;	//index of last prisoner selected
	
	/**
	 *Create a new Genetic Breeder
	 */
	public Breeder()
	{	
		//defaults
		mutateP = .001;
		crossP = .95;
		rand = new Random(); //uses current time as seed
		lastparent = -1;
	}
	
	/**
	 *Create a new Genetic Breeder using {@link  ie.errity.pd.Rules Rules} given
	 *@param r1 parameters for the breeder
	 */
	public Breeder(Rules r1)
	{	
		//init based on rules	
		mutateP = r1.getMutateP();
		crossP = r1.getCrossP();
		rand = new Random(); //uses current time as seed
		lastparent = -1;
	}

	
	/**
	 *Breeds the next generation (panmictic mating) of an array of 
	 *{@link  ie.errity.pd.Prisoner Prisoners} 
	 *@param c	initial population (raw fitness of population must be calcualted previously)
	 *@return the next generation
	 */
	public Prisoner[] Breed(Prisoner[] c)
	{
		BitSet Offspring[]; //stores children
		curPopulation = c;	//population to breed
		popSize = curPopulation.length;
		Prisoner newPopulation[] = new Prisoner[popSize];;
		int P1,P2,d;	//parents and index	
		
		//fitness scaling
		scaledScores = Genetic.scale(curPopulation);
							
		//Breed new population
		d = 0;
		while(d < popSize)
		{	
			Offspring = new BitSet[2];
		
			//Selection		
			P1 = selectRoulette();
			P2 = selectRoulette();
			
			//Cross Over
			if(rand.nextDouble() <= crossP)
				Offspring = Genetic.crossover(curPopulation[P1].getStrat(),curPopulation[P2].getStrat());
			else //clone
			{
				Offspring[0] = curPopulation[P1].getStrat();
				Offspring[1] = curPopulation[P2].getStrat();
			}
			
			//Mutation
			Offspring[0] = Genetic.mutate(Offspring[0],mutateP);
			Offspring[1] = Genetic.mutate(Offspring[1],mutateP);
			
			//Replacement
			newPopulation[d] = new Prisoner(Offspring[0]);
			if( (d+1) < popSize)	//in case of odd population
				newPopulation[d+1] = new Prisoner(Offspring[1]);		
			d+=2;
		}
		
		curPopulation = newPopulation;
		repaint();	//update display (if any)
		return curPopulation; //return the bred population
	}
	
	/**
	 *Roulette wheel selection
	 */
	private int selectRoulette()
	{
		double t1, fitSum = 0;
		int target;
		
		//Set Target Fitness
		for(int j = 0; j < popSize; j++)
				fitSum += scaledScores[j];
		t1 = fitSum * rand.nextDouble();
		target = (int)t1;
			
		//Build up a sum of fitness
		//the individual who's fitness causes the sum to
		//exceed the target is selected	
		int fitness = 0;
		int nextparent = lastparent;
		while(fitness < target)
		{
			nextparent++;
			if(nextparent >= popSize)
				nextparent = 0;
			
			if(nextparent != lastparent)	
				fitness += scaledScores[nextparent];
		}
		lastparent = nextparent;
		return nextparent; //return index of selected player
	}
	
	
	/**
	 *Responsible for updating the graphical representation
	 */
	public void paintComponent(Graphics g) 
    {
        super.paintComponent(g); //paint background

		//Get limits of viewable area
      	Insets insets = getInsets();
      	int x0 = insets.left;
      	int y0 = insets.top;
	    int currentWidth = getWidth() - insets.left - insets.right;
	    int currentHeight = getHeight() - insets.top - insets.bottom;
	    
	    //Display a series of rectangles, representing the players
	    for(int i = 0; i < popSize; i++)
	    {
	    	g.setColor(curPopulation[i].getColor());	
	    	g.fillRect((x0*2)+((currentWidth/popSize)*(i)),(currentHeight/4)+y0,(currentWidth/popSize),currentHeight/2);
	    }
	}
	
	/**
	 *Set the {@link  ie.errity.pd.Rules Rules}
	 *@param r new {@link  ie.errity.pd.Rules Rules}
	 */
	public void setRules(Rules r)
	{
		mutateP = r.getMutateP();
		crossP = r.getCrossP();
	}
	
	/**
	 *Reset the breeder
	 */
	public void clear()
	{
		popSize = 0;
		repaint();
	}
	
}
	