import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * TableTest - run and graph the performance of different table implementation.  
 *             This class holds the main code for running the tests generating the 
 *             graph, including much of the tricky Swing GUI setup code.
 *             
 * Eric McCreath 2017
 */


public class TableTest implements Runnable, ChangeListener {

	public static final Dimension GRAPHDIM = new Dimension(800, 500);

	JFrame jframe;
	JPanel graphpanel;
	JSlider timeScaleSlider, maxsizeSlider;
	BufferedImage graphpanelimage;
	RunTests tests;

	public TableTest() {
		SwingUtilities.invokeLater(this);
	}

	
	// run - set up the jframe adding components once this is done the worker for doing the testing is started with "restart".
	public void run() {
		jframe = new JFrame("TableTest");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphpanelimage = new BufferedImage(GRAPHDIM.width, GRAPHDIM.height,
				BufferedImage.TYPE_INT_ARGB);
		
		graphpanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(graphpanelimage, 0, 0, null);

			}
		};
		JPanel controlpanel = new JPanel();
		controlpanel.setLayout(new GridLayout(4, 1));
		timeScaleSlider = new JSlider(JSlider.HORIZONTAL, 10, 10000, 500);
		maxsizeSlider = new JSlider(JSlider.HORIZONTAL, 100, 100000, 1000);
		timeScaleSlider.addChangeListener(this);
		maxsizeSlider.addChangeListener(this);
		controlpanel.add(new JLabel("time scale"));
		controlpanel.add(timeScaleSlider);
		controlpanel.add(new JLabel("max table size"));
		controlpanel.add(maxsizeSlider);
		graphpanel.setPreferredSize(GRAPHDIM);
		jframe.getContentPane().add(graphpanel, BorderLayout.CENTER);
		jframe.getContentPane().add(controlpanel, BorderLayout.PAGE_END);
		jframe.setVisible(true);
		jframe.pack();
		restart();
	}

	public static void main(String[] args) {
		new TableTest();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		tests.cancel(true);		
		restart();
	}

	// make and start a new worker. 
	public void restart() {
		tests = new RunTests(this, maxsizeSlider.getValue(), timeScaleSlider.getValue());
		tests.execute();
	}

}
