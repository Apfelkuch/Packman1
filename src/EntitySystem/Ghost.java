package EntitySystem;

import ImageLoad.Assets;
import Main.Handler;
import States.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ghost extends Creature {
    // Attributes for moving
    private enum Edirections {
        Up, Right, Down, Left
    }
    private Edirections direction;
    private BufferedImage img;
    private int imgID;

    //////////////////////////////////
    public Ghost(Handler handler, float posX, float posY, float pSPEED) {
        super(handler, posX, posY, Assets.TILEWIDTH, Assets.TILEHEIGHT, 40, 40, pSPEED);
        direction = Edirections.Down;

        int r = 1;
        if(handler.getGameState().getGhostCount() >= (handler.getGhosts().length - 6)) {
            boolean possible = false;
            while (!possible) {
                possible = true;
                r = (int) (Math.random() * 6);
                for (Ghost g : handler.getGhosts()) {
                    if (g == null) continue;
                    if (r == g.getImgID()) possible = false;
                }
            }
        } else {
            r = (int) (Math.random() * 6);
        }

        imgID = r;
        switch (r) {
            case 0:
                img = Assets.ghost1;
                break;
            case 1:
                img = Assets.ghost2;
                break;
            case 2:
                img = Assets.ghost3;
                break;
            case 3:
                img = Assets.ghost4;
                break;
            case 4:
                img = Assets.ghost5;
                break;
            case 5:
                img = Assets.ghost6;
                break;
        }
    }

    public void render(Graphics g) {
        g.drawImage(img, (int) posX, (int) posY, width, height, null);

//        //collisionBOX
//        g.setColor(Color.MAGENTA);
//        g.drawRect(collisionBOX.x, collisionBOX.y, collisionBOX.width, collisionBOX.height);
    }

    public void tick() {
        setMove();
        move();
        if (this.collisionBOX.intersects(handler.getPlayer().collisionBOX)) // ghost eat the player, if the player and the ghost intersect.
            eatPlayer();
    }

    /**
     * Translate a direction as int, in a direction from {@link Edirections}
     * @param dir = direction as int
     * @return = direction as {@link Edirections}
     */
    private Edirections dirInEdirection(int dir) {
        if (dir == 0) {
            return Edirections.Up;
        } else if (dir == 1) {
            return Edirections.Right;
        } else if (dir == 2) {
            return Edirections.Down;
        } else if (dir == 3) {
            return Edirections.Left;
        } else {
            // print to the console if direction is invalid and the program is closed
            System.out.println("[ERROR]" + "Not possible _dir_: " + dir);
            System.exit(1);
        }
        return null;
    }

    /**
     * generating a random direction based on the forerunner direction
     */
    private int generatingDirection(int maxDirections, int forerunnerDirection) {
        int dir = (new Random().nextInt(maxDirections) - 1);
        dir = forerunnerDirection + dir;
        if(dir == -1) {
            dir = 3;
        }
        if(dir == 4) {
            dir = 0;
        }
        return dir;
    }

    /**
     * set direction to the next direction from the path-Array
     */
    public void setDirection(boolean motionZero) {
        if(motionZero) {
            int collide = 0;
            boolean[] freeSpaces = new boolean[4];
            if(collide(0,-SPEED) == NoCollision) { // Up
                collide++;
                freeSpaces[0] = true;
            }
            if(collide(SPEED,0) == NoCollision) { // Right
                collide++;
                freeSpaces[1] = true;
            }
            if(collide(0,SPEED) == NoCollision) { // Down
                collide++;
                freeSpaces[2] = true;
            }
            if(collide(-SPEED,0) == NoCollision) { // Left
                collide++;
                freeSpaces[3] = true;
            }
            // remove current direction from freeSpaces
            freeSpaces[currentLookingBack] = false;
            collide--;

            if (collide == 1) { // if only one possible direction
                // set direction to the only free direction
                for(int i=0;i< freeSpaces.length;i++) {
                    if(freeSpaces[i]) {
                        direction = dirInEdirection(i);
                    }
                }
            } else if (collide == -1) {
                direction = Edirections.Down;
                resetMove();
            } else {
                // generating direction
                int dir = generatingDirection(collide, direction.ordinal());
                // set direction to the new direction
                direction = dirInEdirection(dir);
            }
        } else {
            // generating direction
            int dir = generatingDirection(3,direction.ordinal());
            // set direction to the new direction
            direction = dirInEdirection(dir);
        }
    }

    /**
     * Testing if move is possible,
     * If it is impossible move is the Motion.
     * If it is not possible moveOLD is the Motion.
     *
     * Adjust the looking.
     */
    public void move() {
        // collision
        if (super.collide(xMove, yMove) != NoCollision) { //test if collision
            if (super.xMove == super.xMoveOLD) // canceling xMove, if collide
                super.xMove = 0;
            else // set xMove to xMoveOLD, if collision was not on the x-Axis
                super.xMove = super.xMoveOLD;

            if (super.yMove == super.yMoveOLD) // canceling yMove, if collide
                super.yMove = 0;
            else // set yMove to yMoveOLD, if collision was not on the y-Axis
                super.yMove = super.yMoveOLD;

        }
        if (collide(xMoveOLD, yMoveOLD) != NoCollision) {
            xMove = 0;
            yMove = 0;
        }
        if (xMove == 0 && yMove == 0 && xMoveOLD == 0 && yMoveOLD == 0) { // detect if direction is not possible and change the direction to a possible direction
            setDirection(true);
            return;
        }
        // adjust looking
        super.adjustLooking();
        // change the position with xMove and yMove
        posX += xMove;
        posY += yMove;
        //adjust collisionBOX
        super.adjustCollisionBOX();
    }

    /**
     * OLDMove is the current move
     * if the motion of the entity has changed
     *  setDirection is called and return out of the function that setDirection can finish before the product is called
     */
    private void setMove() {
        // set MoveOLD to the current Move
        super.xMoveOLD = super.xMove;
        super.yMoveOLD = super.yMove;

        // setDir
        if (direction.equals(Edirections.Up) && yMove < 0) {
            setDirection(false);
            return;
        }
        if (direction.equals(Edirections.Down) && yMove > 0) {
            setDirection(false);
            return;
        }
        if (direction.equals(Edirections.Right) && xMove > 0) {
            setDirection(false);
            return;
        }
        if (direction.equals(Edirections.Left) && xMove < 0) {
            setDirection(false);
            return;
        }

        // set Input
        switch (direction) {
            case Up:
                super.yMove = -SPEED;
                super.xMove = 0;
                break;
            case Down:
                super.yMove = SPEED;
                super.xMove = 0;
                break;
            case Right:
                super.xMove = SPEED;
                super.yMove = 0;
                break;
            case Left:
                super.xMove = -SPEED;
                super.yMove = 0;
                break;
        }
    }

    /**
     * set the game to gameOver and call the GameOverWindow
     */
    public void eatPlayer() {
        handler.getGameState().setGameStatus(GameState.LOST);
    }

    /**
     * set all Move-Attributes to 0
     */
    public void resetMove() {
        xMove = 0;
        xMoveOLD = 0;
        yMove = 0;
        yMoveOLD = 0;
    }

    // GETTER && SETTER
    public BufferedImage getImg() {
        return img;
    }
    public int getImgID() {
        return imgID;
    }
}
