/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals.Tiles;

/**
 *
 * @author gabriel
 */
public class BuildingTile extends Tile{    
    
    //Default number of citizens in a building tile.
    private static final int DEFAULTCITIZENS = 20;
    
    public final static int MAXCITIZEN = 100;
    public final static int MINCITIZEN = 5;
    
    final private int CITIZENS;
    
    //Default Contructor
    public BuildingTile(){
        CITIZENS = DEFAULTCITIZENS;
    }
    
    //Constructor that sets citizens
    public BuildingTile(int nCitycents){
        CITIZENS = nCitycents;
    }
    
    //Getters and setters
    public int getCITIZENS(){
        return CITIZENS;
    }
    
    @Override
    public int getValue(TileType type){
        if(type == TileType.BUILDING)
            return getCITIZENS();
        else
            return NOVALUETILE;
    }
    
    //toSetring method
    @Override
    public String toString(){
        return "B";
    }
}
