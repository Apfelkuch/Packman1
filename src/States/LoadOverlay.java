package States;

import Main.Handler;

import java.awt.*;

public class LoadOverlay {

    private Handler handler;

    public LoadOverlay(Handler handler) {
        this.handler = handler;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        System.out.println("LoadOverlay.render");
        g.setColor(Color.CYAN);
        g.fillRect(10, 10, 120, 120);
    }

}
