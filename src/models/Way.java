package models;

import java.awt.*;

public class Way extends CellType{
    boolean isVisited;
    Way() {
        isVisited = false;
        Color wayColor = Config.getInstance().getWayColor();
        super.setColor(wayColor);
    }

    @Override
    public String getChar() {
        if(this.isVisited == true)
            return "I";
        return "i";
    }

    @Override
    public void reset() {
        isVisited = false;
        Color wayColor = Config.instance.getWayColor();
        super.setColor(wayColor);
    }
    public void setRout() {
        Color routColor = Config.getInstance().getRoutColor();
        super.setColor(routColor);
    }

    @Override
    public void markMoved() {
        isVisited = true;
        Color wayVisitedColor = Config.instance.getWayVisitedColor();
        super.setColor(wayVisitedColor);
    }

    @Override
    public boolean isCanMove() {
        return !isVisited;
    }
}
