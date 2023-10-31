/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Basics;

/**
 *
 * @author gabriel
 */
public class Position {
    static final public Position ZERO = new Position(0);
    
    private int x;
    private int y;
    
    public Position(){
        x = 0;
        y = 0;
    }
    
    public Position(int n){
        x = n;
        y = n;
    }
    
    public Position(int _x, int _y){
        x = _x;
        y = _y;
    }
    
    static public Position substract(Position pos1, Position pos2){
        return new Position(pos1.getX()-pos2.getX(),pos1.getY()-pos2.getY());
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int _x){
        x = _x;
    }
    
    public void setY(int _y){
        y = _y;
    }
    
    public void setPos(int _x, int _y){
        setX(_x);
        setY(_y);
    }
    
    public Position mult(int m){
        x *= m;
        y *= m;
        
        return this;
    }
    
    public Position div(int d){
        x /= d;
        y /= d;
        
        return this;
    }
    
    public boolean inRange(Position topRight, Position botLeft){
        return(x < topRight.getX() && x > botLeft.getX() && 
               y < topRight.getY() && y > botLeft.getY());
    }
    
    public boolean isEqual(Position pos){
        return (getX() == pos.getX()) && (getY() == pos.getY());
    }
}
