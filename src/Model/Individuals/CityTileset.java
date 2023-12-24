/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals;

import Model.Individuals.Tiles.*;
import Basics.*;
import Model.CityParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    
    private int availableTiles;
    ///Number of park tiles and free tiles
    
    private static int maxValue;
    ///Saves the max value that a park can take. Used in fitness function.
    
    private static int minValue;
    ///Saves the min value that a park can take. Used in fitness function.
    
    private static double meanValue;
    ///Saves the mean value that a park can take. Used in fitness function.

    final private int id;
    ///id of a city.
    
    //Offsets to access the tiles neighboring a given tile
    final static public Position[] parkOffsets = 
    {new Position(-1, -1), new Position(0, -1), new Position(1, -1), 
     new Position(-1, 0),new Position(1, 0), new Position(-1, 1), 
     new Position(0, 1), new Position(1, 1)};
    
    ////////////////////////////////////////////////////////////////////////////
    /*CONSTRUCTORS*/
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Default constructor.
     */
    public CityTileset(){
        
        //Inicialize arrays
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        parkTiles = new ArrayList<>();
        
        id = ++nCities;
        freeTiles = CityParameters.DEFAULTSIZE*CityParameters.DEFAULTSIZE;
        availableTiles = freeTiles;
        
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
    
    /**
     * Size parameter constructor.
     * @param size Size of the city. The total size will be size x size.
     */
    public CityTileset(int size){
        
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        parkTiles = new ArrayList<>();
        
        id = ++nCities;
        freeTiles = size*size;
        availableTiles = freeTiles;
        
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
    
    /**
     * Copy constructor.
     * @param cp City to copy.
     */ 
    public CityTileset(CityTileset cp){
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        parkTiles = new ArrayList<>();
        
        id = ++nCities;
        freeTiles = cp.getFreeTiles();
        availableTiles = freeTiles;
        
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
    
    /**
     * Constructor with a given TilseSet
     * @param tiles Tiles to create a new city from.
     */
    public CityTileset(ArrayList<ArrayList<Tile>> tiles){
        tileset = new ArrayList<>();
        neighborhoods = new ArrayList<>();
        parkTiles = new ArrayList<>();

        ++nCities;
        id = nCities;
        freeTiles = 0;
        availableTiles = 0;
        
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
                    ++availableTiles;
                }
                if(tiles.get(i).get(j).isPark()){
                    ++availableTiles;
                    neighborhoods.get(i/(CityParameters.NEIGHBORHOODSIZE)).
                                  get(j/(CityParameters.NEIGHBORHOODSIZE)).
                                  addPark((ParkTile)tiles.get(i).get(j));
                }
               
            }
            
            for(int j = tiles.get(0).size(); j < tiles.size(); ++j){
                aux.add(new VoidTile());
                ++freeTiles;
                ++availableTiles;
                
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
                ++availableTiles;
                
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
    
    /**
     * Getter of max value. 
     * 
     * @return Max tile value.
     */
    public static int getMaxValue(){
        return maxValue;
    }
    
    /**
     * Setter for max value.
     * @param m New max value.
     */
    public static void setMaxValue(int m){
        maxValue = m;
    }
    
    /**
     * Getter of min value. 
     * 
     * @return Min tile value.
     */
    public static int getMinValue(){
        return minValue;
    }
    
    /**
     * Setter for min value.
     * @param m New min value.
     */
    public static void setMinValue(int m){
        minValue = m;
    }
    
    /**
     * Getter of mean value. 
     * 
     * @return Mean tile value.
     */
    public static double getMeanValue(){
        return meanValue;
    }
    
    /**
     * Setter for mean value.
     * @param m Mean value.
     */
    public static void setMeanValue(double m){
        meanValue = m;
    }
    
    /**
     * Getter for available tiles.
     * @return Sum of park and void tiles.
     */
    public int getAvailableTiles(){
        return availableTiles;
    }
    
    /**
     * Setter of available tiles.
     * @param i New available tiles value
     */
    public void setAvailableTiles(int i){
        availableTiles = i;
    }
    
    /**
     * Getter for percentage of parks.
     * @return The percentage of parks in available tiles
     */
    public double getPercentageOfParks(){
        return ((double)parkTiles.size()/(double)getAvailableTiles())*100;
    }
    
    /**
     * Getter of free tiles.
     * @return Number of void tiles.
     */
    public int getFreeTiles(){
        return freeTiles;
    }
    
    /**
     * Setter for freeTiles.
     * @param ft New free tiles value.
     */
    public void setFreeTiles(int ft){
        freeTiles = ft;
    }
    
    /**
     * Getter for id
     * @return The id of the city.
     */
    public int getId(){
        return id;
    }
     
    /**
     * Getter of NparkTiles
     * @return Number of parks tiles
     */
     public int getNparkTiles(){
         return parkTiles.size();
     }
     
     /**
      * Getter of a park tile.
      * @param i Position of the park tile in the array parkTiles.
      * @return The park tile in the position i.
      */
     public Position getParkTile(int i){
        return parkTiles.get(i);
     }
     
     /**
      * Getter for park tiles.
      * @return The list of park tiles positions of the city
      */
     public List<Position> getArrayOfParkPositions(){
    	 return this.parkTiles;
     }
     
     /**
      * Getter of a tile.
      * @param pos Position of the tile in the city.
      * @return Tile in the position. Null if position invalid.
      */
    public Tile getTile(Position pos){
        if(inRange(pos)){
            return tileset.get(pos.getX()).get(pos.getY());
        }
        else return new NullTile();
    }
    
    /**
     * Getter of a tile.
     * @param x coordinate of the tile.
     * @param y coordinate of the tile.
     * @return The tile in the position.
     */
    public Tile getTile(int x, int y){
        return getTile(new Position(x,y));
    }
    
    /**
     * Getter of an array of tiles
     * @param topLeft Corner of the tiles box.
     * @param botRight Corner of the tiles box.
     * @return An arrayList of arrayLists of tiles
     */
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
    
    /**
     * Getter of the tiles of a neighborhood.
     * @param pos Position of the neighborhood.
     * @return An arrayList of arrayLists of tiles 
     */
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
    
    /**
     * Iterate all the positions in a neighborhood in search of those that are parks.
     * @param pos - position of the neighborhood in relation to the rest of the neighborhoods
     * @return list of positions of the park tiles that belong to the neighborhood
     */
    public List<Position> getNeighborhoodParks(Position pos) {
        // Scale position to actual map scale
        Position realPos = Position.mul(pos, CityParameters.NEIGHBORHOODSIZE);
        
        // Verify if the scaled position is within the map range
        if (!inRange(realPos)) {
            return Collections.emptyList(); // Return an empty list if out of range
        }

        // Calculate the position of the lower right end of the neighborhood
        int maxX = Math.min(realPos.getX() + CityParameters.NEIGHBORHOODSIZE, getSize() - 1);
        int maxY = Math.min(realPos.getY() + CityParameters.NEIGHBORHOODSIZE, getSize() - 1);

        // Ready to save the neighborhood park positions
        List<Position> parks = new ArrayList<>();

        // Iterate neighborhood positions
        for (int x = realPos.getX(); x <= maxX; x++) {
            for (int y = realPos.getY(); y <= maxY; y++) {
                // Check if the tile is a park
                if (this.getTile(new Position(x, y)).isPark()) {
                    parks.add(new Position(x, y));
                }
            }
        }
        
        return parks;
    }
    
    public void setTiles(Position topLeft, ArrayList<ArrayList<Tile>> tiles){  
        
        //Ensures the box fits in the city
        int hLength = Math.min(tiles.size(), this.getSize() - topLeft.getX());
        int yLength = Math.min(tiles.get(0).size(), this.getSize() - topLeft.getY());

        //If the box fits
        if(inRange(Position.sum(topLeft, new Position(tiles.size(),0))) &&
           inRange(Position.sum(topLeft, new Position(0,tiles.get(0).size())))){
            
            for(int x = 0; x < hLength; ++x){
                for(int y = 0; y < yLength; ++y){
                    
                    boolean addVoid = false;
                    Position aux = new Position(topLeft.getX()+x,topLeft.getY()+y);
                    
                    //Handles if a park is added
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
                    
                    //Handles if a park is removed
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
    
    /**
     * Getter of a neighborhood given a tile inside it.
     * @param pos Position if tile
     * @return The neighborhood that contains the tile. If position is incorrect, null.
     */
    private Neighborhood getNeigborhoodWithTilePos(Position pos){
        return getNeigborhood(new Position(pos).div(CityParameters.NEIGHBORHOODSIZE));
    }
    
    /**
     * Getter of neighborhood.
     * @param pos Position of the neighborhood.
     * @return The neighborhood.
     */
    private Neighborhood getNeigborhood(Position pos){
        if(pos.inRange(Position.ZERO,  new Position(neighborhoods.size()-1)))
            return neighborhoods.get(pos.getX()).get(pos.getY());
        else
            return null;
    }
    
    /**
     * Getter for the size of the city.
     * @return 
     */
    public int getSize(){
        return tileset.size();
    }
    
    /**
     * Getter for number of neighborhoods.
     * @return Number of neighborhoods.
     */
    public int getNNeighborhood(){
        return neighborhoods.size();
    }
    
    public int getTotalNeighborhoods(){
        return neighborhoods.size() * neighborhoods.get(0).size();
    }
    
        
    private void setTile(Position pos, Tile t){        
        tileset.get(pos.getX()).set(pos.getY(), t);
    }
    
    /**
     * Getter for number of parks in a neighborhood.
     * @param pos Position of the neighborhood.
     * @return Number of parks in the neighborhood.
     */
    public int getNeighborhoodNParks(Position pos){
        return this.getNeigborhood(pos).getTotalValue();
    }
    
    public double getNeighborhoodParkValue(Position pos) {
    	return this.getNeigborhood(pos).getParkValue();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /*Processing of parkTiles*/
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Creates a new ParkTile. 
     * @param pos Position of the new parkTile
     * @return True if can create a park tile, false if not.
     */
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
    
    
    /*    
    public boolean NewParkTile(Position pos, Position neighbour){
    
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
    
    /*
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
    */
    
    //
    /**
     * Counts the number of citizens in the ParkTile area.
     * @param pos Position of the park tile.
     * @return Sum of citizens in range of the tile.
     */
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
    
    /**
     * Remove a park tile from the city.
     * @param pos The position of the tile.
     */
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
    
    /**
     * Try to create a new park next to the given position.
     * @param pos Position of the park tile to expand.
     * @return true if can expand, false if not.
     */
    public boolean extendPark(Position pos){
        
        if(this.getTile(pos).isPark()){
            Position aux = Position.subtract(pos, new Position(1,1));
        
            for(int i = 0; i < 3; ++i){
                for(int j = 0; j < 3; ++j){

                    Position auxPos = Position.sum(aux, new Position(i,j));
                    if(this.inRange(auxPos)){
                        if(getTile(auxPos).isVoid()){
                            NewParkTile(auxPos);
                            return true;
                        }
                    }

                }
            }
        }
        
        return false;
    }
    
    public boolean extendPark(Position pos, int tile){
    	if (this.getTile(pos).isPark()) {
            int parkOffsetsLength = CityTileset.parkOffsets.length;

            for (int i = 0; i < parkOffsetsLength; i++) {
                int index = (tile + i) % parkOffsetsLength;
                Position auxPos = Position.sum(pos, CityTileset.parkOffsets[index]);

                if (this.inRange(auxPos) && getTile(auxPos).isVoid()) {
                    NewParkTile(auxPos);
                    return true;
                }
            }
        }
        return false;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /*Rest of methods*/
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Checks if a position is inside the city.
     * @param pos Position to check.
     * @return True if pos in range.
     */
    private boolean inRange(Position pos){
        return pos.inRange(Position.ZERO, new Position(getSize()-1));
    }
    
    /**
     * Change a Tile to another.
     * @param pos POsition of the tiles.
     * @param tile New tile.
     * @return True if position is in range.
     */
    private boolean ChangeTile(Position pos, Tile tile){
        if(inRange(pos)){
            setTile(pos,tile);
            return true;
        }
        else
            return false;
    }
    
    /**
     * Create a building Tile.
     * @param pos Position of building tile.
     */
    public void NewBuildingTile(Position pos){
        NewBuildingTile(pos, new BuildingTile());
    }
    
    /**
     * Copy a building Tile.
     * @param pos Position of the tile.
     * @param bt Building tile.
     */
    public void NewBuildingTile(Position pos, Tile bt){
        if(getTile(pos).canBuild())
            ChangeTile(pos, bt);
    }
    
    /**
     * Checks if a Building can be placed in given position.
     * @param pos Position
     * @return True if can build, false if not.
     */
    public boolean canBuild(Position pos){
        if(inRange(pos)){
            if(getTile(pos).canBuild()){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Create a new road Tile.
     * @param pos Position of the new road tile.
     */
    public void NewRoadTile(Position pos){
        if(getTile(pos).isVoid())
            ChangeTile(pos, new RoadTile());
    }
    
    /**
     * Override of toString. 
     * Outputs the city in text.
     * @return 
     */
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
    
    /**
     * Checks if there is available tiles to create parks.
     * @return true if there are available parks.
     */
    public boolean hasAvailableTiles() {
    	return freeTiles != 0;
    }
}
