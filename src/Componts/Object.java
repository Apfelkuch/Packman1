package Componts;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Object {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private boolean dragDrop = false;
    private Point prevPoint;

    public Object(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // own Methods
    public boolean intersect(Rectangle r, Point p) {
        return r.contains((int) p.getX(), (int) p.getY());
    }

    public void renderCollisionBox(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(x, y, width, height);
    }
    // extra Methods
    public void tick() {
    }

    public void render(Graphics g) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (dragDrop) {
            // set the mouse position to the prevPoint if the mouse is above the object.
            if (intersect(new Rectangle(x, y, width, height), e.getPoint())) {
                prevPoint = e.getPoint();
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (dragDrop) {
            // reset the prevPoint, if the mouse is released
            prevPoint = null;
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        if (dragDrop) {
            if (prevPoint != null) {
                // get the currentPoint mouse position
                Point currentPoint = e.getPoint();
                // move X,Y by the difference between the currentPoint and the prev Point
                x = x + (currentPoint.x - prevPoint.x);
                y = y + (currentPoint.y - prevPoint.y);
                // set currentPoint to the prevPoint (reset prevPoint for the next move)
                prevPoint = currentPoint;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    // ACTIVE && DEACTIVATE
    public void activateDragDrop() {
        dragDrop = true;
    }

    public void deactivateDragDrop() {
        dragDrop = false;
    }

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
