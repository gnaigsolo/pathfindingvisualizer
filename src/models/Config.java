package models;

import java.awt.*;

public class Config {
    static Config instance;
    private Config() {
        setMaxSize();
    }
    static public Config getInstance() {
        if(instance == null) {
            instance = new Config();
        }
        return instance;
    }
    Boolean isGrid;
    Color startColor;
    private int slowest = 50;
    private int speed = 100;
    Color wallColor;
    Color wayColor;
    Color wayVisitedColor;
    Color roadColor;
    Color roadVisitedColor;
    Color routColor;
    Size mapSize;
    Size cellSize;

    public void setStartColor(Color startColor) {
        this.startColor = startColor;
    }


    public Color getStartColor() {
        return startColor;
    }

    public void setRoadVisitedColor(Color roadVisitedColor) {
        this.roadVisitedColor = roadVisitedColor;
    }

    public Color getRoadVisitedColor() {
        return roadVisitedColor;
    }

    public Color getWayVisitedColor() {
        return wayVisitedColor;
    }

    public void setWayVisitedColor(Color wayVisitedColor) {
        this.wayVisitedColor = wayVisitedColor;
    }


    public static void setInstance(Config instance) {
        Config.instance = instance;
    }

    public void setWallColor(Color wallColor) {
        this.wallColor = wallColor;
    }

    public void setWayColor(Color wayColor) {
        this.wayColor = wayColor;
    }

    public void setRoadColor(Color roadColor) {
        this.roadColor = roadColor;
    }

    public void setMapSize(Size mapSize) {
        this.mapSize = mapSize;
    }

    public Color getWallColor() {
        return wallColor;
    }

    public Color getWayColor() {
        return wayColor;
    }

    public Color getRoadColor() {
        return roadColor;
    }

    public Size getMapSize() {
        return mapSize;
    }

    public void setDefault() {
        isGrid = true;
        startColor = Color.green;
        roadColor = Color.red;
        roadVisitedColor = Color.BLUE;
        wallColor = Color.black;
        wayColor = Color.white;
        wayVisitedColor = Color.CYAN;
        routColor = Color.gray;
        mapSize = new Size(30,30);
        cellSize = new Size(30,30);
        setSpeed(10);
    }

    public void setCellSize(Size cellSize) {
        this.cellSize = cellSize;
    }

    public int getSlowest() {
        return slowest;
    }

    public Size getCellSize() {
        return cellSize;
    }

    public void setTheme1() {
        startColor = new Color(1, 127, 167);
        roadColor = new Color(92, 49, 104);
        roadVisitedColor = new Color(245, 209, 174);
        wallColor = new Color(27, 24, 90);
        wayColor = Color.white;
        wayVisitedColor = new Color(234, 135, 121);
        routColor = new Color(180, 198, 177);
        mapSize = new Size(35,64);
        cellSize = new Size(30,30);
    }

    public void setGrid(Boolean grid) {
        isGrid = grid;
    }

    public Boolean getGrid() {
        return isGrid;
    }

    public void setMaxSize() {
        setDefault();
        startColor = new Color(92, 49, 104);
        roadColor = new Color(49, 44, 166);
        roadVisitedColor = new Color(114, 242, 64);
        wallColor = new Color(27, 24, 90);
        wayColor = Color.white;
        wayVisitedColor = Color.cyan;
        routColor = Color.blue;
        setMapSize(new Size(100,200));
        setCellSize(new Size(8,8));
    }

    public void speedUp() {
        if(slowest == 1) {
            this.slowest = 0;
            return;
        }
        if(slowest ==0) {
            return;
        }
        this.setSpeed(this.slowest - 10);
    }

    public void speedDown() {
        if(slowest ==0) {
            this.slowest = 1;
            return;
        }
        this.setSpeed(this.slowest + 10);
    }
    int i = 0;
    public Color getRoutColor() {

        return routColor;
    }

    public void setSpeed(int speed) {
        speed = Math.max(speed,1);
        speed = Math.min(speed,50);
        this.slowest = speed;
    }
    public int getSpeed() {
        if(speed != 0)
            return this.slowest;
        return slowest;
    }
}
