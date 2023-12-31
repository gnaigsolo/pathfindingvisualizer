package models;

import java.awt.*;

public class Road extends CellType {
    boolean isVisited;

    Road() {
        Color roadCollor = Config.getInstance().getRoadColor();
        super.setColor(roadCollor);
        isVisited = false;
    }

    @Override
    public String getChar() {
        return "X";
    }

    @Override
    public void reset() {
        isVisited = false;
        Color visitedColor = Config.instance.getRoadColor();
        super.setColor(visitedColor);
    }

    @Override
    public void setRout() {

    }

    @Override
    public void markMoved() {
        isVisited = true;
        Color roadVisitedColor = Config.instance.getRoadVisitedColor();
        super.setColor(roadVisitedColor);
    }

    @Override
    public boolean isCanMove() {
        return !isVisited;
    }
}
