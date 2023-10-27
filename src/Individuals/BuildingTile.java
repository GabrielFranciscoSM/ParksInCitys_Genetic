/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals;

/**
 *
 * @author gabriel
 */
public class BuildingTile extends Tile{
    
    static int MAXPARKSINRANGE = 10;
    
    int citycents;
    int parksInRange; // < MAXPARKSINRANGE
    
    BuildingTile(){
        citycents = 0;
        parksInRange = 0;
        isVoid = false;
    }
    
    void setCitycents(int c){
        citycents = c;
    }
    
    int getCitycents(){
        return citycents;
    }
    
    void setParksInRange(int p){
        parksInRange = p;
    }
    
    int getParksInRange(){
        return parksInRange;
    }
    
    
}
