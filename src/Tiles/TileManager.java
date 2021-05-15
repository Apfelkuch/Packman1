package Tiles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {

    private ArrayList<Tile> tiles;

    public TileManager() {
        tiles = new ArrayList<Tile>();
    }

    /**
     * Add a Tile to the TILES-SET
     *
     * @param name  The name of the Tile
     * @param img   The BufferedImage which presents the Tile
     * @param solid True if the Tile is solid, otherwise false.
     * @return true: Tile is added, false: Tile is not added because the class is not instantiated or the parameters are null or the name is redundant.
     */
    public boolean addTile(String name, BufferedImage img, boolean solid) {
        if (tiles == null || name == null || img == null) return false; // check if tiles & the Tile is creatable

        for (Tile t : tiles) { // check if the id or the name is already used
            if (t.name.equals(name))
                return false;
        }

        int id = tiles.size();

        tiles.add(new Tile(name, id, img, solid));

        return true;
    }

    // GETTER && SETTER

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
