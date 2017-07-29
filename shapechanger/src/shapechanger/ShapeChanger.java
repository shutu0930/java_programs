package shapechanger;


import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;


/**
 * The skeleton code for comp6700.2016 assignment 2.
 * Draws free-hand shapes and morphs one into another.
 *
 * Created with IntelliJ IDEA.
 * User: abx
 * Date: 21/04/2016
 * Time: 5:46 PM
 * Created for ass2 in package shapechanger
 *
 * @author abx
 * @author (your name and id)
 * @version 1.0
 *
 * @see Morph
 */

public class ShapeChanger extends Application {

    private static final double useOfScreenFactor = 0.8;
    private final Desktop desktop = Desktop.getDesktop();

    private static Map<Predicate<KeyEvent>, Consumer<KeyEvent>> keyEventSelectors =
            new HashMap<>();
    // these are for smoother-like part, for drawing and splotching
    private final Path onePath = new Path();
    private final Path twoPath = new Path();
    
    private final Path ptemp1 = new Path();// for saving transfered shape1 path
    private  Morph Mtemp1;
    private final Path ptemp2 = new Path();// for saving transfered shape2 path
    private  Morph Mtemp2;
    private final Path ptemp3 = new Path();// for saving transfered shape3 path
    private  Morph Mtemp3;
    

    private final ArrayList<Point> points = new ArrayList<>();
    private Scale scale;
    private Morph oneMorph;
    private Morph twoMorph;
    private Point2D anchorPt;
    private Point currentPoint;
    private Point lastPoint;
    private State state = State.CLEAR; // at the start there is no paths
    private ShapeState shapeState = ShapeState.NONE; // at the start there is no shape requirement
    private State selectMode =State.UNSELECT; // at the start it is unselected mode
    private PathSelection pathSelect=PathSelection.NOTHING; // at the start there is no shape selected
    private boolean normalised = false;
    private int PolyNum=0;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * override start method which creates the scene
     * and all nodes and shapes in it (main window only),
     * and redefines how the nodes react to user inputs
     * and other events;
     *
     * @param primaryStage Stage (the top level container)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));

        primaryStage.setTitle("Shape Changing Objects");
        final Group root = new Group();
        
       
       
        //======================================
        
        MenuBar menuBar=new MenuBar();
        
        //Menu File
        Menu menuFile=new Menu("File");
        
        MenuItem itemSave=new MenuItem("Save");

        itemSave.setOnAction((ActionEvent t) -> {
            
            if(state == State.CLEAR){
                messageBox("No shape found! Please draw shapes to save!", "Warning", null);
            }
            if(state == State.ONE){
                messageBox("Not enough shapes found! Please draw two shapes to save!", "Warning", null);
            } 
            if(state == State.BOTH){
                  
                try{
                final FileChooser fileChooser = new FileChooser();     
                fileChooser.setTitle("Save File");
                File file = fileChooser.showSaveDialog(primaryStage);
                if (file != null) {
                    //openFile(file);
                        writeFile(file);
                    }

                }catch(Exception exe){
                    System.out.println("save error!");
                    messageBox("save Error", "Exception", null);
                }

             }
        });
        
        
        
        MenuItem itemOpen=new MenuItem("Open");
        
         itemOpen.setOnAction((ActionEvent t) -> {
            
               try{
                final FileChooser fileChooser = new FileChooser();    
                fileChooser.setTitle("Open File");
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    //openFile(file);
                        readFile(file);
                    }

                }catch(Exception exe){
                    System.out.println("open error!");
                    messageBox("Open Error", "Exception", null);

                }

        });
        
        
         
        MenuItem itemQuit=new MenuItem("Quit");
        
        itemQuit.setOnAction((ActionEvent t) -> {
            if(state != State.CLEAR){
              
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
              alert.setTitle("Reminder");
              alert.setHeaderText(null);
              alert.setContentText("Do you want to save the shapes?");

             Optional<ButtonType> answer = alert.showAndWait();
             if (answer.get() == ButtonType.OK){
                 try{
                 FileChooser fileChooser = new FileChooser();
                 fileChooser.setTitle("Save File");
                 File file = fileChooser.showSaveDialog(primaryStage);
                 if (file != null) {
                      writeFile(file);
                 }
             }catch(Exception exe){
                  System.out.println("save error! ");
                  messageBox("save Error!", "Exception", null);
             }
            }
             Platform.exit(); // better, JavaFX's way
           }            

        });
        
        menuFile.getItems().addAll(itemOpen,itemSave,itemQuit);
         
        //Menu Edit
        Menu menuEdit=new Menu("Edit");
        MenuItem itemSelect=new MenuItem("Select");
        itemSelect.setOnAction((ActionEvent t) -> {       
            //shapeState=ShapeState.TRIANGLE;
            selectMode=State.SELECT;

        });
        
        MenuItem itemUnSelect=new MenuItem("Unselect & Clear");
         itemUnSelect.setOnAction((ActionEvent t) -> {       
            //shapeState=ShapeState.TRIANGLE;
            selectMode=State.UNSELECT;
            state = State.CLEAR;
            if (state == State.CLEAR) {
                //state = State.ONE;
                normalised = false;
                // clear both paths
                onePath.getElements().clear();
                onePath.setOpacity(1);
                onePath.setFill(null);
                twoPath.getElements().clear();
                twoPath.setOpacity(1);
                twoPath.setFill(null);
                // start collecting points into path one
                ptemp1.getElements().clear();
                ptemp1.setOpacity(1);
                ptemp1.setFill(null);
                ptemp2.getElements().clear();
                ptemp2.setOpacity(1);
                ptemp2.setFill(null);
                
                
                
//                onePath.setStrokeWidth(3);
//                onePath.setStrokeDashOffset(0.7);
//                onePath.setStroke(Color.BLACK);
//                onePath.getElements()
//                        .add(new MoveTo(anchorPt.getX(), anchorPt.getY()));
            }
            
            
            
            
            
            // points.clear();
        });
         menuEdit.getItems().addAll(itemSelect,itemUnSelect);
        
        //Menu Morph
        Menu menuMorph=new Menu("Morph");
        
