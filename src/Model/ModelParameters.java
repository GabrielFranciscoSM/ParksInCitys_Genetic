/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author gabriel
 */

///Class containing parameters to controll the algorithm
public class ModelParameters {
    
    private final int POPULATIONSIZE;
    ///< Size of the population.
    private final int MONEYPONDERATION;
    ///< Ponderation of the money fitness function.
    
    //Crossover
    private final double DEFCROSSOVERPROB = 0.1;
    ///< Default crossover probability.
    private final double CROSSOVERPROB = 0.1;
    ///< Actual crossover probability.
    
    private final int DEFCROSSOVERINTENSITY = 2;
    ///< Crossover intensity. Times a crossover between the same two individuals.
    private final int CROSSOVERINTENSITY = 2;
    ///< Actual crossover intensity.
    
    //Selection
    private final boolean USEELITISM = true;
    ///< Use or not elitism in selection.
    private final boolean USETRUNCATE = true;
    ///< Use or not truncation in selection.
    private final double TRUNCATESIZE = 0.3;
    ///< Size of the truncation if used.
    
    //Stop condition 
    public final static int MAXREPETITIONLIMIT = 10000;
    ///< Max iterations of the algorithm.
    public final static int DEFREPETITIONLIMIT = 5000;
    ///< Default iterations of the algorithm.
    public final static int MINREPETITIONLIMIT = 2;
    ///< Min iterations of the algorithm.

    //Mutation
    private final double MUTATIONPROB = 0.1;
    
    private int repetitionLimits;
    
    /**
     * Constructor
     * @param popSize           Size of the population
     * @param moneyPond         Ponderation of the money function
     * @param repetitionLimits  Number of iterations of the algorithm
     */
    public ModelParameters(int popSize, int moneyPond, int repetitionLimits){
        POPULATIONSIZE = popSize;
        this.MONEYPONDERATION = moneyPond;
        this.repetitionLimits = repetitionLimits;
    }
    
    /**
     * Getter of population size.
     * @return Population size.
     */
    public int getPOPULATIONSIZE(){
        return POPULATIONSIZE;
    }
    
    /**
     * Getter of money ponderation.
     * @return Money ponderation.
     */
    public int getMONEYPONDERATION(){
        return MONEYPONDERATION;
    }
    
    /**
     * Getter of crossover probability.
     * @return Crossover probability.
     */
    public double getCROSSOVERPROB(){
        return CROSSOVERPROB;
    }
    
    /**
     * Getter crossover intensity.
     * @return Crossover intensity.
     */
    public int getCROSSOVERINTENSITY(){
        return CROSSOVERINTENSITY;
    }
    
    /**
     * Getter of use of elitism.
     * @return Use elitism.
     */
    public boolean getUSEELITISM(){
        return USEELITISM;
    }
    
    /**
     * Getter of use of truncate.
     * @return Use truncate.
     */
    public boolean getUSETRUNCATE(){
        return USETRUNCATE;
    }
    
    /**
     * Getter of truncation size.
     * @return Truncation size.
     */
    public double getTRUNCATESIZE(){
        return TRUNCATESIZE;
    }
    
    public double getMUTATIONPROB(){
        return MUTATIONPROB;
    }
    
    /**
     * Getter of repetitions limit.
     * @return Repetitions limit.
     */
    public int getRepetitionsLimit(){
        return this.repetitionLimits;
    }
}
