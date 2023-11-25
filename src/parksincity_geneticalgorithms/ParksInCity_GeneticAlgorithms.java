/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parksincity_geneticalgorithms;

import Basics.Position;
import Model.Inicializer.RandomCityInicializer;
import Model.Individuals.CityTileset;
import Model.Individuals.FixedSizePopulation;

import Views.GUI.MainWindow;

/**
 *
 * @author gabriel
 */
public class ParksInCity_GeneticAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CityTileset ct = new CityTileset(200);
        FixedSizePopulation<CityTileset> ctPop = new FixedSizePopulation<>(1,10);
                        
        RandomCityInicializer generator = 
                new RandomCityInicializer(ct,300);
        generator.createRoads();
        generator.createBuildings(300, true);
        generator.createBuildings(300, false);

        for(int i = 0; i < 10; ++i){
            
            if(i >= 3){
                 ct.NewParkTile(new Position(i,i));
            }
            ctPop.add(new CityTileset(ct));
            
        }

        MainWindow gui = MainWindow.getInstance();
        //gui.setIcon(ct);
        gui.setPopulationCT(ctPop);
        
        gui.showView();
        
    }
    
}
