/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.crossover;

import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;
import Model.Individuals.Population;

/**
 *
 * @author gabriel
 */
public class CrossoverController {
    
    NeighborhoodCrossover nc;
    
    public CrossoverController(){
        nc = new NeighborhoodCrossover();
    }
    
    public Population<CityTileset> apply(CityTilesetPopulation pop){
        return nc.apply(pop);
    }
}
