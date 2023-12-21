/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.fitness;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Tiles.TileType;

/**
 *
 * @author gabriel
 */
public class ValueFunction {
    public static Double Evaluate(CityTileset city){
        double maxPark = CityTileset.getMaxValue();
        double minPark = CityTileset.getMinValue();
        int counter = 0;
        
        for (Position park : city.getArrayOfParkPositions()) {	// Get each park from the city
            int val = city.getTile(park).getValue(TileType.PARK);
            counter += val;
    	}
        
    	double average = 0;
        if(city.getNparkTiles() != 0){
            average = counter / city.getNparkTiles();
        }	// Average score of the parks

    	//return (average-minPark)/(maxPark-minPark);	// Percentage of the average of the parks
        return (average)/CityTileset.getMeanValue();
    }
}
