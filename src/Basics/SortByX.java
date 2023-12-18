/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Basics;

import java.util.Comparator;

/**
 *
 * @author gabriel
 */

///Sort algorithm for position by the x coordinate
public class SortByX implements Comparator<Position>{
    
    /**
     * Compare two positions.
     * @param a On position.
     * @param b The other position.
     * @return The difference between positions.
     */
    public int compare(Position a, Position b)
    {
        return a.getX() - b.getX();
    }
}
