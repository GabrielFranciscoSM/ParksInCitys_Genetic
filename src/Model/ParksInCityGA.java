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
    
    private int repetitionLimits;
    
    public ParksInCityGA(CityParameters cp, ModelParameters mp){
        inicializer =   new InicializerController(cp, mp);
        crossover =     new CrossoverController(mp);
        fitness =       new PonderatedFunction(mp.getMONEYPONDERATION());
        selection =     new SelectionController(mp);
        repetitionLimits = mp.getRepetitionsLimit();
    }
    
    public void run(){
        pop = inicializer.Inicialize();
        
        for(int i = 0; i < repetitionLimits; ++i){
            this.applyFitness();
            if(i % (repetitionLimits/10) == 0){
                System.out.print("\n\n Generation " + i + ": " + "\n");
                for(CityTileset ct : pop){
                    System.out.print(ct.getFitness() + " ");
                }
                
            }
            this.applySelection();
            this.applyFitness();
            if(i % (repetitionLimits/10) == 0){
                System.out.print("\n\n Generation " + i + ": " + "\n");
                for(CityTileset ct : pop){
                    System.out.print(ct.getFitness() + " ");
                }
                
            }
            this.applyCrossover();
            this.applyFitness();
            if(i % (repetitionLimits/10) == 0){
                System.out.print("\n\n Generation " + i + ": " + "\n");
                for(CityTileset ct : pop){
                    System.out.print(ct.getFitness() + " ");
                }
                
            }
        }        
    }
    
    public CityTilesetPopulation getPopulation(){
        return pop;
    }
    
    public void applyCrossover(){
        pop = crossover.apply(pop);
    }
    
    public void applyFitness(){
        fitness.evaluate(pop);
    }
    
    public void applySelection(){
        pop = selection.apply(pop);
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
