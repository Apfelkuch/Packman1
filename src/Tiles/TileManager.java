package Tiles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {

    public static ArrayList<Tile> TILES;

    public TileManager () {
        TILES = new ArrayList<Tile>();
    }

    /**
     * Add a Tile to the TILES-SET
     * @param name: The name of the Tile
     * @param img: The BufferedImage which presents the Tile
     * @return true: Tile is added, false: Tile is not added because the class is not instantiated or the parameters are null or the name is redundant.
     */
    public boolean addTile(String name, BufferedImage img, boolean solid) {
        if(TILES == null || name == null || img == null) return false; // check if tiles & the Tile is creatable

        for (Tile t : TILES) { // check if the id or the name is already used
            if (t.name == name)
                return false;
        }

        int id = TILES.size();

        TILES.add(new Tile(name, id, img, solid));

        return true;
    }

}
