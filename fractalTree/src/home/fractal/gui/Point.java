package home.fractal.gui;

public class Point {
	private final int x;
	private final int y;
	
	public Point(final int x, final int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}

	@Override
	public String toString() {
		return "Point [X=" + x + ", Y=" + y + "]";
	}
}