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
    private int nparks;
    final private int maxParks;
    
    Neighborhood(){
        nparks = 0;
        maxParks = 0;
    }
    
    Neighborhood(int _maxParks){
        nparks = 0;
        maxParks = _maxParks;
    }
    
    protected void addPark(){
        if(nparks < maxParks){
            ++nparks;
        }
    }
}
