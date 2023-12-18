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
    
    private int possibleValue;
    
    public VoidTile(){
        possibleValue = 1;
    }
    
    public VoidTile(int possibleValue){
        this.possibleValue = possibleValue;
    }
    
    public VoidTile makeCopy(){
        return new VoidTile(this.possibleValue);
    }
    
    public void setPossibleValue(int value){
        this.possibleValue = value;
    }
    
    //Function to identificate if a generic tile is a Void tile
    @Override
    public int getValue(TileType type){
        if(type == TileType.VOID)
            return possibleValue;
        else
            return NOVALUETILE;
    }
    
    //toString method
    @Override
    public String toString(){
        return "_";
    }
}
