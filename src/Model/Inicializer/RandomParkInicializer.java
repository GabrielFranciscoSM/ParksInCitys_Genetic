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
    Random generator;

    
    public RandomParkInicializer(CityTileset _ct){
        generator = new Random(System.currentTimeMillis());
    }
    
    public void Inicialize(CityTileset ct){
        
        while(ct.getNparkTiles() < InicializerController.MINPARKS/2){
            Position pos = new Position(
             generator.nextInt(ct.getSize()),
             generator.nextInt(ct.getSize()));
            
            ct.NewParkTile(pos);
        }
        
    }
}
