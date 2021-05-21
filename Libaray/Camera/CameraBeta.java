package Camera;

import EntitySystem.Entity;
import ImageLoad.Assets;
import Worldmanager.WorldGenerator;

import java.awt.*;

public class CameraBeta extends Camera { // TODO

    private float offsetX; // x position relative to the world.
    private float offsetY; // y position relative to the world.

    private int windowWidth; // The window width.
    private int windowHeight; // The window height.

    private int verticalTileCount; // The Tile count in the vertical.
    private int horizontalTileCount; // The Tile count in the horizontal.

    private WorldGenerator worldGenerator; // The access to the world.

    private int entityRenderExtra = 2; // The extra around the shown area, where the Entities are already loaded. To avoid flickering when the Entities are seen.

    private int renderOffsetX, renderOffsetY, renderStartX, renderStartY, renderEndX, renderEndY; // start x, y and end x, y and offset x, y of the rendered area.

    public CameraBeta(WorldGenerator worldGenerator, float offsetX, float offsetY, int verticalTileCount, int horizontalTilCount, int windowWidth, int windowHeight) {
        this.worldGenerator = worldGenerator;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.verticalTileCount = verticalTileCount;
        this.horizontalTileCount = horizontalTilCount;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void move(float posX, float posY) {
//        offsetX = (posX / Assets.TILEWIDTH) - verticalTileCount / 2;
//        offsetY = (posY / Assets.TILEHEIGHT) - horizontalTileCount / 2;
        offsetX = posX - (verticalTileCount / 2) * Assets.TILEWIDTH;
        offsetY = posY - (horizontalTileCount / 2) * Assets.TILEHEIGHT;
        System.out.println("CameraBeta.move");
        System.out.println("offsetX = " + offsetX);
        System.out.println("offsetY = " + offsetY);

        renderStartX = (int) Math.floor(offsetX / Assets.TILEWIDTH);
        renderStartY = (int) Math.floor(offsetY / Assets.TILEHEIGHT);

        renderEndX = (int) Math.floor(offsetX / Assets.TILEWIDTH + verticalTileCount);
        renderEndY = (int) Math.floor(offsetY / Assets.TILEHEIGHT + horizontalTileCount);

        renderOffsetX = -Math.floorMod((int) offsetX, Assets.TILEWIDTH);
        renderOffsetY = -Math.floorMod((int) offsetY, Assets.TILEHEIGHT);
    }

    @Override
    public void render(Graphics g) {
        // Set the render border to the max if the border is greater than the max border.
        if (renderStartX < 0) {
            renderStartX = 0;
        }
        if (renderStartY < 0) {
            renderStartY = 0;
        }
        if (renderEndX > (worldGenerator.getWorldGrid().length)) {
            renderEndX = worldGenerator.getWorldGrid().length;
        }
        if (renderEndY > (worldGenerator.getWorldGrid()[0].length)) {
            renderEndY = worldGenerator.getWorldGrid()[0].length;
        }
        worldGenerator.render(g, renderOffsetX, renderOffsetY, renderStartX, renderStartY, renderEndX, renderEndY, windowWidth / verticalTileCount, windowHeight / horizontalTileCount);
//        worldGenerator.render(g, renderOffsetX, renderOffsetY, renderStartX, renderStartY, renderEndX, renderEndY, 48, 48);
    }

    @Override
    public void renderEntities(Graphics g, Entity entity) {
        if (entity.getPosX() / Assets.TILEWIDTH > renderStartX - entityRenderExtra &&
                entity.getPosY() / Assets.TILEHEIGHT > renderStartY - entityRenderExtra &&
                (entity.getPosX() + entity.getCBwidth()) / Assets.TILEWIDTH < renderEndX + entityRenderExtra &&
                (entity.getPosY() + entity.getCBheight()) / Assets.TILEHEIGHT < renderEndY + entityRenderExtra) {
            entity.render(g);
        }

    }
}
