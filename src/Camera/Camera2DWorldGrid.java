package Camera;

import ImageLoad.Assets;
import Tiles.Tiles;
import Worldmanager.WorldGenerator;

import java.awt.*;

// TODO camera
public class Camera2DWorldGrid {

    private float x;
    private float y;

    private int width;
    private int height;

    private boolean noCamera;

    private final WorldGenerator worldGenerator;

    public Camera2DWorldGrid(WorldGenerator worldGenerator, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.worldGenerator = worldGenerator;
        this.noCamera = false;
    }

    public Camera2DWorldGrid(WorldGenerator worldGenerator) {
        this.worldGenerator = worldGenerator;
        this.noCamera = true;
    }

    public void move(float xMove, float yMove) {
        System.out.println("Camera2DWorldGrid.move");
        System.out.println("xMove = " + xMove);
        System.out.println("yMove = " + yMove);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        if ((x += xMove) < 0) {
            x = 0;
        } else if ((y += yMove) < 0) {
            y = 0;
        } else if ((x += xMove) > (worldGenerator.getWidth() * Assets.TILEWIDTH - width)) {
            x = worldGenerator.getWidth() * Assets.TILEWIDTH - width;
        } else if ((y += yMove) > (worldGenerator.getHeight() * Assets.TILEHEIGHT - height)) {
            y = worldGenerator.getHeight() * Assets.TILEHEIGHT - height;
        } else {
            x += xMove;
            y += yMove;
        }
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }

    public void moveTo(float toX, float toY) {
        float xDiff = x - toX;
        float yDiff = toY - y;
        move(xDiff, yDiff);
    }

    public void render(Graphics g) {
        if(noCamera) {
            worldGenerator.render(g);
        } else {
            worldGenerator.render(g, x, y, width, height);
        }
    }

    // GETTER && SETTER

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
