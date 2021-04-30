package Componts;

import java.awt.*;
import java.awt.event.MouseEvent;

public class object {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public object(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // own Methods
    protected boolean intersect(Rectangle r, Point p) {
        return r.contains((int) p .getX(), (int) p.getY());
    }


    // extra Methods
    public void tick() {}
    public void render(Graphics g){}
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}

    // GETTER && SETTER
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Rectangle getBodyRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
