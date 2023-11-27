/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Inicializer;

/**
 *
 * @author gabriel
 */
public class ModelParameters {
    private int populationSize;
    
    public ModelParameters(int popSize){
        populationSize = popSize;
    }
    
    public int getPopulationSize(){
        return populationSize;
    }
}
