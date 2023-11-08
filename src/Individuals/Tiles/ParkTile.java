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
    //Determines the distance of reach of a park (Final area is:
    //AREAOFEFFECT*2+1 x AREAOFEFFECT*2+1
    private final static int AREAOFEFFECT = 2;
    
    //Multiplicative coefficient to obtain value from citizens
    private final static int VALUEPERCITIZEN = 1;
    
    private int value; // >= 1
    
    //Constructor to inizilize value
    public ParkTile(int v){
        value = v;
    }
    
    //Default Constructor
    public ParkTile(){
        value = 1;
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
    
    //Increment value taking into account a value per citizen
    public void addValue(int v){
        value += v*VALUEPERCITIZEN;
    }
    
    //To string method
    @Override
    public String toString(){
        return "P";
    }
}
