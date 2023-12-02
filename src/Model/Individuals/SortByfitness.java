/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Individuals;

import Basics.*;
import java.util.Comparator;

/**
 *
 * @author gabriel
 */
public class SortByfitness implements Comparator<Individual>{
    
    public int compare(Individual a, Individual b)
    {
        return (int)(a.getFitness() - b.getFitness());
    }
}
