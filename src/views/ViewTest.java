package views;

import models.Map;

public class ViewTest {
    Map map;

    public ViewTest(Map map) {
        this.map = map;
    }

    public void show() {
        for(var row : map.getMaze()) {
            for(var cell: row) {
                System.out.printf(cell.getChar());
            }
            System.out.println();
        }
    }
}
