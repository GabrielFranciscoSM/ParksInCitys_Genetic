/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals;

import Individuals.Tiles.*;
import Basics.*;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */

//Individual
public class CityTileset {
    
    //Size of the array of Tiles
    static final int SETSIZE = 100;
    
    //Size of the neighborhoods. It works better if it divides SETSIZE
    static final int NEIGHBORHOODSIZE = 10;
    
    //Arrays of Tiles and Neighborhoods
    ArrayList<ArrayList<Tile>> tileset;
    ArrayList<ArrayList<Neighborhood>> neighborhoods;
    
    
    //Default constructor
    public CityTileset(){
        
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        
        for(int i = 0; i < SETSIZE; ++i){
            
            ArrayList<Tile> aux = new ArrayList<>(); 
            ArrayList<Neighborhood> aux2 = new ArrayList<>(); 
            
            for(int j = 0; j < SETSIZE; ++j){
                aux.add(new VoidTile());
                
                if(j % (NEIGHBORHOODSIZE) == 0){
                    aux2.add(new Neighborhood());
                }
            }
            
            tileset.add(aux);
            if(i % (NEIGHBORHOODSIZE) == 0){
                neighborhoods.add(aux2);
            }
        }
    }
    
    //Getters and setters
    Tile getTile(Position pos){
        if(pos.inRange(Position.ZERO, new Position(SETSIZE-1))){
            return tileset.get(pos.getX()).get(pos.getY());
        }
        else return null;
        
    }
    
    Tile getTile(int x, int y){
        return getTile(new Position(x,y));
    }
    
    Neighborhood getNeigborhoodWithTilePos(Position pos){
        return getNeigborhood(pos.div(NEIGHBORHOODSIZE));
    }
    
    Neighborhood getNeigborhood(Position pos){
        if(pos.inRange(Position.ZERO,  new Position(neighborhoods.size()-1)))
            return neighborhoods.get(pos.getX()).get(pos.getY());
        else
            return null;
    }
    
    //Change a Tile to another
    private boolean ChangeTile(Position pos, Tile tile){
        if(pos.inRange(Position.ZERO, new Position(SETSIZE-1))){
            tileset.get(pos.getX()).set(pos.getY(), tile);
            return true;
        }
        else
            return false;
    }
    
    //Create a new ParkTile. Things to take in consideration:
    //  -The number of parks in the Neighborhood of the park updates
    //  -The citizens inside the Parks Area are counted
    boolean NewParkTile(Position pos){
        
        boolean canChange = false;
        
        if(getTile(pos).isVoid()){
            
            getNeigborhoodWithTilePos(pos).addPark();
            ChangeTile(pos, new ParkTile(getValueOfPark(pos)));
            
            canChange = true;
        }
        
        return canChange;
    }
    
    //
    boolean NewParkTile(Position pos, Position neighbour){
        
        boolean canChange = false;
        
        if(getTile(pos).isVoid()){
            
            getNeigborhoodWithTilePos(pos).addPark();
            
            int v  = getValueOfPark(pos, neighbour);
            
            if(v < 0){
                v = getValueOfPark(pos);
            }
            
            ChangeTile(pos, new ParkTile(v));
            
            canChange = true;
        }
        
        return canChange;
    }
    
    //Get the value of a new park, but generated with another park next to it.
    //This is done to increase effciency by decreasing the number of tiles
    //to count. 
    //This method can be generalized so the "neighbour" tile doesn have to be
    //next to de original.
    int getValueOfPark(Position pos, Position neighbour){
        int v = getTile(neighbour).getValue(TileType.PARK);
        int offset = ParkTile.getAreaOfEffect();

        Position rePos = Position.substract(pos, neighbour);
        
        switch (Math.abs(rePos.getX())+ Math.abs(rePos.getY())) {
            case 2:
                
                Position corner = new Position(
                        pos.getX()-rePos.getX()*ParkTile.getAreaOfEffect(),
                        pos.getY()-rePos.getY()*ParkTile.getAreaOfEffect());
                Position opositeCorner = new Position(
                        pos.getX()+rePos.getX()*ParkTile.getAreaOfEffect(),
                        pos.getY()+rePos.getY()*ParkTile.getAreaOfEffect());
                
                v += getTile(corner).getValue(TileType.BUILDING);
                
                for(int i = 1; i < ParkTile.getAreaOfEffect()*2+1; ++i){
                    v += getTile(corner.getX()+ i*rePos.getX(), 
                            corner.getY()).getValue(TileType.BUILDING);
                    v += getTile(corner.getX(), corner.getY()+ 
                            i*rePos.getY()).getValue(TileType.BUILDING);
                    v -= getTile(corner.getX()- i*rePos.getX(), 
                            corner.getY()).getValue(TileType.BUILDING);
                    v -= getTile(corner.getX(), corner.getY()- 
                            i*rePos.getY()).getValue(TileType.BUILDING);
                }
                
                break;
            case 1:
                
                int XindependentPart = (ParkTile.getAreaOfEffect())*rePos.getX()
                        +pos.getX() + rePos.getY()*(ParkTile.getAreaOfEffect());
                
                int YindependentPart = (ParkTile.getAreaOfEffect())*rePos.getY()
                        +pos.getY()+rePos.getX()*(ParkTile.getAreaOfEffect());
                
                for(int i = 0; i < ParkTile.getAreaOfEffect()*2+1; ++i){
                    
                    v += getTile( XindependentPart-rePos.getY()*i, 
                        YindependentPart-rePos.getX()*i).getValue(TileType.BUILDING)* 
                        (rePos.getX()+rePos.getY());
                    
                    v -= getTile(-XindependentPart+rePos.getY()*i, 
                        -YindependentPart + rePos.getX()*i).getValue(TileType.BUILDING)* 
                        (rePos.getX()+rePos.getY());
                }
                break;
            default:
                v = -1;
                break;
        }
        
        return v;
        
    }
    
    //It counts the number of citizens in the ParkTile area
    int getValueOfPark(Position pos){
        int v = 0;
        int offset = ParkTile.getAreaOfEffect();
        
        Position topLeft = new Position(Math.max(0, pos.getX() - offset), 
                Math.max(0, pos.getY() - offset));
        Position botRight = new Position(Math.min(SETSIZE-1, pos.getX() + offset), 
                Math.min(SETSIZE-1, pos.getY() + offset));
        
        for(int i = topLeft.getX(); i < botRight.getX(); ++i){
            for(int j = topLeft.getY(); j < botRight.getY(); ++j){
                v += getTile(i,j).getValue(TileType.BUILDING);
            }
        }
        
        return v;
    }
}
