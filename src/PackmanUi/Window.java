package PackmanUi;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private final Canvas canvas = new Canvas();

    public Window() {
        this.setSize(new Dimension(1200, 500));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        canvas.setPreferredSize(new Dimension(1200, 500));
        canvas.setPreferredSize(this.getSize());
        this.add(canvas);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // GETTER && SETTER
    public Canvas getCanvas() {
        return canvas;
    }

    public void setSize(Dimension size) {
        this.setSize(size.width, size.height);
//        canvas.setPreferredSize(size);
//        this.pack();
//        this.setLocationRelativeTo(null);
    }
}
