/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals;

import Model.Individuals.Tiles.*;
import Basics.*;
import Model.CityParameters;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriel Sanchez
 * 
 * CityTileset Represents an individual. It is formed by a two 
 * dimensional array of Tiles that can be: 
 *  - Parks
 *  - Building
 *  - Roads
 *  - Void tiles
 */
public class CityTileset extends Individual{
        
    static private int nCities = 0;
    ///Saves the numer of cities created. Used to asign id
    
    private ArrayList<ArrayList<Tile>> tileset;
    ///Array of tiles
    private ArrayList<ArrayList<Neighborhood>> neighborhoods;
    ///Array of neighborhoods (set of tiles)
    
    private List<Position> parkTiles;
    ///Saves the positions of the parkTiles in th city

    
    private int freeTiles;
    ///Numeber of disponible tiles (void tiles)
    
    private int disponibleTiles;
    ///Number of park tiles and free tiles
    
    private static int maxValue;
    ///Saves the max value that a park can take. Used in fitness function.
    
    //id of a city.
    final private int id;
    
    ////////////////////////////////////////////////////////////////////////////
    /*CONSTRUCTORS*/
    ////////////////////////////////////////////////////////////////////////////
    
    //Default constructor
    public CityTileset(){
        
        //Inicialize arrays
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        parkTiles = new ArrayList<>();
        
        id = ++nCities;
        freeTiles = CityParameters.DEFAULTSIZE*CityParameters.DEFAULTSIZE;
        disponibleTiles = freeTiles;
        maxValue = 0;
        
        for(int i = 0; i < CityParameters.DEFAULTSIZE; ++i){
            
            ArrayList<Tile> aux = new ArrayList<>(); 
            ArrayList<Neighborhood> aux2 = new ArrayList<>(); 
            
            for(int j = 0; j < CityParameters.DEFAULTSIZE; ++j){
                aux.add(new VoidTile());
                
                if(j % (CityParameters.NEIGHBORHOODSIZE) == 0){
                    aux2.add(new Neighborhood(
                            Neighborhood.DEFAULTMAXPARKS, CityParameters.NEIGHBORHOODSIZE));
                }
            }
            
            tileset.add(aux);
            if(i % (CityParameters.NEIGHBORHOODSIZE) == 0){
                neighborhoods.add(aux2);
            }
        }
    }
    
    //Size parammeter constructor
    public CityTileset(int size){
        
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        parkTiles = new ArrayList<>();
        
        id = ++nCities;
        freeTiles = size*size;
        disponibleTiles = freeTiles;
        maxValue = 0;
        
        for(int i = 0; i < size; ++i){
            
            ArrayList<Tile> aux = new ArrayList<>(); 
            ArrayList<Neighborhood> aux2 = new ArrayList<>(); 
            
            for(int j = 0; j < size; ++j){
                aux.add(new VoidTile());
                
                if(j % (CityParameters.NEIGHBORHOODSIZE) == 0){
                    aux2.add(new Neighborhood(
                            Neighborhood.DEFAULTMAXPARKS, CityParameters.NEIGHBORHOODSIZE));
                }
            }
            
            tileset.add(aux);
            if(i % (CityParameters.NEIGHBORHOODSIZE) == 0){
                neighborhoods.add(aux2);
            }
        }
    }
    
    //Copy constructor. 
    public CityTileset(CityTileset cp){
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        parkTiles = new ArrayList<>();
        
        id = ++nCities;
        freeTiles = cp.getFreeTiles();
        disponibleTiles = freeTiles;
        maxValue = cp.maxValue;
        
        for(int i = 0; i < cp.getSize(); ++i){
            
            ArrayList<Tile> aux = new ArrayList<>(); 
            ArrayList<Neighborhood> aux2 = new ArrayList<>(); 
            
            for(int j = 0; j < cp.getSize(); ++j){
                aux.add(cp.getTile(i,j).makeCopy());
                
                if(j % (CityParameters.NEIGHBORHOODSIZE) == 0)
                    aux2.add(cp.getNeigborhoodWithTilePos(new Position(i,j)));
            }
            
            tileset.add(aux);
            if(i % (CityParameters.NEIGHBORHOODSIZE) == 0){
                this.neighborhoods.add(aux2);
            }
        }
    }
    
