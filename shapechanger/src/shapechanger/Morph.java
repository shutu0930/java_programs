package shapechanger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.*;

/**
 * The model part of the ShapeChanger.
 * The morphing algorithm @link Morph.normalise
 * is not perfected -- for complex enough shapes
 * the results is a strongly diminished shape compared with
 * the expected one (TODO: 25/04/2016 needs fixing later)
 *
 * <p>
 * Created with IntelliJ IDEA.
 * User: abx
 * Date: 21/04/2016
 * Time: 5:46 PM
 * Created for ass2 in package shapechanger
 * @version 1.0
 * @author abx
 * @author (your name and id)
 *
 * @see ShapeChanger
 * </p>
 */

public class Morph {

    public List<Point> points;

    public Morph(List<Point> points) {

        this.points = new ArrayList<>();
        this.points.addAll(points
                .stream()
                .map(p -> Point.makePoint(p.x, p.y))
                .collect(Collectors.toList())
        );
    }

    public Point anchorPoint() {
        return points.get(0);
    }
//==============================================================================
//This function used to find the Point with Max X coordibate and return the it
//==============================================================================        
    public Point findMaxXPoint(){
        double max = points.get(0).x;
        int maxNo=0;
       for(int i=0;i<points.size();i++){        
           Point p= Point.makePoint(points.get(i));
            if(max<p.x){
                max=p.x;
                maxNo=i;
            }     
       }
        return Point.makePoint(points.get(maxNo));   
    }
//==============================================================================
//This function used to find the Point with Min X coordibate and return the it
//==============================================================================  
    public Point findMinXPoint(){
        double min = points.get(0).x;
        int minNo=0;
       for(int i=0;i<points.size();i++){        
           Point p= Point.makePoint(points.get(i));
            if(min>p.x){
                min=p.x;
                minNo=i;
            }     
       }
        return Point.makePoint(points.get(minNo));   
    }
//==============================================================================
//This function used to find the Point with Max Y coordibate and return the it
//==============================================================================        
    public Point findMaxYPoint(){
        double max = points.get(0).y;
        int maxNo=0;
       for(int i=0;i<points.size();i++){        
           Point p= Point.makePoint(points.get(i));
            if(max<p.y){
                max=p.y;
                maxNo=i;
            }     
       }
       return Point.makePoint(points.get(maxNo));   
    }
//==============================================================================
//This function used to find the Point with Min Y coordibate and return the it
//==============================================================================      
    public Point findMinYPoint(){
        double min = points.get(0).y;
        int minNo=0;
       for(int i=0;i<points.size();i++){        
           Point p= Point.makePoint(points.get(i));
            if(min>p.y){
                min=p.y;
                minNo=i;
            }     
       }
        return Point.makePoint(points.get(minNo));   
    }
//==============================================================================
//This function used to calculate the distance between two points
//==============================================================================         
       public double calDistance(Point p1, Point p2){
           
          // Point p1=points.get(num1);
           //Point p2=points.get(num2);
           double temp=(p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);
           return sqrt(temp);
       
       }
//==============================================================================
//This function used to find the longest point far from top pot
//==============================================================================         
    public Point findCirCenter(){
        Point maxX=findMaxXPoint();
        //System.out.println(maxX);
        Point minX=findMinXPoint();
        //System.out.println(minX);
        Point maxY=findMaxYPoint();
        //System.out.println(maxY);
        Point minY=findMinYPoint();
        
        Point topPot=new Point(minX.x,minY.y);
        int longest=0;
        int n=points.size();
        double maxDiatance=0.0;
        for(int i=0;i<n;i++){
            
            double tempDistance=calDistance(topPot,points.get(i));
            if(maxDiatance<tempDistance){
                maxDiatance=tempDistance;
                longest=i;
            }
        
        
        }
        Point longestPot=Point.makePoint(points.get(longest));
        return longestPot;
    
    }
//==============================================================================
//This function used to find the three apex of the triangle
//============================================================================== 
    public List<Point> findTriAngles(){
        List<Point> keyPoints=new ArrayList<>();
        Point minX=findMinXPoint();
        Point minY=findMinYPoint();
        Point topPot=Point.makePoint(minX.x,minY.y);
        Point center=findCirCenter();
        double sideEqual=center.x+center.y;
        double sideLeft=topPot.x+center.y-center.x;
        double sideRight=topPot.y- center.y + center.x;
        
          Point leftAngle;
          Point rightAngle;

        leftAngle=Point.makePoint(topPot.x,sideEqual-topPot.x);
        //System.out.println(leftAngle);
        rightAngle=Point.makePoint(sideEqual-topPot.y,topPot.y);
        //System.out.println(rightAngle);
        
        keyPoints.add(topPot);
        keyPoints.add(leftAngle);
        keyPoints.add(rightAngle);
        return   keyPoints;

    }
//==============================================================================
//This function used to generate the Morph of Triangle
//============================================================================== 
    public Morph triangleMorph(){
        
          if (points.size() < 3)
            return this;

        List<Point> newPoints = new ArrayList<>();
        
        
        List<Point> keyPoints=findTriAngles();
       
       double horizonalSide=calDistance(keyPoints.get(0),keyPoints.get(2));
       double verticalSide=calDistance(keyPoints.get(0),keyPoints.get(1));
       
       //double slopeSide=calDistance(keyPoints.get(1),keyPoints.get(2));
       

        int n = points.size();
        if(n%3!=0){
            int number=n/3;
            n=3*(number);         
        }
        int difference=points.size()-n;
        int sideNo=n/3;
        //int partitionNo=n/3;
        for(int i=0;i<sideNo;i++){
            
            double xCoordinate=keyPoints.get(0).x+i*horizonalSide/sideNo;
            double yCoordinate=keyPoints.get(0).y;
            
//            if(xCoordinate>=keyPoints.get(2).x-horizonalSide/sideNo){
//                break;
//            }
            newPoints.add(Point.makePoint(xCoordinate,yCoordinate));
             if(i==1){
                for(int j=0;j<difference;j++){
                  double xCoord=keyPoints.get(0).x+i*horizonalSide/sideNo+i*horizonalSide/sideNo/200;
                  double yCoord=keyPoints.get(0).y;
                  newPoints.add(Point.makePoint(xCoord,yCoord));
            
                }
            
            } 

        }
        for(int i=0;i<sideNo;i++){
            
            double xCoordinate=keyPoints.get(2).x-i*horizonalSide/sideNo;
            double yCoordinate=keyPoints.get(2).y+i*verticalSide/sideNo;
//            if(yCoordinate>=keyPoints.get(1).y+horizonalSide/sideNo|| xCoordinate<=keyPoints.get(1).x-horizonalSide/sideNo){
//                break;
//            }
            newPoints.add(Point.makePoint(xCoordinate,yCoordinate));

        }
        for(int i=0;i<sideNo;i++){
            
           double xCoordinate=keyPoints.get(1).x;
           double yCoordinate=keyPoints.get(1).y-i*verticalSide/sideNo;
//            if(yCoordinate<=keyPoints.get(0).y+horizonalSide/sideNo){
//                break;
//            }
            newPoints.add(Point.makePoint(xCoordinate,yCoordinate));
        }
        //System.out.printf("Morph radius %.2f%n", r);
        return new Morph(newPoints);

    }
//==============================================================================
//This function used to generate the Morph of Rectangle
//============================================================================== 
    public Morph rectangleMorph(){
        if (points.size() < 3)
            return this;

        List<Point> newPoints = new ArrayList<>();
        //Point centerPoint=getCenter();
        //double mx = centerPoint.x;
       // double my = centerPoint.y;
        
        List<Point> keyPoints=getKeyPoints();
        Point maxX=findMaxXPoint();
        //System.out.println(maxX);
        Point minX=findMinXPoint();
        //System.out.println(minX);
        Point maxY=findMaxYPoint();
        //System.out.println(maxY);
        Point minY=findMinYPoint();
       
//        double width=calDistance(maxX,minX);
//        double height=calDistance(maxY,minY);
        double width=maxX.x-minX.x;
        double height=maxY.y-minY.y;
        
        int n = points.size();
        if(n%4!=0){
            int number=n/4;
            n=4*(number);         
        }
        
        int difference=points.size()-n;
        int sideNo=n/4;
        //int partitionNo=n/4;
        for(int i=0;i<sideNo;i++){
            
            double xCoordinate=minX.x+i*width/sideNo;
            double yCoordinate=maxY.y;
            
//            if(xCoordinate>=maxX.x-width/partitionNo){
//                break;
//            }
            newPoints.add(Point.makePoint(xCoordinate,yCoordinate));

        }
        for(int i=0;i<sideNo;i++){
            
            double yCoordinate=maxY.y-i*height/sideNo;
            double xCoordinate=maxX.x;
//            if(yCoordinate<=minY.y+height/partitionNo){
//                break;
//            }
            newPoints.add(Point.makePoint(xCoordinate,yCoordinate));

        }
        for(int i=0;i<sideNo;i++){
            
            double xCoordinate=maxX.x-i*width/sideNo;
            double yCoordinate=minY.y;
//            if(xCoordinate<=minX.x+width/partitionNo){
//                break;
//            }

            newPoints.add(Point.makePoint(xCoordinate,yCoordinate));
            if(i==2){
                for(int j=0;j<difference;j++){
                  double xCoord=maxX.x-i*width/sideNo-i*width/sideNo/20;
                  double yCoord=minY.y;
                  newPoints.add(Point.makePoint(xCoord,yCoord));
            
                }
            
            } 
            
        }
        for(int i=0;i<sideNo;i++){
            
            double yCoordinate=minY.y+i*height/sideNo;
            double xCoordinate=minX.x;
//            if(yCoordinate>=maxY.y-height/partitionNo){
//                break;
//            }
            newPoints.add(Point.makePoint(xCoordinate,yCoordinate));
        }

        //System.out.printf("Morph radius %.2f%n", r);
        return new Morph(newPoints);

    }
//==============================================================================
//This function used to generate the Morph of Advanced Triangle
//============================================================================== 
    public Morph advancedRectangle(){
        Morph newMorph=rectangleMorph();
        Point maxX=findMaxXPoint();
        //System.out.println(maxX);
        Point minX=findMinXPoint();
        //System.out.println(minX);
        Point maxY=findMaxYPoint();
        //System.out.println(maxY);
        Point minY=findMinYPoint();
        Point center=Point.makePoint(0.5*(minX.x+maxX.x), 0.5*(minY.y+maxY.y));
        double mx=center.x;
        double my=center.y;
        double radius=0.5*calDistance(Point.makePoint(minX.x,minY.y),Point.makePoint(maxX.x,maxY.y));
//        double width=calDistance(maxX,minX);
//        double height=calDistance(maxY,minY);
        //double width=maxX.x-minX.x;
        //double height=maxY.y-minY.y;
        
        
        List<Point> newPoints = new ArrayList<>();
        List<Point> newPoints2 = new ArrayList<>();
        List<Point> keyPoints = new ArrayList<>();
        keyPoints=calMaximalDistance();
        Point p1=keyPoints.get(0);
        Point p2=keyPoints.get(1);
        double XCoord=p2.x-p1.x;
        double YCoord=p2.y-p1.y;
        double transTheta=atan2(YCoord,XCoord);
        //System.out.println("Angle: "+ transTheta);
        newPoints.addAll(points);
        int size=newPoints.size();
//        for(int i=0;i<size;i++){
//            double xValue=newPoints.get(i).x;
//            double yValue=newPoints.get(i).y;
//            double XCoordTemp=xValue-center.x;
//            double YCoordTemp=yValue-center.y;
//            double bate=atan2(YCoordTemp,XCoordTemp);
//            double length=calDistance(newPoints.get(i),center);
//            double Xcorodinate=mx+length*cos(transTheta+bate);
//            double Ycorodinate=my+length*sin(transTheta+bate);
//            newPoints2.add(Point.makePoint(Xcorodinate, Ycorodinate));
//            System.out.println(newPoints2.get(i));
//
//        }
        double maxLength=0.0;
        double minLength=2.0;
        Point pLong=Point.makePoint(newPoints.get(0));
        Point pShort=Point.makePoint(newPoints.get(0));
        for(int i=0;i<size;i++){
            double xValue=newPoints.get(i).x;
            double yValue=newPoints.get(i).y;
            double distance= abs(xValue*tan(transTheta)-yValue)/(sqrt(tan(transTheta)*tan(transTheta)+1));
            if(distance>maxLength){
                maxLength=distance;
                pLong=Point.makePoint(newPoints.get(i));
            }
            if(distance<minLength){
                minLength=distance;
                
                pShort=Point.makePoint(newPoints.get(i));
            
            }
        
        }
         List<Point> anglePoints = new ArrayList<>();
         keyPoints.add(pShort);
         keyPoints.add(pLong);
         double x1=keyPoints.get(0).x;
         double y1=keyPoints.get(0).y;
         
         double x2=keyPoints.get(1).x;
         double y2=keyPoints.get(1).y;
         
         double x3=keyPoints.get(2).x;
         double y3=keyPoints.get(2).y;
         
         double x4=keyPoints.get(3).x;
         double y4=keyPoints.get(3).y;
         double slope=tan(transTheta);
         double revSlope=-1.0/slope;
          newPoints2.clear();
         //first point
         double XCoordinate1=(x1*revSlope+y3-slope*x3-y1)/(revSlope-slope);
         double YCoordinate1=y3+slope*(XCoordinate1-x3);
         newPoints2.add(Point.makePoint(XCoordinate1, YCoordinate1));
         for(int i=0;i<6;i++){
            double XCoordinate=XCoordinate1+i*0.1;
            double YCoordinate=y3+slope*(XCoordinate-x3);
            newPoints2.add(Point.makePoint(XCoordinate, YCoordinate));
         
         }
         
         
         double XCoordinate2=(x2*revSlope+y3-slope*x3-y2)/(revSlope-slope);
         double YCoordinate2=y3+slope*(XCoordinate2-x3);
          newPoints2.add(Point.makePoint(XCoordinate2, YCoordinate2));
          for(int i=0;i<6;i++){
            double XCoordinate=XCoordinate2+i*0.1;
            double YCoordinate=y2+revSlope*(XCoordinate-x2);
            newPoints2.add(Point.makePoint(XCoordinate, YCoordinate));
         
         }
         
         double XCoordinate3=(x2*revSlope+y4-slope*x4-y2)/(revSlope-slope);
         double YCoordinate3=y4+slope*(XCoordinate3-x4);
          newPoints2.add(Point.makePoint(XCoordinate3, YCoordinate3));
           for(int i=0;i<6;i++){
            double XCoordinate=XCoordinate3+i*0.1;
            double YCoordinate=y4+slope*(XCoordinate-x4);
            newPoints2.add(Point.makePoint(XCoordinate, YCoordinate));
         
         }
         
         double XCoordinate4=(x1*revSlope+y4-slope*x4-y1)/(revSlope-slope);
         double YCoordinate4=y4+slope*(XCoordinate4-x4);
         newPoints2.add(Point.makePoint(XCoordinate4, YCoordinate4));
         
         for(int i=0;i<6;i++){
            double XCoordinate=XCoordinate4+i*0.1;
            double YCoordinate=y1+revSlope*(XCoordinate-x1);
            newPoints2.add(Point.makePoint(XCoordinate, YCoordinate));
         
         }
         
         
         
        
        
        
        
        
        
        
    
         return new Morph(newPoints2);
    }
//==============================================================================
//This function used to find the two points which has longest diatance
//============================================================================== 
    public List<Point> calMaximalDistance(){
         List<Point> keyPoints=new ArrayList<>();
         double maximalDis=0.0;
         Point p1;
         Point p2;
         int size=points.size();
         for(int i=0;i<size;i++){
             p1=Point.makePoint(points.get(i));
             for(int j=i+1;j<size;j++){
                 p2=Point.makePoint(points.get(j));
                 double distance=calDistance(p1,p2);
                 if(distance>maximalDis){
                    maximalDis=distance;
                    keyPoints.clear();
                    keyPoints.add(p1);
                    keyPoints.add(p2);
                 
                 }
             
             }
         
         
         }
         return keyPoints;
    
    
    
    }
//==============================================================================
//This function used to generate the Morph of Ellipse
//==============================================================================       
    public Morph ellipseMorph(){
         if (points.size() < 3)
            return this;

        List<Point> newPoints = new ArrayList<>();
        Point centerPoint=getCenter();
        double mx = centerPoint.x;
        double my = centerPoint.y;
        
       // List<Point> keyPoints=getKeyPoints();
        Point maxX=findMaxXPoint();
        //System.out.println(maxX);
        Point minX=findMinXPoint();
        //System.out.println(minX);
        Point maxY=findMaxYPoint();
        //System.out.println(maxY);
        Point minY=findMinYPoint();
       
//        double width=calDistance(maxX,minX);
//        double height=calDistance(maxY,minY);
        double width=maxX.x-minX.x;
        double height=maxY.y-minY.y;
        double sideA=width/2.0;
        double sideB=height/2.0;
 
       // int n = points.size();
        
        double firstX = points.get(0).x - mx;
        double firstY = points.get(0).y - my;
        double theta0 = /*0.5*PI*/ -atan2(firstY, firstX);
        double secondX = points.get(1).x - mx;
        double secondY = points.get(2).y - my;
        int orient = (int) signum(firstX * secondY - firstY * secondX);
        int n = points.size();
        double r = radius();
        double theta;

        for (int i = 0; i < n; i++) {
            theta = orient * 2 * PI * i / n - theta0;
            newPoints.add(Point.makePoint(mx + sideA * cos(theta),
                    my + sideB * sin(theta)));
        }

        //System.out.printf("Morph radius %.2f%n", r);
        return new Morph(newPoints);
    
    
    
    }
       
//==============================================================================
//This function used to generate the Morph of Polygon
//==============================================================================     
    public Morph PolygonMorph(int sideNumber){
     if (points.size() < 3)
            return this;
     if(sideNumber< 3)
         return this;

        List<Point> newPoints = new ArrayList<>();
        Point centerPoint=findCenter();
        double mx = centerPoint.x;
        double my = centerPoint.y;
        double firstX = points.get(0).x - mx;
        double firstY = points.get(0).y - my;
        //double theta0 = /*0.5*PI*/ -atan2(firstY, firstX);
        double theta0 =0.5*PI;
        double secondX = points.get(1).x - mx;
        double secondY = points.get(2).y - my;
        int orient = (int) signum(firstX * secondY - firstY * secondX);
        int n = points.size();
        
        //if(n%sideNumber!=0){
            int temp=n/sideNumber;
            n=sideNumber*(temp+1);
            //System.out.println("points number: "+n);
        
        //}
        
        int sidePointNo=n/sideNumber;
        int difference=n-points.size();
        double r = getRadius();
      
        double eachAngle= 2.0*PI/(sideNumber*1.0);
        double outsideR=r/cos(eachAngle/2.0);
        double equalAngle=(PI-eachAngle)/2.0;
        double SideC=outsideR*sqrt(2*(1-cos(eachAngle)));
        double eachSideC=SideC/(sidePointNo*1.0);
        
       
        //double sideLength=sqrt(outsideR*outsideR+eachSideC*eachSideC-2.0*eachSideC*outsideR*cos())
        
        double theta;
        double temptheta;

        for (int i = 0; i < sideNumber; i++) {
            theta = orient * 2 * PI * i / sideNumber - theta0;
            temptheta=theta;
            newPoints.add(Point.makePoint(mx + outsideR * cos(theta),
                    my + outsideR * sin(theta)));
          // if(i!=sideNumber-1){
            for(int j=1;j<sidePointNo;j++){
                theta = orient * 2 * PI * (j+i*sidePointNo) / n - theta0;
                double tempSideC=eachSideC*j;
                double tempLength=outsideR*outsideR + tempSideC*tempSideC;
                double sideLength= sqrt(tempLength-2.0*tempSideC*outsideR*cos(equalAngle));
                newPoints.add(Point.makePoint(mx +sideLength * cos(theta),
                    my + sideLength * sin(theta)));

            }
 
        }
        
        
        for(int k=0;k<difference;k++){
        
            if(k<=sideNumber){
            
             newPoints.remove(k*sidePointNo+1);
             //newPoints.remove(k*sidePointNo+2);
            
            }
   
        }
        //System.out.printf("Morph radius %.2f%n", r);

        return new Morph(newPoints);

    }
//==============================================================================
//This function used to generate the Morph of circle with the same length of side
//==============================================================================  
    public Morph roundMorph() {
        if (points.size() < 3)
            return this;

        List<Point> newPoints = new ArrayList<>();
        double mx = medX();
        double my = medY();
        double firstX = points.get(0).x - mx;
        double firstY = points.get(0).y - my;
        double theta0 = /*0.5*PI*/ -atan2(firstY, firstX);
        double secondX = points.get(1).x - mx;
        double secondY = points.get(2).y - my;
        int orient = (int) signum(firstX * secondY - firstY * secondX);
        int n = points.size();
        double r = radius();
        double theta;

        for (int i = 0; i < n; i++) {
            theta = orient * 2 * PI * i / n - theta0;
            newPoints.add(Point.makePoint(mx + r * cos(theta),
                    my + r * sin(theta)));
        }
        //System.out.printf("Morph radius %.2f%n", r);
        return new Morph(newPoints);
    }
//==============================================================================
//This function used to generate the Morph of excircle
//==============================================================================    
    public Morph circleMorph() {
        if (points.size() < 3)
            return this;

        List<Point> newPoints = new ArrayList<>();
        Point centerPoint=findCenter();
        double mx = centerPoint.x;
        double my = centerPoint.y;
        double firstX = points.get(0).x - mx;
        double firstY = points.get(0).y - my;
        double theta0 = /*0.5*PI*/ -atan2(firstY, firstX);
        double secondX = points.get(1).x - mx;
        double secondY = points.get(2).y - my;
        int orient = (int) signum(firstX * secondY - firstY * secondX);
        int n = points.size();
        double r = getRadius();
        double theta;

        for (int i = 0; i < n; i++) {
            theta = orient * 2 * PI * i / n - theta0;
            newPoints.add(Point.makePoint(mx + r * cos(theta),
                    my + r * sin(theta)));
        }
        //System.out.printf("Morph radius %.2f%n", r);
        return new Morph(newPoints);
    }

