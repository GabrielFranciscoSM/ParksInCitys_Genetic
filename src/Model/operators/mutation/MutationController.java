/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.mutation;

import Model.Individuals.CityTileset;
import Model.Individuals.Population;

/**
 *
 * @author gabriel
 */
public class MutationController {

    private RandomParkMutation rpm;
    private PointNeighborhoodMutation pnm;
    private int POINTGENERATION;
	
    public MutationController(double mutationProb, int pointNeigh, int pointGen) {
    	rpm = new RandomParkMutation(mutationProb);
    	pnm = new PointNeighborhoodMutation(mutationProb, pointNeigh);
    	POINTGENERATION = pointGen;
    }
    
    public void apply(Population<CityTileset> pop){
    	//rpm.apply(pop);
    	pnm.apply(pop);
    }
}
