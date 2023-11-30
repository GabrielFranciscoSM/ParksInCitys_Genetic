/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.crossover;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Population;
import Model.Individuals.Tiles.Tile;
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
    
    public Population<CityTileset> apply(Population<CityTileset> pop){
        Population<CityTileset> offsprings = pop.clone();
        offsprings.setId(pop.getId() + 1);
        offsprings.clear();
        
        ArrayList<Pairing> pairings = makeRandomPairings(pop);
        for (Pairing pairing : pairings) {
            
            CityTileset offspring1 = new CityTileset(pairing.firstParent);
            CityTileset offspring2 = new CityTileset(pairing.secondParent);
            
            Position randNeigh = new Position(
                    generator.nextInt()%offspring1.getNeighborhoodSize(),
                    generator.nextInt()%offspring1.getNeighborhoodSize());
            
            ArrayList<ArrayList<Tile>> tilesAux = new ArrayList<>();
            
            tilesAux = offspring1.getNeighborhoodTiles(randNeigh);
            offspring1.setTiles(randNeigh, 
                    offspring2.getNeighborhoodTiles(randNeigh));
            offspring2.setTiles(randNeigh, tilesAux);
            
            int aux = offspring1.getNeighborhoodNParks(randNeigh);
            
            offspring1.setNeighborhoodNParks(randNeigh, offspring2.getNeighborhoodNParks(randNeigh));
            offspring2.setNeighborhoodNParks(randNeigh, aux);
            
            offsprings.add(offspring1);
            offsprings.add(offspring2);
        }
        return offsprings;
    }
}
