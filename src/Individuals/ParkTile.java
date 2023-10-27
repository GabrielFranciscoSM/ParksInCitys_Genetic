/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals;

/**
 *
 * @author gabriel
 */
public class ParkTile extends Tile{
    int value;
    
    ParkTile(){
        isVoid = false;
    }
    
    void setValue(int v){
        value = v;
    }
    
    int getValue(){
        return value;
    }
}
