package home.fractal.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	private static final int SIZE = 150;
	private static final int PROP = 2;
	private final JFrame frame = new JFrame();
	private final TreePanel panel = new TreePanel();
	private final FractalTree tree;

	public GUI() {
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setSize(dim.width,dim.height);
		this.frame.setLocationByPlatform(true);
		tree = new FractalTree(SIZE, new Point(this.frame.getWidth() / 2, this.frame.getHeight()));
		this.frame.add(panel);
		this.frame.setVisible(true);
		new Thread(() -> {
			while(1){
				tree.moreBranch();
				this.panel.repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		new GUI();
	}

	private class TreePanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g) {
			int startGreen = 0;
			List<Pair<Point, Point>> lines = GUI.this.tree.getTree();
			System.out.println(lines.size());
			lines.forEach(x -> {
				g.setColor(new Color(0,
				startGreen,0));
				g.drawLine(x.getFirst().getX(), x.getFirst().getY(), x.getSecond().getX(), x.getSecond().getY());

			});
		}
	}
}
