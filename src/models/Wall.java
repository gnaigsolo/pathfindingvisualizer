package models;

import java.awt.*;

public class Wall extends CellType{
    Wall() {
        Color wallColor = Config.instance.getWallColor();
        super.setColor(wallColor);
    }

    @Override
    public String getChar() {
        return "I";
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

    @Override
    public boolean isCanMove() {
        return false;
    }
}