//==============================================================================
//              Triangle transferation
//==============================================================================
        MenuItem itemTriangle=new MenuItem("Triangle");
        itemTriangle.setOnAction((ActionEvent t) -> {       
            shapeState=ShapeState.TRIANGLE;
            //selectMode=State.SELECT;
 
        if(state==State.BOTH && selectMode==State.SELECT){
             //select the first Path
            if(pathSelect==PathSelection.PATHONE){
                onePath.setStrokeWidth(2);
                onePath.setFill(Color.LIGHTSLATEGRAY);
                normalised = false;

                   Morph temp1= oneMorph.triangleMorph();
                   //Path pathTemp2=drawMorph(temp2);   
                   drawMorph(temp1,1);   
                   //normalised = false;
                  //  if (!normalised) {
                        System.out.println("Triangle Normalising before morphing can be attempted");
                        //normalisePaths(onePath, ptemp1, oneMorph, temp1);
                        normalisePaths(onePath, ptemp1, oneMorph, temp1);
                  //  }
                    final Timeline timeline1 = makeTimeline(onePath, ptemp1);
                     timeline1.play();    

                //}
                
            } else if(pathSelect==PathSelection.PATHTWO){//select the second Path
                twoPath.setStrokeWidth(2);
                twoPath.setFill(Color.LIGHTSLATEGRAY);
                normalised = false;
                //if(shapeState==ShapeState.TRIANGLE){

                   Morph temp2= twoMorph.triangleMorph();
                  
                   drawMorph(temp2,2);   
                   //normalised = false;
                    //if (!normalised) {
                        System.out.println("Triangle Normalising before morphing can be attempted");
                        //normalisePaths(onePath, ptemp1, oneMorph, temp1);
                        normalisePaths(twoPath, ptemp2, twoMorph, temp2);
                    //}
                    //Timeline timeline1 = makeTimeline(onePath, ptemp1);
                    final Timeline timeline2 = makeTimeline(twoPath, ptemp2);
                    timeline2.play();    
            }

           }

        });
        MenuItem itemEllipse=new MenuItem("Ellipse");
