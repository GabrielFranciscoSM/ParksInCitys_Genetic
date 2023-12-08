/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.crossover;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;
import Model.Individuals.Population;
import Model.Individuals.Tiles.Tile;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class NeighborhoodCrossover extends CrossoverOperator<CityTileset>{
    Random generator;
    
    NeighborhoodCrossover(){
        generator = new Random(System.currentTimeMillis());
    }
    
    public Population<CityTileset> apply(CityTilesetPopulation pop){
        Population<CityTileset> offsprings = pop.clone();
        
        offsprings.setId(pop.getId() + 1);
        offsprings.clear();
        ArrayList<Pairing> pairings = makeRandomPairings(pop);
        for (Pairing pairing : pairings) {
            
            CityTileset offspring1 = pairing.firstParent;
            CityTileset offspring2 = pairing.secondParent;
            
            for(int i = 0; i < 2; i++){
                Position randNeigh = new Position(
                    generator.nextInt(offspring1.getNeighborhoodSize()),
                    generator.nextInt(offspring1.getNeighborhoodSize()));
                        
                ArrayList<ArrayList<Tile>> tilesAux = offspring1.getNeighborhoodTiles(randNeigh);

                ArrayList<ArrayList<Tile>> tilesAux2 = offspring2.getNeighborhoodTiles(randNeigh); 
                            int aux = offspring1.getNeighborhoodNParks(randNeigh);

                offspring1.setTiles(Position.mul(randNeigh, CityTileset.NEIGHBORHOODSIZE), 
                        tilesAux2);

                offspring2.setTiles(Position.mul(randNeigh, CityTileset.NEIGHBORHOODSIZE), 
                        tilesAux);

                offspring1.setNeighborhoodNParks(randNeigh, offspring2.getNeighborhoodNParks(randNeigh));
                offspring2.setNeighborhoodNParks(randNeigh, aux);
            }
            
            
            offsprings.add(offspring1);
            offsprings.add(offspring2);
        }
        
        return offsprings;
    }
}
