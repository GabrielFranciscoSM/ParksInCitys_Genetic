/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.mutation;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Population;

import java.util.Random;

/**
 *
 * @author gabriel
 */
public class RandomParkMutation {
    
    private Random generator;
    private double MUTATIONPROB; // Mutation probability

    RandomParkMutation(double mutationProb){
    	this.generator = new Random();
    	this.MUTATIONPROB = mutationProb;
    }

    public void apply(Population<CityTileset> pop) {
    	int action = -1;	//Stores the action that has been assigned until it is carried out (-1 -> no operation,
    						//0 -> extend park, 1 -> remove park)
        for (CityTileset city : pop) {
        	Position[] parkPositions = city.getArrayOfParkPositions().toArray(new Position[0]);	//Parks in a city
        	for (Position park : parkPositions) {
	        	if ((generator.nextDouble() < MUTATIONPROB) || action != -1) {	
	        		//action != -1 means that the operation assigned to another park have not been carried out, so
	        		//it will be try on the next park.
		        	if (city.hasAvailableTiles()) {
		        		if (action == -1) {	//Choose an operation
		        			action = generator.nextInt(2);
		        		}
		        		if (action == 0) {
		        			if (city.extendPark(park, generator.nextInt(8))) {
		        				action = -1;
		        			}
		        		}else {
		        			city.removeParkTile(park);
		        			action = -1;
		        		}
		        	}else {
		        		city.removeParkTile(park);
		        		action = -1;
		        	}
		        }
	        }
        }
    }
}