//==============================================================================
//              Ellipse transferation
//==============================================================================
        itemEllipse.setOnAction((ActionEvent t) -> {   
            shapeState=ShapeState.ELLIPSE;
            //selectMode=State.SELECT;
             if(state==State.BOTH && selectMode==State.SELECT){
               
            if(pathSelect==PathSelection.PATHONE){//select the first Path
                onePath.setStrokeWidth(2);
                onePath.setFill(Color.LIGHTSLATEGRAY);
                normalised = false;

                   Morph temp1= oneMorph.ellipseMorph();
                   //Path pathTemp2=drawMorph(temp2);   
                   drawMorph(temp1,1);   
                   //normalised = false;
                  //  if (!normalised) {
                        System.out.println("Ellipse Normalising before morphing can be attempted");
                        //normalisePaths(onePath, ptemp1, oneMorph, temp1);
                        normalisePaths(onePath, ptemp1, oneMorph, temp1);
                  //  }
                    final Timeline timeline1 = makeTimeline(onePath, ptemp1);
                     timeline1.play();    

                //}

            } else if(pathSelect==PathSelection.PATHTWO){// select the second Path
                twoPath.setStrokeWidth(2);
                twoPath.setFill(Color.LIGHTSLATEGRAY);
                normalised = false;
                //if(shapeState==ShapeState.TRIANGLE){

                   Morph temp2= twoMorph.ellipseMorph();
                  
                   drawMorph(temp2,2);   
                   //normalised = false;
                    //if (!normalised) {
                        System.out.println("Ellipse Normalising before morphing can be attempted");
                        //normalisePaths(onePath, ptemp1, oneMorph, temp1);
                        normalisePaths(twoPath, ptemp2, twoMorph, temp2);
                    //}
                    //Timeline timeline1 = makeTimeline(onePath, ptemp1);
                    final Timeline timeline2 = makeTimeline(twoPath, ptemp2);             
                    timeline2.play();    
            }

           }

        });
         
        MenuItem itemRectangle=new MenuItem("Rectangle");
//==============================================================================
//              Rectangle transferation
//==============================================================================
        itemRectangle.setOnAction((ActionEvent t) -> {       
            shapeState=ShapeState.RECTANGLE;
            //selectMode=State.SELECT;
             if(state==State.BOTH && selectMode==State.SELECT){
                
            if(pathSelect==PathSelection.PATHONE){
                onePath.setStrokeWidth(2);
                onePath.setFill(Color.LIGHTSLATEGRAY);
                normalised = false;

                   Morph temp1= oneMorph.rectangleMorph();
                   //Path pathTemp2=drawMorph(temp2);   
                   drawMorph(temp1,1);   
                   //normalised = false;
                  //  if (!normalised) {
                        System.out.println("Rectangle Normalising before morphing can be attempted");
                        //normalisePaths(onePath, ptemp1, oneMorph, temp1);
                        normalisePaths(onePath, ptemp1, oneMorph, temp1);
                  //  }
                    final Timeline timeline1 = makeTimeline(onePath, ptemp1);
                     timeline1.play();    

                //}

            } else if(pathSelect==PathSelection.PATHTWO){
                twoPath.setStrokeWidth(2);
                twoPath.setFill(Color.LIGHTSLATEGRAY);
                normalised = false;
                //if(shapeState==ShapeState.TRIANGLE){

                   Morph temp2= twoMorph.rectangleMorph();
                   drawMorph(temp2,2);   
                   //normalised = false;
                    //if (!normalised) {
                        System.out.println("Rectangle Normalising before morphing can be attempted");
                        //normalisePaths(onePath, ptemp1, oneMorph, temp1);
                        normalisePaths(twoPath, ptemp2, twoMorph, temp2);
                    //}
                    final Timeline timeline2 = makeTimeline(twoPath, ptemp2);
                    timeline2.play();    
            }

           }
        });
       
        MenuItem itemPolygon=new MenuItem("Polygon");
