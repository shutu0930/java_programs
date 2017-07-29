import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class DialDecorator extends Dial {

	Dial parent;
	DialDecorator(Dial d){
		this.parent=d;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		
	}
	
	
	@Override
	public void mouseDragged(MouseEvent me) {
		parent.mouseDragged(me);
		repaint();

	}

	@Override
	public void mousePressed(MouseEvent me) {
		parent.mousePressed(me);

	}

	@Override
	public void mouseReleased(MouseEvent me) {
		parent.mouseReleased(me);

	}

	@Override
	public void setGUI(DialGUI dialGUI) {
		parent.setGUI(dialGUI);

	}

}
