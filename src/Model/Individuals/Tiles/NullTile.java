/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals.Tiles;

/**
 *
 * @author gabriel
 */
public class NullTile extends Tile{
    
    public NullTile makeCopy(){
        return new NullTile();
    }
    
    public int getValue(TileType t){
        return NOVALUETILE;
    }
    
    @Override
    public String toString(){
        return "";
    }
}
