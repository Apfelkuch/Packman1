package Tiles;

import ImageLoad.Assets;

public class Tiles {

    TileManager tileManager = new TileManager();

    public Tiles() {
        tileManager.addTile("groundTile", Assets.ground, false);
        tileManager.addTile("upperU", Assets.upperU, true);
        tileManager.addTile("lowerU", Assets.lowerU, true);
        tileManager.addTile("updownTunnel", Assets.updowntunnel, true);
        tileManager.addTile("leftU", Assets.leftU, true);
        tileManager.addTile("leftUpperCornerTile", Assets.leftUpperCorner, true);
        tileManager.addTile("leftLowerCornerTile", Assets.leftLowerCorner, true);
        tileManager.addTile("leftWallTile", Assets.leftWall, true);
        tileManager.addTile("rightU", Assets.rightU, true);
        tileManager.addTile("rightupperCornerTile", Assets.rightUpperCorner, true);
        tileManager.addTile("rightLowerCornerTile", Assets.rightLowerCorner, true);
        tileManager.addTile("rightWallTile", Assets.rightWall, true);
        tileManager.addTile("rightleftTunnel", Assets.rightleftTunnel, true);
        tileManager.addTile("upperWallTile", Assets.upperWall, true);
        tileManager.addTile("lowerWallTile", Assets.lowerWall, true);
        tileManager.addTile("allWall", Assets.allWall, true);
    }
}
