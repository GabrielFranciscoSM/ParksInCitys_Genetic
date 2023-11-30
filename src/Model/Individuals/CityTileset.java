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
    
    private ArrayList<Position> parkTiles;
    private int freeTiles;
    //id of a city.
    final private int id;
    
    //Default constructor
    public CityTileset(){
        
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        parkTiles = new ArrayList<>();

        ++nCities;
        id = nCities;
        freeTiles = DEFAULTSIZE*DEFAULTSIZE;
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
        parkTiles = new ArrayList<>();

        ++nCities;
        id = nCities;
        freeTiles = size*size;
        
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
        parkTiles = new ArrayList<>();

        ++nCities;
        id = nCities;
        freeTiles = cp.getFreeTiles();
        
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
    
    //Getters and setters
    
    public int getNeighborhoodNParks(Position pos){
        return getNeigborhood(pos).getNParks();
    }
    
    public void setNeighborhoodNParks(Position pos, int nparks){
        getNeigborhood(pos).setNParks(nparks);
    }
    
    public int getFreeTiles(){
        return freeTiles;
    }
    
     public int getId(){
        return id;
    }
     
     public int getNparkTiles(){
         return parkTiles.size();
     }
     
     public Position getParkTile(int i){
         
        Position pos = parkTiles.get(i);
        
        return pos;
     }
     
     
    public Tile getTile(Position pos){
        
        if(inRange(pos)){
            return tileset.get(pos.getX()).get(pos.getY());
        }
        else return new NullTile();
        
    }
    
    public Tile getTile(int x, int y){
        return getTile(new Position(x,y));
    }
    
    public ArrayList<ArrayList<Tile>> getTiles(Position topLeft, Position botRight){
        ArrayList<ArrayList<Tile>> part = new ArrayList<>();
        if(inRange(topLeft) && inRange(botRight)){
            for(int y = topLeft.getY(); y <= botRight.getY(); ++y){
                
                ArrayList<Tile> aux = new ArrayList<>();
                
                for(int x = topLeft.getX(); x <= botRight.getX(); ++x){
                    aux.add(getTile(new Position(x,y)).makeCopy());
                }
                
                part.add(aux);
            }
        }
        
        return part;
    }
    
    public ArrayList<ArrayList<Tile>> getNeighborhoodTiles(Position pos){
        
        if(inRange(pos)){
            Position botRight = new Position(
                Math.min(pos.getX()+NEIGHBORHOODSIZE, getSize()-1),
                Math.min(pos.getY()+NEIGHBORHOODSIZE, getSize()-1));
            
            return getTiles(pos,botRight);
        }
        
        
        return null;
    }
    
    public void setTiles(Position topLeft, ArrayList<ArrayList<Tile>> tiles){
        if(inRange(Position.sum(topLeft, new Position(tiles.size(),0))) &&
           inRange(Position.sum(topLeft, new Position(0,tiles.get(0).size())))){
            for(int y = topLeft.getY(); y <= topLeft.getY()+tiles.size(); ++y){
                
                for(int x = topLeft.getX(); x <= topLeft.getY()+tiles.get(0).size(); ++x){
                    ChangeTile(topLeft,getTile(new Position(x,y)));
                }                
            }
        }
    
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
        return neighborhoods.size();
    }
    
        
    private void setTile(Position pos, Tile t){
        if(!t.toString().equals(getTile(pos).toString())){
            if(t.isVoid()){
                ++freeTiles;
            } else if(!getTile(pos).isVoid()){
                --freeTiles;
            }
        }
        
        tileset.get(pos.getX()).set(pos.getY(), t);
    }
    
    private boolean inRange(Position pos){
        return pos.inRange(Position.ZERO, new Position(getSize()-1));
    }
    
    //Change a Tile to another
    private boolean ChangeTile(Position pos, Tile tile){
        if(inRange(pos)){
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
        if(inRange(pos)){
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
            parkTiles.add(pos);
            
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
            parkTiles.add(pos);
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
    
    public void removeParkTile(Position pos){
        if(inRange(pos)){
            if(tileset.get(pos.getX()).get(pos.getY()).isPark()){
                parkTiles.remove(pos);
                ChangeTile(pos,new VoidTile());
            }
        }
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
    
    public boolean extendPark(Position pos){
        
        Position aux = Position.substract(pos, new Position(1,1));
        
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                if(getTile(
               Position.sum(aux, new Position(i,j)))
                   .isVoid()){
                    NewParkTile(Position.sum(aux, new Position(i,j)));
                    return true;
                }
            }
        }
        
        return false;
    }
}
