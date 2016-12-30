package home.fractal.gui;

public class Line {
    private final Point start;
    private final Point end;
    
    public Line(final Point start, final Point end){
        this.start = start;
        this.end = end;
    }
    
    public Point getStart(){
        return this.start;
    }
    
    public Point getEnd(){
        return this.end;
    }
    
    public double getDistance(){
        return Math.sqrt(Math.pow(this.end.getX() - this.start.getX(),2)
                        + Math.pow(this.end.getY() - this.start.getY(),2));
    }
}
