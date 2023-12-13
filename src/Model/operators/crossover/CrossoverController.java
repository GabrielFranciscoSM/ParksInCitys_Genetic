/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.crossover;

import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;
import Model.Individuals.Population;
import Model.ModelParameters;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class CrossoverController {

    public static int REPETITIONS = 2;
    
    NeighborhoodCrossover nc;
    Random generator;
    
    public CrossoverController(ModelParameters mp){
        nc = new NeighborhoodCrossover();
        generator = new Random(System.currentTimeMillis());
        REPETITIONS = mp.getCROSSOVERINTENSITY();
    }
    
    public Population<CityTileset> apply(CityTilesetPopulation pop){
        return nc.apply(pop,generator);
    }
}
