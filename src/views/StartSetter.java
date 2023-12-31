package views;
import models.Map;
import models.Start;

public class StartSetter implements MouseHandler {
    Map map;
    @Override
    public void operate(int x, int y) {
        map.setStart(x,y);
    }
    StartSetter(Map map) {
        this.map = map;
    }
}
