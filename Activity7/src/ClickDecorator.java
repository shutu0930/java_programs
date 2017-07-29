import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

public class ClickDecorator extends DialDecorator {
	
	int clicks;
	ClickDecorator(int clicks, Dial d){
		super(d);
		this.clicks=clicks;
	
	}
	@Override
	public double value() {
		return ((double) Math.round(this.parent.value() * clicks)) / clicks;
	}

	@Override
	void drawbackground(Graphics2D g) {
		this.parent.drawbackground(g);
		
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(1.0f));
		drawtick(g, 0.0, dialRadius,  tickOuterRadius);
		drawtick(g, 1.0, dialRadius,  tickOuterRadius);
		for (int s = 1; s < clicks; s++)
			drawtick(g, s / (clicks * 1.0),dialRadius,  tickOuterRadius);

		g.setColor(Color.LIGHT_GRAY);

	}

	@Override
	void drawdial(Graphics2D g, double v) {
		parent.drawdial(g, v);

	}

}
