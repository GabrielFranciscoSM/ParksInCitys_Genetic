/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author gabriel
 */

///Class containing parameters for inizalitation of cities
public class CityParameters {

    //Size of a side of the tileset
    public static final int DEFAULTSIZE = 200;
    ///<Default size for cities.
    public static final int MINSIZE = 10;
    ///<Min size of a city
    public static final int MAXSIZE = 400;
    ///<Max Size of city
    
    public static final int NEIGHBORHOODSIZE = 50;
    ///<Size of the neighborhoods. It works better if it divides SETSIZE
    
    //How many schools per 1000 tiles
    public static final int MAXSCHOOLSPERTILES = 30;
    ///<Max schools per 1000 tiles
    public static final int DEFSCHOOLSPERTILES = 10;
    ///<Default schools per 1000 tiles
    public static final int MINSCHOOLSPERTILES = 1;
    ///<Min school per 1000 tiles
    
    //Inicializer constants
    public static int MINPERCENTAGEOFPARKS;
    ///<Min percentage of parks in free tiles.
    public static int MAXPERCENTAGEOFPARKS;
    ///<Max percentage of parks in free tiles
    
    public static final int PERCENTAGERANGE = 1;
    ///< Range of error of initial percentage of parks
    public static final int DEFPARKSPARCENTAGE = 7;
    ///< Default percentage of parks
    
    //City building Constants
    public static final int MINSEPARATIONOFROADS = 4;
    ///<Least tiles beteween two roads. It make cities more realistio.
    
    public static final int MINROADDENSITY = 1;
    ///<Min density of roads.
    public static final int DEFROADDENSITY = 20;
    ///<Default density of roads
    public static final int MAXROADDENSITY = 50;
    ///<Max density of roads
    
    public static final int MINBUILDINGDENSITY = 10;
    ///<Min building density
    public static final int DEFBUILDINGDENSITY = 70;
    ///<Default building density
    public static final int MAXBUILDINGDENSITY = 100;
    ///<Max building density
    
    public static final int MAXBUILDINGSIZE = MINSEPARATIONOFROADS * 2;
    ///<Max size of a sigle building. Double of min separation of roads.
    public static final int MINBUILDINGSIZE = MINSEPARATIONOFROADS / 2;
    ///<Min size of a sigle building. Half of min separation of roads.
    
    //Parks Inicizlizer constants
    public static int DEFPARKSPREADNESS = 1;
    ///<Default parks spreadness.
    public static int MAXPARKSPREADNESS = 1000;
    ///<Max park spreadness.
    public static int MINPARKSPREADNESS = 1;
    ///<Min park spreadness.
    
    public static int STOPTRYSPAND = 100;
    ///<Number of times the park inizializer will try to 
    /// expand an existent park

    
    //InputParameters
    private final int size;
    private final int roadDensity;
    private final int buildingDensity;
    private final int parkSpreadness;
    private final int parksPercentage; //1 to 1000
    
    /**
     * Constructor
     * @param s     Size of the city
     * @param rd    Road density
     * @param bd    Building density
     * @param ps    Parks spreadness. The less spreadness, the bigger the parks
     * @param pp    Parks percentage
     */
    public CityParameters(int s, int rd, int bd, int ps, int pp){
        size            =   s;
        roadDensity     =   rd;
        buildingDensity =   bd;
        parkSpreadness  =   ps;
        parksPercentage =   pp;
    }
    
    /**
     * Getter of size
     * @return Size of cities
     */
    public int getSize(){
        return size;
    }
    
    /**
     * Getter of road density
     * @return Road density
     */
    public int getRoadDensity(){
        return roadDensity;
    }
    
    /**
     * Getter of building density
     * @return Building density
     */
    public int getBuildingDensity(){
        return buildingDensity;
    }
    
    /**
     * Getter of park spreadness
     * @return Park spreadness
     */
    public int getParkSpreadness(){
        return parkSpreadness;
    }
    
    /**
     * Getter of parks percentage
     * @return Parks percentage
     */
    public int getParksPercentage(){
        return parksPercentage;
    }
}
