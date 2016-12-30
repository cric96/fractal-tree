package home.fractal.gui;

import java.util.ArrayList;
import java.util.List;

public class FractalTree {
    private static final int MIN_DISTANCE = 5;
    private static final double DEGREE = Math.PI / 3;
    private static final double DIFFERENCE = 2;
    private static final double minDiff = 1.4;
    private double difference = DIFFERENCE;
    private final int initialDistance;
    private final List<Line> tree;
    private final Point startPos;

    /**
     * 
     * @param initialDistance
     *            the lenght of the first branch
     * @param startPos
     *            the initial position of the tree
     */
    public FractalTree(final int initialDistance, final Point startPos) {
        this.initialDistance = initialDistance;
        tree = new ArrayList<>();
        this.startPos = startPos;
    }

    /**
     * 
     * @return all the rect that rappresent the tree
     */
    public List<Line> getTree() {
        this.tree.clear();
        return createTree(this.startPos, this.initialDistance, Math.PI / 2);
    }
    //Recursive function to create a fractal tree
    private List<Line> createTree(Point pos, final double distance, double degree) {
        final List<Line> lines = new ArrayList<>();
        if (distance < MIN_DISTANCE) {
            return new ArrayList<>();
        }
        //the new point to draw
        final Point addPoint = new Point((int) (pos.getX() - distance * Math.cos(degree)),
                (int) (pos.getY() - distance * Math.sin(degree)));
        //The new degree (the degree between the angle remain costant to DEGREE
        final double newDegree = degree - (Math.PI / 2 - DEGREE);
        lines.add(new Line(pos, addPoint));
        lines.addAll(this.createTree(addPoint, distance / difference, newDegree));
        lines.addAll(this.createTree(addPoint, distance / difference, newDegree + DEGREE));
        return lines;
    }
    /**
     * 
     * @return
     *          true if it is possible to create new branch
     */
    public boolean moreBranch() {
       if (this.difference > minDiff) {
            this.difference -= 0.001;
            return true;
       }
       return false;
    }
}
