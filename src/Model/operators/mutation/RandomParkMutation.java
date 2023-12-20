/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.mutation;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Population;

import java.util.Random;

import Basics.Position;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class RandomParkMutation {
    
	private double MUTATIONPROB; // Mutation probability
    private Random generator;

    RandomParkMutation(double mutationProb){
    	this.generator = new Random();
    	this.MUTATIONPROB = mutationProb;
    }

    public void apply(Population<CityTileset> pop) {
        for (CityTileset city : pop) {
        	Position[] parkPositions = city.getArrayOfParkPositions().toArray(new Position[0]);
        	for (Position park : parkPositions) {
	        	if (generator.nextDouble() < MUTATIONPROB) {
		        	if (!(city.hasAvailableTiles()) || (generator.nextInt(2) == 0) || !city.extendPark(park)) {
		        		city.removeParkTile(park);
		        	}
		        }
	        }
        }
    }
}
