/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals;

import Model.Individuals.Tiles.TileType;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gabriel
 */
public class CityTilesetPopulation extends FixedSizePopulation<CityTileset>{
    
    public CityTilesetPopulation(long id, int maxSize) {
        super(id, maxSize);
    }
    
    public int getMaxParkValue(){
        return CityTileset.getMaxValue();
    }
    
        
    public List<CityTileset> sortPopulationByFitness(){
        return this.stream()
        .sorted(Comparator.comparingDouble(CityTileset::getFitness).reversed())
        .collect(Collectors.toList());
    }
}
