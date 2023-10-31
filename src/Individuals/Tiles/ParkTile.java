/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals.Tiles;

/**
 *
 * @author gabriel
 */
public class ParkTile extends Tile{
    private final static int AREAOFEFFECT = 2;
    private final static int VALUEPERCITIZEN = 1;
    
    private int value;
    
    //DefaultConstructor
    public ParkTile(){
        value = 0;
    }
    
    public ParkTile(int v){
        value = v;
    }
    
    //getters and setters
    static public int getAreaOfEffect(){
        return AREAOFEFFECT;
    }
    
    void setValue(int v){
        value = v;
    }
    
    @Override
    public int getValue(TileType type){
        if(type == TileType.PARK)
            return value;
        else
            return NOVALUETILE;
    }
    
    //Increment value taking into account a value per citicent
    public void addValue(int v){
        value += v*VALUEPERCITIZEN;
    }
    
    //To string method
    @Override
    public String toString(){
        return "P";
    }
}
