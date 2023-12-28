package Model.operators.crossover;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;

public class TilesCrossover extends CrossoverOperator<CityTileset> {

    private Random generator = new Random();
    private int SEGMENTS;
    private int size;
    private int totalTiles;

    /**
     * Constructs a TilesCrossover operator with a specified number of segments.
     *
     * @param segments Number of crossover segments.
     */
    public TilesCrossover(int segments) {
        this.SEGMENTS = segments;
    }

    /**
     * Applies the crossover operation to a population of CityTilesets.
     *
     * @param pop Population of CityTilesets.
     * @return Population of offspring CityTilesets after crossover.
     */
    public CityTilesetPopulation apply(CityTilesetPopulation pop) {
        size = pop.getArrayList().get(1).getSize();
        totalTiles = size * size;

        // Initialize offspring population
        CityTilesetPopulation offsprings = new CityTilesetPopulation(pop.getId(), pop.size());

        // Create random pairings for crossover
        ArrayList<Pairing> pairings = makeRandomPairings(pop);

        // Apply crossover to each pairing
        for (Pairing pairing : pairings) {
            // Generate random cutoff points for crossover
            Set<Integer> cutOffPoints = generateCutOffPoints();

            // Perform crossover on the pair
            CityTileset offspring1 = crossover(pairing.firstParent, pairing.secondParent, new TreeSet<>(cutOffPoints));
            CityTileset offspring2 = crossover(pairing.secondParent, pairing.firstParent, cutOffPoints);

            // Add offspring to the population
            offsprings.add(offspring1);
            offsprings.add(offspring2);
        }

        return offsprings;
    }
    
    /**
     * Generates random cutoff points for crossover segments.
     *
     * @return Set of random cutoff points.
     */
    private Set<Integer> generateCutOffPoints() {
        Set<Integer> cutOffPoints = new TreeSet<>();
        while (cutOffPoints.size() < this.SEGMENTS - 1) {
            cutOffPoints.add(generator.nextInt(totalTiles));
        }
        return cutOffPoints;
    }

    /**
     * Performs crossover on two parent CityTilesets using specified cutoff points.
     *
     * @param firstParent   First parent CityTileset.
     * @param secondParent  Second parent CityTileset.
     * @param cutOffPoints  Set of cutoff points for crossover segments.
     * @return Offspring CityTileset after crossover.
     */
    private CityTileset crossover(CityTileset firstParent, CityTileset secondParent, Set<Integer> cutOffPoints) {
        // Create offspring as a copy of the first parent
        CityTileset offspring = new CityTileset(firstParent);

        // Get pairs of start and end indices for crossover segments
        int[][] pairs = getPairs((TreeSet<Integer>) cutOffPoints);

        // Perform crossover for each segment
        for (int[] pair : pairs) {
            int start = pair[0];
            int end = pair[1] != -1 ? pair[1] : (totalTiles - 1);

            // Convert 1D index to 2D coordinates
            int xStart = start % size;
            int yStart = start / size;
            int xEnd = end % size;
            int yEnd = end / size;

            // Copy tiles from the second parent to the offspring
            for (int y = yStart; y <= yEnd; y++) {
                for (int x = xStart; x <= xEnd; x++) {
                    offspring.setTile(x, y, secondParent.getTile(x, y));
                }
            }
        }

        return offspring;
    }

    /**
     * Converts a sorted set of cutoff points into pairs of start and end indices.
     *
     * @param set Sorted set of cutoff points.
     * @return Array of pairs representing start and end indices.
     */
    private static int[][] getPairs(TreeSet<Integer> set) {
        int numElems = set.size();
        int[][] pairs = new int[numElems / 2 + numElems % 2][2];

        int i = 0;
        while (!set.isEmpty()) {
            int firstValue = set.pollFirst();
            int secondValue = set.isEmpty() ? -1 : set.pollFirst();

            pairs[i][0] = firstValue;
            pairs[i][1] = secondValue;

            i++;
        }

        return pairs;
    }
}
