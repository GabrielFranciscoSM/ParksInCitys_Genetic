/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.selection;

import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;
import Model.Individuals.Population;
import Model.ModelParameters;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class SelectionController{
    
    RankSelection rs;
    Random generator;
    
    private boolean useElitism;
    private boolean useTruncate;
    private double  truncateSize;

    public SelectionController(ModelParameters mp){
        rs =            new RankSelection();
        generator =     new Random(System.currentTimeMillis());
        useElitism =    mp.getUSEELITISM();
        useTruncate =   mp.getUSETRUNCATE();
        truncateSize =  mp.getTRUNCATESIZE();
    }
    
    //include elitism and truncation.
    public CityTilesetPopulation apply(CityTilesetPopulation pop){
        return rs.apply(pop,useElitism,useTruncate,generator,truncateSize);
    }
}
