package ImageLoad;

import java.awt.image.BufferedImage;

public class Assets {

    //width and height
    public static final int TILEWIDTH = 48, TILEHEIGHT =48;
    //Game
    public static BufferedImage packman_UP, packman_DOWN, packman_LEFT, packman_RIGHT;
    public static BufferedImage ghost1, ghost2, ghost3, ghost4, ghost5, ghost6;
    public static BufferedImage upperWall, rightWall, leftWall, lowerWall;
    public static BufferedImage upperU, rightU, leftU, lowerU;
    public static BufferedImage rightUpperCorner, rightLowerCorner, leftUpperCorner, leftLowerCorner;
    public static BufferedImage updowntunnel, rightleftTunnel, ground, allWall;
    //GameOverWindow in the game
    public static BufferedImage gameOverWindow;
    //Menu
    public static BufferedImage menuBackground;

    public static void init(){
        //PACKMAN
        Spritsheet allPackman = new Spritsheet(ImageLoader.loadImage("/textures/packman.png"));
        packman_RIGHT = allPackman.getTile(0,0, TILEWIDTH, TILEHEIGHT);
        packman_DOWN = allPackman.getTile(TILEWIDTH,0, TILEWIDTH, TILEHEIGHT);
        packman_UP = allPackman.getTile(TILEWIDTH * 2,0, TILEWIDTH, TILEHEIGHT);
        packman_LEFT = allPackman.getTile(TILEWIDTH * 3,0, TILEWIDTH, TILEHEIGHT);
        //GEISTER
        Spritsheet allGhosts = new Spritsheet(ImageLoader.loadImage("/textures/ghost.png"));
        ghost1 = allGhosts.getTile(0,0, TILEWIDTH, TILEHEIGHT);
        ghost2 = allGhosts.getTile(TILEWIDTH,0, TILEWIDTH, TILEHEIGHT);
        ghost3 = allGhosts.getTile(TILEWIDTH * 2,0, TILEWIDTH, TILEHEIGHT);
        ghost4 = allGhosts.getTile(TILEWIDTH * 3,0, TILEWIDTH, TILEHEIGHT);
        ghost5 = allGhosts.getTile(TILEWIDTH * 4,0, TILEWIDTH, TILEHEIGHT);
        ghost6 = allGhosts.getTile(TILEWIDTH * 5,0, TILEWIDTH, TILEHEIGHT);
        //BACKGROUND
        Spritsheet background = new Spritsheet(ImageLoader.loadImage("/textures/background.png"));
        upperWall = background.getTile(0,0, TILEWIDTH, TILEHEIGHT);
        rightWall = background.getTile(TILEWIDTH,0 , TILEWIDTH, TILEHEIGHT);
        leftWall = background.getTile(TILEWIDTH * 2,0, TILEWIDTH, TILEHEIGHT);
        lowerWall = background.getTile(TILEWIDTH * 3,0, TILEWIDTH, TILEHEIGHT);
        upperU = background.getTile(0, TILEHEIGHT, TILEWIDTH, TILEHEIGHT);
        rightU = background.getTile(TILEWIDTH, TILEHEIGHT, TILEWIDTH, TILEHEIGHT);
        leftU = background.getTile(TILEWIDTH * 2, TILEHEIGHT, TILEWIDTH, TILEHEIGHT);
        lowerU = background.getTile(TILEWIDTH * 3, TILEHEIGHT, TILEWIDTH, TILEHEIGHT);
        rightUpperCorner = background.getTile(0, TILEHEIGHT * 2, TILEWIDTH, TILEHEIGHT);
        rightLowerCorner = background.getTile(TILEWIDTH, TILEHEIGHT * 2, TILEWIDTH, TILEHEIGHT);
        leftUpperCorner = background.getTile(TILEWIDTH * 2, TILEHEIGHT * 2, TILEWIDTH, TILEHEIGHT);
        leftLowerCorner = background.getTile(TILEWIDTH * 3, TILEHEIGHT * 2, TILEWIDTH, TILEHEIGHT);
        rightleftTunnel = background.getTile(0, TILEHEIGHT * 3, TILEWIDTH, TILEHEIGHT);
        updowntunnel = background.getTile(0, TILEHEIGHT * 3, TILEWIDTH, TILEHEIGHT);
        rightleftTunnel = background.getTile(TILEWIDTH, TILEHEIGHT * 3, TILEWIDTH, TILEHEIGHT);
        ground = background.getTile(TILEWIDTH * 2, TILEHEIGHT * 3, TILEWIDTH, TILEHEIGHT);
        allWall = background.getTile(TILEWIDTH * 3, TILEHEIGHT *3, TILEWIDTH, TILEHEIGHT);

        gameOverWindow = ImageLoader.loadImage("/textures/GameOverWindow.png");

        //MenuBackground
        menuBackground = ImageLoader.loadImage("/textures/menuBackground.png");
    }
}
