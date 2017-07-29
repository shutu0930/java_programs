import java.awt.Color;
import java.awt.Graphics2D;

public class ColorDecorator extends DialDecorator {
	Color color;
	ColorDecorator(Color c, Dial d){
		super(d);
		this.color=c;

	}
	@Override
	public double value() {
		return parent.value();
	}

	@Override
	void drawbackground(Graphics2D g) {
		parent.drawbackground(g);
		g.setColor(color);

	}

	@Override
	void drawdial(Graphics2D g, double v) {
		parent.drawdial(g, v);

	}

}
