/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals.Tiles;

/**
 *
 * @author gabriel
 */
public class BuildingTile extends Tile{    
    static final int DEFAULTCITIZENS = 20;
    
    final private int citizens;
    
    //Default Contructor
    public BuildingTile(){
        citizens = DEFAULTCITIZENS;
    }
    
    //Constructor that sets citizens
    public BuildingTile(int nCitycents){
        citizens = nCitycents;
    }
    
    
    //Getters and setters
    public int getCitizens(){
        return citizens;
    }
    
    @Override
    public int getValue(TileType type){
        if(type == TileType.BUILDING)
            return getCitizens();
        else
            return NOVALUETILE;
    }
    
    
    //toSetring method
    @Override
    public String toString(){
        return "B";
    }
}
