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
 *
 * @author Gabriel Sanchez
 */

//Individual
public class CityTileset extends Individual{
        
    //Saves the numer of cities created. Used to asign id
    static private int nCities = 0;
    
    //Arrays of Tiles and Neighborhoods
    private ArrayList<ArrayList<Tile>> tileset;
    private ArrayList<ArrayList<Neighborhood>> neighborhoods;
    
    //Saves the positions of the parkTiles in th city
    private List<Position> parkTiles;
    //Numeber of disponible tiles (void tiles)
    private int freeTiles;
    //number of park tiles and free tiles
    private int disponibleTiles;
    
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
        
        for(int i = 0; i < tiles.size(); ++i){
            
            ArrayList<Tile> aux = new ArrayList<>(); 
            ArrayList<Neighborhood> aux2 = new ArrayList<>();
            
            tileset.add(aux);
            if(i % (CityParameters.NEIGHBORHOODSIZE) == 0){
                neighborhoods.add(aux2);
            }
            
            for(int j = 0; j < tiles.size(); ++j){
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
                    neighborhoods.get(i%(CityParameters.NEIGHBORHOODSIZE)).
                                  get(j%(CityParameters.NEIGHBORHOODSIZE)).
                                  addPark((ParkTile)tiles.get(i).get(j));
                }
               
            }
            
            
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /*Getters and setters*/
    ////////////////////////////////////////////////////////////////////////////
    
    public Position getMaxPark(){
        int max = 0;
        Position position = new Position();
        for(Position pos: parkTiles){
            if(getTile(pos).getValue(TileType.PARK)>max){
                max = getTile(pos).getValue();
                position = pos;
            }
                
        }
        
        return position;
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
         
        Position pos = parkTiles.get(i);
        
        return pos;
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
        if(inRange(Position.sum(topLeft, new Position(tiles.size(),0))) &&
           inRange(Position.sum(topLeft, new Position(0,tiles.get(0).size())))){
            
            for(int y = 0; y < tiles.size(); ++y){
                
                for(int x = 0; x < tiles.get(0).size(); ++x){
                    
                    boolean addVoid = false;
                    Position aux = new Position(topLeft.getX()+x,topLeft.getY()+y);
                    
                    if(tiles.get(y).get(x).isPark()){
                        if(this.getTile(aux).isVoid()){
                            addVoid = !this.getNeigborhoodWithTilePos(new Position(y,x)).
                                    addPark((ParkTile)tiles.get(y).get(x));
                            if(!addVoid)
                                --freeTiles;
                        }
                        else if(this.getTile(aux).isPark()){
                            this.getNeigborhoodWithTilePos(new Position(y,x)).
                                    deletePark((ParkTile)tiles.get(y).get(x));
                            this.getNeigborhoodWithTilePos(new Position(y,x)).
                                    addPark((ParkTile)tiles.get(y).get(x));
                        }
                    }
                    
                    if(tiles.get(y).get(x).isVoid() && 
                            this.getTile(aux).isPark()){
                        
                        this.getNeigborhoodWithTilePos(new Position(y,x)).
                                deletePark((ParkTile)tiles.get(y).get(x));
                        ++freeTiles;
                    }
                    
                    if(!addVoid){
                        ChangeTile(new Position(topLeft.getX()+x,topLeft.getY()+y), 
                        tiles.get(y).get(x).makeCopy());
                    }
                    else
                        ChangeTile(new Position(topLeft.getX()+x,topLeft.getY()+y), 
                            new VoidTile());
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
            ParkTile aux = new ParkTile(getValueOfPark(pos));
            if(getNeigborhoodWithTilePos(pos).addPark(aux)){
                --freeTiles;
                ChangeTile(pos, aux);
                parkTiles.add(pos);
                canChange = true;
            }
        }
        
        return canChange;
    }
    
    //
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
                ChangeTile(pos,new VoidTile());
            }
        }
    }
    
    public boolean extendPark(Position pos){
        
        if(this.getTile(pos).isPark()){
            Position aux = Position.substract(pos, new Position(1,1));
        
            for(int i = 0; i < 3; ++i){
                for(int j = 0; j < 3; ++j){

                    Position auxPos = Position.sum(aux, new Position(i,j));
                    if(this.inRange(auxPos)){
                        if(getTile(auxPos).isVoid()){
                            NewParkTile(auxPos,pos);
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

    /*   public void getRandomNeighborhood(int neighborhoodSelector, int operation, int tileSelector) {
    // Get the dimensions of the neighborhoods
    int lengthRow = this.neighborhoods.size();
    int lengthColumn = this.neighborhoods.get(0).size();
    
    neighborhoodSelector %= (lengthRow * lengthColumn);	 // Ensure neighborhoodSelector is within bounds
    
    // Calculate the row and column within the neighborhoods
    int row = neighborhoodSelector / lengthRow;
    int column = neighborhoodSelector % lengthColumn;
    
    
    // Consider what operation to do in the neighborhood
    if (operation == 0) {	// Priority for add operation
    operation = handleAddOperation(row, column);
    } else {				// Priority for delete operation
    operation = handleDeleteOperation(row, column);
    }
    
    // Set the area size for the tileset (within bounds)
    int area = CityParameters.NEIGHBORHOODSIZE * CityParameters.NEIGHBORHOODSIZE;
    tileSelector %= area;
    
    // Calculate the indices for the tileset
    int i = tileSelector / this.tileset.size();
    int j = tileSelector % this.tileset.get(0).size();
    
    // Performs the operation on a neighborhood tile according to the specified operation
    if (operation == 0) {
    handleAddPark(i, j, area);
    } else {
    handleDeletePark(i, j);
    }
    }
    
    // Handle operation selection to mutate the neighborhood (priority to add a park tile)
    private int handleAddOperation(int row, int column) {
    if (!this.neighborhoods.get(row).get(column).addPark()) {
    this.neighborhoods.get(row).get(column).deletePark();
    return 1;
    }
    return 0;
    }
    
    // Handle operation selection to mutate the neighborhood (priority to delete a park tile)
    private int handleDeleteOperation(int row, int column) {
    if (!this.neighborhoods.get(row).get(column).deletePark()) {
    this.neighborhoods.get(row).get(column).addPark();
    return 0;
    }
    return 1;
    }
    
    // Handle adding a park from the neighborhood
    private void handleAddPark(int i, int j, int area) {
    int counter = 0;
    do {
    while (!this.tileset.get(i).get(j).isPark()) {
    j++;
    i = (i + (j % CityParameters.NEIGHBORHOODSIZE)) % CityParameters.NEIGHBORHOODSIZE;
    j %= CityParameters.NEIGHBORHOODSIZE;
    counter++;
    }
    } while (!this.extendPark(new Position(i, j)) || counter < area);
    }
    
    // Handle deleting a park from the neighborhood
    private void handleDeletePark(int i, int j) {
    while (!this.tileset.get(i).get(j).isPark()) {
    j++;
    i = (i + (j % CityParameters.NEIGHBORHOODSIZE)) % CityParameters.NEIGHBORHOODSIZE;
    j %= CityParameters.NEIGHBORHOODSIZE;
    }
    this.removeParkTile(new Position(i, j));
    }*/
}
