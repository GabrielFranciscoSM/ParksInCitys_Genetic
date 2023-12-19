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
    private final int POPULATIONSIZE;
    private final int MONEYPONDERATION;
    
    //Crossover
    private final double DEFCROSSOVERPROB = 0.1;
    private final double CROSSOVERPROB = 0.1;
    
    private final int DEFCROSSOVERINTENSITY = 1;
    private final int CROSSOVERINTENSITY = 1;
    
    //Selection
    private final boolean USEELITISM = true;
    private final boolean USETRUNCATE = true;
    private final double TRUNCATESIZE = 0.3;
    
    //Mutation
    private final double MUTATIONPROB = 0.1;

    
    public ModelParameters(int popSize, int moneyPond){
        POPULATIONSIZE = popSize;
        this.MONEYPONDERATION = moneyPond;
    }
    
    public int getPOPULATIONSIZE(){
        return POPULATIONSIZE;
    }
    
    public int getMONEYPONDERATION(){
        return MONEYPONDERATION;
    }
    
    public double getCROSSOVERPROB(){
        return CROSSOVERPROB;
    }
    
    public int getCROSSOVERINTENSITY(){
        return CROSSOVERINTENSITY;
    }
    
    public boolean getUSEELITISM(){
        return USEELITISM;
    }
    
    public boolean getUSETRUNCATE(){
        return USETRUNCATE;
    }
    
    public double getTRUNCATESIZE(){
        return TRUNCATESIZE;
    }
    
    public double getMUTATIONPROB(){
        return MUTATIONPROB;
    }
}
