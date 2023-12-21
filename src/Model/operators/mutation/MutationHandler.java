package Model.operators.mutation;

import java.util.Random;

import Basics.Position;
import Model.Individuals.CityTileset;

public class MutationHandler {

	private Random generator = new Random();

	public void mutate(CityTileset city, Position[] parkPositions, double MUTATIONPROB) {
		int action = -1;	//Stores the action that has been assigned until it is carried out (-1 -> no operation,
							//0 -> extend park, 1 -> remove park)
		for (Position park : parkPositions) {
			if ((generator.nextDouble() < MUTATIONPROB) || action != -1) {	
				//action != -1 means that the operation assigned to another park have not been carried out, so
				//it will be try on the next park.
				if (city.hasAvailableTiles()) {
					if (action == -1) {	//Choose an operation
						action = generator.nextInt(2);
					}
					if (action == 0) {
						if (city.extendPark(park, generator.nextInt(8))) {
							action = -1;
						}
					}else {
						city.removeParkTile(park);
						action = -1;
					}
				}else {
					city.removeParkTile(park);
					action = -1;
				}
			}
		}
	}   
}
