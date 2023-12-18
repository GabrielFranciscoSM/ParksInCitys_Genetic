/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Inicializer;

import Basics.Position;
import Model.CityParameters;
import Model.Individuals.CityTileset;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class RandomParkInicializer {    
    
    Random generator;

    private double parkSpreadness;
    public int maxParks;
    public int minParks;
    
    public RandomParkInicializer(){
        generator = new Random(System.currentTimeMillis());
        setSpreadness(CityParameters.DEFPARKSPREADNESS);
    }
    
    public RandomParkInicializer(int spreadness){
        generator = new Random(System.currentTimeMillis());
        setSpreadness(spreadness);

    }
    
    public void setSpreadness(int sp){
        parkSpreadness = (double)(sp)/(double)
                            (CityParameters.MAXPARKSPREADNESS- 
                             CityParameters.MINPARKSPREADNESS);
    }
    
    //Method to create parks:
    // 1) Set Parameters
    // 2) Generate random parks until a given number
    // 3) Try to expand the parks that exists
    public void Inicialize(CityTileset ct){
        
        maxParks = ct.getFreeTiles()/1000 * CityParameters.MAXPERCENTAGEOFPARKS;
        minParks = ct.getFreeTiles()/1000 * CityParameters.MINPERCENTAGEOFPARKS;
        
        int totalParks = (int)(generator.nextDouble()*(
                maxParks-minParks)) + minParks;
        //Random ganeration
        while(ct.getNparkTiles() < totalParks*parkSpreadness){

            Position pos = new Position(
             generator.nextInt(ct.getSize()),
             generator.nextInt(ct.getSize()));

             while(!ct.NewParkTile(pos)){

                pos = new Position(
                generator.nextInt(ct.getSize()),
                generator.nextInt(ct.getSize()));
             }
        }
        
        //Expansion generation
        int counter = 0;
        while(ct.getNparkTiles() < (totalParks)){
            int aux = generator.nextInt(ct.getNparkTiles());
            if(!ct.extendPark(ct.getParkTile(aux))){
                ++counter;
            }
            
            //If cant expand, generate a new random park
            if(counter > CityParameters.STOPTRYSPAND){
                counter = 0;
                Position pos = new Position(
             generator.nextInt(ct.getSize()),
             generator.nextInt(ct.getSize()));
                
                while(!ct.NewParkTile(pos)){
                    pos = new Position(
                    generator.nextInt(ct.getSize()),
                    generator.nextInt(ct.getSize()));
                }
            }
        }
    }
}
