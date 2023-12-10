/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Inicializer;

import Model.ModelParameters;
import Model.CityParameters;
import Model.Individuals.CityTileset;
import Model.Individuals.CityTilesetPopulation;

/**
 *
 * @author gabriel
 */
public class InicializerController {
    RandomParkInicializer rdmPrk;
    CloseToBuildingsParkInicializer ctbPrk;
    
    CityParameters cityParams;
    ModelParameters modelParams;
    
    public InicializerController(CityParameters _cp, ModelParameters _mp){
        cityParams = _cp;
        modelParams = _mp;
        
        CityParameters.MAXPERCENTAGEOFPARKS = _cp.getParksPercentage() + 
                                               CityParameters.PERCENTAGERANGE;
        CityParameters.MINPERCENTAGEOFPARKS = _cp.getParksPercentage() - 
                                               CityParameters.PERCENTAGERANGE;
        
        rdmPrk = new RandomParkInicializer(_cp.getParkSpreadness());
    }
    
    public CityTilesetPopulation Inicialize(){
        CityTilesetPopulation pop = InicializeCities();
        
        for(CityTileset ct: pop){
            rdmPrk.Inicialize(ct);
        }
        
        return pop;
    }
    
    public CityTilesetPopulation InicializeCities(){
        CityTileset ct = new CityTileset(cityParams.getSize());
        CityTilesetPopulation ctPop = 
                new CityTilesetPopulation(1,modelParams.getPopulationSize());
                        
        RandomCityInicializer generator = new RandomCityInicializer();
        
        generator.setNewBuildingProb((double)cityParams.getBuildingDensity()/100); 
        
        int numberOfNodes = ct.getSize()/10*cityParams.getRoadDensity();
        
        generator.inicialize(ct, numberOfNodes);

        for(int i = 0; i < modelParams.getPopulationSize(); ++i){
            ctPop.add(new CityTileset(ct));
        }
        
        return ctPop;
    }
}
