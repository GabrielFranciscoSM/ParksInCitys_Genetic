/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals.Tiles;

/**
 *
 * @author gabriel
 */
public class VoidTile extends Tile{
    
    //Function to identificate if a generic tile is a Void tile
    @Override
    public int getValue(TileType type){
        if(type == TileType.VOID)
            return 1;
        else
            return NOVALUETILE;
    }
    
    //toString method
    @Override
    public String toString(){
        return "_";
    }
}
