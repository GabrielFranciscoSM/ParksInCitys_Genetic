/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.fitness;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Tiles.BuildingTile;
import Model.Individuals.Tiles.ParkTile;
import Model.Individuals.Tiles.TileType;

/**
 *
 * @author gabriel
 */
public class ValueFunction {
    public static Double Evaluate(CityTileset city){
        int maxPark = (ParkTile.getAreaOfEffect() * 2 + 1) * 
                      (ParkTile.getAreaOfEffect() * 2 + 1) *
                      BuildingTile.MAXCITIZEN;
        int counter = 0;
        for (Position park : city.getArrayOfParkPositions()) {	// Get each park from the city
            int val = city.getTile(park).getValue(TileType.PARK);
            counter += val;
    	}
    	double average = counter / city.getNparkTiles();	// Average score of the parks

    	return average/maxPark;	// Percentage of the average of the parks
    }
}
