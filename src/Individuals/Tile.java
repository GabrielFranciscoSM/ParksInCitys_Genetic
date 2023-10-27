/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals;

/**
 *
 * @author gabriel
 */
public abstract class Tile {
    boolean isVoid;
    
    boolean isVoid(){
        return isVoid;
    }
    
    @Override
    public abstract String toString();
}
