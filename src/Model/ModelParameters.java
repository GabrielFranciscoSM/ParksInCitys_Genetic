/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author gabriel
 */
public class ModelParameters {
    private final int populationSize;
    private final int moneyPonderation;
    
    public ModelParameters(int popSize, int moneyPond){
        populationSize = popSize;
        this.moneyPonderation = moneyPond;
    }
    
    public int getPopulationSize(){
        return populationSize;
    }
    
    public int getMoneyPonderation(){
        return moneyPonderation;
    }
}
