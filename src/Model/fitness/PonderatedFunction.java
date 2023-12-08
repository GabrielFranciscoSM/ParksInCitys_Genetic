/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.fitness;

import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;

/**
 *
 * @author gabriel
 */
public class PonderatedFunction {
    private double moneyPonderation = 1;
    private double valuePonderation = 1;
    
    public void evaluate(CityTilesetPopulation pop){
        
        for (CityTileset city : pop) {	// Get each city from the population
            // Fitness is the result of the multiplication of both percentages
            city.setFitness(((ValueFunction.Evaluate(city))*moneyPonderation + 
                    MoneyFunction.Evaluate(city)*valuePonderation)/2);
        }
    }
}
