/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Inicializer;

import Model.Individuals.Tiles.BuildingTile;
import Model.Individuals.CityTileset;
import Basics.*;

import java.util.Random;
import java.util.PriorityQueue;
import java.util.Vector;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author gabriel
 */
public class RandomCityInicializer {
    
    final private int MINSEPARATIONOFROADS = 4;
    
    final private int MAXBUILDINGSIZE = MINSEPARATIONOFROADS*2;
    final private int MINBUILDINGSIZE = MINSEPARATIONOFROADS/2;
    
    final private double NEWROADPROB = 0.5; //<0.5
    final private double NEWBUILDINGPROB = 0.5;
    final private double STOPBUILDINGPROB = 0.05;
    
    Random generator;
    HashSet<Position> nodes;
    CityTileset ct;
    
    //Inicializador con ciudad
    public RandomCityInicializer(CityTileset _ct, int n_nodes){
        ct = _ct;
        generator = new Random(System.currentTimeMillis());
        nodes = new HashSet();
        generateNodes(n_nodes);
    }
    
    //Function to generate nodes.
    // This nodes will be used to generate random roads and Buildings
    private void generateNodes(int n_nodes){
        for(int  i = 0; i < n_nodes; ++i){
            nodes.add(new Position(
             generator.nextInt(ct.getSize()/MINSEPARATIONOFROADS)
                     *MINSEPARATIONOFROADS,
             generator.nextInt(ct.getSize()/MINSEPARATIONOFROADS)
                     *MINSEPARATIONOFROADS));
            
        }        
    }
    
    //Create building defaults
    private void createBuildings(){
        createBuildings(ct.getSize(),false);
        createBuildings(ct.getSize(),true);
    }
    
    //Function to create Buildings, two modes:
    // -With nodes: uses nodes as references to build from
    // -Random: Places random buildings in the map
    public void createBuildings(int n_buildings, boolean type){
        if(type){
            createBuildings(nodes);
        }else{
            
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
                            (MAXBUILDINGSIZE-MINBUILDINGSIZE) 
                       + MINBUILDINGSIZE, ct.getSize()-1),
                    Math.min(TopLeft.getY()+ generator.nextInt      
                            (MAXBUILDINGSIZE-MINBUILDINGSIZE)
                       + MINBUILDINGSIZE, ct.getSize()-1));

                for(int j = TopLeft.getX(); j <= BotRight.getX(); ++j){
                    for(int k = TopLeft.getY(); k <= BotRight.getY(); ++k){
                        ct.NewBuildingTile(new Position(j,k),bt);
                    }
                }

            
            }
        }
        
    }
    
    //Method to create buildings in the corner of road croses (nodes)
    private void createBuildings(HashSet<Position> pos){
        
        for(Position n: pos){
            int i = 1;
            
            if(generator.nextDouble() < NEWBUILDINGPROB){
                while(ct.canBuild(Position.sum(n, new Position(i)))
                        && generator.nextDouble() >= STOPBUILDINGPROB){
                    ++i; 
                }

                createBuilding(new Position(n.getX()+1,n.getY()+1)
                        ,new Position(n.getX()+i-1,n.getY()+i-1));
            }
            i=1;

            if(generator.nextDouble() < NEWBUILDINGPROB){
                while(ct.canBuild(Position.sum(n, new Position(i,-i)))
                        && generator.nextDouble() >= STOPBUILDINGPROB){
                    ++i;
                }

                createBuilding(new Position(n.getX()+1,n.getY()-1),
                        new Position(n.getX()+i-1,n.getY()-i+1));
            }
            i=1;

            if(generator.nextDouble() < NEWBUILDINGPROB){
                while(ct.canBuild(Position.sum(n, new Position(-i,i)))
                        && generator.nextDouble() >= STOPBUILDINGPROB){
                    ++i;
                }

                if(n.getX()-i > 0 && n.getY()+i < ct.getSize())
                    createBuilding(new Position(n.getX()-1,n.getY()+1),
                            new Position(n.getX()-i+1,n.getY()+i-1));
            }
            i=1;

            if(generator.nextDouble() < NEWBUILDINGPROB){
                while(ct.canBuild(Position.substract(n, new Position(i)))
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
    void createRoad(Position pos){
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
