/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals;

/**
 *
 * @author gabriel
 */
public class Neighborhood {
    
    final static public int DEFAULTMAXPARKS = Integer.MAX_VALUE;
    
    //Number of parks inside de area of the Neighborhood
    private int nparks;
    
    //Maximun cuantity of parks. Infinite at default.
    //Used so the parkTiles doesnt clump
    final private int MAXPARKS;
    
    private int size;
    
    //Default Constructor
    protected Neighborhood(){
        nparks = 0;
        MAXPARKS = DEFAULTMAXPARKS;
        size = 0;
    }
    
    //Parametter constructor
    protected Neighborhood(int _maxParks, int _size){
        nparks = 0;
        MAXPARKS = _maxParks;
        size = _size;
    }
    
    protected Neighborhood(Neighborhood cp){
        nparks = cp.getNParks();
        MAXPARKS = cp.MAXPARKS;
        size = cp.getSize();
    }
    
    protected int getNParks(){
        return nparks;
    }
    
    protected int getSize(){
        return size;
    }
    
    //Add a park in the neighborhood
    protected boolean addPark(){
        if(nparks < MAXPARKS){
            ++nparks;
            return true;
        }
        else
            return false;
    }
}
