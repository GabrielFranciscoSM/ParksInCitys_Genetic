/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals.Tiles;

/**
 *
 * @author gabriel
 */
public class RoadTile extends Tile {
    
    //getter
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
