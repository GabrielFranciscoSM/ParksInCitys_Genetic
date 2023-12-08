/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals;

import Model.Individuals.Tiles.ParkTile;
import Model.Individuals.Tiles.TileType;

/**
 *
 * @author gabriel
 */
public class Neighborhood {
    
    final static public int DEFAULTMAXPARKS = Integer.MAX_VALUE;
    
    //Number of parks inside de area of the Neighborhood
    private int nparks;
    //Total value of parks
    private int totalValue;
    
    //Maximun cuantity of parks. Infinite at default.
    //Used so the parkTiles doesnt clump
    final private int MAXPARKS;
    
    private int size;
    
    //Default Constructor
    protected Neighborhood(){
        nparks = 0;
        totalValue = 0;
        MAXPARKS = DEFAULTMAXPARKS;
        size = 0;
    }
    
    //Parametter constructor
    protected Neighborhood(int _maxParks, int _size){
        nparks = 0;
        totalValue = 0;
        MAXPARKS = _maxParks;
        size = _size;
    }
    
    protected Neighborhood(Neighborhood cp){
        nparks = cp.getNParks();
        totalValue = cp.getTotalValue();
        MAXPARKS = cp.MAXPARKS;
        size = cp.getSize();
    }
    
    protected int getTotalValue(){
        return totalValue;
    }
    
    protected void setTotalValue(int tv){
        totalValue = tv;
    }
    
    protected void setNParks(int nparks){
        this.nparks = nparks;
    }
    
    protected int getNParks(){
        return nparks;
    }
    
    protected int getSize(){
        return size;
    }
    
    //Add a park in the neighborhood
    protected boolean addPark(ParkTile p){
        if(this.canAddPark()){
            ++nparks;
            this.totalValue += p.getValue(TileType.PARK);
            return true;
        }
        else
            return false;
    }
    
    protected boolean canAddPark(){
        if(nparks < MAXPARKS){
            return true;
        }
        else
            return false;
    }
    
    //Delete a park in the neighborhood
    protected boolean deletePark(ParkTile p){
        if(nparks > 0){
            --nparks;
            this.totalValue -= p.getValue(TileType.PARK);
            return true;
        }
        else
            return false;
    }
}
