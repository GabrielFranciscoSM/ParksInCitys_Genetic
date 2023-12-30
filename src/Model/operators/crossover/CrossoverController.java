/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.crossover;

import Model.Individuals.CityTilesetPopulation;
import Model.ModelParameters;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class CrossoverController {    
    protected static int REPETITIONS = 2;
    private double CROSSOVERPROB;
    
    NeighborhoodCrossover nc;
    TilesCrossover tc;
    Random generator;
    ModelParameters mp;
    
    public CrossoverController(ModelParameters mp){
        nc = new NeighborhoodCrossover();
        generator = new Random(System.currentTimeMillis());
        REPETITIONS = mp.getCROSSOVERINTENSITY();
        CROSSOVERPROB = mp.getCROSSOVERPROB();
        tc = new TilesCrossover(REPETITIONS, CROSSOVERPROB);
        this.mp = mp;
    }
    
    public CityTilesetPopulation apply(CityTilesetPopulation pop, int intensity){
        this.REPETITIONS = intensity;
        if(generator.nextDouble() < mp.getCROSSOVERPROB())
            return nc.apply(pop,generator);
        else return pop;
        //return tc.apply(pop);
    }
}
