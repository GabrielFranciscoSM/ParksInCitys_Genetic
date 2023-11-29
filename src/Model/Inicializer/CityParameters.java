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
    private int parkSpreadness;
    private int parksPercentage;
    
    public CityParameters(int s, int rd, int bd, int ps, int pp){
        size            =   s;
        roadDensity     =   rd;
        buildingDensity =   bd;
        parkSpreadness  =   ps;
        parksPercentage =   pp;
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
    
    public int getParkSpreadness(){
        return parkSpreadness;
    }
    
    public int getParksPercentage(){
        return parksPercentage;
    }
}
