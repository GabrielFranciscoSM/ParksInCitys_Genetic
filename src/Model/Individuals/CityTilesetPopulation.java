/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals;

import Model.Individuals.Tiles.TileType;

/**
 *
 * @author gabriel
 */
public class CityTilesetPopulation extends FixedSizePopulation<CityTileset>{
    
    public CityTilesetPopulation(long id, int maxSize) {
        super(id, maxSize);
    }
    
    public int getMaxParkValue(){
        int max = 0;
        for(CityTileset ct: this){
            int aux = ct.getTile(ct.getMaxPark()).getValue(TileType.PARK);
            if(aux > max){
                max = aux;
            }
        }
        
        return max;
    }
    
}
