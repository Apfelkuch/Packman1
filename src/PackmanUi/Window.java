package PackmanUi;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private final Canvas canvas = new Canvas();

    public Window() {
        this.setSize(new Dimension(1200,500));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(canvas);
        canvas.setPreferredSize(this.getSize());
        this.pack();
        this.setVisible(true);
    }

    // GETTER && SETTER
    public Canvas getCanvas(){
        return canvas;
    }

    public void setSize(Dimension size) {
        this.setSize(size.width, size.height);
    }
}
