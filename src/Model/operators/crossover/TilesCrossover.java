package Model.operators.crossover;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import Basics.Position;
import Model.CityParameters;
import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;
import Model.Individuals.Tiles.Tile;
import Model.operators.crossover.CrossoverOperator.Pairing;

public class TilesCrossover extends CrossoverOperator<CityTileset> {

    private Random generator = new Random();
    private int SEGMENTS;
    private double PROBABILITY;
    private int size;
    private int totalTiles;

    public TilesCrossover(int segments, double probability) {
        this.SEGMENTS = segments;
        this.PROBABILITY = probability;
    }

    public CityTilesetPopulation apply(CityTilesetPopulation pop) {
        size = pop.getArrayList().get(1).getSize();
        totalTiles = size * size;

        CityTilesetPopulation offsprings = new CityTilesetPopulation(pop.getId(), pop.size());

        ArrayList<Pairing> pairings = makeRandomPairings(pop);

        for (Pairing pairing : pairings) {
            Set<Integer> cutOffPoints = generateCutOffPoints();
            
            CityTileset offspring1 = crossover(pairing.firstParent, pairing.secondParent, new TreeSet<>(cutOffPoints));
            CityTileset offspring2 = crossover(pairing.secondParent, pairing.firstParent, cutOffPoints);

            offsprings.add(offspring1);
            offsprings.add(offspring2);
        }

        return offsprings;
    }

    private Set<Integer> generateCutOffPoints() {
        Set<Integer> cutOffPoints = new TreeSet<>();
        while (cutOffPoints.size() < this.SEGMENTS - 1) {
            cutOffPoints.add(generator.nextInt(totalTiles));
        }
        return cutOffPoints;
    }

    private CityTileset crossover(CityTileset firstParent, CityTileset secondParent, Set<Integer> cutOffPoints) {
        CityTileset offspring = new CityTileset(firstParent);

        int[][] pairs = getPairs((TreeSet<Integer>) cutOffPoints);
        for (int[] pair : pairs) {
            int start = pair[0];
            int end = pair[1] != -1 ? pair[1] : (totalTiles - 1);

            int xStart = start % size;
            int yStart = start / size;
            int xEnd = end % size;
            int yEnd = end / size;

            if (xStart > xEnd) {
                int temp = xStart;
                xStart = xEnd;
                xEnd = temp;
            }

            if (yStart > yEnd) {
                int temp = yStart;
                yStart = yEnd;
                yEnd = temp;
            }

            offspring.setTiles(new Position(xStart, yStart), secondParent.getTiles(new Position(xStart, yStart), new Position(xEnd, yEnd)));
        }

        return offspring;
    }

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
