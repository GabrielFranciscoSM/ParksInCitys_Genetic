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
        
        return (double)(city.getFreeTiles()+ (double)(ctp.getParksPercentage()- CityParameters.PERCENTAGERANGE*2)/1000)/city.getDisponibleTiles();
    }
}
