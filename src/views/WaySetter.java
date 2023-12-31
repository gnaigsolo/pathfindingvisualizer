package views;
import models.Map;
public class WaySetter implements MouseHandler {
    Map map;
    @Override
    public void operate(int x, int y) {
        map.setWay(x,y);
    }
    WaySetter(Map map) {
        this.map = map;
    }
}
