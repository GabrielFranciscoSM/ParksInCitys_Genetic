/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.fitness;

import Model.Individuals.CityTileset;
import Model.Individuals.Population;
import Model.Individuals.Tiles.BuildingTile;
import Model.Individuals.Tiles.ParkTile;
import Basics.Position;

/**
 *
 * @author gabriel
 */
public class PonderatedFunction {
    MoneyFunction money;
    ValueFunction value;
    
    private double moneyPonderation;
    private double valuePonderation;
    
    public void evaluate(Population<CityTileset> pop){
    	int average, counter, maxValuePark;
    	maxValuePark = 4 * ParkTile.getAreaOfEffect() * (ParkTile.getAreaOfEffect() + 1) * BuildingTile.MAXCITIZEN;	// Stores the maximum score that a park can achieve
    	double parkValuePercentage, parkTilesPercentage;
    	for (CityTileset city : pop) {	// Get each city from the population
    		counter = 0;
    		for (Position park : city.getArrayPark()) {	// Get each park from the city
    			counter += city.getValueOfPark(park);
    		}
    		average = counter / city.getNparkTiles();	// Average score of the parks
    		parkValuePercentage = average / maxValuePark;	// Percentage of the average of the parks
    		parkTilesPercentage = 1 - (city.getNparkTiles() / CityTileset.MAXSIZE);	// Percentage of the remaining available park tiles
    		
    		// Fitness is the result of the multiplication of both percentages
    		city.setFitness(parkValuePercentage * parkTilesPercentage);
        }
    }
}
