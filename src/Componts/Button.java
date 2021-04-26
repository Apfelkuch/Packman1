package Componts;

import Main.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Button extends object{

    Handler handler;

    //graphic data
    private Color backgroundColor = Color.pink;
    private Color pressedColor = backgroundColor.darker();

    private int CornerRounds;

    private boolean enabled;
    private boolean pressed;

    private String text;
    private Font font = new Font(null,Font.PLAIN,20);
    private final ActionListener listener;

    public Button(ActionListener listener, Handler handler, String text, int x, int y, int width, int height) {
        this.listener = listener;
        this.handler = handler;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.CornerRounds = 0;
        enabled = true;
    }

    public void render(Graphics g) {
        if(pressed) {
            g.setColor(pressedColor);
        } else {
            g.setColor(backgroundColor);
        }

        if (enabled) {
            g.fillRoundRect(x, y, width, height, CornerRounds, CornerRounds);
            g.setFont(font);
            g.setColor(Color.BLACK);
            int stringWidth = g.getFontMetrics().stringWidth(text);
            g.drawString(text,(x + width / 2) - stringWidth / 2, y + (height / 2) + (font.getSize() / 3));
        }
    }

    private boolean isPressed(int x, int y) {
        return x >= this.x && x <= this.x + width
                && y >= this.y && y <= this.y + height;
    }

    public void mousePressed(MouseEvent e) {
        if(enabled && isPressed(e.getX(),e.getY())) {
            pressed = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if(enabled && pressed) {
            pressed = false;
            if(isPressed(e.getX(),e.getY())) {
                listener.actionPerformed(new ActionEvent(this, 0, "ButtonAction"));
            }
        }
    }

    // GETTER && SETTER
    public boolean isEnabled() {
        return enabled;
    }
    public int getCornerRounds() {
        return CornerRounds;
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public String getText() {
        return text;
    }
    public Font getFont() {
        return  font;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setCornerRounds(int cornerRounds) {
        this.CornerRounds = cornerRounds;
    }
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        pressedColor = backgroundColor.darker();
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setFont(Font font) {
        this.font = font;
    }
}
