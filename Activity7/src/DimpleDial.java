import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

public class DimpleDial extends Dial {
	double value; // range from 0.0 - 1.0
	
	Integer dragStart;
	
	DialGUI dialgui;
	
	public DimpleDial() {
		value = 0.5;	
		dragStart = null;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	
	public double value() {
		return value;
	}

	

	void drawbackground(Graphics2D g) {  // this paints the background parts of the nob
		g.setColor(Color.white);
		g.fillRect(0, 0, dim.width, dim.height);
		
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(1.0f));
		drawtick(g, 0.0, dialRadius,  tickOuterRadius);
		drawtick(g, 1.0, dialRadius,  tickOuterRadius);
		
		g.setColor(Color.LIGHT_GRAY);
	}

	void drawdial(Graphics2D g, double v) { // this paints the foreground parts of the nob
		g.fill(new Arc2D.Double(inset, inset, dialRadius*2.0, dialRadius*2.0, 0, 360, Arc2D.OPEN));
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(4.0f));
		drawtick(g, v, dialRadius, markerInnerRadius);
	}



	@Override
	public void mouseDragged(MouseEvent me) {
		if (dragStart != null) {
		    value += (me.getX() - dragStart)/MOUSEVALUESCALINGFACTOR;
		    if (value < 0.0) value = 0.0;
		    if (value > 1.0) value = 1.0;
		    dragStart = me.getX();
		}
		dialgui.update();
		this.repaint();
	}


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
	public void mousePressed(MouseEvent me) {
		dragStart = me.getX();
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		dragStart = null;
	}


	public void setGUI(DialGUI dialGUI) {
		dialgui = dialGUI;
	}
}
