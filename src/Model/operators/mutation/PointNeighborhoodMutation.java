package Model.operators.mutation;

import java.util.Random;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Population;

import java.util.ArrayList;
import java.util.List;

/**
 * Mutation handler for applying point neighborhood mutations to a population of CityTilesets.
 */
public class PointNeighborhoodMutation extends MutationHandler {
    private Random generator;
    private double MUTATIONPROB; // Mutation probability
    private double POINTNEIGHBORHOODS; // Number of mutable neighborhoods
    private List<Integer> totalNeighborhoods;

    /**
     * Constructs a PointNeighborhoodMutation instance with a specified mutation probability and number of neighborhoods.
     *
     * @param mutationProb     Probability of mutation.
     * @param pointNeigh       Number of neighborhoods for mutation.
     */
    PointNeighborhoodMutation(double mutationProb, int pointNeigh) {
        this.generator = new Random();
        this.MUTATIONPROB = mutationProb;
        this.POINTNEIGHBORHOODS = pointNeigh;
        this.totalNeighborhoods = new ArrayList<>();
    }

    /**
     * Applies point neighborhood mutations to each city in the population.
     *
     * @param pop Population of CityTilesets.
     */
    public void apply(Population<CityTileset> pop) {
        int nNeighborhoods = pop.getArrayList().get(0).getTotalNeighborhoods();
        
        // Ensure POINTNEIGHBORHOODS does not exceed the number of neighborhoods
        POINTNEIGHBORHOODS = Math.min(POINTNEIGHBORHOODS, nNeighborhoods);

        // Initialize the list of total neighborhoods
        for (int i = 0; i < nNeighborhoods; i++) {
            totalNeighborhoods.add(i);
        }

        // Apply mutations to random neighborhoods in each city
        for (CityTileset city : pop) {
            List<Integer> neighborhoods = getRandomNeighborhoods();
            for (Integer neighborhood : neighborhoods) {
                // Get parks in the selected neighborhood
                List<Position> parks = city.getNeighborhoodParks(new Position(neighborhood % 4, neighborhood / 4));
                
                // Apply mutation to parks
                this.mutate(city, parks.toArray(new Position[0]), MUTATIONPROB);
            }
        }
    }

    /**
     * Generates a list of random neighborhoods.
     *
     * @return List of random neighborhoods.
     */
    private List<Integer> getRandomNeighborhoods() {
        List<Integer> neighborhoods = new ArrayList<>();
        List<Integer> options = new ArrayList<>(this.totalNeighborhoods);

        // Choose random neighborhoods from the available options
        for (int cont = 0; cont < this.POINTNEIGHBORHOODS; cont++) {
            int index = generator.nextInt(options.size());
            neighborhoods.add(options.remove(index));
        }

        return neighborhoods;
    }
}

