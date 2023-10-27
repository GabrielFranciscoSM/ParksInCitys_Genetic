/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */

//Individuo
public class CityTileset {
    static int SETSIZE = 10;
    ArrayList<ArrayList<CitySubTileset>> Tileset;
    
    public CityTileset(){
        Tileset = new ArrayList<ArrayList<CitySubTileset>>();
        for(int i = 0; i < SETSIZE; ++i){
            ArrayList<CitySubTileset> aux = new ArrayList<CitySubTileset>(); 
            
            for(int j = 0; j < SETSIZE; ++j){
                aux.add(new CitySubTileset());
            }
            
            Tileset.add(aux);
        }
    }
    
    public void ChangeTile(int x, int y, CitySubTileset tile){
        Tileset.get(y).set(x, tile);
    }
    
    public void NewParkTile(int x, int y){
        Tileset.get(x%SETSIZE).get(y%SETSIZE).NewParkTile(x/SETSIZE, y/SETSIZE);
        //Buscar la manera de hacer el INicializeParkTile entre varios subTileset
    }
}
