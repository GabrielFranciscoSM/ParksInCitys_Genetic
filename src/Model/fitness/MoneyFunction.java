/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.fitness;

import Model.Individuals.CityTileset;

/**
 *
 * @author gabriel
 */
public class MoneyFunction {
    public static double Evaluate(CityTileset city){
        // Percentage of the remaining available park tiles
        return (city.getFreeTiles() / (city.getDisponibleTiles()));	
    }
}