//==============================================================================
//              Polygon transferation
//==============================================================================
        itemPolygon.setOnAction((ActionEvent t) -> {     
           shapeState=ShapeState.POLYGON;
           //selectMode=State.SELECT;
            TextInputDialog dialogPoly = new TextInputDialog();
            dialogPoly.setTitle("Polygon Setting");
            dialogPoly.setHeaderText("Assign the number of sides");
            dialogPoly.setContentText("Please enter the number:");
            Optional<String> setting = dialogPoly.showAndWait();
            
            setting.ifPresent(name -> System.out.println("You assigned" + name));
            PolyNum = parseInt(setting.get());
            System.out.println("Polygon Setting: "+PolyNum);
            
            if(state==State.BOTH && selectMode==State.SELECT){
             
            if(pathSelect==PathSelection.PATHONE){
                onePath.setStrokeWidth(2);
                onePath.setFill(Color.LIGHTSLATEGRAY);
                normalised = false;

                   Morph temp1= oneMorph.PolygonMorph(PolyNum);
                   //Path pathTemp2=drawMorph(temp2);   
                   drawMorph(temp1,1);   
                   //normalised = false;
                  //  if (!normalised) {
                        System.out.println("Polygon Normalising before morphing can be attempted");
                        //normalisePaths(onePath, ptemp1, oneMorph, temp1);
                        normalisePaths(onePath, ptemp1, oneMorph, temp1);
                  //  }
                    final Timeline timeline1 = makeTimeline(onePath, ptemp1);
                     timeline1.play();    

                //}

            } else if(pathSelect==PathSelection.PATHTWO){
                twoPath.setStrokeWidth(2);
                twoPath.setFill(Color.LIGHTSLATEGRAY);
                normalised = false;
                //if(shapeState==ShapeState.TRIANGLE){
  
                   Morph temp2= twoMorph.PolygonMorph(PolyNum);
                   drawMorph(temp2,2);   
                   //normalised = false;
                    //if (!normalised) {
                        System.out.println("Polygon Normalising before morphing can be attempted");
                        //normalisePaths(onePath, ptemp1, oneMorph, temp1);
                        normalisePaths(twoPath, ptemp2, twoMorph, temp2);
                    //}
                    //Timeline timeline1 = makeTimeline(onePath, ptemp1);
                    final Timeline timeline2 = makeTimeline(twoPath, ptemp2);
                    timeline2.play();    

            }

           }
        });
        
        
//===============================================================================
//      Advanced Rectangle
//=============================================================================

 MenuItem itemAdvancedRec=new MenuItem("Advanced Rectangle");
        itemAdvancedRec.setOnAction((ActionEvent t) -> {       
            shapeState=ShapeState.TRIANGLE;
            //selectMode=State.SELECT;
 
        if(state==State.BOTH && selectMode==State.SELECT){
             //select the first Path
            if(pathSelect==PathSelection.PATHONE){
                onePath.setStrokeWidth(2);
                onePath.setFill(Color.LIGHTSLATEGRAY);
                normalised = false;

                   Morph temp1= oneMorph.advancedRectangle();
                   //Path pathTemp2=drawMorph(temp2);   
                   drawMorph(temp1,1);   
                   //normalised = false;
                  //  if (!normalised) {
                        System.out.println("Triangle Normalising before morphing can be attempted");
                        //normalisePaths(onePath, ptemp1, oneMorph, temp1);
                        normalisePaths(onePath, ptemp1, oneMorph, temp1);
                  //  }
                    final Timeline timeline1 = makeTimeline(onePath, ptemp1);
                     timeline1.play();    

                //}
                
            } else if(pathSelect==PathSelection.PATHTWO){//select the second Path
                twoPath.setStrokeWidth(2);
                twoPath.setFill(Color.LIGHTSLATEGRAY);
                normalised = false;
                //if(shapeState==ShapeState.TRIANGLE){

                   Morph temp2= twoMorph.advancedRectangle();
                  
                   drawMorph(temp2,2);   
                   //normalised = false;
                    //if (!normalised) {
                        System.out.println("Triangle Normalising before morphing can be attempted");
                        //normalisePaths(onePath, ptemp1, oneMorph, temp1);
                        normalisePaths(twoPath, ptemp2, twoMorph, temp2);
                    //}
                    //Timeline timeline1 = makeTimeline(onePath, ptemp1);
                    final Timeline timeline2 = makeTimeline(twoPath, ptemp2);
                    timeline2.play();    
            }

           }

        });




         Label label1=new Label("There are two modes: \n"
                + "1. After you draw two shapes, if you choose select item in Menu,"
                + "the next step is to select one of two shapes, \n then click the objective shape you want it to change to.\n"
                + "2. If the unselect mode has been clicked, then you will able to draw another two shapes in the panel. \n");
       label1.setTextFill(Color.BLACK);
      // label1.setWrapText(true);
       //label1.setContentDisplay(ContentDisplay.RIGHT);
       //label1.setScaleX(1);
       //label1.setScaleY(1);
       label1.setLayoutX(200);
       label1.setLayoutY(10);
        menuMorph.getItems().addAll(itemTriangle,itemRectangle,itemEllipse,itemPolygon,itemAdvancedRec);
        
        menuBar.getMenus().addAll(menuFile,menuEdit,menuMorph);
        
         root.getChildren().addAll(menuBar,label1);  
 
        //=======================================

        /* next two lines are needed to read command-line args
         * -- such are JavaFX's awkward ways
         */
