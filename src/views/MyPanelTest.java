package views;

import javax.swing.*;
import java.awt.*;

public class MyPanelTest extends JFrame {
    MyPanel myPanel;
    public MyPanelTest() {
        this.myPanel = new MyPanel(null);
        this.add(myPanel);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
    public void setColor(int i, int j, Color c) {
        myPanel.setColor(i,j,c);
    }
}
