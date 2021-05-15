package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    protected String name;
    protected final int id;
    protected BufferedImage texture;
    protected boolean isSolid;

    public Tile(String name, int id, BufferedImage texture, boolean isSolid) {
        this.name = name;
        this.id = id;
        this.texture = texture;
        this.isSolid = isSolid;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y, int width, int height) {
//        g.drawImage(texture, x, y, Assets.TILEWIDTH, Assets.TILEHEIGHT, null);
        g.drawImage(texture, x, y, width, height, null);
    }

    // GETTER & SETTER
    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public boolean isSolid() {
        return isSolid;
    }
}

