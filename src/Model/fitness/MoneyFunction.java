/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.fitness;

import Model.Individuals.CityTileset;
import Model.CityParameters;

/**
 *
 * @author gabriel
 */
public class MoneyFunction {
    public static double Evaluate(CityTileset city, CityParameters ctp){
        
        Double recomendedParks = (double)(ctp.getParksPercentage()*city.getAvailableTiles())/1000;
        
        return 1 - ((double)Math.abs(recomendedParks-city.getNparkTiles())/
                recomendedParks);
    }
}
