/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals.Tiles;

/**
 *
 * @author gabriel
 */
public class RoadTile extends Tile {
    
    public RoadTile makeCopy(){
        return new RoadTile();
    }
    
    //Function to identificate if a generic tile is a Road tile
    @Override
    public int getValue(TileType type){
        if(type == TileType.ROAD)
            return 1;
        else
            return NOVALUETILE;
    }
    
    //toString method
    @Override
    public String toString(){
        return "R";
    }
}
