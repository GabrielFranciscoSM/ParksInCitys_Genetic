/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals;

import Model.Individuals.Tiles.ParkTile;
import Model.Individuals.Tiles.TileType;

/**
 * Class that represents a portion of a cityTileset.
 * @author gabriel
 */
public class Neighborhood {
    
    final static public int DEFAULTMAXPARKS = Integer.MAX_VALUE;
    ///Maximum number of parks allowed

    private int nparks;
    ///Number of parks inside de area of the Neighborhood
    
    private int totalValue;
    ///Total value of parks

    final private int MAXPARKS;
    ///Maximun cuantity of parks. Infinite at default.
    
    private int size;
    ///Size of the neighborhood
    
    /**
     * Default Constructor
     */
    protected Neighborhood(){
        nparks = 0;
        totalValue = 0;
        MAXPARKS = DEFAULTMAXPARKS;
        size = 0;
    }
    
    /**
     * Parameter constructor
     * @param _maxParks Max parks allowed.
     * @param _size Size of the neighborhood.
     */
    protected Neighborhood(int _maxParks, int _size){
        nparks = 0;
        totalValue = 0;
        MAXPARKS = _maxParks;
        size = _size;
    }
    
    /**
     * Copy constructor.
     * @param cp Neighbor to be copied.
     */
    protected Neighborhood(Neighborhood cp){
        nparks = cp.getNParks();
        totalValue = cp.getTotalValue();
        MAXPARKS = cp.MAXPARKS;
        size = cp.getSize();
    }
    
    /**
     * Getter of total value.
     * @return Total value.
     */
    protected int getTotalValue(){
        return totalValue;
    }
    
    /**
     * Setter of total value.
     * @param tv Total value.
     */
    protected void setTotalValue(int tv){
        totalValue = tv;
    }
    
    /**
     * Setter of number of parks.
     * @param nparks Number of parks.
     */
    protected void setNParks(int nparks){
        this.nparks = nparks;
    }
    
    /**
     * Getter of number of parks.
     * @return Number of parks.
     */
    protected int getNParks(){
        return nparks;
    }
    
    /**
     * Getter of size.
     * @return Size of neighborhood.
     */
    protected int getSize(){
        return size;
    }
    
    /**
     * Add a park in the neighborhood if able to.
     * @param p Park tile.
     * @return True if able to add parktile.
     */
    protected boolean addPark(ParkTile p){
        if(this.canAddPark()){
            ++nparks;
            this.totalValue += p.getValue(TileType.PARK);
            return true;
        }
        else
            return false;
    }
    
    /**
     * Checks if a park can be added.
     * @return True if park can be added.
     */
    protected boolean canAddPark(){
        if(nparks < MAXPARKS){
            return true;
        }
        else
            return false;
    }
    
    /**
     * Delete a park in the neighborhood
     * @param p Park to be deleted.
     * @return True if can be deleted.
     */
    protected boolean deletePark(ParkTile p){
        if(nparks > 0){
            --nparks;
            this.totalValue -= p.getValue(TileType.PARK);
            return true;
        }
        else
            return false;
    }

	public double getParkValue() {
		return (double) totalValue / this.nparks;
	}
}
