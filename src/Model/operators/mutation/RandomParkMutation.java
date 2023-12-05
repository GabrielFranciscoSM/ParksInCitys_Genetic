/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.mutation;

import Model.Individuals.CityTileset;
import Model.Individuals.Population;

import java.util.Random;

/**
 *
 * @author gabriel
 */
public class RandomParkMutation {
    private static final double MUTATION_PROBABILITY = 0.01; // Mutation probability (1%)
    
    private Random generator;
    
    RandomParkMutation(){
    	this.generator = new Random();
    }

    public void apply(Population<CityTileset> pop) {
        double probability;
        
        for (CityTileset city : pop) {
            // Decides whether the individual is mutated with the given probability
        	probability = generator.nextDouble();
            if (probability < MUTATION_PROBABILITY) {
            	city.getRandomNeighborhood(generator.nextInt(101), generator.nextInt(2), generator.nextInt());
            }
        }
    }
}