    public static Morph normalize(Morph source, int pointLimit) {
        if (source.points.size() < pointLimit)
            throw new AssertionError("Source oneMorph has less points than pointLimit");

        int sourcePoints = source.points.size();
        int excess = sourcePoints - pointLimit;
        int gap = sourcePoints / (excess + 1);
        List<Point> filteredPoints =
                IntStream.range(0, source.points.size())
                        .filter(i -> (i+1) % gap != 0 || i >= gap * excess)
                        .mapToObj(source.points::get)
                        .collect(Collectors.toList());

        System.out.printf("source %d, limit %d and normalised morph %d%n",
                source.points.size(), pointLimit, filteredPoints.size());

        return new Morph(filteredPoints);
    }
//==============================================================================
//This function used to calculate the radius of a excircle
//============================================================================== 
    public double getRadius(){
        
        List<Point> keyPoints=calMaximalDistance();
        Point p1=keyPoints.get(0);
        Point p2=keyPoints.get(1);
        double distance=calDistance(p1,p2);
        return distance/2.0;

    }

    public List<Point> getKeyPoints(){
        
        List<Point> keyPoints=new ArrayList<>();
        Point maxX=findMaxXPoint();
        //System.out.println(maxX);

        Point minX=findMinXPoint();
        //System.out.println(minX);

        Point maxY=findMaxYPoint();
        //System.out.println(maxY);

        Point minY=findMinYPoint();
        //System.out.println(minY);
        
        //System.out.println("=====================================");

        double distance1=calDistance(maxX, minX);
        double distance2=calDistance(maxY, minY);
         //Point centerPoint;
        if(distance1<distance2){

        keyPoints.add(maxY);
        keyPoints.add(minY);
        keyPoints.add(maxX);
        keyPoints.add(minX);
//centerPoint=new Point((maxY.x+minY.x)/2.0,(maxY.y+minY.y)/2.0);
           
          
        }else{
           keyPoints.add(maxX);
           keyPoints.add(minX);
           keyPoints.add(maxY);
           keyPoints.add(minY);
            //centerPoint=new Point((maxX.x+minX.x)/2.0,(maxX.y+minX.y)/2.0);

        }
    
        return keyPoints;
    
    }
//==============================================================================
//This function used to find the center of bounding box
//==============================================================================   
    public Point getCenter(){
    List<Point> keyPoints=getKeyPoints();
         
        Point maxX=findMaxXPoint();
        //System.out.println(maxX);
        Point minX=findMinXPoint();
        //System.out.println(minX);
        Point maxY=findMaxYPoint();
        //System.out.println(maxY);
        Point minY=findMinYPoint();
       
        Point centerPoint=new Point((maxX.x+minX.x)/2.0,(minY.y+maxY.y)/2.0);
  
        return centerPoint;
    
    
    }
    
//=============================================================
//to find center of the circumcircle of the Morph
//============================================================
    public Point findCenter(){
        
         List<Point> keyPoints=calMaximalDistance();
         
         Point p1=keyPoints.get(0);
         Point p2=keyPoints.get(1);
         Point centerPoint=new Point((p1.x+p2.x)/2.0,(p1.y+p2.y)/2.0);
  
        return centerPoint;

    }
    private double medX() {
        double x = 0;
        for (Point p : points) {
            x += p.x;
        }
        return x / points.size();
    }

    private double medY() {
        double y = 0;
        for (Point p : points) {
            y += p.y;
        }
        return y / points.size();
    }

    private double area() {
        double res = 0;
        if (points.size() < 3)
            return res;
        double cx = medX();
        double cy = medY();
        double dx1, dx2, dy1, dy2;
        for (int i = 0; i < points.size() - 1; i++) {
            dx1 = points.get(i).x - cx;
            dy1 = points.get(i).y - cy;
            dx2 = points.get(i + 1).x - cx;
            dy2 = points.get(i + 1).y - cy;
            res += 0.5 * abs(dx1 * dy2 - dx2 * dy1);
        }
        dx1 = points.get(points.size() - 1).x - cx;
        dy1 = points.get(points.size() - 1).y - cy;
        dx2 = points.get(0).x - cx;
        dy2 = points.get(0).y - cy;
        res += 0.5 * abs(dx1 * dy2 - dx2 * dy1);

        return res;
    }

    private double radius() {
        return sqrt(area() / PI);
    }
}