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
public class RandomParkMutation extends MutationHandler {
    
    private double MUTATIONPROB; // Mutation probability

    RandomParkMutation(double mutationProb){
    	this.MUTATIONPROB = mutationProb;
    }

    public void apply(Population<CityTileset> pop) {
        for (CityTileset city : pop) {
        	Position[] parkPositions = city.getArrayOfParkPositions().toArray(new Position[0]);	//Parks in a city
        	this.mutate(city, parkPositions, MUTATIONPROB);
        }
    }
}
