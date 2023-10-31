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
    
    //Number of parks inside de area of the Neighborhood
    private int nparks;
    
    //Maximun cuantity of parks. Infinite at default.
    //Used so the parkTiles doesnt clump
    final private int maxParks;
    
    Neighborhood(){
        nparks = 0;
        maxParks = Integer.MAX_VALUE;
    }
    
    Neighborhood(int _maxParks){
        nparks = 0;
        maxParks = _maxParks;
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
