/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Inicializer;

import Model.ModelParameters;
import Model.CityParameters;
import Model.Individuals.CityTileset;
import Model.Individuals.FixedSizePopulation;
import Model.Individuals.Population;

/**
 *
 * @author gabriel
 */
public class InicializerController {
    public static int MAXPERCENTAGEOFPARKS;
    public static int MINPERCENTAGEOFPARKS;
    
    ;public static int DEFPARKSPARCENTAGE = 7;
    public static int PERCENTAGERANGE = 1;
    
    RandomParkInicializer rdmPrk;
    CloseToBuildingsParkInicializer ctbPrk;
    
    CityParameters cp;
    ModelParameters mp;
    
    public InicializerController(CityParameters _cp, ModelParameters _mp){
        cp = _cp;
        mp = _mp;
        
        MAXPERCENTAGEOFPARKS = _cp.getParksPercentage() + PERCENTAGERANGE;
        MINPERCENTAGEOFPARKS = _cp.getParksPercentage() - PERCENTAGERANGE;
        
        rdmPrk = new RandomParkInicializer(_cp.getParkSpreadness());
    }
    
    public Population Inicialize(){
        Population<CityTileset> pop = InicializeCities();
        
        for(CityTileset ct: pop){
            rdmPrk.Inicialize(ct);
        }
        return pop;
    }
    
    public Population InicializeCities(){
        CityTileset ct = new CityTileset(cp.getSize());
        FixedSizePopulation<CityTileset> ctPop = new FixedSizePopulation<>(1,mp.getPopulationSize());
                        
        RandomCityInicializer generator = 
                new RandomCityInicializer();
        generator.setNewBuildingProb((double)cp.getBuildingDensity()/100); 
        generator.inicialize(ct,ct.getSize()/10*cp.getRoadDensity());

        for(int i = 0; i < mp.getPopulationSize(); ++i){
            ctPop.add(new CityTileset(ct));
            
        }
        
        return ctPop;
    }
}
