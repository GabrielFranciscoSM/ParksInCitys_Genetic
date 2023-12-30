package Model.operators.mutation;

import java.util.Random;

import Basics.Position;
import Model.Individuals.CityTileset;

/**
 * Handles mutation operations on a city tileset.
 */
public class MutationHandler {

    private Random generator = new Random();

    /**
     * Applies mutation to the specified city tileset based on the given parameters.
     *
     * @param city          The city tileset to mutate.
     * @param parkPositions Array of park positions in the city for potential mutation.
     * @param mutationProb  Probability of mutation.
     */
    public void mutate(CityTileset city, Position[] parkPositions, double mutationProb) {
        int action = -1; // Stores the action that has been assigned until it is carried out
                         // (-1 -> no operation, 0 -> extend park, 1 -> remove park)

        for (Position park : parkPositions) {
            if ((generator.nextDouble() < mutationProb) || action != -1) {
                // action != -1 means that the operation assigned to another park has not been carried out, so
                // it will be tried on the next park.
                if (city.hasAvailableTiles()) {
                    if (action == -1) { // Choose an operation
                        action = generator.nextInt(2);
                    }
                    if (action == 0) {
                        if (city.extendPark(park, generator.nextInt(8))) {
                            action = -1;
                        }
                    } else {
                        city.removeParkTile(park);
                        action = -1;
                    }
                } else {
                    city.removeParkTile(park);
                    action = -1;
                }
            }
        }
    }
}
