package Worldmanager;

import ImageLoad.Assets;
import Main.Handler;
import Tiles.Tile;
import Tiles.Tiles;
import Utility.CustomFileReader;

import java.awt.*;

public class WorldGenerator {
    private int[][] worldGrid;
    private int width, height;
    private int spawnX, spawnY;
    private int ghostSpawnX, ghostSpawnY;
    private int ghostCount;
    private final Handler handler;
    private final Tiles tiles;
    private final PowerUpManager powerupManager;

    /////////////////////////////////////////////////////////
    public WorldGenerator(String path, Handler handler) {
        this.handler = handler;
        tiles = new Tiles(); // initialize the Tiles
        genWorld(path); // generate the world1
        powerupManager = new PowerUpManager(this, handler);
    }

    /**
     * generates the world from a file, which hold the world data.
     * Then the world is modify via {Methode: modifyWorld()}
     *
     * @param path: the file-path of the Data file
     */
    public void genWorld(String path) {
        String file = CustomFileReader.loadFileAsString(path);
        String[] tokens = file.split("\\s+"); // split on one or many whitespaces
        width = Integer.parseInt(tokens[0]);
        height = Integer.parseInt(tokens[1]);
        spawnX = Integer.parseInt(tokens[2]) * Assets.TILEWIDTH;
        spawnY = Integer.parseInt(tokens[3]) * Assets.TILEHEIGHT;
        ghostSpawnX = Integer.parseInt(tokens[4]) * Assets.TILEWIDTH;
        ghostSpawnY = Integer.parseInt(tokens[5]) * Assets.TILEHEIGHT;
        ghostCount = Integer.parseInt(tokens[6]);
        worldGrid = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                worldGrid[x][y] = Integer.parseInt(tokens[(x + y * width) + 7]);
            }
        }
        modifyWorld(worldGrid);
    }

    public void tick() {

    }

    /**
     * Returns the Tile in the worldGrid at the given location(x,y)
     *
     * @param x: x-location
     * @param y: y-location
     * @return Tile at the given location.
     */
    public Tile getTile(int x, int y) {
        if (tiles.getTiles() == null || tiles.getTiles().size() == 0) {
            System.out.println("[WorldGenerator/getTile] Tile-List-size = 0 or Tiles == null");
        }
//        System.out.println("[WorldGenerator/getTile] " + worldGrid[x][y]);
//        System.out.println("[WorldGenerator/getTile] Tiles.size:" + tiles.getTiles().size());
        if (tiles.getTiles().size() == 0) return null;
        Tile t = tiles.getTiles().get(worldGrid[x][y]);
        if (t == null) {
            System.out.println("[WorldGenerator/getTile] Tile is null at x:" + x + " ,y:" + y);
            return tiles.getTiles().get(0);
        }
        return t;
    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(g, x * Assets.TILEWIDTH, y * Assets.TILEHEIGHT, Assets.TILEWIDTH, Assets.TILEHEIGHT);
            }
        }
        powerupManager.render(g);
    }

    public void render(Graphics g, int startX, int startY, int endX, int endY, int tileWidth, int tileHeight) {
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                getTile(x, y).render(g, x * tileWidth, y * tileHeight, tileWidth, tileHeight);
            }
        }
        powerupManager.render(g, startX, startY, endX, endY);
    }

    public void render(Graphics g, int offsetX, int offsetY, int startX, int startY, int endX, int endY, int tileWidth, int tileHeight) {
        System.out.println("WorldGenerator.render");
        System.out.println("offsetX = " + offsetX);
        System.out.println("offsetY = " + offsetY);
        System.out.println("startX = " + startX);
        System.out.println("startY = " + startY);
        System.out.println("endX = " + endX);
        System.out.println("endY = " + endY);
        System.out.println("tileWidth = " + tileWidth);
        System.out.println("tileHeight = " + tileHeight);
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                getTile(x, y).render(g, (x * tileWidth) + offsetX, (y * tileHeight) + offsetY, tileWidth, tileHeight);
            }
        }
        powerupManager.render(g, offsetX, offsetY, startX, startY, endX, endY);
    }

    /**
     * Modify the world to chose the perfect tile at every location.
     *
     * @param world: the world as 2d int-Array, which is to by modified.
     */
    public void modifyWorld(int[][] world) {
        // 1 = wall // 0 = free
        String pattern = "";// wall = LEFT RIGHT UP DOWN
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (world[x][y] != 0) {
                    if (x == 0) {
                        pattern += 0;
                    } else {
                        if (world[x - 1][y] != 0) { // check Wall Left
                            pattern += 1;
                        } else {
                            pattern += 0;
                        }
                    }
                    if (x == width - 1) {
                        pattern += 0;
                    } else {
                        if (world[x + 1][y] != 0) { // check Wall Right
                            pattern += 1;
                        } else {
                            pattern += 0;
                        }
                    }
                    if (y == 0) {
                        pattern += 0;
                    } else {
                        if (world[x][y - 1] != 0) { // check Wall Up
                            pattern += 1;
                        } else {
                            pattern += 0;
                        }
                    }
                    if (y == height - 1) {
                        pattern += 0;
                    } else {
                        if (world[x][y + 1] != 0) { // check Wall Down
                            pattern += 1;
                        } else {
                            pattern += 0;
                        }
                    }
                    worldGrid[x][y] = getIdFromPattern(pattern);
//                    System.out.println("[WorldGenerator/modifyWorld] At x: " + x + ", y: " + y + "is the pattern: " + pattern);
                    pattern = "";
                }
            }
        }
    }

    public int getIdFromPattern(String pattern) {
        //wall = LEFT RIGHT UP DOWN
        return Integer.parseInt(pattern, 2);
    }


    //GETTER & SETTER
    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public int getGhostSpawnX() {
        return ghostSpawnX;
    }

    public int getGhostSpawnY() {
        return ghostSpawnY;
    }

    public int getGhostCount() {
        return ghostCount;
    }

    public int[][] getWorldGrid() {
        return worldGrid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public PowerUpManager getPowerUpManager() {
        return powerupManager;
    }
}
























