package models;

import views.View;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.*;
import java.util.List;

public class Map {
    Cell[][] maze;
    Size size;
    View view;

    public Cell[][] getMaze() {
        return maze;
    }

    public Size getSize() {
        return size;
    }

    public Point getStart() {
        return start;
    }

    public Point getRoad() {
        return road;
    }

    Point start;
    Point road;
    List<Point> rout;

    public Map() {
        size = Config.getInstance().getMapSize();
        maze = new Cell[size.width][size.length];
        for(int i = 0 ; i <size.width; ++i) {
            for (int j = 0 ; j < size.length; ++j) {
                maze[i][j] = new Cell();
            }
        }
        road = new Point(size.width - 1 , size.length - 1);
        start = new Point(0,0);
        maze[road.getX()][road.getY()].setCellType(new Road());
        maze[start.getX()][start.getY()].setCellType(new Start() );

    }
    public void setWall(int i, int j) {
        Point point = new Point(i,j);
        if(point.equals(road) || point.equals(start))
            return;
        maze[i][j].setCellType(new Wall());
        view.update();
    }
    public void setWay(int i, int j) {
        Point point = new Point(i,j);
        if(point.equals(road) || point.equals(start))
            return;
        maze[i][j].setCellType(new Way());
        view.update();
    }
    public void setRoad(int i, int j) {
        Point point = new Point(i,j);
        if(point.equals(start))
            return;
        int x = road.getX();
        int y = road.getY();
        road = new Point(i,j);

        if(road != null)
            setWay(x, y);
        maze[i][j].setCellType(new Road());
        view.update();
    }
    public void setStart(int i, int j) {
        Point point = new Point(i,j);
        if(point.equals(road))
            return;
        if(start != null)
            setWay(start.x, start.y);
        start = new Point(i,j);
        maze[i][j].setCellType(new Start());
        view.update();
    }
    public boolean isCanMove(Point point)   {

        if(point.getX() >= size.getWidth() || point.getX() < 0 || point.getY() >= size.getLength() || point.getY()< 0) {
            return false;
        }
        boolean res = maze[point.getX()][point.getY()].isMovable();
        if(res == true) {
            try {
            Thread.sleep(Config.getInstance().getSpeed());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        }
        return res;
    }

    public void clear() {
        for(int i = 0 ; i <size.width; ++i) {
            for (int j = 0 ; j < size.length; ++j) {
                maze[i][j] = new Cell();
            }
        }
        setRoad(size.width - 1 , size.length - 1);
        setStart(0,0);
        view.update();
    }
    public void markRout() {
        reset();
        for(Point i : rout) {
            maze[i.x][i.y].setRout();

        }
        view.update();
    }
    public void markMove(Point i) {
        maze[i.x][i.y].markMove();
        view.update();
    }
    public boolean aStar() {
        HashMap<Point,Integer> distance = new HashMap<Point,Integer>();
        distance.put(start,0);
        HashMap<Point,Point> father = new HashMap<Point,Point>();
        PriorityQueue<Point> q = new PriorityQueue<Point>();
        q.add(new Point(start.x,start.y));
        maze[start.x][start.y].markMove();
        while (!q.isEmpty()) {
            Point now = q.poll();
            Point up = new Point(now.getX() -1, now.y );
            Point down = new Point(now.getX()+1, now.y );
            Point left = new Point(now.getX() , now.y-1);
            Point right = new Point(now.getX(), now.y +1);
            up.countHaminton(road);
            down.countHaminton(road);
            left.countHaminton(road);
            right.countHaminton(road);
            if(isCanMove(up)) {
                markMove(up);
                father.put(up,now);
                distance.put(up, distance.get(now) + 1);
                if(road.equals(up)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                up.addHeuristic(distance.get(up));
                q.add(up);
            }
            if(isCanMove(right)) {
                father.put(right,now);
                distance.put(right, distance.get(now) + 1);
                markMove(right);
                if(road.equals(right)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                right.addHeuristic(distance.get(right));
                q.add(right);
            }
            if(isCanMove(down)) {
                distance.put(down, distance.get(now) + 1);
                father.put(down,now);
                markMove(down);
                if(road.equals(down)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                down.addHeuristic(distance.get(down));
                q.add(down);
            }
            if(isCanMove(left)) {
                distance.put(left, distance.get(now) + 1);
                father.put(left,now);
                markMove(left);
                if(road.equals(left)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                left.addHeuristic(distance.get(left));
                q.add(left);
            }

        }
        return false;

    }
    public boolean akt() {
        HashMap<Point,Integer> distance = new HashMap<Point,Integer>();
        distance.put(start,0);
        HashMap<Point,Point> father = new HashMap<Point,Point>();
        Queue<Point> q = new PriorityQueue<Point>();
        q.add(new Point(start.x,start.y));
        maze[start.x][start.y].markMove();
        while (!q.isEmpty()) {
            Point now = q.poll();
            Point up = new Point(now.getX() -1, now.y );
            Point down = new Point(now.getX()+1, now.y );
            Point left = new Point(now.getX() , now.y-1);
            Point right = new Point(now.getX(), now.y +1);
            if(isCanMove(up)) {
                markMove(up);
                father.put(up,now);
                distance.put(up, distance.get(now) + 1);
                if(road.equals(up)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                up.addHeuristic(distance.get(up));
                q.add(up);
            }
            if(isCanMove(right)) {
                father.put(right,now);
                distance.put(right, distance.get(now) + 1);
                markMove(right);
                if(road.equals(right)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                right.addHeuristic(distance.get(right));
                q.add(right);
            }
            if(isCanMove(down)) {
                distance.put(down, distance.get(now) + 1);
                father.put(down,now);
                markMove(down);
                if(road.equals(down)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                down.addHeuristic(distance.get(down));
                q.add(down);
            }
            if(isCanMove(left)) {
                distance.put(left, distance.get(now) + 1);
                father.put(left,now);
                markMove(left);
                if(road.equals(left)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                left.addHeuristic(distance.get(left));
                q.add(left);
            }

        }
        return false;

    }
    public boolean haminton() {
        HashMap<Point,Integer> distance = new HashMap<Point,Integer>();
        distance.put(start,0);
        HashMap<Point,Point> father = new HashMap<Point,Point>();
        PriorityQueue<Point> q = new PriorityQueue<Point>();
        q.add(new Point(start.x,start.y));
        maze[start.x][start.y].markMove();
        while (!q.isEmpty()) {
            Point now = q.poll();
            recalculateHeuristic(q);
            Point up = new Point(now.getX() -1, now.y );
            Point down = new Point(now.getX()+1, now.y );
            Point left = new Point(now.getX() , now.y-1);
            Point right = new Point(now.getX(), now.y +1);
            up.countHaminton(road);
            down.countHaminton(road);
            left.countHaminton(road);
            right.countHaminton(road);
            if(isCanMove(up)) {
                markMove(up);
                father.put(up,now);
                distance.put(up, distance.get(now) + 1);
                if(road.equals(up)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.add(up);
            }
            if(isCanMove(right)) {
                father.put(right,now);
                distance.put(right, distance.get(now) + 1);
                markMove(right);
                if(road.equals(right)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.add(right);
            }
            if(isCanMove(down)) {
                distance.put(down, distance.get(now) + 1);
                father.put(down,now);
                markMove(down);
                if(road.equals(down)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.add(down);
            }
            if(isCanMove(left)) {
                distance.put(left, distance.get(now) + 1);
                father.put(left,now);
                markMove(left);
                if(road.equals(left)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.add(left);
            }

        }
        return false;

    }
    public boolean bfs() {
        HashMap<Point,Point> father = new HashMap<Point,Point>();
        Queue<Point> q = new LinkedList<Point>();
        q.add(new Point(start.x,start.y));
        maze[start.x][start.y].markMove();
        while (!q.isEmpty()) {
            Point now = q.poll();
            Point up = new Point(now.getX() -1, now.y );
            Point down = new Point(now.getX()+1, now.y );
            Point left = new Point(now.getX() , now.y-1);
            Point right = new Point(now.getX(), now.y +1);
            if(isCanMove(up)) {
                markMove(up);
                father.put(up,now);
                if(road.equals(up)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.add(up);
            }
            if(isCanMove(right)) {
                father.put(right,now);
                markMove(right);
                if(road.equals(right)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.add(right);
            }
            if(isCanMove(down)) {
                father.put(down,now);
                markMove(down);
                if(road.equals(down)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.add(down);
            }
            if(isCanMove(left)) {
                father.put(left,now);
                markMove(left);
                if(road.equals(left)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.add(left);
            }

        }
        return false;

    }
    public boolean dfs() {
        HashMap<Point,Point> father = new HashMap<Point,Point>();
        Stack<Point> q = new Stack<>();
        q.push(new Point(start.x,start.y));
        maze[start.x][start.y].markMove();
        while (!q.isEmpty()) {

            Point now = q.pop();

            Point up = new Point(now.getX(), now.y + 1);
            Point down = new Point(now.getX(), now.y -1 );
            Point left = new Point(now.getX() - 1, now.y);
            Point right = new Point(now.getX()+1, now.y);
            if(isCanMove(right)) {
                father.put(right,now);
                markMove(right);
                if(road.equals(right)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.push(right);
            }
            if(isCanMove(up)) {
                markMove(up);
                father.put(up,now);
                if(road.equals(up)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.push(up);
            }
            if(isCanMove(down)) {
                father.put(down,now);
                markMove(down);
                if(road.equals(down)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.push(down);
            }
            if(isCanMove(left)) {
                father.put(left,now);
                markMove(left);
                if(road.equals(left)) {
                    this.rout = tracert(father);
                    markRout();
                    return true;
                }
                q.push(left);

            }

        }
        return false;
    }


    public void setView(View view) {
        this.view = view;
        view.update();
    }

    public Color[][] getColorGrid() {
        Color[][] res = new Color[size.width][size.length];
        for(int i = 0 ; i < size.width; ++i)
            for(int j = 0 ; j < size.length; ++j)
                res[i][j] = maze[i][j].cellType.getColor();
        return res;
    }

    public void recalculateHeuristic(PriorityQueue<Point> q) {
        for(Point i : q) {
            i.countHaminton(road);
        }
    }
    public void reset() {
        for(int i = 0 ; i < size.width; ++i)
            for(int j = 0 ; j < size.length; ++j) {
                maze[i][j].reset();
            }
        view.update();
    }
    public List tracert(HashMap<Point,Point> father) {
        ArrayList<Point> res = new ArrayList<Point>();
        Point i = father.get(road);
        while(!i.equals(start)) {
            res.add(i);
            i = father.get(i);
        }

       return res;
    }
}
