/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicializer;

import Individuals.CityTileset;
import Individuals.Tiles.*;
import Basics.*;

import java.util.Random;
import java.util.PriorityQueue;

/**
 *
 * @author gabriel
 */
public class RandomCityInicializer {
    
    final int MAXBUILDINGSIZE = 6;
    final int MINBUILDINGSIZE = 2;
    final int MAXCITIZEN = 100;
    final int MINCITIZEN = 5;
    
    final double NEWROADPROB = 0.5; //<0.5
    
    Random generator;
    CityTileset ct;
    
    public RandomCityInicializer(CityTileset _ct){
        ct = _ct;
        generator = new Random(System.currentTimeMillis());
    }
    
    public void createBuildings(){
        createBuildings(ct.getSize());
    }
    
    public void createBuildings(int n_buildings){
        for(int i = 0; i < n_buildings; ++i){
            int citizens = generator.nextInt(MAXCITIZEN-MINCITIZEN)+MINCITIZEN;
            BuildingTile bt = new BuildingTile(citizens);
            
            Position TopLeft = new Position(
             generator.nextInt(ct.getSize()),
             generator.nextInt(ct.getSize()));
            
            Position BotRight = new Position(
                Math.min(TopLeft.getX()+ generator.nextInt(MAXBUILDINGSIZE-MINBUILDINGSIZE) 
                   + MINBUILDINGSIZE, ct.getSize()-1),
                Math.min(TopLeft.getY()+ generator.nextInt(MAXBUILDINGSIZE-MINBUILDINGSIZE)
                   + MINBUILDINGSIZE, ct.getSize()-1));
            
            for(int j = TopLeft.getX(); j <= BotRight.getX(); ++j){
                for(int k = TopLeft.getY(); k <= BotRight.getY(); ++k){
                    ct.NewBuildingTile(new Position(j,k),bt);
                }
            }

            
        }
    }
    
    void createRoads(){
        
    }
    
    public void createRoads(int n_nodes){
        PriorityQueue<Position> nodes = new PriorityQueue(new SortByX());
        
        for(int  i = 0; i < n_nodes; ++i){
            nodes.add(new Position(
             generator.nextInt(ct.getSize()),
             generator.nextInt(ct.getSize())));
        }
        
        System.out.print(nodes);
        System.out.print("\n");
        while(nodes.size() > 1)
        {
            createRoad(nodes.poll(),nodes.peek());
            if(generator.nextDouble() <= NEWROADPROB*2)
                createRoad(nodes.peek());
        }
        
    }
    
    void createRoad(Position pos1, Position pos2){
        for(int i = pos1.getX(); i <= pos2.getX(); i++){
            ct.NewRoadTile(new Position(i,pos1.getY()));
        }
        
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int minY = Math.min(pos1.getY(), pos2.getY());
        
        for(int i = minY; i <= maxY; ++i){
            ct.NewRoadTile(new Position(pos2.getX(),i));
        } 
    }
    
    void createRoad(Position pos){
        int direction = generator.nextInt(2);
        
        if(direction == 1){
            createRoad(pos,new Position(pos.getX(),ct.getSize()-1));
        }
        else{
            int i = pos.getX()-1;
            while(i > 0 && ct.getTile(i, pos.getY()).getValue(TileType.ROAD) == 0){
                ct.NewRoadTile(new Position(i,pos.getY()));
                --i;
            }
        }
    }
}
