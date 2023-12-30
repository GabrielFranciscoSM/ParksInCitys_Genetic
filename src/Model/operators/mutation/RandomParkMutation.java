/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.mutation;

import Model.Individuals.CityTileset;
import Model.Individuals.Population;

import Basics.Position;

/**
 * Mutation handler for applying random park mutations to a population of CityTilesets.
 */
public class RandomParkMutation extends MutationHandler {

    private double MUTATIONPROB; // Mutation probability

    /**
     * Constructs a RandomParkMutation instance with a specified mutation probability.
     *
     * @param mutationProb Probability of mutation.
     */
    public RandomParkMutation(double mutationProb) {
        this.MUTATIONPROB = mutationProb;
    }

    /**
     * Applies random park mutations to each city in the population.
     *
     * @param pop Population of CityTilesets.
     */
    public void apply(Population<CityTileset> pop) {
        for (CityTileset city : pop) {
            Position[] parkPositions = city.getArrayOfParkPositions().toArray(new Position[0]);    // Parks in a city
            this.mutate(city, parkPositions, MUTATIONPROB);
        }
    }
}
