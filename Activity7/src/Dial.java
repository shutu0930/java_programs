import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

/*
 * Dial - a grey nob for controlling a value that ranges between 0 and 1.
 * The dials value is changed as you drag the mouse horizontally.
 * Eric McCreath
 */


public abstract class Dial extends JComponent implements MouseMotionListener, MouseListener {

	final static double MOUSEVALUESCALINGFACTOR = 100.0;
	final static Dimension dim = new Dimension(70,70);
	final static int inset = 8;
	final static double dialRadius = dim.width/2.0-inset;
	final static double tickOuterRadius = dim.width / 2.0 - inset / 4.0;
	final static double markerInnerRadius = dim.width / 4.0;

	
	
	public abstract double value(); 

	public Dimension getPreferredSize(){
		return dim;
	}
	
	@Override
	public 
	void paintComponent(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		drawbackground(g);
		drawdial(g,value());
	}

	abstract void  drawbackground(Graphics2D g) ;

	abstract void drawdial(Graphics2D g, double v);

	
	// draw a line that radiates from the center of the dial
	// the angle of the line is based on the value for this dial
	public void drawtick(Graphics2D g, double v, double s, double e) {
		double ang = (1.0 - v) * Math.PI * 2.0 * 0.8 + Math.PI * 0.2;
		double x1 = Math.sin(ang)*s + dim.width/2.0;
		double y1 = Math.cos(ang)*s + dim.height/2.0;
		double x2 = Math.sin(ang)*e + dim.width/2.0;
		double y2 = Math.cos(ang)*e + dim.height/2.0;
		g.draw(new Line2D.Double(x1,y1,x2,y2));
	}

	@Override
	public abstract void mouseDragged(MouseEvent me);


	@Override
	public void mouseMoved(MouseEvent me) {
	}

	@Override
	public void mouseClicked(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}

	@Override
	public abstract void mousePressed(MouseEvent me);
	@Override
	public abstract void mouseReleased(MouseEvent me);


	public abstract void setGUI(DialGUI dialGUI);
}
