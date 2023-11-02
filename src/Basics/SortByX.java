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
public class SortByX implements Comparator<Position>{
    
    public int compare(Position a, Position b)
    {
        return a.getX() - b.getX();
    }
}
