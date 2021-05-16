package Camera;

import EntitySystem.Entity;
import ImageLoad.Assets;
import Worldmanager.WorldGenerator;

import java.awt.*;

// TODO camera
public class Camera2DWorldGrid extends Camera{

    private float x;
    private float y;

    private int verticalTileCount;
    private int horizontalTileCount;

    private int oldTopLeftX = -1;
    private int oldTopLeftY = -1;
    private int oldDownRightX = -1;
    private int oldDownRightY = -1;

    private int topLeftX;
    private int topLeftY;
    private int downRightX;
    private int downRightY;

    private int windowWidth;
    private int windowHeight;

    private int entityRenderExtra = 2;

    private boolean noCamera;

    private final WorldGenerator worldGenerator;

    public static final int CameraFormat = 0;
    public static final int CameraFormat1 = 1;

    public Camera2DWorldGrid(WorldGenerator worldGenerator, int x, int y, int verticalTileCount, int horizontalTileCount, int windowWidth, int windowHeight) {
        this.x = x / Assets.TILEWIDTH - verticalTileCount / 2;
        this.y = y / Assets.TILEHEIGHT - horizontalTileCount / 2;
        this.verticalTileCount = verticalTileCount;
        this.horizontalTileCount = horizontalTileCount;
        this.worldGenerator = worldGenerator;
        this.noCamera = false;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public Camera2DWorldGrid(WorldGenerator worldGenerator) {
        this.worldGenerator = worldGenerator;
        this.noCamera = true;
    }

    public void showAround(float xPos, float yPos, int format) {
        // Set the x and y of the camera that xPos, yPos is in the center.
        x = (xPos / Assets.TILEWIDTH) - verticalTileCount / 2;
        y = (yPos / Assets.TILEHEIGHT) - horizontalTileCount / 2;

        // Set the borders for the Tiles that needs to be rendered.
        topLeftX = (int) Math.floor(x);
        topLeftY = (int) Math.floor(y);
        downRightX = (int) Math.ceil(x + verticalTileCount);
        downRightY = (int) Math.ceil(y + horizontalTileCount);

        // Adjust the render border for the tiles whit a certain format.
        // The format is given with the methode.
        if (format == CameraFormat1) {
            // The filled is always th size of viewedTilesInWidth * viewedTilesInHeight and xPos, yPos is in this.
            // If the camera view collides with a border the camera view is not moved.
            if (topLeftX < 0) {
                if (oldDownRightX != -1) {
                    downRightX = oldDownRightX;
                }
                topLeftX = 0;
            } else {
                oldDownRightX = downRightX;
            }
            if (topLeftY < 0) {
                if (oldDownRightY != -1) {
                    downRightY = oldDownRightY;
                }
                topLeftY = 0;
            } else {
                oldDownRightY = downRightY;
            }
            if (downRightX > (worldGenerator.getWorldGrid().length)) {
                if (oldTopLeftX != -1) {
                    topLeftX = oldTopLeftX;
                }
                downRightX = worldGenerator.getWorldGrid().length;
            } else {
                oldTopLeftX = topLeftX;
            }
            if (downRightY > (worldGenerator.getWorldGrid()[0].length)) {
                if (oldTopLeftY != -1) {
                    topLeftY = oldTopLeftY;
                }
                downRightY = worldGenerator.getWorldGrid()[0].length;
            } else {
                oldTopLeftY = topLeftY;
            }
        } else { // format == CameraFormat is the default
            // Set the render border to the max if the border is greater than the max border.
            if (topLeftX < 0) {
                topLeftX = 0;
            }
            if (topLeftY < 0) {
                topLeftY = 0;
            }
            if (downRightX > (worldGenerator.getWorldGrid().length)) {
                downRightX = worldGenerator.getWorldGrid().length;
            }
            if (downRightY > (worldGenerator.getWorldGrid()[0].length)) {
                downRightY = worldGenerator.getWorldGrid()[0].length;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (noCamera) {
            worldGenerator.render(g);
        } else {
            g.fillRect(0, 0, windowWidth, windowHeight);
//            worldGenerator.render(g, topLeftX, topLeftY, downRightX, downRightY, windowWidth / verticalTileCount, windowHeight / horizontalTileCount);
            worldGenerator.render(g, topLeftX, topLeftY, downRightX, downRightY, 48, 48);
        }
    }

    @Override
    public void renderEntities(Graphics g, Entity entity) {
        if (noCamera) {
            entity.render(g);
        } else {
            if(entity.getPosX() / Assets.TILEWIDTH > topLeftX - entityRenderExtra &&
                    entity.getPosY() / Assets.TILEHEIGHT > topLeftY -entityRenderExtra &&
                    (entity.getPosX() + entity.getCBwidth()) / Assets.TILEWIDTH < downRightX +entityRenderExtra &&
                    (entity.getPosY() + entity.getCBheight()) / Assets.TILEHEIGHT < downRightY + entityRenderExtra) {
                entity.render(g);
            }
        }
    }

    // GETTER && SETTER

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getVerticalTileCount() {
        return verticalTileCount;
    }

    public int getHorizontalTileCount() {
        return horizontalTileCount;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setVerticalTileCount(int verticalTileCount) {
        this.verticalTileCount = verticalTileCount;
    }

    public void setHorizontalTileCount(int horizontalTileCount) {
        this.horizontalTileCount = horizontalTileCount;
    }
}
