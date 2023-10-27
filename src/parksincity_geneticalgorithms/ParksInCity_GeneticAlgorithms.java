/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parksincity_geneticalgorithms;

import Individuals.*;

/**
 *
 * @author gabriel
 */
public class ParksInCity_GeneticAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        CitySubTileset cst = new CitySubTileset();
        
        for(int i = 0; i < 10; ++i){
            cst.NewParkTile(i, i);
        }
        
        System.out.print(cst);
    }
    
}
