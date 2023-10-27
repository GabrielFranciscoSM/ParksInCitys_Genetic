/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuals;
import java.util.ArrayList; // import the ArrayList class
/**
 *
 * @author gabriel
 */
public class CitySubTileset {
    static int SUBSETSIZE = 10;
    ArrayList<ArrayList<Tile>> Tileset;
    int nParkTiles;
    int subsetValue;
    
    public CitySubTileset(){
        Tileset = new ArrayList<ArrayList<Tile>>();
        for(int i = 0; i < SUBSETSIZE; ++i){
            ArrayList<Tile> aux = new ArrayList<Tile>(); 
            
            for(int j = 0; j < SUBSETSIZE; ++j){
                aux.add(new VoidTile());
            }
            
            Tileset.add(aux);
        }
        
        nParkTiles = 0;
        subsetValue = 0;
    }
    
    private void ChangeTile(int x, int y, Tile tile){
        Tileset.get(y).set(x, tile);
    }
    
    public void NewParkTile(int x, int y){
        if(CheckIfVoid(x,y)){
            ParkTile parkTile = new ParkTile();
            InicializeParkTile(x,y,parkTile);
            ChangeTile(x,y,parkTile);
        }
        
    }
    
    void InicializeParkTile(int x, int y, ParkTile pTile){
        //recorrer un cuadrado centrado en (x,y) 
        //para sumar los puntos de la nueva casilla
    }
    
    boolean CheckIfVoid(int x, int y){
        //comprobar si el camino está ocupado;
        return Tileset.get(x).get(y).isVoid();
    }
    
    @Override
    public String toString() {
        String out = "";
        for(int i = 0; i < SUBSETSIZE; ++i){
            for(int j = 0; j < SUBSETSIZE; ++j){
                out += Tileset.get(i).get(j).toString();
            }
            
            out += "\n";
        }
        
        return out;
    }
}
