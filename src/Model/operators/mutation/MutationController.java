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
public class MutationController {

    private RandomParkMutation rpm;
    private PointNeighborhoodMutation pnm;
    private int POINTGENERATION;
    private double mutationProb;
    
    Random r;
	
    public MutationController(double mutationProb, int pointNeigh, int pointGen) {
    	rpm = new RandomParkMutation(mutationProb);
    	pnm = new PointNeighborhoodMutation(mutationProb, pointNeigh);
    	POINTGENERATION = pointGen;
        r =     new Random(System.currentTimeMillis());
        this.mutationProb = mutationProb;

    }
    
    public void apply(Population<CityTileset> pop){
    	//rpm.apply(pop);
    	//pnm.apply(pop);
        for(CityTileset ct: pop){
            if(r.nextDouble() < mutationProb)
                ParkExpansionMutation.apply(ct, r);
        }        
    }
}