//        Parameters parameters = getParameters();
//        String fontFileName = parameters.getRaw().get(0);

        
        // add paths
        root.getChildren().addAll(onePath, twoPath,ptemp1,ptemp2,ptemp3);

        /* reading the screen size and using it to set up all
         * necessary dimensions, scaling factor and locations */
        Rectangle2D screenBound = Screen.getPrimary().getBounds();
        double screenWidth = screenBound.getWidth();
        double screenHeight = screenBound.getHeight();
        final Scene scene = new Scene(root, screenWidth * useOfScreenFactor,
                screenHeight * useOfScreenFactor, Color.WHEAT);

        // starting initial path
        scene.onMousePressedProperty().set(event ->
        {
            if(selectMode==State.UNSELECT){
            anchorPt = new Point2D(event.getX(), event.getY());
            // clean points which comprise a path to be drawn and start anew
            points.clear();
            points.add(Point.makePoint(anchorPt.getX(), anchorPt.getY()));
            if (state == State.BOTH ) state = State.CLEAR;//clear

            if (state == State.CLEAR) {
                state = State.ONE;
                normalised = false;
                // clear both paths
                onePath.getElements().clear();
                onePath.setOpacity(1);
                onePath.setFill(null);
                twoPath.getElements().clear();
                twoPath.setOpacity(1);
                twoPath.setFill(null);
                // start collecting points into path one
                ptemp1.getElements().clear();
                ptemp1.setOpacity(1);
                ptemp1.setFill(null);
                ptemp2.getElements().clear();
                ptemp2.setOpacity(1);
                ptemp2.setFill(null);
                
                
                
                onePath.setStrokeWidth(3);
                onePath.setStrokeDashOffset(0.7);
                onePath.setStroke(Color.BLACK);
                onePath.getElements()
                        .add(new MoveTo(anchorPt.getX(), anchorPt.getY()));
            } else{
                state = State.BOTH;
                // start collecting points into path two
               // twoPath.getElements().clear();
                
                
                twoPath.setStrokeWidth(3);
                twoPath.setStrokeDashOffset(0.7);
                twoPath.setStroke(Color.BLACK);
                twoPath.getElements()
                        .add(new MoveTo(anchorPt.getX(), anchorPt.getY()));
            }
           }
        });

        // dragging creates lineTos added to the path
        scene.onMouseDraggedProperty().set(event ->
        {
            if(selectMode==State.UNSELECT){
            currentPoint = Point.makePoint(event.getX(), event.getY());
            points.add(currentPoint);
            if (state == State.ONE) {
                onePath.getElements()
                        .add(new LineTo(currentPoint.x, currentPoint.y));
            } else if (state == State.BOTH) {//!!!!!!!!!!!!
                twoPath.getElements()
                        .add(new LineTo(currentPoint.x, currentPoint.y));
            }
           }
        });

        // end onePath or twoPath (depending on which
        // is being drawn) when mouse released event
        scene.onMouseReleasedProperty().set(event ->
        {
           if(selectMode==State.UNSELECT){ 
//System.out.printf("Switching from %s -> ", state);
            lastPoint = Point.makePoint(event.getX(), event.getY());
            points.add(lastPoint);
            if (state == State.ONE) {
                onePath.getElements().add(new LineTo(lastPoint.x, lastPoint.y));
                onePath.getElements().add(new LineTo(anchorPt.getX(), anchorPt.getY()));
                onePath.setStrokeWidth(1);
                onePath.setFill(Color.DARKGRAY);
                oneMorph = new Morph(points);
                System.out.printf("morph one has %d points %n", oneMorph.points.size());
            } else if (state == State.BOTH) {//!!!!!!!!!!!!!!!!!!!!!!!!!!
                twoPath.getElements().add(new LineTo(lastPoint.x, lastPoint.y));
                twoPath.getElements().add(new LineTo(anchorPt.getX(), anchorPt.getY()));
                twoPath.setStrokeWidth(1);
                twoPath.setFill(Color.DARKGRAY);
                twoMorph = new Morph(points);
                System.out.printf("morph two has %d points %n", twoMorph.points.size());
            }
            System.out.printf("%s%n", state);
            System.out.printf("The size of path %s is %d%n",
                    state == State.ONE ? "one" : "two", points.size());
           }
        });

        // simple event handlers (key board inputs which initiate transitions
        scene.onKeyPressedProperty().set(keyEvent ->
        {
            //isAltDown
            if (keyEvent.isAltDown() && keyEvent.getCode() == KeyCode.M) {
                if (state != State.BOTH) {
                    System.out.println("Need BOTH paths to perform morphing");
                    return;
                }
                if (!normalised) {
                    System.out.println("Normalising before morphing can be attempted");
                    normalisePaths(onePath, twoPath, oneMorph, twoMorph);
                }
                if (onePath.getElements().size() > 0
                        && twoPath.getElements().size() > 0) {
                    //twoPath.setFill(Color.GRAY);
                    //twoPath.setOpacity(0.5);
//                    normalisePaths(onePath, twoPath);
                    final Timeline timeline = makeTimeline(onePath, twoPath);
                    timeline.play();
                    System.out.println("Morphing should be seen now");
                } else {
                    System.out.println("Paths are empty");
                }
            } else if (keyEvent.isAltDown()
                    && keyEvent.getCode() == KeyCode.N
                    && state == State.BOTH) {
                System.out.println("Attempt to Normalise...");
                System.out.printf("oneMorph: %d, twoMorph: %d%n",
                        oneMorph.points.size(), twoMorph.points.size());
                // normalise longest morph to shortest
                normalisePaths(onePath, twoPath, oneMorph, twoMorph);
            } else if (keyEvent.isAltDown() && keyEvent.getCode() == KeyCode.Q) {
//                System.exit(0);
                Platform.exit(); // better, JavaFX's way
            }else if (keyEvent.isAltDown() && keyEvent.getCode() == KeyCode.T){
            
                if(state == State.BOTH && selectMode==State.SELECT ){
                    
                 //normalised = false;
                 Morph temp3= oneMorph.advancedRectangle();
//                   //Path pathTemp2=drawMorph(temp2);   
                 drawMorph(temp3,3);   
//                    normalised=false;
//                if (!normalised) {
                System.out.println("Advanced Rectangle Normalising before morphing can be attempted");
                normalisePaths(onePath, ptemp3, oneMorph, temp3);
//                }
                    final Timeline timeline = makeTimeline(onePath, ptemp3);
                    timeline.play();

                }
            
            }
        });
        
        
