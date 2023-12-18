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
import Views.GUI.MainWindow;

/**
 *
 * @author gabriel
 */
public class ParksInCityGA {
        
    private InicializerController initializer;
    ///< Inicializer of a population.
    private CrossoverController crossover;
    ///< Controller of crossover operations.
    private MutationController mutation;
    ///< Controller of mutation operations.
    private SelectionController selection;
    ///< Controller of selection operations.
    private PonderatedFunction fitness;
    ///< Controller of fitness functions
    
    private CityTilesetPopulation pop;
    
    private int repetitionLimits;
    
    /**
     * Constructor.
     * @param cp City parameters.
     * @param mp Model (algorithm) parameters.
     */
    public ParksInCityGA(CityParameters cp, ModelParameters mp){
        initializer =   new InicializerController(cp, mp);
        crossover =     new CrossoverController(mp);
        fitness =       new PonderatedFunction(mp.getMONEYPONDERATION());
        selection =     new SelectionController(mp);
        repetitionLimits = mp.getRepetitionsLimit();
    }
    
    /**
     * Run method to start the algorithm.
     * 1) Initialize The population
     * 2) Make n repetitions of:
     * 2.1) Apply fitness function to population
     * 2.2) Apply Selection to population
     * 2.3) Apply crossover operation to population
     */
    public void run(){
        pop = initializer.Inicialize();
                        
        for(int i = 0; i < repetitionLimits; ++i){
            this.applyFitness();
            if(i % (repetitionLimits/10) == 0){
                System.out.print("\n\n Generation " + i + ": " + "\n");
                for(CityTileset ct : pop){
                    System.out.print(ct.getFitness() + " ");
                }
                System.out.print("\n");
            }
            this.applySelection();
            this.applyCrossover();

        }        
    }
    
    /**
     * Getter of population.
     * @return Population.
     */
    public CityTilesetPopulation getPopulation(){
        return pop;
    }
    
    /**
     * Apply the crossover function(s) of crossover.
     */
    public void applyCrossover(){
        pop = crossover.apply(pop);
    }
    
    /**
     * Apply the fitness function(s) to the population.
     */
    public void applyFitness(){
        fitness.evaluate(pop);
    }
    
    /**
     * Apply the selection function(s) to the population.
     */
    public void applySelection(){
        pop = selection.apply(pop);
    }
    
    /**
     * Makes a copy of the population.
     * @return A copy of the population.
     */
    public Population savePopulation(){
        Population auxpop = pop.clone();
        auxpop.clear();
        
        for(CityTileset ct: pop){
            auxpop.add(new CityTileset(ct));
        }
        
        return auxpop;
    }
}
