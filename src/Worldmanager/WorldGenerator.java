package Worldmanager;

import ImageLoad.Assets;
import Main.Handler;
import Tiles.Tile;
import Tiles.TileManager;
import Tiles.Tiles;
import Utility.CustomFileReader;

import java.awt.*;

public class WorldGenerator {
    private int[][] worldGrid;
    private int width,height;
    private int spawnX, spawnY;
    private int ghostSpawnX, ghostSpawnY;
    private int ghostCount;
    private Handler handler;
    private Tiles tiles;
    private PowerupManager powerupManager;

    /////////////////////////////////////////////////////////Class
    public WorldGenerator(String path, Handler handler){
        this.handler = handler;
        tiles = new Tiles(); // initialize the Tiles
        genWorld(path); // generate the world
        powerupManager = new PowerupManager(this, handler);
    }

    /**
     * generates the world from a file, which hold the world data.
     * Then the world is modify via {Methode: modifyWorld()}
     * @param path: the file-path of the Data file
     */
    public void genWorld(String path){
        String file = CustomFileReader.loadFileAsString(path);
        String[] tokens = file.split("\\s+"); // split on one or many whitespaces
        width = CustomFileReader.parseInt(tokens[0]);
        height = CustomFileReader.parseInt(tokens[1]);
        spawnX = CustomFileReader.parseInt(tokens[2]) * Assets.TILEWIDTH;
        spawnY = CustomFileReader.parseInt(tokens[3]) * Assets.TILEHEIGHT;
        ghostSpawnX = CustomFileReader.parseInt(tokens[4]) * Assets.TILEWIDTH;
        ghostSpawnY = CustomFileReader.parseInt(tokens[5]) * Assets.TILEHEIGHT;
        ghostCount = CustomFileReader.parseInt(tokens[6]);
        worldGrid = new int[width][height];
        for (int y = 0 ; y< height; y++){
            for (int x = 0; x< width; x++){
                worldGrid[x][y] = CustomFileReader.parseInt(tokens[(x + y* width)+7]);
            }
        }
        modifyWorld(worldGrid);
    }

    public void tick(){

    }

    /**
     * Returns the Tile in the worldGrid at the given location(x,y)
     * @param x: x-location
     * @param y: y-location
     * @return Tile at the given location.
     */
    public Tile getTile (int x ,int y){
        if(TileManager.TILES == null || TileManager.TILES.size() == 0) {
            System.out.println("[WorldGenerator/getTile] Tile-List-size = 0 or Tiles == null");
        }
//        System.out.println("[WorldGenerator/getTile] " + worldGrid[x][y]);
//        System.out.println("[WorldGenerator/getTile] Tiles.size:" + TileManager.TILES.size());
        if(TileManager.TILES.size() == 0) return null;
        Tile t = TileManager.TILES.get(worldGrid[x][y]);
        if (t == null ) {
            System.out.println("[WorldGenerator/getTile] Tile is null at x:" + x + " ,y:" + y);
            return TileManager.TILES.get(0);
        }
        return t;
    }

    public void render(Graphics g){
        for (int y = 0 ; y< height; y++){
            for (int x = 0; x< width; x++){
                getTile(x,y).render(g,x * Assets.TILEWIDTH,y * Assets.TILEHEIGHT);
            }
        }
        powerupManager.render(g);
    }

    /**
     * Modify the world to chose the perfect tile at every location.
     * @param world: the world as 2d int-Array, which is to by modified.
     */
    public void modifyWorld(int[][] world) {
        // 1 = wall // 0 = free
        String pattern = "";// wall = LEFT RIGHT UP DOWN
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (world[x][y]!= 0) {
                    if ( x == 0) {
                        pattern += 0;
                    }else{
                        if ( world[x - 1][y] != 0) { // check Wall Left
                            pattern += 1;
                        } else {
                            pattern += 0;
                        }
                    }
                    if (x == width -1) {
                        pattern += 0;
                    }else {
                        if ( world[x + 1][y] != 0 ){ // check Wall Right
                            pattern += 1;
                        } else{
                            pattern += 0;
                        }
                    }
                    if ( y == 0) {
                        pattern += 0;
                    }else {
                        if (world[x][y - 1] != 0) { // check Wall Up
                            pattern += 1;
                        } else {
                            pattern += 0;
                        }
                    }
                    if ( y == height - 1 ) {
                        pattern += 0;
                    }else {
                        if (world[x][y + 1] != 0) { // check Wall Down
                            pattern += 1;
                        } else {
                            pattern += 0;
                        }
                    }
                    worldGrid[x][y] = getIdFromPattern(pattern);
//                    System.out.println("[WorldGenerator/modifyWorld] An der Stelle "+"x="+x+" "+"y="+y+" "+"ist das pattern"+pattern);
                    pattern ="";
                }
            }
        }
    }

    public int getIdFromPattern (String pattern){
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
    public PowerupManager getPowerupManager() {
        return powerupManager;
    }
}
























