package models;
import java.awt.*;
public abstract class CellType {
    Color color;
    public abstract String getChar();
    public abstract void reset();
    public abstract void setRout();
    public abstract void markMoved();
    public abstract boolean isCanMove();
    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
