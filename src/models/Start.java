package models;

import java.awt.*;

public class Start extends CellType {

    @Override
    public String getChar() {
        return "X";
    }

    @Override
    public void reset() {

    }

    @Override
    public void setRout() {

    }

    @Override
    public void markMoved() {

    }
    Start() {
        Color startColor = Config.getInstance().getStartColor();
        super.setColor(startColor);
    }
    @Override
    public boolean isCanMove() {
        return false;
    }
}
