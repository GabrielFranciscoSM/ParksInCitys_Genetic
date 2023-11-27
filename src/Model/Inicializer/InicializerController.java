/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Inicializer;

import Model.Individuals.CityTileset;
import Model.Individuals.FixedSizePopulation;
import Model.Individuals.Population;

/**
 *
 * @author gabriel
 */
public class InicializerController {
    public static int MAXPARKS;
    public static int MINPARKS;
    
    RandomParkInicializer rdmPrk;
    CloseToBuildingsParkInicializer ctbPrk;
    
    CityParameters cp;
    ModelParameters mp;
    
    public InicializerController(CityParameters _cp, ModelParameters _mp){
        cp = _cp;
        MAXPARKS = cp.getSize()*cp.getSize()/3;
        MINPARKS = cp.getSize()*cp.getSize()/5;
        mp = _mp;
    }
    
    public Population Inicialize(){
        return InicializeCities();
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
