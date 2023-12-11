/**
 * \file Position.java
 */


package Basics;

/**
 *
 * @author gabriel
 */

///Position Represents a 2D point, and has methods to operate with them
public class Position {
    
    ///Default position. Origin.
    static final public Position ZERO = new Position(0);
    
    ///x coordinate
    private int x;
    //y coordinate
    private int y;
    
    /**
     * Default constructor
     */
    public Position(){
        this.setPos(0, 0);
    }
    
    /**
     * Constructor with one parameter
     * @param n This number will be the x and y coordinates
     */
    public Position(int n){
        this.setPos(n, n);
    }
    
    /**
     * Parameter given the two coordinates
     * @param _x x coordinate
     * @param _y y coordinate
     */
    public Position(int _x, int _y){
        this.setPos(_x, _y);
    }
    
    /**
     * Copy constructor
     * @param p Position that will be copied
     */
    public Position(Position p){
        x = p.getX();
        y = p.getY();
    }
    
    /**
     * Static method to subtract position
     * @param pos1 Minuend position
     * @param pos2 Subtrahend position
     * @return New position with the result of the operation
     */
    static public Position subtract(Position pos1, Position pos2){
        return new Position(pos1.getX()-pos2.getX(),pos1.getY()-pos2.getY());
    }
    
    /**
     * Static method to sum positions
     * @param pos1 Addend position
     * @param pos2 Addend position
     * @return New position with the result of the operation
     */
    static public Position sum(Position pos1, Position pos2){
        return new Position(pos1.getX()+pos2.getX(),pos1.getY()+pos2.getY());
    }
    
    /**
     * Static function to multiply a position with a number (integer)
     * @param pos1 Position to be multiplied
     * @param num Number that multiply the position
     * @return New position with the result of the operation
     */
    static public Position mul(Position pos1, int num){
        return new Position(pos1.getX()*num,pos1.getY()*num);
    }
    
    /**
     * Getter of x
     * @return 
     */
    public int getX(){
        return x;
    }
    
    /**
     * Getter of y
     * @return 
     */
    public int getY(){
        return y;
    }
    
    /**
     * Setter of x
     * @param _x 
     */
    public void setX(int _x){
        x = _x;
    }
    
    /**
     * Setter of y
     * @param _y 
     */
    public void setY(int _y){
        y = _y;
    }
    
    /**
     * Setter for a position
     * @param _x
     * @param _y 
     */
    public void setPos(int _x, int _y){
        setX(_x);
        setY(_y);
    }
    
    /**
     * Multiplication of the position with a integer
     * @param m
     * @return 
     */
    public Position mult(int m){
        x *= m;
        y *= m;
        
        return this;
    }
    
    /**
     * Division of the position with an integer
     * @param d
     * @return 
     */
    public Position div(int d){
        x /= d;
        y /= d;
        
        return this;
    }
    
    /**
     * Checks if the point is "inside" the square made by the two corners given
     * @param topRight
     * @param botLeft
     * @return 
     */
    public boolean inRange(Position topRight, Position botLeft){
        
        return(x >= topRight.getX() && x <= botLeft.getX() && 
               y >= topRight.getY() && y <= botLeft.getY());
    }
    
    /**
     * Checks if two points have the same coordinates
     * @param pos
     * @return 
     */
    public boolean isEqual(Position pos){
        return (getX() == pos.getX()) && (getY() == pos.getY());
    }
    
    /**
     * toString method for Position
     * format: (x,y)
     * @return 
     */
    @Override
    public String toString(){
        return "(" + Integer.toString(getX()) + ", " + Integer.toString(getY()) + ")";
    }
    
}
