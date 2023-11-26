/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals;

import Model.Individuals.Tiles.RoadTile;
import Model.Individuals.Tiles.VoidTile;
import Model.Individuals.Tiles.TileType;
import Model.Individuals.Tiles.NullTile;
import Model.Individuals.Tiles.Tile;
import Model.Individuals.Tiles.ParkTile;
import Model.Individuals.Tiles.BuildingTile;
import Basics.*;
import java.util.ArrayList;

/**
 *
 * @author Gabriel Sanchez
 */

//Individual
public class CityTileset extends Individual{
    
    public static final int DEFAULTSIZE = 200;
    static final public int MAXSIZE = 400;
    static final public int MINSIZE = 10;
    
    //Size of the neighborhoods. It works better if it divides SETSIZE
    static final private int NEIGHBORHOODSIZE = 10;
    
    //Saves the numer of cities created. Used to asign id
    static private int nCities = 0;
    
    //Arrays of Tiles and Neighborhoods
    private ArrayList<ArrayList<Tile>> tileset;
    private ArrayList<ArrayList<Neighborhood>> neighborhoods;
    
    
    //id of a city.
    final private int id;
    
    //Default constructor
    public CityTileset(){
        
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        ++nCities;
        id = nCities;
        
        for(int i = 0; i < DEFAULTSIZE; ++i){
            
            ArrayList<Tile> aux = new ArrayList<>(); 
            ArrayList<Neighborhood> aux2 = new ArrayList<>(); 
            
            for(int j = 0; j < DEFAULTSIZE; ++j){
                aux.add(new VoidTile());
                
                if(j % (NEIGHBORHOODSIZE) == 0){
                    aux2.add(new Neighborhood(
                            Neighborhood.DEFAULTMAXPARKS, 
                            NEIGHBORHOODSIZE));
                }
            }
            
            tileset.add(aux);
            if(i % (NEIGHBORHOODSIZE) == 0){
                neighborhoods.add(aux2);
            }
        }
    }
    
    //Size parammeter constructor
    public CityTileset(int size){
        
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        ++nCities;
        id = nCities;

        for(int i = 0; i < size; ++i){
            
            ArrayList<Tile> aux = new ArrayList<>(); 
            ArrayList<Neighborhood> aux2 = new ArrayList<>(); 
            
            for(int j = 0; j < size; ++j){
                aux.add(new VoidTile());
                
                if(j % (NEIGHBORHOODSIZE) == 0){
                    aux2.add(new Neighborhood(
                            Neighborhood.DEFAULTMAXPARKS, 
                            NEIGHBORHOODSIZE));
                }
            }
            
            tileset.add(aux);
            if(i % (NEIGHBORHOODSIZE) == 0){
                neighborhoods.add(aux2);
            }
        }
    }
    
    //Copy constructor. 
    public CityTileset(CityTileset cp){
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();

        ++nCities;
        id = nCities;
        for(int i = 0; i < cp.getSize(); ++i){
            
            ArrayList<Tile> aux = new ArrayList<>(); 
            ArrayList<Neighborhood> aux2 = new ArrayList<>(); 
            
            for(int j = 0; j < cp.getSize(); ++j){
                aux.add(cp.getTile(i,j).makeCopy());
                
                if(j % (NEIGHBORHOODSIZE) == 0){
                    aux2.add(new Neighborhood(cp.getNeigborhoodWithTilePos(new Position(i,j))));
                }
            }
            
            tileset.add(aux);
            if(i % (NEIGHBORHOODSIZE) == 0){
                neighborhoods.add(aux2);
            }
        }
    }
    
    public int getId(){
        return id;
    }
    
    //Getters and setters
    public Tile getTile(Position pos){
        
        if(pos.inRange(Position.ZERO, new Position(getSize()-1))){
            return tileset.get(pos.getX()).get(pos.getY());
        }
        else return new NullTile();
        
    }

    
    public Tile getTile(int x, int y){
        return getTile(new Position(x,y));
    }
    
