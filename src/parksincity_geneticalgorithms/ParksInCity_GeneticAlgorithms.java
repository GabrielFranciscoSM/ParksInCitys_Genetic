/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parksincity_geneticalgorithms;

import Views.GUI.MainWindow;
import Individuals.*;
import inicializer.*;

/**
 *
 * @author gabriel
 */
public class ParksInCity_GeneticAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CityTileset ct = new CityTileset(300);
        
        RandomCityInicializer generator = new RandomCityInicializer(ct);
        
        //System.out.print(ct);
        
        generator.createBuildings(10);
        
        //System.out.print(ct);
        
        generator.createRoads(10);
        
        //System.out.print(ct);
        
        MainWindow gui = MainWindow.getInstance();
        gui.createCityImage(ct);
        
        gui.showView();
        
    }
    
}
