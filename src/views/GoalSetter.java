package views;
import models.Map;
public class GoalSetter implements MouseHandler{
    Map map;
    @Override
    public void operate(int x, int y) {
        map.setRoad(x,y);
    }
    GoalSetter(Map map) {
        this.map = map;
    }
}
