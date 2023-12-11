/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Inicializer;

import Model.Individuals.Tiles.BuildingTile;
import Model.Individuals.CityTileset;
import Basics.*;
import Model.CityParameters;

import java.util.Random;
import java.util.HashSet;

/**
 *
 * @author gabriel
 */
public class RandomCityInicializer {            
    //final private double NEWROADPROB = 0.5; //<0.5
    private double newBuildingProb = 0.8;
    final private double STOPBUILDINGPROB = 0.05;
    
    private Random generator;
    private HashSet<Position> nodes;
    private CityTileset ct;
    
    //Inicializador con ciudad
    public RandomCityInicializer(){
        generator = new Random(System.currentTimeMillis());
        nodes = new HashSet();
    }
    
    public void setCt(CityTileset _ct){
        ct = _ct;
    }
        
    public void setNewBuildingProb(double nbp){
        newBuildingProb = nbp;
    }
    
    //Main inicizlizer method
    // 1) Gets the city
    // 2) Generate the nodes randomly. This nodes will be used 
    //    to generate roads and buildings proceduraly
    // 3) Generate the roads
    // 4) Generate the Buildings with two algorithms:
    //      a) First a more organizated method using nodes
    //      b) Then a random caotic method
    // 5) Count the void tiles and set them to de cities
    public void inicialize(CityTileset _ct, int n_nodes){
        setCt(_ct);
        generateNodes(n_nodes);
        createRoads();
        createBuildings();
        _ct.setDisponibleTiles(_ct.getFreeTiles());
    }
    
    //Function to generate nodes.
    // This nodes will be used to generate random roads and Buildings
    public void generateNodes(int n_nodes){
        nodes.clear();
        
        for(int  i = 0; i < n_nodes; ++i){
            nodes.add(new Position(
             generator.nextInt(ct.getSize()/CityParameters.MINSEPARATIONOFROADS)
                     *CityParameters.MINSEPARATIONOFROADS,
             generator.nextInt(ct.getSize()/CityParameters.MINSEPARATIONOFROADS)
                     *CityParameters.MINSEPARATIONOFROADS));
            
        }        
    }
    
    //Create building defaults, a mix of the to methods
    public void createBuildings(){
        createBuildings(ct.getSize()*(int)(newBuildingProb*8),false);
        createBuildings(ct.getSize(),true);
    }
    
    //Function to create Buildings, two modes:
    // -With nodes: uses nodes as references to build from
    // -Random: Places random buildings in the map
    public void createBuildings(int n_buildings, boolean type){
        if(type){
            createBuildings(nodes);
        }else{
            
            createBuildings(n_buildings);
        }
        
    }
    
    //Random method:
    // Pick a random tile and expand it with a rectangular shape
    private void createBuildings(int n_buildings){
        for(int i = 0; i < n_buildings; ++i){
                int citizens = generator.nextInt(BuildingTile.MAXCITIZEN-
                        BuildingTile.MINCITIZEN)+
                        BuildingTile.MINCITIZEN;
                
                BuildingTile bt = new BuildingTile(citizens);

                Position TopLeft = new Position(
                 generator.nextInt(ct.getSize()),
                 generator.nextInt(ct.getSize()));

                Position BotRight = new Position(
                    Math.min(TopLeft.getX()+ generator.nextInt
                            (CityParameters.MAXBUILDINGSIZE-CityParameters.MINBUILDINGSIZE) 
                       + CityParameters.MINBUILDINGSIZE, ct.getSize()-1),
                    Math.min(TopLeft.getY()+ generator.nextInt      
                            (CityParameters.MAXBUILDINGSIZE-CityParameters.MINBUILDINGSIZE)
                       + CityParameters.MINBUILDINGSIZE, ct.getSize()-1));

                for(int j = TopLeft.getX(); j <= BotRight.getX(); ++j){
                    for(int k = TopLeft.getY(); k <= BotRight.getY(); ++k){
                        ct.NewBuildingTile(new Position(j,k),bt);
                    }
                }

            
            }
    }
    
