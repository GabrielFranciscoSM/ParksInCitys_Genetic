/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.crossover;

import Model.Individuals.CityTileset;
import Model.Individuals.Population;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 *
 * @author gabriel
 */
public class NeighborhoodCrossover extends CrossoverOperator<CityTileset>{
    Random generator;
    
    NeighborhoodCrossover(){
        generator = new Random(System.currentTimeMillis());
    }
    /*
    public Population<CityTileset> apply(Population<CityTileset> pop){
        Population<CityTileset> offsprings = pop.clone();
        offsprings.setId(pop.getId() + 1);
        offsprings.clear();
        
        ArrayList<Pairing> pairings = makeRandomPairings(pop);
        for (Pairing pairing : pairings) {
            
            pairing.
            offsprings.add(offspring1);
            offsprings.add(offspring2);
        }
        return offsprings;
    }*/
}
