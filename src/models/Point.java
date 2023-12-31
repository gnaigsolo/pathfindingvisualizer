package models;

import java.lang.management.MemoryType;

public class Point implements Comparable<Point> {
    int x;
    int y;
    int heuristic;
    @Override
    public int hashCode() {
    final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    public Point(int x, int y) {
        x = Math.max(x,0);
        x = Math.min(x,Config.getInstance().getMapSize().width-1);
        y = Math.max(y,0);
        y = Math.min(y,Config.getInstance().getMapSize().length-1);
        this.x = x;
        this.y = y;
    }
    public Point(int x, int y, int heuristic ) {
        this(x,y);
        this.heuristic = heuristic;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        Point p = (Point) o;
        if(p.x == this.x && p.y == this.y)
            return true;
        return false;
    }
    public void countHaminton(Point i) {
        int dx = Math.abs(i.x - x) ;
        int dy = + Math.abs(i.y - y);
        this.heuristic = (int)Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public int compareTo(Point o) {
        return -o.heuristic + this.heuristic;
    }
    public void addHeuristic(int i) {
        this.heuristic *= 2;
        this.heuristic +=i;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }
}
