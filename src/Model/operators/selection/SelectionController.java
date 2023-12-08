/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.selection;

import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;
import Model.Individuals.Population;

/**
 *
 * @author gabriel
 */
public class SelectionController{
    
    RankSelection rs;
    
    public SelectionController(){
        rs = new RankSelection();
    }
    
    //include elitism and truncation.
    public Population<CityTileset> apply(CityTilesetPopulation pop,boolean useElitism, boolean truncate){
        return rs.apply(pop,useElitism,truncate);
    }
    
}
