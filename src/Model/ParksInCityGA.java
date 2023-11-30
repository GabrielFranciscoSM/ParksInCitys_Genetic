/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Model.Inicializer.CityParameters;
import java.util.ArrayList;
import Model.Individuals.CityTileset;
import Model.Individuals.Population;
import Model.Inicializer.InicializerController;
import Model.Inicializer.ModelParameters;
import Model.operators.crossover.CrossoverController;
import Model.operators.mutation.MutationController;
import Model.operators.selection.SelectionController;

/**
 *
 * @author gabriel
 */
public class ParksInCityGA {
    
    private ArrayList<CityTileset> population;
    
    private InicializerController inicializer;
    private CrossoverController crossover;
    private MutationController mutation;
    private SelectionController selection;
    
    private Population pop;
    
    public ParksInCityGA(CityParameters cp, ModelParameters mp){
        inicializer = new InicializerController(cp, mp);
        crossover = new CrossoverController();
    }
    
    public void run(){
        pop = inicializer.Inicialize();
    }
    
    public Population getPopulation(){
        return pop;
    }
    
    public Population applyCrossover(){
        pop = crossover.apply(pop);
        return pop;
    }
}
