/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.selection;

import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;
import Model.Individuals.Population;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author gabriel
 */
public class RankSelection {
    
    public Population<CityTileset> apply(
            CityTilesetPopulation pop, 
            boolean useElitism, 
            boolean truncate,
            Random generator,
            double truncateSize){
        
        //Get the population sorted by fitness
        List<CityTileset> sortedPop = pop.sortPopulationByFitness();
                
        Population<CityTileset> aux = pop.clone();
        aux.clear();
        
        if(useElitism){
            for(int i = 0; i < pop.size()/10; ++i){
                System.out.print(pop.size()/100);
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