    //Constructor with a given TilseSet
    public CityTileset(ArrayList<ArrayList<Tile>> tiles){
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        parkTiles = new ArrayList<>();

        ++nCities;
        id = nCities;
        freeTiles = 0;
        disponibleTiles = 0;
        maxValue = 0;
        
        for(int i = 0; i < tiles.size(); ++i){
            
            ArrayList<Tile> aux = new ArrayList<>(); 
            ArrayList<Neighborhood> aux2 = new ArrayList<>();
            
            tileset.add(aux);
            if(i % (CityParameters.NEIGHBORHOODSIZE) == 0){
                neighborhoods.add(aux2);
            }
            
            for(int j = 0; j < tiles.get(0).size(); ++j){
                aux.add(tiles.get(i).get(j).makeCopy());
                
                if(j % (CityParameters.NEIGHBORHOODSIZE) == 0){
                    aux2.add(new Neighborhood(
                         Neighborhood.DEFAULTMAXPARKS, 
                            CityParameters.NEIGHBORHOODSIZE));
                }
                
                if(tiles.get(i).get(j).isVoid()){
                    ++freeTiles;
                    ++disponibleTiles;
                }
                if(tiles.get(i).get(j).isPark()){
                    ++disponibleTiles;
                    neighborhoods.get(i/(CityParameters.NEIGHBORHOODSIZE)).
                                  get(j/(CityParameters.NEIGHBORHOODSIZE)).
                                  addPark((ParkTile)tiles.get(i).get(j));
                }
               
            }
            
            for(int j = tiles.get(0).size(); j < tiles.size(); ++j){
                aux.add(new VoidTile());
                ++freeTiles;
                ++disponibleTiles;
                
                if(j % (CityParameters.NEIGHBORHOODSIZE) == 0){
                    aux2.add(new Neighborhood(
                         Neighborhood.DEFAULTMAXPARKS, 
                            CityParameters.NEIGHBORHOODSIZE));
                }
            }
            
            
        }
        
        for(int i = tiles.size(); i < tiles.get(0).size(); ++i){
            ArrayList<Tile> aux = new ArrayList<>();
            ArrayList<Neighborhood> aux2 = new ArrayList<>();

            tileset.add(aux);
            if(i % (CityParameters.NEIGHBORHOODSIZE) == 0){
                neighborhoods.add(aux2);
            }
            for(int j = 0; j < tiles.get(0).size(); ++j){
                aux.add(new VoidTile());
                ++freeTiles;
                ++disponibleTiles;
                
                if(j % (CityParameters.NEIGHBORHOODSIZE) == 0){
                    aux2.add(new Neighborhood(
                         Neighborhood.DEFAULTMAXPARKS, 
                            CityParameters.NEIGHBORHOODSIZE));
                }
            }
            
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /*Getters and setters*/
    ////////////////////////////////////////////////////////////////////////////
    
    public static int getMaxValue(){
        return maxValue;
    }
    
    public static void setMaxValue(int m){
        maxValue = m;
    }
    
    public int getDisponibleTiles(){
        return disponibleTiles;
    }
    
    public void setDisponibleTiles(int i){
        disponibleTiles = i;
    }
    
    public double getPercentageOfParks(){
        return ((double)parkTiles.size()/(double)getDisponibleTiles())*100;
    }
    
    public int getFreeTiles(){
        return freeTiles;
    }
    
    public void setFreeTiles(int ft){
        freeTiles = ft;
    }
    
    public int getId(){
        return id;
    }
     
     public int getNparkTiles(){
         return parkTiles.size();
     }
     
     public Position getParkTile(int i){
        return parkTiles.get(i);
     }
     
     public List<Position> getArrayOfParkPositions(){
    	 return this.parkTiles;
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
            for(int x = topLeft.getX(); x <= botRight.getX(); ++x){
                
                ArrayList<Tile> aux = new ArrayList<>();
                
                for(int y = topLeft.getY();y <= botRight.getY(); ++y){
                    aux.add(getTile(new Position(x,y)).makeCopy());
                }
                
                part.add(aux);
            }
        }
        
        return part;
    }
    
    public ArrayList<ArrayList<Tile>> getNeighborhoodTiles(Position pos){
        Position realPos = Position.mul(pos, CityParameters.NEIGHBORHOODSIZE);
        
        if(inRange(realPos)){
            Position botRight = new Position(
                Math.min(realPos.getX()+CityParameters.NEIGHBORHOODSIZE, getSize()-1),
                Math.min(realPos.getY()+CityParameters.NEIGHBORHOODSIZE, getSize()-1));
            
            return getTiles(realPos,botRight);
        }
        
        
        return null;
    }
    
    public void setTiles(Position topLeft, ArrayList<ArrayList<Tile>> tiles){  
        
        int hLength = Math.min(tiles.size(), this.getSize() - topLeft.getX());
        int yLength = Math.min(tiles.get(0).size(), this.getSize() - topLeft.getY());

        if(inRange(Position.sum(topLeft, new Position(tiles.size(),0))) &&
           inRange(Position.sum(topLeft, new Position(0,tiles.get(0).size())))){
            
            for(int x = 0; x < hLength; ++x){
                
                for(int y = 0; y < yLength; ++y){
                    
                    boolean addVoid = false;
                    Position aux = new Position(topLeft.getX()+x,topLeft.getY()+y);
                    
                    if(tiles.get(x).get(y).isPark()){
                        if(this.getTile(aux).isVoid()){
                            addVoid = !this.getNeigborhoodWithTilePos(new Position(x,y)).
                                    addPark((ParkTile)tiles.get(x).get(y));
                            if(!addVoid){
                                --freeTiles;
                                ChangeTile(aux, 
                                tiles.get(x).get(y).makeCopy());
                                
                                this.parkTiles.add(aux);
                            }
                                
                        }
                    }
                    
                    if(tiles.get(x).get(y).isVoid() && 
                            this.getTile(aux).isPark()){
                        
                        this.getNeigborhoodWithTilePos(aux).
                                deletePark((ParkTile)this.getTile(aux));
                        ++freeTiles;
                        ChangeTile(aux, new VoidTile(this.getTile(aux).getValue(TileType.PARK)));
                        
                        this.parkTiles.remove(aux);
                    }   
                }                
            }
        }
    
    }
    
    private Neighborhood getNeigborhoodWithTilePos(Position pos){
        return getNeigborhood(new Position(pos).div(CityParameters.NEIGHBORHOODSIZE));
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
    
    public int getNNeighborhood(){
        return neighborhoods.size();
    }
    
        
    private void setTile(Position pos, Tile t){        
        tileset.get(pos.getX()).set(pos.getY(), t);
    }
    
    public int getNeighborhoodNParks(Position pos){
        return this.getNeigborhood(pos).getTotalValue();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /*Processing of parkTiles*/
    ////////////////////////////////////////////////////////////////////////////
    
    //Create a new ParkTile. Things to take in consideration:
    //  -The number of parks in the Neighborhood of the park updates
    //  -The citizens inside the Parks Area are counted
    public boolean NewParkTile(Position pos){
        boolean canChange = false;
        if(getTile(pos).isVoid()){
            ParkTile aux = new ParkTile(this.getTile(pos).getValue(TileType.VOID));
            if(getNeigborhoodWithTilePos(pos).addPark(aux)){
                --freeTiles;
                ChangeTile(pos, aux);
                parkTiles.add(pos);
                canChange = true;
            }
        }
        
        return canChange;
    }
    
    
    /*    public boolean NewParkTile(Position pos, Position neighbour){
    
    boolean canChange = false;
    
    if(getTile(pos).isVoid()){
    
    if(getNeigborhoodWithTilePos(pos).canAddPark()){
    int v  = getValueOfPark(pos, neighbour);
    
    if(v < 0){
    v = getValueOfPark(pos);
    }
    
    ParkTile aux = new ParkTile(v);
    
    --freeTiles;
    ChangeTile(pos, aux);
    getNeigborhoodWithTilePos(pos).addPark(aux);
    parkTiles.add(pos);
    canChange = true;
    }
    
    }
    
    return canChange;
    }*/
    
    //Get the value of a new park, but generated with another park next to it.
    //This is done to increase effciency by decreasing the number of tiles
    //to count. 
    //This method can be generalized so the "neighbour" tile doesn have to be
    //next to de original.
    int getValueOfPark(Position pos, Position neighbour){
        
        int v = getTile(neighbour).getValue(TileType.PARK);
        int offset = ParkTile.getAreaOfEffect();

        Position rePos = Position.subtract(pos, neighbour);
        
        switch (Math.abs(rePos.getX())+ Math.abs(rePos.getY())) {
            case 2:
                
                Position corner = Position.subtract
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
        
        //This is done because the min value of park is 1
        if(v == 0)
            v = 1;
        
        return v;
        
    }
    
    //It counts the number of citizens in the ParkTile area
    public int getValueOfPark(Position pos){
        int v = 1;
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
        
        return v;
    }
    
    public void removeParkTile(Position pos){
        if(inRange(pos)){
            Tile aux = tileset.get(pos.getX()).get(pos.getY());
            if(aux.isPark()){
                ++freeTiles;
                parkTiles.remove(pos);
                getNeigborhoodWithTilePos(pos).deletePark((ParkTile)aux);
                ChangeTile(pos,new VoidTile(aux.getValue(TileType.PARK)));
            }
        }
    }
    
    public boolean extendPark(Position pos){
        
        if(this.getTile(pos).isPark()){
            Position aux = Position.subtract(pos, new Position(1,1));
        
            for(int i = 0; i < 3; ++i){
                for(int j = 0; j < 3; ++j){

                    Position auxPos = Position.sum(aux, new Position(i,j));
                    if(this.inRange(auxPos)){
                        if(getTile(auxPos).isVoid()){
                            NewParkTile(auxPos);
    //SI OCURRE ALGÚN FALLO RARO POSIBLEMENTE TIRE POR AQUÍ                        
                            return true;
                        }
                    }

                }
            }
        }
        
        return false;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /*Rest of methods*/
    ////////////////////////////////////////////////////////////////////////////
    
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