    //Method to create buildings in the corner of road croses (nodes)
    private void createBuildings(HashSet<Position> pos){
        for(Position n: pos){
            int i = 1;
            
            if(generator.nextDouble() < newBuildingProb){
                while(ct.canBuild(Position.sum(n, new Position(i)))
                        && generator.nextDouble() >= STOPBUILDINGPROB){
                    ++i; 
                }

                createBuilding(new Position(n.getX()+1,n.getY()+1)
                        ,new Position(n.getX()+i-1,n.getY()+i-1));
            }
            i=1;

            if(generator.nextDouble() < newBuildingProb){
                while(ct.canBuild(Position.sum(n, new Position(i,-i)))
                        && generator.nextDouble() >= STOPBUILDINGPROB){
                    ++i;
                }

                createBuilding(new Position(n.getX()+1,n.getY()-1),
                        new Position(n.getX()+i-1,n.getY()-i+1));
            }
            i=1;

            if(generator.nextDouble() < newBuildingProb){
                while(ct.canBuild(Position.sum(n, new Position(-i,i)))
                        && generator.nextDouble() >= STOPBUILDINGPROB){
                    ++i;
                }

                if(n.getX()-i > 0 && n.getY()+i < ct.getSize())
                    createBuilding(new Position(n.getX()-1,n.getY()+1),
                            new Position(n.getX()-i+1,n.getY()+i-1));
            }
            i=1;

            if(generator.nextDouble() < newBuildingProb){
                while(ct.canBuild(Position.subtract(n, new Position(i)))
                        && generator.nextDouble() >= STOPBUILDINGPROB){
                    ++i;
                }


                createBuilding(new Position(n.getX()-1,n.getY()-1),
                        new Position(n.getX()-i+1,n.getY()-i+1));
            }
        }
        
    }
    
    //Method to create a square building with given positions as corners
    private void createBuilding(Position pos1,Position pos2){
        
        Position botRight = new Position(Math.max(pos1.getX(), pos2.getX()), 
                Math.max(pos1.getY(), pos2.getY()));
        Position topLeft = new Position(Math.min(pos1.getX(), pos2.getX()), 
                Math.min(pos1.getY(), pos2.getY()));
        
        int citizens = generator.nextInt(BuildingTile.MAXCITIZEN-
                        BuildingTile.MINCITIZEN)+
                        BuildingTile.MINCITIZEN;
        
        for(int i = topLeft.getX(); i <= botRight.getX();++i){
            for(int j = topLeft.getY(); j <= botRight.getY(); ++j){
                ct.NewBuildingTile(new Position(i,j),
                        new BuildingTile(citizens));
            }
        }
    }
    
    //Create Roads Based on nodes
    public void createRoads(){
        for(Position i: nodes){
            createRoad(i);
        }
    }
    
    //Method to create roads at four directions from positions.
    //Roads stop generating when they encounter another road
    private void createRoad(Position pos){
        ct.NewRoadTile(pos);
        
        int i = pos.getX()-1;
        while(i > 0 && !ct.getTile(i, pos.getY()).isRoad()){
            ct.NewRoadTile(new Position(i,pos.getY()));
            --i;
        }
        
        i = pos.getX()+1;
        while(i < ct.getSize() && !ct.getTile(i, pos.getY()).isRoad()){
            ct.NewRoadTile(new Position(i,pos.getY()));
            ++i;
        }
        
        i = pos.getY()-1;
        while(i > 0 && !ct.getTile(pos.getX(),i).isRoad()){
            ct.NewRoadTile(new Position(pos.getX(),i));
            --i;
        }
        
        i = pos.getY()+1;
        while(i < ct.getSize() && !ct.getTile(pos.getX(),i).isRoad()){
            ct.NewRoadTile(new Position(pos.getX(),i));
            ++i;
        }
    }
}
