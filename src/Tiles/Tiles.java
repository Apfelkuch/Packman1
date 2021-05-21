package Tiles;

import ImageLoad.Assets;

import java.util.ArrayList;

public class Tiles {

    private TileManager tileManager = new TileManager();

    public Tiles() {
        tileManager.addTile("groundTile", Assets.ground, false);
        tileManager.addTile("upperU", Assets.upperU, true);
        tileManager.addTile("lowerU", Assets.lowerU, true);
        tileManager.addTile("upDownTunnel", Assets.upDownTunnel, true);
        tileManager.addTile("leftU", Assets.leftU, true);
        tileManager.addTile("leftUpperCornerTile", Assets.leftUpperCorner, true);
        tileManager.addTile("leftLowerCornerTile", Assets.leftLowerCorner, true);
        tileManager.addTile("leftWallTile", Assets.leftWall, true);
        tileManager.addTile("rightU", Assets.rightU, true);
        tileManager.addTile("rightUpperCornerTile", Assets.rightUpperCorner, true);
        tileManager.addTile("rightLowerCornerTile", Assets.rightLowerCorner, true);
        tileManager.addTile("rightWallTile", Assets.rightWall, true);
        tileManager.addTile("rightLeftTunnel", Assets.rightLeftTunnel, true);
        tileManager.addTile("upperWallTile", Assets.upperWall, true);
        tileManager.addTile("lowerWallTile", Assets.lowerWall, true);
        tileManager.addTile("allWall", Assets.allWall, true);
    }

    // GETTER && SETTER

    public TileManager getTileManager() {
        return tileManager;
    }

    public ArrayList<Tile> getTiles() {
        return tileManager.getTiles();
    }
}
