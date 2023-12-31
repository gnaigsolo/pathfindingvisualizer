import models.*;
import views.*;

import java.awt.*;


public class Main {
    public static void main(String[] args) {
        

        Map map = new Map();
        RealView view = new RealView(map);
        map.setView(view);



    }
}
