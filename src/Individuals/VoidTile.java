/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals;

/**
 *
 * @author gabriel
 */
public class VoidTile extends Tile{
    public VoidTile(){
        isVoid = true;
    }
    
    @Override
    public String toString(){
        return " ";
    }
}
