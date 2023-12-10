/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;
import Model.Individuals.Population;
import Model.Inicializer.InicializerController;
import Model.fitness.PonderatedFunction;
import Model.operators.crossover.CrossoverController;
import Model.operators.mutation.MutationController;
import Model.operators.selection.SelectionController;

/**
 *
 * @author gabriel
 */
public class ParksInCityGA {
        
    private InicializerController inicializer;
    private CrossoverController crossover;
    private MutationController mutation;
    private SelectionController selection;
    private PonderatedFunction fitness;
    
    private CityTilesetPopulation pop;
    
    public ParksInCityGA(CityParameters cp, ModelParameters mp){
        inicializer = new InicializerController(cp, mp);
        crossover = new CrossoverController(mp);
        fitness = new PonderatedFunction(mp.getMONEYPONDERATION());
        selection = new SelectionController(mp);
    }
    
    public void run(){
        pop = inicializer.Inicialize();
        this.applyFitness();
        this.applySelection();
        //this.applyCrossover();
    }
    
    public CityTilesetPopulation getPopulation(){
        return pop;
    }
    
    public void applyCrossover(){
        crossover.apply(pop);
    }
    
    public void applyFitness(){
        fitness.evaluate(pop);
    }
    
    public void applySelection(){
        selection.apply(pop);
    }
    
    public Population savePopulation(){
        Population auxpop = pop.clone();
        auxpop.clear();
        
        for(CityTileset ct: pop){
            auxpop.add(new CityTileset(ct));
        }
        
        return auxpop;
    }
}
