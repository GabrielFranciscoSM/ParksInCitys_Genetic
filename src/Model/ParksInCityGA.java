/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;
import Model.Individuals.CityTileset;
import Model.Inicializer.InicializerController;
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
    
    public void run(){
        
    }
}