//==============================================================================
//      Mouse click Event for the first Path
//==============================================================================
        onePath.onMouseClickedProperty().set((EventHandler<MouseEvent>) (MouseEvent e) -> {
            
              if(state==State.BOTH && selectMode==State.SELECT){
       
                   pathSelect=PathSelection.PATHONE;
                   onePath.setFill(Color.RED);

            }

        });
//==============================================================================
//      Mouse enter Event for the first Path
//==============================================================================      
         onePath.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent e) -> {
            
           if(state == State.BOTH && selectMode == State.SELECT){
                onePath.setStrokeWidth(5);
                onePath.setFill(Color.GRAY);
            }

        });
//==============================================================================
//      Mouse Exit shape for the first Path
//==============================================================================
        onePath.onMouseExitedProperty().set((EventHandler<MouseEvent>) (MouseEvent e) -> {
            
             if(state == State.BOTH && selectMode == State.SELECT){
                onePath.setStrokeWidth(1);
                //onePath.setFill(Color.DARKGRAY);
            }

        });
        
        
//==============================================================================
//      Mouse click Event for the second Path
//==============================================================================       
        twoPath.onMouseClickedProperty().set((EventHandler<MouseEvent>) (MouseEvent e) -> {
            
            if(state==State.BOTH && selectMode==State.SELECT){

                   pathSelect=PathSelection.PATHTWO;
                   twoPath.setFill(Color.RED);

            }

        });
//==============================================================================
//      Mouse enter Event for the second Path
//==============================================================================           
         twoPath.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent e) -> {
             if(state == State.BOTH && selectMode == State.SELECT){
                twoPath.setStrokeWidth(5);
                twoPath.setFill(Color.GRAY);
            }
        
        });
         
