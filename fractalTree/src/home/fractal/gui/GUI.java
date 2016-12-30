package home.fractal.gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
    private static final int PROPORTION = 4;
    private final JFrame frame = new JFrame();
    private final TreePanel panel = new TreePanel();
    private final FractalTree tree;

    public GUI() {
        this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationByPlatform(true);
        tree = new FractalTree(this.frame.getHeight()/ PROPORTION, new Point(this.frame.getWidth() / 2, this.frame.getHeight()));
        this.frame.add(panel);
        this.frame.setVisible(true);
        new Thread(() -> {
            while (true) {
                if(tree.moreBranch()){
                    this.panel.repaint();
                }
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
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            List<Line> lines = GUI.this.tree.getTree();
            final Graphics2D g2d = (Graphics2D)g;
            lines.forEach(x -> {
                //To give a different heigth to the line
                if(x.getDistance() < BranchWidth.SMALL.getMinSize()){
                    g2d.setStroke(new BasicStroke(BranchWidth.SMALL.getSize()));
                }else if(x.getDistance() < BranchWidth.MEDIUM.getMinSize()){
                    g2d.setStroke(new BasicStroke(BranchWidth.MEDIUM.getSize()));
                }else if(x.getDistance() < BranchWidth.BIG.getMinSize()){
                    g2d.setStroke(new BasicStroke(BranchWidth.BIG.getSize()));
                }else{
                    g2d.setStroke(new BasicStroke(BranchWidth.GIGANT.getSize()));
                }
                g2d.drawLine(x.getStart().getX(), x.getStart().getY(), x.getEnd().getX(), x.getEnd().getY());
                
            });
        }
    }
    private enum BranchWidth{
        SMALL(20,1),
        MEDIUM(50,2),
        BIG(90,3),
        GIGANT(140,6);
        private int minSize;
        private int size;
        private BranchWidth (final int minSize, final int size){
            this.minSize = minSize;
            this.size = size;
        }
        
        public int getMinSize(){
            return this.minSize;
        }
        
        public int getSize(){
            return this.size;
        }
    }
}


