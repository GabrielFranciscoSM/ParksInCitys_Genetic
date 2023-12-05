/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.fitness;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Tiles.BuildingTile;
import Model.Individuals.Tiles.ParkTile;

/**
 *
 * @author gabriel
 */
public class ValueFunction {
    public static Double Evaluate(CityTileset city){
        int maxValuePark = 4 * ParkTile.getAreaOfEffect() 
                         * (ParkTile.getAreaOfEffect() + 1) 
                         * BuildingTile.MAXCITIZEN;	// Stores the maximum score that a park can achieve
        
        int counter = 0;
        for (Position park : city.getArrayPark()) {	// Get each park from the city
            counter += city.getValueOfPark(park);
    	}
        
    	double average = counter / city.getNparkTiles();	// Average score of the parks
    	return (average / maxValuePark);	// Percentage of the average of the parks
    }
}
