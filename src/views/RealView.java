package views;

import models.Map;
import models.Point;

import javax.swing.*;
import java.awt.*;

public class RealView extends JFrame implements  View{
    MyPanel myPanel;
    Map model;
    public RealView(Map model) {
        super("Trình mô phỏng các thuật toán tìm kiếm");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.myPanel = new MyPanel(model);
        this.add(myPanel);
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        this.setVisible(true);
        this.model = model;
    }

    @Override
    public void update() {
        myPanel.setGrid(model.getColorGrid());
        myPanel.repaint();
    }
}
