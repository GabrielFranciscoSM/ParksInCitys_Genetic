/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.mutation;

import Model.Individuals.CityTileset;
import Model.Individuals.Population;

/**
 *
 * @author gabriel
 */
public class MutationController {
	RandomParkMutation rpm;
	
	public MutationController() {
		rpm = new RandomParkMutation();
	}
    public void apply(Population<CityTileset> pop){
    	rpm.apply(pop);
    }
}
