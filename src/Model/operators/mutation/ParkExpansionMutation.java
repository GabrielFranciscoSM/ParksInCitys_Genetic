/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.operators.mutation;

import Basics.Position;
import Model.Individuals.CityTileset;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class ParkExpansionMutation {
    static int maxSize = 105;
    
    static public void apply(CityTileset ct, Random r){
        if(r.nextInt(2) == 0){
            Position auxPos = new Position(
                    r.nextInt(ct.getSize()),
                    r.nextInt(ct.getSize()));
            
            while(!ct.getTile(auxPos).isVoid()){
                auxPos = new Position(
                    r.nextInt(ct.getSize()),
                    r.nextInt(ct.getSize()));
            }
            
            ct.NewParkTile(auxPos);
            extendParkRecursive(auxPos,ct,r,0);
        }
        else{
            if(ct.getNparkTiles() <= 0){
                Position auxPos = new Position(
                    r.nextInt(ct.getSize()),
                    r.nextInt(ct.getSize()));
            
                while(!ct.getTile(auxPos).isVoid()){
                    auxPos = new Position(
                        r.nextInt(ct.getSize()),
                        r.nextInt(ct.getSize()));
                }

                extendParkRecursive(auxPos,ct,r,0);
            }
            else{
                Position auxPos = ct.getParkTile(r.nextInt(ct.getNparkTiles()));
                removeParkRecursive(auxPos,ct,r);
                
                if(ct.getNparkTiles() <= 0){
                    auxPos = new Position(
                    r.nextInt(ct.getSize()),
                    r.nextInt(ct.getSize()));
            
                    while(!ct.getTile(auxPos).isVoid()){
                        auxPos = new Position(
                            r.nextInt(ct.getSize()),
                            r.nextInt(ct.getSize()));
                    }

                    extendParkRecursive(auxPos,ct,r,0);
                }

            }
        }
    }
    
    static public int extendParkRecursive(Position pos, CityTileset ct, Random r, int size){
                
        if(ct.getTile(pos).isPark() && size < maxSize){
            int parkOffsetsLength = CityTileset.parkOffsets.length;
            
            int tile = r.nextInt(parkOffsetsLength);
            
            for (int i = 0; i < parkOffsetsLength; i++) {
                int index = (tile + i) % parkOffsetsLength;
                Position auxPos = Position.sum(pos, CityTileset.parkOffsets[index]);
                
                if(ct.getTile(auxPos).isVoid() && size < maxSize){
                    ct.NewParkTile(auxPos);
                    size += 1;
                    size = extendParkRecursive(auxPos,ct,r, size);
                }
            }
        }
        
        return size;
    }
    
    static public void removeParkRecursive(Position pos, CityTileset ct, Random r){
                
        if(ct.getTile(pos).isPark()){
            ct.removeParkTile(pos);
            int parkOffsetsLength = CityTileset.parkOffsets.length;
            
            int tile = r.nextInt(parkOffsetsLength);
            
            for (int i = 0; i < parkOffsetsLength; i++) {
                
                int index = (tile + i) % parkOffsetsLength;
                Position auxPos = Position.sum(pos, CityTileset.parkOffsets[index]);
                
                if(ct.getTile(auxPos).isPark()){
                    removeParkRecursive(auxPos,ct,r);
                }
            }
        }        
    }
    
}
