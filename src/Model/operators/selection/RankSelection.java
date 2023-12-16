/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.selection;

import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;
import Model.Individuals.Population;
import java.util.List;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class RankSelection {
    
    public CityTilesetPopulation apply(
            CityTilesetPopulation pop, 
            boolean useElitism, 
            boolean truncate,
            Random generator,
            double truncateSize){
        
        //Get the population sorted by fitness
        List<CityTileset> sortedPop = pop.sortPopulationByFitness();
                
        CityTilesetPopulation aux = new CityTilesetPopulation(pop.getId()+1,pop.size());
        
        if(useElitism){
            for(int i = 0; i < pop.size()/10; ++i){
                aux.add(sortedPop.get(generator.nextInt(pop.size()/100 + 1)));
            }
        }
        
        if(truncate){
            //Esto funciona bien o del reves?
            sortedPop.subList(0, (int)(sortedPop.size()*truncateSize));
        }
        
        while(aux.add(sortedPop.get(generator.nextInt(pop.size())))){}
            
        return aux;
    }
}
