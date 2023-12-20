/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.mutation;

import Model.Individuals.CityTileset;
import Model.Individuals.Population;

/**
 *
 * @author gabriel
 */
public class MutationController {

    private static final double MUTATION_PROBABILITY = 0.01; // Mutation probability (1%)
    RandomParkMutation rpm;
	
    public MutationController(double mutationProb) {
    	rpm = new RandomParkMutation(mutationProb);
    }
    
    public void apply(Population<CityTileset> pop){
    	rpm.apply(pop);
    }
}
