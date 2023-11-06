/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals.Tiles;

/**
 *
 * @author gabriel
 */
public abstract class Tile {
    
    //Default value to send back to getValue()
    public static int NOVALUETILE = -1;
    
    //Give back a value:
    //  -NOVALUETILE if TileType is not its type
    //  -Other value (usually 1 for existance or personalized value)
    public int getValue(){
        return getValue(TileType.VOID);
    }
    public abstract int getValue(TileType type);
    
    //Specific function to know if TIle is VoidTile
    public boolean isVoid(){
        return(getValue(TileType.VOID) == 1);
    }
    
    //toString method
    @Override
    public abstract String toString();
}
