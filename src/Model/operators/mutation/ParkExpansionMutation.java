/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.mutation;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Population;

import java.util.ArrayList;
import java.util.Random;

/**
 * Handles the park expansion mutation for a population of city tilesets.
 */
public class ParkExpansionMutation {
    static int maxSize = 500;
    final static double EXTENDPROB = 0.5;
    private Random generator;
    private double MUTATIONPROB; // Mutation probability
    
    ParkExpansionMutation(double mutationProb){
    	this.generator = new Random();
    	this.MUTATIONPROB = mutationProb;
    }
    
    /**
     * Applies park expansion mutation to the population of city tilesets.
     *
     * @param pop The population of city tilesets.
     */
    public void apply(Population<CityTileset> pop) {
        for (CityTileset city : pop) {
            if (generator.nextDouble() < MUTATIONPROB) {
                if (generator.nextDouble() < EXTENDPROB) {
                    Position auxPos = getRandomVoidPosition(city);
                    city.NewParkTile(auxPos);
                    extendParkRecursive(auxPos, city, 0);
                } else {
                    if (city.getNparkTiles() <= 0) {
                        Position auxPos = getRandomVoidPosition(city);
                        city.NewParkTile(auxPos);
                        extendParkRecursive(auxPos, city, 0);
                    } else {
                        Position auxPos = city.getParkTile(generator.nextInt(city.getNparkTiles()));
                        removeParkRecursive(auxPos, city);

                        if (city.getNparkTiles() <= 0) {
                            auxPos = getRandomVoidPosition(city);
                            city.NewParkTile(auxPos);
                            extendParkRecursive(auxPos, city, 0);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Get a random position in the city that is currently a void tile.
     *
     * @param city The CityTileset instance representing the city.
     * @return A random void position in the city.
     */
    private Position getRandomVoidPosition(CityTileset city) {
        // Generate a random position within the city size
        Position auxPos = new Position(
                generator.nextInt(city.getSize()),
                generator.nextInt(city.getSize()));

        // Keep generating new positions until a void tile is found
        while (!city.getTile(auxPos).isVoid()) {
            auxPos = new Position(
                    generator.nextInt(city.getSize()),
                    generator.nextInt(city.getSize()));
        }

        return auxPos;
    }

    
    /**
     * Recursive method to extend a park and its connected voids in a city tileset.
     *
     * @param pos  The position of the park to be extended.
     * @param ct   The city tileset containing the park.
     * @param size Current size of the extended park.
     * @return The updated size of the extended park.
     */
    public int extendParkRecursive(Position pos, CityTileset ct, int size) {
        // Check if the tile at the given position is a park and the size limit is not reached
        if (ct.getTile(pos).isPark() && size < maxSize) {
            int parkOffsetsLength = CityTileset.parkOffsets.length;
            int tile = generator.nextInt(parkOffsetsLength);

            // Iterate through park offsets to extend the park
            for (int i = 0; i < parkOffsetsLength; i++) {
                int index = (tile + i) % parkOffsetsLength;
                Position auxPos = Position.sum(pos, CityTileset.parkOffsets[index]);

                // Check if the tile is void and the size limit is not reached
                if (ct.getTile(auxPos).isVoid() && size < maxSize) {
                    ct.NewParkTile(auxPos);
                    size += 1;
                    size = extendParkRecursive(auxPos, ct, size);
                }
            }
        }

        return size;
    }
    
    /**
     * Recursive method to remove a park and its connected parks from a city tileset.
     *
     * @param pos The position of the park to be removed.
     * @param ct The city tileset containing the park.
     */
    public void removeParkRecursive(Position pos, CityTileset ct) {
        // Check if the tile at the given position is a park
        if (ct.getTile(pos).isPark()) {
            ct.removeParkTile(pos);

            int parkOffsetsLength = CityTileset.parkOffsets.length;
            int tile = generator.nextInt(parkOffsetsLength);

            // Iterate through park offsets to check connected parks
            for (int i = 0; i < parkOffsetsLength; i++) {
                int index = (tile + i) % parkOffsetsLength;
                Position auxPos = Position.sum(pos, CityTileset.parkOffsets[index]);

                // Recursively remove connected parks
                if (ct.getTile(auxPos).isPark()) {
                    removeParkRecursive(auxPos, ct);
                }
            }
        }
    }
    
}
