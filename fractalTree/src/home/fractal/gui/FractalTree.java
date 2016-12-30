package home.fractal.gui;

import java.util.ArrayList;
import java.util.List;

public class FractalTree {
	private static final int MIN_DISTANCE = 1 ;
	private static final double DEGREE = Math.PI/3; 
	private static final double DIFFERENCE = 2;
	private static final double minDiff = 1.5; 
	private double difference = DIFFERENCE;
	private final int initialDistance;
	private final List<Pair<Point,Point>> tree;
	private final Point startPos;
	/**
	 * 
	 * @param initialDistance
	 * 		the lenght of the first branch
	 * @param startPos
	 * 		the initial position of the tree
	 */
	public FractalTree(final int initialDistance, final Point startPos){
		this.initialDistance =  initialDistance;
		tree = new ArrayList<>();
		this.startPos = startPos;
	}
	/**
	 * 
	 * @return
	 * 		all the rect that rappresent the tree
	 */
	public List<Pair<Point,Point>> getTree(){
		this.tree.clear();
		return createTree(this.startPos,this.initialDistance,Math.PI/2);
	}
	
	private List<Pair<Point,Point>> createTree(Point pos, final double distance,double degree){
		final List<Pair<Point,Point>> line = new ArrayList<>();
		if(distance < MIN_DISTANCE){
			return new ArrayList<>();
		}
		final Point addPoint = new Point((int)(pos.getX() - distance * Math.cos(degree)) ,(int)(pos.getY() - distance* Math.sin(degree)));
		final double newDegree = degree - (Math.PI/2 - DEGREE); 
		line.add(new Pair<Point,Point>(pos,addPoint));
		line.addAll(this.createTree(addPoint, distance / difference , newDegree));
		line.addAll(this.createTree(addPoint, distance / difference ,newDegree+ DEGREE));
		return line;
	}
	
	public void moreBranch(){
		System.out.println("ENT");
		if(this.difference > minDiff){
			this.difference -= 0.05;
		}
	}
}
