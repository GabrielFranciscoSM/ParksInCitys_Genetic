/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.fitness;

import Model.CityParameters;
import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;

/**
 *
 * @author gabriel
 */
public class PonderatedFunction {
    private double moneyPonderation = 1;
    private double valuePonderation = 1;
    
    public PonderatedFunction(int moneyPoneration){
        moneyPonderation = (double)moneyPoneration/100;
        valuePonderation = 1 - moneyPonderation;
    }
    
    public void evaluate(CityTilesetPopulation pop, CityParameters ctp){
        
        Double maxFitness = 0d;
        
        for (CityTileset city : pop) {	// Get each city from the population
            // Fitness is the result of the multiplication of both percentages
            
            Double fitnessValue = ((ValueFunction.Evaluate(city)*moneyPonderation + 
                    MoneyFunction.Evaluate(city, ctp)*valuePonderation)/2);
            
            if(fitnessValue < 0){
                System.out.print("money: " + MoneyFunction.Evaluate(city, ctp)*valuePonderation + "\n");
                System.out.print("value: " + ValueFunction.Evaluate(city)*moneyPonderation + "\n");
            }
            
            if(fitnessValue > maxFitness){
                maxFitness = fitnessValue;
                pop.setBestIndividual(city);
            }
                
            
            city.setFitness(fitnessValue);
        }
    }
}
