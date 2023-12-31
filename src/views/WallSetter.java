package views;
import models.*;
public class WallSetter implements MouseHandler {
    Map map;
    @Override
    public void operate(int x, int y) {
        map.setWall(x,y);
    }
    WallSetter(Map map) {
        this.map = map;
    }
}
