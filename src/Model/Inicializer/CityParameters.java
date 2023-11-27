/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Inicializer;

/**
 *
 * @author gabriel
 */
public class CityParameters {
    private int size;
    private int roadDensity;
    private int buildingDensity;
    
    public CityParameters(int s, int rd, int bd){
        size = s;
        roadDensity = rd;
        buildingDensity = bd;
    }
    
    public int getSize(){
        return size;
    }
    
    public int getRoadDensity(){
        return roadDensity;
    }
    
    public int getBuildingDensity(){
        return buildingDensity;
    }
}
