package Worldmanager;

import EntitySystem.Dot;
import EntitySystem.Item;
import ImageLoad.Assets;
import Main.Handler;
import Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class PowerupManager {
    private ArrayList<Item> items;
    private ArrayList<Item> emptyPlaces;
    private WorldGenerator world;
    private ArrayList<Vektor2D> emptyTiles;
    private Handler handler;
    private int dotCount = 0;
    private int powerUpCout = 0;

    // itemType
    private final char Dot = 'D';

    public PowerupManager(WorldGenerator world, Handler handler) {
        this.world = world;
        this.handler = handler;
        emptyTiles = new ArrayList<Vektor2D>();
        items = new ArrayList<Item>();
        emptyPlaces = new ArrayList<Item>();

        findEmptySpaces();
        initDots();
    }

    public void initDots(){
        for (Vektor2D v : emptyTiles){
            items.add(new Dot(handler,v.getX() * Assets.TILEHEIGHT + Assets.TILEWIDTH / 2, v.getY() * Assets.TILEHEIGHT + Assets.TILEHEIGHT / 2, Dot));
            dotCount++;
        }
    }


    public void findEmptySpaces(){
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y <world.getHeight() ; y++) {
                if(world.getWorldGrid()[x][y] ==  0){
                   emptyTiles.add(new Vektor2D(x, y));
                }
            }
        }
    }

    public void render(Graphics g) {
        for (Item i : items) {
            i.render(g);
//            // collisionBox Dots
//            if(i.getItemType() == Dot) {
//                g.setColor(Color.RED);
//                g.drawRect(i.getCollisionBOX().x, i.getCollisionBOX().y, i.getCollisionBOX().width, i.getCollisionBOX().height);
//            }
        }
    }

    public void removeItem(Item item) {
        if(item == null)
            return;
        if(item.getClass() == Dot.class) {
            dotCount--;
        }
        items.remove(item);
        emptyPlaces.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public ArrayList<Item> getEmptyPlaces() {
        return emptyPlaces;
    }
    public int getDotCount() {
        return dotCount;
    }
    public int getPowerUpCout() {
        return powerUpCout;
    }
}