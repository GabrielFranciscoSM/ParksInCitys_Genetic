/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals;

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
    final private int maxParks;
    
    int size;
    
    //Default Constructor
    Neighborhood(){
        nparks = 0;
        maxParks = DEFAULTMAXPARKS;
        size = 0;
    }
    
    //Parametter constructor
    Neighborhood(int _maxParks, int _size){
        nparks = 0;
        maxParks = _maxParks;
        size = _size;
    }
    
    //Add a park in the neighborhood
    protected boolean addPark(){
        if(nparks < maxParks){
            ++nparks;
            return true;
        }
        else
            return false;
    }
}
