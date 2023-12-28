package Model.operators.mutation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Population;

/**
 * Mutation handler for applying targeted neighborhoods mutation to a population of CityTilesets.
 */
public class TargetedNeighborhoodMutation extends MutationHandler {

    private double MUTATIONPROB; // Mutation probability
    private int MUTABLENEIGHBORHOODS; // Number of mutable neighborhoods
    TreeMap<Integer, Double> totalNeighborhoods; // Map of neighborhood indices to values

    /**
     * Constructs a TargetedNeighborhoodMutation instance with a specified mutation probability and number of neighborhoods.
     *
     * @param mutationProb         Probability of mutation.
     * @param mutableNeighborhoods Number of neighborhoods for mutation.
     */
    TargetedNeighborhoodMutation(double mutationProb, int mutableNeighborhoods) {
        this.MUTATIONPROB = mutationProb;
        this.MUTABLENEIGHBORHOODS = mutableNeighborhoods;
    }

    /**
     * Applies targeted neighborhood mutation to the population of city tilesets.
     *
     * @param pop Population of city tilesets.
     */
    public void apply(Population<CityTileset> pop) {
        int nNeighborhoods = pop.getArrayList().get(0).getTotalNeighborhoods();

        // Ensure MUTABLENEIGHBORHOODS does not exceed the number of neighborhoods
        MUTABLENEIGHBORHOODS = Math.min(MUTABLENEIGHBORHOODS, nNeighborhoods);

        List<Map.Entry<Integer, Double>> sortedNeighborhoods = new ArrayList<>();

        for (CityTileset city : pop) {
            this.totalNeighborhoods = new TreeMap<>();

            // Populate totalNeighborhoods map with neighborhood indices and values
            for (int i = 0; i < nNeighborhoods; i++) {
                this.totalNeighborhoods.put(i, city.getNeighborhoodParkValue(new Position(i % 4, i / 4)));
            }

            sortedNeighborhoods.clear();
            sortedNeighborhoods.addAll(totalNeighborhoods.entrySet());

            // Sort the list based on neighborhood values
            sortedNeighborhoods.sort(Comparator.comparingDouble(Map.Entry::getValue));

            // Iterate through the selected number of mutable neighborhoods
            for (Map.Entry<Integer, Double> entry : sortedNeighborhoods.subList(0, MUTABLENEIGHBORHOODS)) {
                // Get the parks in the selected neighborhood
                List<Position> parks = city.getNeighborhoodParks(new Position(entry.getKey() % 4, entry.getKey() / 4));
                // Apply mutation to the city for the selected neighborhoods
                this.mutate(city, parks.toArray(new Position[0]), MUTATIONPROB);
            }
        }
    }
}

