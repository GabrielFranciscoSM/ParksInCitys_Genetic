/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals.Tiles;

/**
 *
 * @author gabriel
 */
public abstract class Tile {
    
    //Default value to send back to getValue()
    public static int NOVALUETILE = 0;
    
    //Give back a value:
    //  -NOVALUETILE if TileType is not its type
    //  -Other value (usually 1 for existance or personalized value)
    public int getValue(){
        return getValue(TileType.VOID);
    }
    public abstract int getValue(TileType type);
    
    //Specific function to know if TIle is VoidTile
    public boolean isVoid(){
        return(getValue(TileType.VOID) != NOVALUETILE);
    }
    
    //Specific function to know if TIle is BuildingTile
    public boolean isBuilding(){
        return(getValue(TileType.BUILDING) != NOVALUETILE);
    }
    
    //Specific function to know if TIle is ParkTile
    public boolean isPark(){
        return(getValue(TileType.PARK) != NOVALUETILE);
    }
    
    //Specific function to know if TIle is RoadTile
    public boolean isRoad(){
        return(getValue(TileType.ROAD) != NOVALUETILE);
    }
    
    //Function that defines in what Tile can be a Building build into
    public boolean canBuild(){
        return isVoid();
    }
    
    //toString method
    @Override
    public abstract String toString();
}
