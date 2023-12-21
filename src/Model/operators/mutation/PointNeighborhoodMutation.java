package Model.operators.mutation;

import java.util.Random;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Population;
import Model.Individuals.Tiles.Tile;

import java.util.ArrayList;

public class PointNeighborhoodMutation {
	private Random generator;
	private double MUTATIONPROB; // Mutation probability
	private double POINTNEIGHBORHOODS;
	private ArrayList<Integer> totalNeighborhoods;

	PointNeighborhoodMutation(double mutationProb, int pointNeigh){
    	this.generator = new Random();
    	this.MUTATIONPROB = mutationProb;
    	this.POINTNEIGHBORHOODS = pointNeigh;
    	this.totalNeighborhoods = new ArrayList<>();
    }

    public void apply(Population<CityTileset> pop) {
    	int nNeighborhoods = pop.getArrayList().get(0).getTotalNeighborhoods();
    	if (POINTNEIGHBORHOODS > nNeighborhoods) {
    		POINTNEIGHBORHOODS = nNeighborhoods;
        }
    	for (int i = 0; i < nNeighborhoods; i++) {
    		totalNeighborhoods.add(i);
        }	// Todo lo de encima se puede definir en el constructor si identifico que variable determina el número de barrios
    	for (CityTileset city : pop) {
    		ArrayList<Integer> neighborhoods = getRandomNeighborhoods();
    		for (Integer neighborhood : neighborhoods) {
    			ArrayList<ArrayList<Tile>> tiles = city.getNeighborhoodTiles(new Position(neighborhood % 4, neighborhood/4));
    			for (ArrayList<Tile> row : tiles) {
    				for (Tile tile : row) {
    					// Esta busqueda no es correcta debido a que accedo a las tiles, pero no puedo conseguir su Posicion
    					// debo hacer la busqueda normal y cuando encuentre casillas de parques, guardar sus posiciones.
    				}
    			}

    		}
    		
    	}
    }
    
   public ArrayList<Integer> getRandomNeighborhoods() {
        ArrayList<Integer> neighborhoods = new ArrayList<>();
        ArrayList<Integer> options = (ArrayList<Integer>) this.totalNeighborhoods.clone();

        for (int cont = 0; cont < this.POINTNEIGHBORHOODS; cont++) {
            int index = generator.nextInt(options.size());
            neighborhoods.add(options.remove(index));
        }

        return neighborhoods;
    }
}
