package Componts;

import java.awt.*;
import java.awt.event.MouseEvent;

// TODO checkbox checking
//  Checking feels a bit strange because the check is only inverted, if the mouse is pressed and released at the same position.
//  If the mouse is moved the check is not inverted.
public class Checkbox extends Object {

    private boolean checked = false;

    private boolean inverted = false;

    private Color foregroundColor = Color.BLACK;
    private Color backgroundColor = Color.WHITE;
    private Color borderColor = foregroundColor;

    private int style = 0;
    public static final int boxStyle = 0; // default
    public static final int circleStyle = 1; //

    public Checkbox(int x, int y, int width, int height, int style) {
        super(x, y, width, height);
        this.style = style;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if(style == circleStyle) {
            // draw border
            g.setColor(foregroundColor);
            g.fillOval(x, y, width, height);
            // draw background
            g.setColor(backgroundColor);
            g.fillOval(x + (width / 10), y + (width / 10), (width * 8)  / 10, (height * 8) / 10);
            // draw checked background
            if (checked) {
                if(inverted) {
                    g.setColor(foregroundColor);
                    g.fillOval(x + (width / 10), y + (width / 10), (width * 8)  / 10, (height * 8) / 10);
                    g.setColor(backgroundColor);
                } else {
                    g.setColor(foregroundColor);
                }
                // draw checked symbol
                g.fillOval(x + (width * 5) / 16, y + (height * 5) / 16, (width * 6) / 16, (height * 6) / 16);
            }
        } else { // boxStyle is the default
            // draw border
            g.setColor(foregroundColor);
            g.fillRect(x, y, width, height);
            // draw background
            g.setColor(backgroundColor);
            g.fillRect(x + (width / 10), y + (width / 10), (width * 8)  / 10, (height * 8) / 10);
            // draw checked background
            if (checked) {
                if(inverted) {
                    g.setColor(foregroundColor);
                    g.fillRect(x + (width / 10), y + (width / 10), (width * 8)  / 10, (height * 8) / 10);
                    g.setColor(backgroundColor);
                } else {
                    g.setColor(foregroundColor);
                }
                // draw checked symbol
                g.fillRect(x + (width * 5) / 16, y + (height * 5) / 16, (width * 6) / 16, (height * 6) / 16);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(intersect(new Rectangle(x, y, width, height), e.getPoint())) { // the checked is inverted if the mouse is clicked above the checkbox
            checked = !checked;
        }
    }

    // GETTER && SETTER

    public boolean isChecked() {
        return checked;
    }
    public boolean isInverted() {
        return inverted;
    }
    public Color getForegroundColor() {
        return foregroundColor;
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public Color getBorderColor() {
        return borderColor;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }
    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
}