//==============================================================================
//      Mouse Exit shape for the second Path
//==============================================================================
         twoPath.onMouseExitedProperty().set((EventHandler<MouseEvent>) (MouseEvent e) -> {
            if(state == State.BOTH && selectMode == State.SELECT){
                twoPath.setStrokeWidth(1);
                //twoPath.setFill(Color.DARKGRAY);
            }        
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());

    }
//==============================================================================
//      Message Box for showing Exception
//==============================================================================
  public static void messageBox(String Message, String title, String header)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(Message);
        alert.showAndWait();
    }
//==============================================================================
//      This function used to write file
//==============================================================================  
    public void writeFile(File file)throws FileNotFoundException, IOException{
        
        BufferedWriter bufferWriter=new BufferedWriter(new FileWriter(file));
        
        for(Point p:oneMorph.points){
             bufferWriter.write("1 "+p.getX()+" "+p.getY());
             bufferWriter.newLine();  //nect line
        
        }
         for(Point p:twoMorph.points){
             bufferWriter.write("2 "+p.getX()+" "+p.getY());
             bufferWriter.newLine();  //next line
        }

        bufferWriter.close();

    } 
    
//==============================================================================
//      This function used to read file
//==============================================================================    
   public void readFile(File file)throws FileNotFoundException, IOException{

   if(file!=null){
     BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
     List<Point> pointList1= new ArrayList<>();
     List<Point> pointList2= new ArrayList<>();
     String line = new String();// 
     while ((line = bufferedReader.readLine()) != null) {
         //System.out.println(line);
        String filecontent= new String(line);
        //System.out.println(line);
        String[] strArry=filecontent.split(" ");
        
        if(strArry[0].equals("1")){
            double tempX=Double.parseDouble(strArry[1]);
            double tempY=Double.parseDouble(strArry[2]);
            //System.out.println(tempX+" "+tempY);
            Point pTemp= Point.makePoint(tempX, tempY);
            pointList1.add(pTemp);
            //System.out.println(pTemp);

        }else if(strArry[0].equals("2")){
            double tempX=Double.parseDouble(strArry[1]);
            double tempY=Double.parseDouble(strArry[2]);
            //System.out.println(tempX+" "+tempY);
            Point pTemp= Point.makePoint(tempX, tempY);
            pointList2.add(pTemp);
            //System.out.println(pTemp);

        }
        
       
     }
        bufferedReader.close();//�
        
        oneMorph= new Morph(pointList1);
        double x0=oneMorph.points.get(0).x;
        double y0=oneMorph.points.get(0).y;
        onePath.getElements().clear();
        onePath.getElements().add(new MoveTo(x0,y0));
        Point p;
        for (int i = 1; i < oneMorph.points.size(); i++) {
            p = Point.makePoint(oneMorph.points.get(i).x,oneMorph.points.get(i).y);
            onePath.getElements().add(new LineTo(p.x, p.y));
           // System.out.println(oneMorph.points.get(i));
         
        }
        onePath.getElements().add(new LineTo(x0, y0));

        twoMorph =new Morph(pointList2);
       
        x0=twoMorph.points.get(0).x;
        y0=twoMorph.points.get(0).y;
        twoPath.getElements().clear();
        twoPath.getElements().add(new MoveTo(x0,y0));
        for (int i = 1; i < twoMorph.points.size(); i++) {
            p = Point.makePoint(twoMorph.points.get(i).x,twoMorph.points.get(i).y);
            twoPath.getElements().add(new LineTo(p.x, p.y));
            //System.out.println(twoMorph.points.get(i));
         
        }
        twoPath.getElements().add(new LineTo(x0, y0));

        state=State.BOTH;//set the state to both


   }

 }
