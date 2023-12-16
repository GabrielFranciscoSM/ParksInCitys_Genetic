/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.crossover;

import Basics.Position;
import Model.CityParameters;
import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;
import Model.Individuals.Tiles.Tile;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class NeighborhoodCrossover extends CrossoverOperator<CityTileset>{
    
    public CityTilesetPopulation apply(CityTilesetPopulation pop, Random generator){
        
        //First the pairs are done
        CityTilesetPopulation offsprings = new CityTilesetPopulation(pop.getId(),pop.size());
        
        ArrayList<Pairing> pairings = makeRandomPairings(pop);
        
        for (Pairing pairing : pairings) {
            
            //gets the parents from a pair
            CityTileset offspring1 = pairing.firstParent;
            CityTileset offspring2 = pairing.secondParent;
            
            
            for(int i = 0; i < CrossoverController.REPETITIONS; i++){
                Position randNeigh = new Position(
                    generator.nextInt(offspring1.getNNeighborhood()),
                    generator.nextInt(offspring1.getNNeighborhood()));
                        
                ArrayList<ArrayList<Tile>> tilesAux = offspring1.getNeighborhoodTiles(randNeigh);

                ArrayList<ArrayList<Tile>> tilesAux2 = offspring2.getNeighborhoodTiles(randNeigh); 
                
                /*
                CityTileset ctaux = new CityTileset(tilesAux);
                CityTileset ctaux2 = new CityTileset(tilesAux2);
                
                
                Population<CityTileset> popaux = new CityTilesetPopulation(0,2);
                popaux.add(ctaux);
                popaux.add(ctaux2);
                MainWindow gui2 = new MainWindow();
                gui2.setPopulationCT(popaux);
                gui2.showView();*/
                
                
                offspring1.setTiles(Position.mul(randNeigh, CityParameters.NEIGHBORHOODSIZE), 
                        tilesAux2);

                offspring2.setTiles(Position.mul(randNeigh, CityParameters.NEIGHBORHOODSIZE), 
                        tilesAux);
            }
            
            
            offsprings.add(offspring1);
            offsprings.add(offspring2);
        }
        
        return offsprings;
    }
}
