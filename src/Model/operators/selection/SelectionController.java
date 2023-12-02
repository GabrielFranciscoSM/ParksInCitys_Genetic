/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.selection;

import Model.Individuals.CityTileset;
import Model.Individuals.Population;

/**
 *
 * @author gabriel
 */
public class SelectionController{
    
    RankSelection rs;
    
    SelectionController(){
        rs = new RankSelection();
    }
    
    //include elitism and truncation.
    /*public Population<CityTileset> apply(Population<CityTileset> pop,boolean useElitism, boolean truncate){
    
    }*/    
    public Population<CityTileset> truncate(Population<CityTileset> pop, double minFitness){
        Population<CityTileset> offsprings = pop.clone();
        
        offsprings.setId(pop.getId() + 1);
        offsprings.clear();
        
        for(CityTileset ct: pop){
            if(ct.getFitness() >= minFitness){
                pop.add(ct);
            }
        }
        
        return pop;
    }
}