//==============================================================================
//      This function used to make Morph to draw Path
//==============================================================================      
    private void drawMorph(Morph m, int type){
    
        double x0 = m.anchorPoint().x;
        double y0 = m.anchorPoint().y;
        
        
        if(type==1)
        {
             //Path tempPath=new Path();
            ptemp1.getElements().clear();
            ptemp1.getElements().add(new MoveTo(x0, y0));
            Point p;
            for (int i = 1; i < m.points.size(); i++) {
                p = Point.makePoint(m.points.get(i).x,m.points.get(i).y);
                ptemp1.getElements().add(new LineTo(p.x, p.y));
            }
            ptemp1.getElements().add(new LineTo(x0, y0));
        
        
        }else if (type==2){
            //System.out.println("TestDraw");
            ptemp2.getElements().clear();
            ptemp2.getElements().add(new MoveTo(x0, y0));
            Point p;
            for (int i = 1; i < m.points.size(); i++) {
                p = Point.makePoint(m.points.get(i).x,m.points.get(i).y);
                ptemp2.getElements().add(new LineTo(p.x, p.y));
                //System.out.println(p);
            }
            ptemp2.getElements().add(new LineTo(x0, y0));

        }else if (type==3){
            //System.out.println("TestDraw");
            ptemp3.getElements().clear();
            ptemp3.getElements().add(new MoveTo(x0, y0));
            Point p;
            for (int i = 1; i < m.points.size(); i++) {
                p = Point.makePoint(m.points.get(i).x,m.points.get(i).y);
                ptemp3.getElements().add(new LineTo(p.x, p.y));
                //System.out.println(p);
            }
            ptemp3.getElements().add(new LineTo(x0, y0));

        }   
       
        
        //return tempPath;
    
    
    }
//==============================================================================
//      This function used to normalise the two required Paths
//==============================================================================      
    private void normalisePaths(Path p1, Path p2, Morph m1, Morph m2) {
        if (normalised) return;
        if (p1.getElements().size() > m2.points.size()) {
            p1.setOpacity(0.5);
            m1 = Morph.normalize(m1, m2.points.size());
            p1.setFill(null);
            adjustPath(p1, m1);
        } else if (p1.getElements().size() < m2.points.size()) {
            p2.setOpacity(0.5);
            m2 = Morph.normalize(m2, m1.points.size());
            p2.setFill(null);
            adjustPath(p2, m2);
            p2.setStrokeWidth(1);
            p2.setFill(Color.CORNSILK);
        }
        p1.setStrokeWidth(1);
        p1.setFill(Color.CORNSILK);
        p2.setOpacity(0.5);
        normalised = true;
    }
//==============================================================================
//      This function used to adjust the required Path and its morph
//==============================================================================      
    private void adjustPath(Path path, Morph morph) {
        System.out.printf("size of path %d, size of morph %d%n",
                path.getElements().size(), morph.points.size());
        path.getElements().clear();
        double x0 = morph.anchorPoint().x;
        double y0 = morph.anchorPoint().y;
        path.getElements().add(new MoveTo(x0, y0));
        Point p;
        for (int i = 1; i < morph.points.size(); i++) {
            p = Point.makePoint(morph.points.get(i).x,morph.points.get(i).y);
          
            path.getElements().add(new LineTo(p.x, p.y));
        }
        path.getElements().add(new LineTo(x0, y0));
    }
//==============================================================================
//      This function used to make time line
//==============================================================================      
    private Timeline makeTimeline(Path p1, Path p2) {
        assert p1.getElements().size() == p2.getElements().size() : "uneven paths";
        //final Timeline timeline = new Timeline();
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);//(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        int n = p1.getElements().size();
        KeyValue kvx, kvy;
        KeyFrame kf;
        MoveTo ap1, ap2;
        LineTo pe1, pe2;
        ap1 = (MoveTo) p1.getElements().get(0);
        ap2 = (MoveTo) p2.getElements().get(0);
        kvx = new KeyValue(ap1.xProperty(), ap2.getX());
        kvy = new KeyValue(ap1.yProperty(), ap2.getY());
        kf = new KeyFrame(Duration.millis(3000), kvx, kvy);
        timeline.getKeyFrames().add(kf);
        for (int i = 1; i < n; i++) {
            pe1 = (LineTo) p1.getElements().get(i);
            pe2 = (LineTo) p2.getElements().get(i);
            kvx = new KeyValue(pe1.xProperty(), pe2.getX());
            kvy = new KeyValue(pe1.yProperty(), pe2.getY());
            kf = new KeyFrame(Duration.millis(3000), kvx, kvy);
            timeline.getKeyFrames().add(kf);
        }
        return timeline;
    }

    enum State {CLEAR, ONE, BOTH,SELECT,UNSELECT} /* to control the keyboard/mouse input */
    enum ShapeState{TRIANGLE, ELLIPSE, RECTANGLE, POLYGON,NONE}/* to control the selection of shape transferation */
    enum PathSelection{PATHONE,PATHTWO,NOTHING}/* to control the which shape has been chosen  */
    

}
