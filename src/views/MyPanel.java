package views;

import models.Config;
import models.Point;
import models.Size;
import models.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements MouseListener, MouseMotionListener {
    Thread now;
    Color[][] grid;
    Map model;
    MouseHandler mouseHandler ;
    public void setModel(Map model) {
        this.model = model;
    }

    MyPanel(Map model) {

        this.model = model;
        addMouseListener(this);
        addMouseMotionListener(this);
        Size size = Config.getInstance().getMapSize();
        Size cellSize = Config.getInstance().getCellSize();
        this.setPreferredSize(new Dimension(size.getWidth()*cellSize.getWidth(),size.getLength()*cellSize.getLength()));
        grid = new Color[size.getWidth()][size.getWidth()];
        for(int i = 0 ; i < size.getWidth() ; ++i ) {
            for (int j = 0 ; j < size.getLength(); ++j) {
                grid[i][j] = Config.getInstance().getWayColor();
            }
        }
        mouseHandler = new WallSetter(model);
        Button wall = new Button("Đặt tường");
        wall.addActionListener(e->setMouseHandler(new WallSetter(model)));
        Button way = new Button("Xóa tường");
        way.addActionListener(e->setMouseHandler(new WaySetter(model)));
        Button goal = new Button("Đặt đích");
        goal.addActionListener(e->setMouseHandler(new GoalSetter(model)));
        Button start = new Button("Đặt điểm xuất phát");
        start.addActionListener(e->setMouseHandler(new StartSetter(model)));
        Button clear = new Button("Làm mới");
        clear.addActionListener(e->model.clear());
        Button dfs = new Button("DFS");
        dfs.addActionListener(e->{
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    model.reset();
                    model.dfs();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            now = thread;
        });
        Button bfs = new Button("BFS");
        bfs.addActionListener(e->{
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    model.reset();
                    model.bfs();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            now = thread;
        });
        Button aStar = new Button("A*");
        aStar.addActionListener(e->{
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    model.reset();
                    model.aStar();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            now = thread;
        });
        Button AKT = new Button("AKT");
        AKT.addActionListener(e->{
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    model.reset();
                    model.akt();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            now = thread;
        });
        Button justHaminton = new Button(" Chỉ dùng Euclid");
        justHaminton.addActionListener(e->{
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    model.reset();
                    model.haminton();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            now = thread;
        });
        Button reset = new Button("Reset");
        reset.addActionListener(e-> {
            model.reset();
            System.out.println(Config.getInstance().getSpeed());
        });
        Button speedUp = new Button("Tăng tốc độ tìm kiếm");
        speedUp.addActionListener(e-> {
            Config.getInstance().speedUp();
        });
        Button speedDown = new Button("Giảm tốc độ tìm kiếm");
        speedDown.addActionListener(e-> {
            Config.getInstance().speedDown();
        });
        Button turnOffGrid = new Button("Tắt viền");
        turnOffGrid.addActionListener(e ->{
            Config config =Config.getInstance();
            Boolean isGrid = config.getGrid();
            if(isGrid) {
                turnOffGrid.setLabel("Bật viền");
                config.setGrid(false);
            } else {
                turnOffGrid.setLabel("Tắt viền");
                config.setGrid(true);
            }
            repaint();
        });
        add(reset);
        add(wall);
        add(way);
        add(start);
        add(goal);
        add(dfs);
        add(bfs);
        add(aStar);
        add(AKT);
        add(justHaminton);
        add(speedUp);
        add(speedDown);
        add(clear);
        add(turnOffGrid);
    }

    public void setMouseHandler(MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Size size = Config.getInstance().getMapSize();
        Size cellSize = Config.getInstance().getCellSize();
        for(int i = 0 ; i < size.getWidth() ; ++i ) {
            for (int j = 0 ; j < size.getLength(); ++j) {
                g2d.setPaint(grid[i][j]);
                g2d.fillRect(i*cellSize.getWidth(),j*cellSize.getLength(),cellSize.getWidth(),cellSize.getLength());
            }
        }
        if(Config.getInstance().getGrid())
        for(int i = 0 ; i < size.getWidth() ; ++i ) {
            for (int j = 0 ; j < size.getLength(); ++j) {
                g2d.setPaint(Color.GRAY);


                g2d.drawRect(i*cellSize.getWidth(),j*cellSize.getLength(),cellSize.getWidth(),cellSize.getLength());
            }
        }
    }

   public void setColor(int i, int j, Color c) {
        grid[i][j] = c;
        repaint();
   }

    public void setGrid(Color[][] grid) {
        this.grid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = toPoint(e.getX(),e.getY());
        mouseHandler.operate(point.getX(),point.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseClicked(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public Point toPoint(int i,int j ) {
        Size cellSize = Config.getInstance().getCellSize();
        double xp = (double)(i /(double)cellSize.getWidth());
        double yp = (double) (j /(double)cellSize.getLength());
        int x = (int)Math.ceil(xp);
        int y = (int)Math.ceil(yp);
        return new Point(x-1,y-1);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseClicked(e);

    }

    public void handleClick(MouseEvent e) {
        Runnable handler = new Runnable() {
            @Override
            public void run() {
                mouseClicked(e);
            }
        };
        Thread runner = new Thread(handler);
        runner.start();
    }
    int i = 0;
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