    private Neighborhood getNeigborhoodWithTilePos(Position pos){
        return getNeigborhood(new Position(pos).div(NEIGHBORHOODSIZE));
    }
    
    private Neighborhood getNeigborhood(Position pos){
        if(pos.inRange(Position.ZERO,  new Position(neighborhoods.size()-1)))
            return neighborhoods.get(pos.getX()).get(pos.getY());
        else
            return null;
    }
    
    public int getSize(){
        return tileset.size();
    }
    
    public int getNeighborhoodSize(){
        return NEIGHBORHOODSIZE;
    }
    
        
    private void setTile(Position pos, Tile t){
        tileset.get(pos.getX()).set(pos.getY(), t);
    }
    
    //Change a Tile to another
    private boolean ChangeTile(Position pos, Tile tile){
        if(pos.inRange(Position.ZERO, new Position(getSize()-1))){
            setTile(pos,tile);
            return true;
        }
        else
            return false;
    }
    
    //Create a building Tile
    public void NewBuildingTile(Position pos){
        NewBuildingTile(pos, new BuildingTile());
    }
    
    public void NewBuildingTile(Position pos, Tile bt){
        if(getTile(pos).canBuild())
            ChangeTile(pos, bt);
    }
    
    public boolean canBuild(Position pos){
        if(pos.inRange(Position.ZERO, new Position(getSize()-1))){
            if(getTile(pos).canBuild()){
                return true;
            }
        }
        
        return false;
    }
    
    public void NewRoadTile(Position pos){
        if(getTile(pos).isVoid())
            ChangeTile(pos, new RoadTile());
    }
    
    //Create a new ParkTile. Things to take in consideration:
    //  -The number of parks in the Neighborhood of the park updates
    //  -The citizens inside the Parks Area are counted
    public boolean NewParkTile(Position pos){
        boolean canChange = false;
        
        if(getTile(pos).isVoid()){
            getNeigborhoodWithTilePos(pos).addPark();
            ChangeTile(pos, new ParkTile(getValueOfPark(pos)));
            
            canChange = true;
        }
        return canChange;
    }
    
    //
    public boolean NewParkTile(Position pos, Position neighbour){
        
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
                
                Position corner = Position.substract
                    (pos, rePos.mult(ParkTile.getAreaOfEffect()));
                Position opositeCorner = Position.sum
                    (pos, rePos.mult(ParkTile.getAreaOfEffect()));
                                
                v += getTile(corner).getValue(TileType.BUILDING);
                v -= getTile(opositeCorner).getValue(TileType.BUILDING);
                
                for(int i = 1; i < ParkTile.getAreaOfEffect()*2; ++i){
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
                
                int XindependentPart = ParkTile.getAreaOfEffect()*rePos.getX()
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
        
        if(v == 0)
            v = 1;
        
        return v;
        
    }
    
    //It counts the number of citizens in the ParkTile area
    int getValueOfPark(Position pos){
        int v = 0;
        int offset = ParkTile.getAreaOfEffect();
        
        Position topLeft = new Position(Math.max(0, pos.getX() - offset), 
                Math.max(0, pos.getY() - offset));
        Position botRight = new Position(Math.min(getSize()-1, pos.getX() + offset), 
                Math.min(getSize()-1, pos.getY() + offset));
        
        for(int i = topLeft.getX(); i <= botRight.getX(); ++i){
            for(int j = topLeft.getY(); j <= botRight.getY(); ++j){
                v += getTile(i,j).getValue(TileType.BUILDING);
            }
        }
        
        if(v == 0)
            v  = 1;
        
        return v;
    }
    
    @Override
    public String toString(){
        String city = new String();
        
        for(int i = 0; i < tileset.size(); ++i){
            for(int j = 0; j < tileset.size(); ++j){
                city += getTile(j,i).toString();
            }
            city += "\n";
        }
        
        return city;
    }
}
