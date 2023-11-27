/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Inicializer;

import Basics.Position;
import Model.Individuals.CityTileset;
import Model.Individuals.Tiles.Tile;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class RandomParkInicializer {
    Random generator;

    
    public RandomParkInicializer(){
        generator = new Random(System.currentTimeMillis());
    }
    
    public void Inicialize(CityTileset ct){
        
        while(ct.getNparkTiles() < InicializerController.MINPARKS/2){
            Position pos = new Position(
             generator.nextInt(ct.getSize()),
             generator.nextInt(ct.getSize()));
            
            ct.NewParkTile(pos);
        }
        
        int restOfParks = (int)(generator.nextDouble()*(
                InicializerController.MAXPARKS- 
                InicializerController.MINPARKS)) + InicializerController.MINPARKS;
        System.out.print(restOfParks);
        while(ct.getNparkTiles() < (restOfParks)){
            int aux = Math.abs(generator.nextInt()%ct.getNparkTiles());
            extendPark(ct,
                   ct.getParkTile(aux));
            
        }
        
    }
    
    public void extendPark(CityTileset ct, Position pos){
        
        Position aux = Position.substract(pos, new Position(1,1));
        
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                if(ct.getTile(
               Position.sum(aux, new Position(i,j)))
                   .isVoid()){
                    ct.NewParkTile(Position.sum(aux, new Position(i,j)));
                    return;
                }
            }
        }
    }
}
