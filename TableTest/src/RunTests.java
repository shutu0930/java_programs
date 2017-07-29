import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.SwingWorker;

/*
 * RunTests - the runs and draws the graphs for testing the 
 * different table implementations.   Noting this is done as a 
 * SwingWorker as it takes the load off the main GUI thread. 
 * 
 * Eric McCreath - 2017
 * 
 */


public class RunTests extends SwingWorker<Void, Void> {

	private static final int XGAP = 30;
	private static final int YGAP = 30;
	static int H = TableTest.GRAPHDIM.height;
	static int W = TableTest.GRAPHDIM.width;

	static final int REPEATLOOKUP = 500000;

	TableTest tabletest;
	int maxsize, sizestep;
	float maxtime;

	public RunTests(TableTest tabletest, int maxsize, int maxtime) {
		this.tabletest = tabletest;
		this.maxsize = maxsize;
		this.maxtime = maxtime;
		this.sizestep = maxsize / (W/10); 
	}

	// getTable - static factory for the different tests.   
	//             Tests "tab" numbers start at zero and go 
	//             up in step until the last one.  The calling method knows 
	//             all the tests have been done when the a null has been returned.
	static public Table getTable(int tab) {
		switch (tab) {
		case 0 : return new HashMapTable();
		case 1 : return new ListTable();
		case 2 : return new BinarySearchTreeTable();
		//case 3 : return new MyHash();   // un-comment this line if you do the optional part. 
		}
		return null;
	}

	
	// goInBackground - run timing tests on the different table implementations and graph them as done.
	//                  Noting this design is not great in terms of being cohesive.  As in this one 
	//                  method we are doing 2 very different tasks (running tests and graphing them)
	@Override
	protected Void doInBackground() throws Exception {
		tabletest.graphpanelimage = new BufferedImage(W, H,
				BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = tabletest.graphpanelimage.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, W, H);
		g.setColor(Color.black);
		drawaxis(g);
		int tablenumber = 0;
		Table currentTable;
		while ((currentTable = getTable(tablenumber)) != null && !this.isCancelled()) {
			g.setColor(Color.BLACK);
			int ylab = YGAP + tablenumber * YGAP;
			g.drawString(currentTable.name(), W - 5 * XGAP, ylab);
			g.setColor(currentTable.color());
			g.drawLine(W - 5 * XGAP - 20, ylab - 5, W - 5 * XGAP - 5, ylab - 5);
			if (testokay(tablenumber)) {
				boolean overtime = false;
				Random r = new Random(0);
				for (int size = sizestep; size < maxsize && !overtime && !this.isCancelled(); size += sizestep) {
					Table table = getTable(tablenumber);
					for (int i = 0; i < size; i++) {
						table.insert(r.nextInt(size * 2), r.nextDouble() + "");
					}
					Date start = new Date();
					for (int i = 0; i < REPEATLOOKUP; i++) {
						table.lookup(r.nextInt(size * 2));
					}
					Date end = new Date();
					float timeperlookup = ((float) (end.getTime() - start
							.getTime())) * 1000000.0f / REPEATLOOKUP; // in ns
					if (timeperlookup > maxtime)
						overtime = true;
					drawcross(g, size, timeperlookup);
					publish();  // this causes the image to be re-painted via the process method. 
				}
			} else {
				g.drawString("Problem!", W - 2 * XGAP, ylab);
			}

			tablenumber++;
		}
		return null;
	}

	// testokay - this tests does a simple test that the implementation is working as expected. 
	static boolean testokay(int tablenumber) {
		Random r = new Random(0);
		Table table = getTable(tablenumber);
		Table gold = new HashMapTable();
		for (int i = 0; i < 1000; i++) {
			int k = r.nextInt(1000 * 2);
			String v = r.nextDouble() + "";
			table.insert(k, v);
			gold.insert(k, v);
		}
		for (int i = 0; i < 2000; i++) {
			String t = table.lookup(i);
			String g = gold.lookup(i);
			if ((t == null && g != null) ||
					(g == null && t != null) ||
					(t != null && g != null && !g.equals(t)))
				return false;
		}
		return true;
	}

	// drawcross - draw a little cross for the graph. 
	private void drawcross(Graphics2D g, int size, float time) {
		float x = XGAP + (W - 2 * XGAP) * ((float) size / (float) maxsize);
		float y = H - (((H - 2 * YGAP) * time) / maxtime) - YGAP;

		g.draw(new Line2D.Float(x - 1, y, x + 1, y));
		g.draw(new Line2D.Float(x, y - 1, x, y + 1));

	}

	
	// drawaxis - draw graph x and y axis along with the heading 
	private void drawaxis(Graphics2D g) {
		g.drawLine(XGAP, H - YGAP, XGAP, YGAP);
		g.drawLine(XGAP, H - YGAP, W - XGAP, H - YGAP);
		g.drawString(String.format("%.0f ns (time)", maxtime), 10, YGAP);
		g.drawString(String.format("%d (tablesize)", maxsize), W - XGAP*6, H-YGAP/3);
		String str = "Average Lookup Time For Tables of Different Sizes";
		int strw = g.getFontMetrics().stringWidth(str);
		g.drawString(str, (W-strw)/2, 2*YGAP);
	}

	
	@Override
	protected void process(List<Void> arg0) {
		tabletest.graphpanel.repaint();
	}

}
