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

    public static void RandomParkMutation(Population<CityTileset> population) {
        Random random = new Random();
        double probability;
        
        for (CityTileset city : population) {
            // Decides whether the individual is mutated with the given probability
        	probability = random.nextDouble();
            if (probability < MUTATION_PROBABILITY) {
            	city.getRandomNeighborhood(random.nextInt(101), random.nextInt(2), random.nextInt());
            }
        }
    }
}
