/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gabriel
 * 
 * This class Represents a fixed size population of CityTileset
 */
public class CityTilesetPopulation extends FixedSizePopulation<CityTileset>{
    
    /**
     * Constructor.
     * @param id        Id of the generation (population)
     * @param maxSize   Max size of population
     */
    public CityTilesetPopulation(long id, int maxSize) {
        super(id, maxSize);
    }
    
    /**
     * Get max possible park value of the city
     * @return Max possible park value in city.
     */
    public int getMaxParkValue(){
        return CityTileset.getMaxValue();
    }
        
    /**
     * Sorts the population by fitness and returns it.
     * @return A ordered list of CityTileset.
     */
    public List<CityTileset> sortPopulationByFitness(){
        return this.stream()
        .sorted(Comparator.comparingDouble(CityTileset::getFitness).reversed())
        .collect(Collectors.toList());
    }
}
