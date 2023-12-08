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
    Random generator;
    
    RankSelection(){
        generator = new Random(System.currentTimeMillis());
    }
    
    public Population<CityTileset> apply(CityTilesetPopulation pop, boolean useElitism, boolean truncate){
        List<CityTileset> sortedPop = pop.stream()
        .sorted(Comparator.comparingDouble(CityTileset::getFitness))
        .collect(Collectors.toList());
        
        Population<CityTileset> aux = pop.clone();
        aux.clear();
        
        if(useElitism){
            for(int i = 0; i < pop.size()/10; ++i){
                aux.add(sortedPop.get(generator.nextInt(pop.size()/100)));
            }
        }
        
        if(truncate){
            sortedPop.subList(0, 2*sortedPop.size()/3);
        }
        
        while(aux.add(sortedPop.get(generator.nextInt(pop.size())))){}
            
        return aux;
    }
}
