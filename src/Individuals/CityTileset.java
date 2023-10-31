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

//Individuo
public class CityTileset {
    static final int SETSIZE = 100;
    static final int NEIGHBORHOODSIZE = 10;
    
    
    ArrayList<ArrayList<Tile>> tileset;
    ArrayList<ArrayList<Neighborhood>> neighborhoods;
    
    //Constructor por defecto
    public CityTileset(){
        tileset = new ArrayList<ArrayList<Tile>>();
        for(int i = 0; i < SETSIZE; ++i){
            ArrayList<Tile> aux = new ArrayList<Tile>(); 
            ArrayList<Neighborhood> aux2 = new ArrayList<Neighborhood>(); 
            
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
    
    //Getters y setters
    Tile getTile(Position pos){
        if(pos.inRange(Position.ZERO, new Position(SETSIZE-1))){
            return tileset.get(pos.getX()).get(pos.getY());
        }
        else return null;
        
    }
    
    Tile getTile(int x, int y){
        if(new Position(x,y).inRange(Position.ZERO, new Position(SETSIZE-1))){
            return tileset.get(x).get(y);
        }
        else return null;
    }
    
    Neighborhood getNeigborhood(Position pos){
        if(pos.inRange(Position.ZERO, new Position(neighborhoods.size()-1)))
            return neighborhoods.get(pos.getX()/NEIGHBORHOODSIZE ).get(pos.getY()/NEIGHBORHOODSIZE);
        else
            return null;
    }
    
    
    //CAmbiar una Tile por otra
    private boolean ChangeTile(Position pos, Tile tile){
        if(pos.inRange(Position.ZERO, new Position(SETSIZE-1))){
            tileset.get(pos.getX()).set(pos.getY(), tile);
            return true;
        }
        else
            return false;
    }
    
    
    //Introducir una ParkTile, haciendo los cambios pertinentes
    //Se actualiza el numero de parques de Neighborhoods
    //Se recuentan los ciudadanos en el area de la nueva Tile
    boolean NewParkTile(Position pos){
        
        boolean canChange = false;
        
        if(getTile(pos).isVoid()){
            getNeigborhood(pos).addPark();
            ChangeTile(pos, new ParkTile(getValueOfPark(pos)));
            
            canChange = true;
        }
        
        return canChange;
    }
    
    boolean NewParkTile(Position pos, Position neighbour){
        
        boolean canChange = false;
        
        if(getTile(pos).isVoid()){
            getNeigborhood(pos).addPark();
            int v  = getValueOfPark(pos, neighbour);
            if(v < 0){
                v = getValueOfPark(pos);
            }
            ChangeTile(pos, new ParkTile(v));
            
            canChange = true;
        }
        
        return canChange;
    }
    
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
    
    //Se cuantan los ciudadanos en el area de la ParkTile en la posición pos
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
