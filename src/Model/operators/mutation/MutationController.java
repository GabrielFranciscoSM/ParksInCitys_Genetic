/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.mutation;

import Model.Individuals.CityTileset;
import Model.Individuals.Population;

/**
 * Controller for managing various mutation operators.
 */
public class MutationController {

    private RandomParkMutation rpm;
    private PointNeighborhoodMutation pnm;
    private TargetedNeighborhoodMutation tnm;
    private ParkExpansionMutation pem;

    /**
     * Constructs a MutationController with specified mutation probabilities and parameters.
     *
     * @param mutationProb Probability of mutation.
     * @param pointNeigh  Number of point mutations in the neighborhood.
     */
    public MutationController(double mutationProb, int pointNeigh) {
        rpm = new RandomParkMutation(mutationProb);
        pnm = new PointNeighborhoodMutation(mutationProb, pointNeigh);
        tnm = new TargetedNeighborhoodMutation(mutationProb, pointNeigh);
        pem = new ParkExpansionMutation(mutationProb);
    }

    /**
     * Applies various mutation operators to a population of CityTilesets.
     *
     * @param pop Population of CityTilesets.
     */
    public void apply(Population<CityTileset> pop){
    	//rpm.apply(pop);
    	//pnm.apply(pop);
    	tnm.apply(pop);
    	/*
    	//tnm.apply(pop);//error, accede a neighborhoods fuera de array
        for(CityTileset ct: pop){
            if(r.nextDouble() < mutationProb)
                ParkExpansionMutation.apply(ct, r);
		*/
    }
}
