/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals.Tiles;

/**
 *
 * @author gabriel
 */
public class SchoolTile extends BuildingTile{    
    
    //Default Contructor
    public SchoolTile(){
        super(0);
    }
    
    @Override
    public SchoolTile makeCopy(){
        return new SchoolTile();
    }
    
    @Override
    public int getValue(TileType type){        
        if(type == TileType.BUILDING || type == TileType.SCHOOL)
            return 1;
        else
            return NOVALUETILE;
    }
    
    //toSetring method
    @Override
    public String toString(){
        return "B";
    }
}
