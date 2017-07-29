package shapechanger;

/**
 * Created with IntelliJ IDEA.
 * User: abx
 * Date: 21/04/2016
 * Time: 5:42 PM
 * Created for ass2 in package shapechanger
 * @version 1.0
 * @author abx
 * @author (your name and id)
 */

class Point {

    public final double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public static Point makePoint (Point p){
        return new Point(p.x, p.y);
    
    }
    public static Point makePoint(double x, double y) {
        return new Point(x, y);
    }
    
    public double getX(){
        return this.x;
    
    }
    
    public double getY(){
        return this.y;
    }
    
    @Override
    public String toString(){
        return "The point cooridinate:  "+this.x+"  "+this.y+"\n";
    }
}
