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
     * @param x x coordinate
     * @param y y coordinate
     */
    public Position(int x, int y){
        this.setPos(x, y);
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
     * @return x coordinate
     */
    public int getX(){
        return x;
    }
    
    /**
     * Getter of y
     * @return y coordinate
     */
    public int getY(){
        return y;
    }
    
    /**
     * Setter of x
     * @param x New x coordinate
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     * Setter of y
     * @param y New y coordinate
     */
    public void setY(int y){
        this.y = y;
    }
    
    /**
     * Setter for a position
     * @param x New x coordinate
     * @param y New y coordinate
     */
    public void setPos(int x, int y){
        setX(x);
        setY(y);
    }
    
    /**
     * Multiplication of the position with a integer.
     * Be careful, this method MODIFY the point.
     * @param m Number that multiply the position
     * @return This position multiplied
     * 
     */
    public Position mult(int m){
        x *= m;
        y *= m;
        
        return this;
    }
    
    /**
     * Division of the position with an integer
     * Be careful, this method MODIFY the point.
     * @param d Number that divides the position
     * @return This position multiplied
     */
    public Position div(int d){
        x /= d;
        y /= d;
        
        return this;
    }
    
    /**
     * Checks if the point is "inside" the square made by the two corners given
     * @param topRight Top right corner of the "square", included
     * @param botLeft  Bottom left corner of the "square"
     * @return Returns true if the position is in the "square" and false in
     * other case.
     */
    public boolean inRange(Position topRight, Position botLeft){
        
        return(x >= topRight.getX() && x <= botLeft.getX() && 
               y >= topRight.getY() && y <= botLeft.getY());
    }
    
    /**
     * Checks if two points have the same coordinates
     * @param pos Position to be compared
     * @return True if coordinates are the same, false if not
     */
    public boolean isEqual(Position pos){
        return (getX() == pos.getX()) && (getY() == pos.getY());
    }
    
    /**
     * toString method for Position
     * 
     * @return A string with format (x,y)
     */
    @Override
    public String toString(){
        return "(" + Integer.toString(getX()) + ", " + Integer.toString(getY()) + ")";
    }
    
}
