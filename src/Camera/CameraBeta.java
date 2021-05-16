package Camera;

import EntitySystem.Entity;
import Worldmanager.WorldGenerator;

import java.awt.*;

public class CameraBeta extends Camera { // TODO

    private float offsetX;
    private float offsetY;

    private float width;
    private float height;

    private WorldGenerator worldGenerator;

    private int renderX, renderY, renderWidth, renderHeight;

    public CameraBeta(WorldGenerator worldGenerator, float offsetX, float offsetY, float width, float height) {
        this.worldGenerator = worldGenerator;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
    }

    public void move(float posX, float posY) {

    }

    @Override
    public void render(Graphics g) {
        float a = offsetX /
        worldGenerator.render(g , );
    }

    @Override
    public void renderEntities(Graphics g, Entity entity) {

    }
}
