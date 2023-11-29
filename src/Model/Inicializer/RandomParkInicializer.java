/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Inicializer;

import Basics.Position;
import Model.Individuals.CityTileset;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class RandomParkInicializer {    
    public static int MAXPARKSPREADNESS = 1000;
    public static int MINPARKSPREADNESS = 1;
    
    public static int DEFPARKSPREADNESS = 1;
    
    public static int EXPANDDESIST = 100;
    
    Random generator;

    private double parkSpreadness;
    public int maxParks;
    public int minParks;
    
    public RandomParkInicializer(){
        generator = new Random(System.currentTimeMillis());
        parkSpreadness = (double)DEFPARKSPREADNESS/
                         (double)(MAXPARKSPREADNESS- MINPARKSPREADNESS);

    }
    
    public RandomParkInicializer(int spreadness){
        generator = new Random(System.currentTimeMillis());
        parkSpreadness = (double)spreadness/
                         (double)(MAXPARKSPREADNESS- MINPARKSPREADNESS);
    }
    
    public void setSpreadness(int sp){
        parkSpreadness = sp/(MAXPARKSPREADNESS- MINPARKSPREADNESS);
    }
    
    public void Inicialize(CityTileset ct){
        
        maxParks = ct.getFreeTiles()/1000 * InicializerController.MAXPERCENTAGEOFPARKS;
        minParks = ct.getFreeTiles()/1000 * InicializerController.MINPERCENTAGEOFPARKS;
        
        int totalParks = (int)(generator.nextDouble()*(
                maxParks-minParks)) + minParks;
        
        while(ct.getNparkTiles() < totalParks*parkSpreadness){

            Position pos = new Position(
             generator.nextInt(ct.getSize()),
             generator.nextInt(ct.getSize()));
            
            ct.NewParkTile(pos);
        }
                
        

        int counter = 0;
        while(ct.getNparkTiles() < (totalParks)){
            int aux = Math.abs(generator.nextInt()%ct.getNparkTiles());
            if(!ct.extendPark(ct.getParkTile(aux))){
                ++counter;
            }
            
            if(counter > EXPANDDESIST){
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
