package Model.operators.mutation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Population;

public class TargetedNeighborhoodMutation extends MutationHandler{
	
	private double MUTATIONPROB;
	private int MUTABLENEIGHBORHOODS;
	TreeMap<Integer, Double> totalNeighborhoods;
	
	TargetedNeighborhoodMutation(double mutationProb, int mutableNeighborhoods){
    	this.MUTATIONPROB = mutationProb;
    	this.MUTABLENEIGHBORHOODS = mutableNeighborhoods;
    }
	
	/**
	 * Applies a genetic algorithm operation to a population of CityTileset instances.
	 * The operation involves sorting neighborhoods within each city based on their values
	 * and applying a mutation to the bottom MUTABLENEIGHBORHOODS neighborhoods.
	 *
	 * @param pop The population of CityTileset instances.
	 */
	public void apply(Population<CityTileset> pop) {
	    // Get the total number of neighborhoods in a city
	    int nNeighborhoods = pop.getArrayList().get(0).getTotalNeighborhoods();

	    // Ensure MUTABLENEIGHBORHOODS does not exceed the number of neighborhoods
	    MUTABLENEIGHBORHOODS = Math.min(MUTABLENEIGHBORHOODS, nNeighborhoods);

	    List<Map.Entry<Integer, Double>> sortedNeighborhoods = new ArrayList<>();

	    for (CityTileset city : pop) {
	        this.totalNeighborhoods = new TreeMap<>();

	        for (int i = 0; i < nNeighborhoods; i++) {
	            this.totalNeighborhoods.put(i, city.getNeighborhoodParkValue(new Position(i % 4, i / 4)));
	        }

	        sortedNeighborhoods.clear();
	        sortedNeighborhoods.addAll(totalNeighborhoods.entrySet());

	        // Sort the list using a custom comparator based on neighborhood values
	        sortedNeighborhoods.sort(Comparator.comparingDouble(Map.Entry::getValue));

	        //System.out.println("Pairs sorted by value:");
	        for (Map.Entry<Integer, Double> entry : sortedNeighborhoods.subList(0, MUTABLENEIGHBORHOODS)) {
	            //System.out.println("Index: " + entry.getKey() + ", Value: " + entry.getValue());
	            
	            List<Position> parks = city.getNeighborhoodParks(new Position(entry.getKey() % 4, entry.getKey() / 4));
	            // Apply mutation to the city for the selected neighborhoods
	            this.mutate(city, parks.toArray(new Position[0]), MUTATIONPROB);
	            
	            //System.out.println("Mutation: " + city.getNeighborhoodParkValue(new Position(entry.getKey() % 4, entry.getKey() / 4)));
	        }
	    }
	}

}
