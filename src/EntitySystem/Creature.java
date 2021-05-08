package EntitySystem;

import ImageLoad.Assets;
import Main.Handler;

import java.awt.*;

public abstract class Creature extends Entity {
    protected int width, height;
    // Attributes for the Movement
    protected float SPEED;
    protected float xMove; // +/- SPEED
    protected float yMove; // +/- SPEED
    protected float xMoveOLD; // +/- SPEED
    protected float yMoveOLD; // +/- SPEED
    // Attributes for the looking
    protected int currentLooking; // view direction
    protected int currentLookingBack; // revers view direction
    protected final int lookingUP = 0;
    protected final int lookingRIGHT = 1;
    protected final int lookingDOWN = 2;
    protected final int lookingLEFT = 3;
    // collision constant returns
    protected static final int NoCollision = 0;
    protected static final int TopCollision = 1;
    protected static final int DownCollision = 2;
    protected static final int LeftCollision = 3;
    protected static final int RightCollision = 4;
    // extras
    protected int killCount;


    public Creature(Handler handler, float posX, float posY, int width, int height, int CBwidth, int CBheight, float speed) {
        super(handler, posX, posY, (CBwidth - 1), (CBheight - 1));
        this.SPEED = speed;
        this.width = (width - 1);
        this.height = (height - 1);
        adjustCollisionBOX();
    }

    /**
     * Test if Entity is colliding in the next move
     *
     * @param XMOVE Movement in x direction.
     * @param YMOVE Movement in y direction.
     * @return true if Entity collide, otherwise false
     */
    protected int collide(float XMOVE, float YMOVE) {
        if (XMOVE > 0) { // if collide when moving right
            if ((posX + width + SPEED) >= (handler.getGame().getGameState().getWorld().getWidth() * Assets.TILEWIDTH)) // leaving on the right
                return RightCollision;
            else if ((handler.getGame().getGameState().getWorld().getTile((int) ((posX + width + SPEED) / Assets.TILEWIDTH), (int) ((posY) / Assets.TILEHEIGHT))).isSolid()) // if solid-Tile right-up
                return RightCollision;
            else if ((handler.getGame().getGameState().getWorld().getTile((int) ((posX + width + SPEED) / Assets.TILEWIDTH), (int) ((posY + height) / Assets.TILEHEIGHT))).isSolid()) // if solid-Tile right-up
                return RightCollision;
        } else if (XMOVE < 0) { // if collide when moving left
            if (posX <= 0) // leaving on the left
                return LeftCollision;
            else if ((handler.getGame().getGameState().getWorld().getTile((int) ((posX - SPEED) / Assets.TILEWIDTH), (int) ((posY) / Assets.TILEHEIGHT))).isSolid()) // if solid-Tile left-up
                return LeftCollision;
            else if ((handler.getGame().getGameState().getWorld().getTile((int) ((posX - SPEED) / Assets.TILEWIDTH), (int) ((posY + height) / Assets.TILEHEIGHT))).isSolid()) // if solid-Tile left-down
                return LeftCollision;
        } else if (YMOVE > 0) { // if collide when moving down
            if ((posY + height + SPEED) >= (handler.getGame().getGameState().getWorld().getHeight() * Assets.TILEHEIGHT)) // leaving on the bottom
                return DownCollision;
            else if ((handler.getGame().getGameState().getWorld().getTile((int) ((posX) / Assets.TILEWIDTH), (int) ((posY + height + SPEED) / Assets.TILEHEIGHT))).isSolid()) // if solid-Tile bottom-left
                return DownCollision;
            else if ((handler.getGame().getGameState().getWorld().getTile((int) ((posX + width) / Assets.TILEWIDTH), (int) ((posY + height + SPEED) / Assets.TILEHEIGHT))).isSolid()) // if solid-Tile bottom-right
                return DownCollision;
        } else if (YMOVE < 0) { // if collide when moving up
            if (posY <= 0) // leaving on the top
                return TopCollision;
            else if ((handler.getGame().getGameState().getWorld().getTile((int) ((posX) / Assets.TILEWIDTH), (int) ((posY - SPEED) / Assets.TILEHEIGHT))).isSolid()) // if solid-Tile top-left
                return TopCollision;
            else if ((handler.getGame().getGameState().getWorld().getTile((int) ((posX + width) / Assets.TILEWIDTH), (int) ((posY - SPEED) / Assets.TILEHEIGHT))).isSolid()) // if solid-Tile top-right
                return TopCollision;
        }
        return NoCollision;
    }

    /**
     * adjust the looking of the entity
     */
    protected void adjustLooking() {
        // adjust the viewing direction
        if (yMove < 0) { // Up
            currentLooking = lookingUP;
            currentLookingBack = lookingDOWN;
        }
        if (xMove > 0) { // Right
            currentLooking = lookingRIGHT;
            currentLookingBack = lookingLEFT;
        }
        if (yMove > 0) { // Down
            currentLooking = lookingDOWN;
            currentLookingBack = lookingUP;
        }
        if (xMove < 0) { // Left
            currentLooking = lookingLEFT;
            currentLookingBack = lookingRIGHT;
        }
    }

    /**
     * adjust collisionBOX
     */
    public void adjustCollisionBOX() {
        collisionBOX.setLocation((int) (posX + (width - collisionBOX.width) / 2), (int) (posY + (height - collisionBOX.height) / 2));
    }

    /**
     * Test if a Creature(c) is in front of the current Creature and the current Creature will intersect in the next move with the other Creature(c).
     *
     * @param c The tested Creature.
     * @return True if the Creature is in front and false if the Creature is not in front.
     */
    protected boolean creatureInFront(Creature c) {
        switch (this.currentLooking) {
            case lookingUP:
                if (c.collisionBOX.intersects(new Rectangle((int) (this.posX), (int) (this.posY - SPEED), width, height)) && c.currentLooking == lookingUP)
                    return true;
                break;
            case lookingRIGHT:
                if (c.collisionBOX.intersects(new Rectangle((int) (this.posX + SPEED), (int) (this.posY), width, height)) && c.currentLooking == lookingRIGHT)
                    return true;
                break;
            case lookingDOWN:
                if (c.collisionBOX.intersects(new Rectangle((int) (this.posX), (int) (this.posY + SPEED), width, height)) && c.currentLooking == lookingDOWN)
                    return true;
                break;
            case lookingLEFT:
                if (c.collisionBOX.intersects(new Rectangle((int) (this.posX - SPEED), (int) (this.posY), width, height)) && c.currentLooking == lookingLEFT)
                    return true;
                break;
        }
        return false;
    }

    // GETTER && SETTER
    public float getSPEED() {
        return SPEED;
    }

    public int getCurrentLooking() {
        return currentLooking;
    }

    public int getCurrentLookingBack() {
        return currentLookingBack;
    }

    public int getKillCount() {
        return killCount;
    }
}
