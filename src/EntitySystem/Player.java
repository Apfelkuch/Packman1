package EntitySystem;

import ImageLoad.Assets;
import Main.Handler;
import States.GameState;
import Text.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Creature {
    //Attributes for the Dots
    private int dotCounter = 0;

    public Player(Handler handler, int spawnX, int spawnY, float pSPEED) {
        super(handler, spawnX, spawnY, Assets.TILEWIDTH, Assets.TILEHEIGHT, 48, 48, pSPEED);
        currentLooking = lookingRIGHT; // starting view direction
    }

    @Override
    public void render(Graphics g) {
        // paint packman with his viewing direction (lookingAT)
        switch (currentLooking) {
            case lookingRIGHT:
                g.drawImage(Assets.packman_RIGHT, (int) posX, (int) posY, width, height, null);
                break;
            case lookingLEFT:
                g.drawImage(Assets.packman_LEFT, (int) posX, (int) posY, width, height, null);
                break;
            case lookingUP:
                g.drawImage(Assets.packman_UP, (int) posX, (int) posY, width, height, null);
                break;
            case lookingDOWN:
                g.drawImage(Assets.packman_DOWN, (int) posX, (int) posY, width, height, null);
                break;
        }
        //DotCounter on Screen
        g.setColor(Color.YELLOW);
        g.setFont(Text.DotFont);
        g.drawString("" + this.dotCounter, (handler.getWorld().getWidth() - 2) * Assets.TILEWIDTH, (int) (Assets.TILEHEIGHT * 0.80));

//        //collisionBox packman
//        renderCollisionBox(g);
    }

    @Override
    public void tick() {
        this.getMove();
        this.move();
        this.win();
        this.eatDot();
        for (Ghost g : handler.getGhosts()) {
            if (g != null) {
                if (this.creatureInFront(g)) { // player eats the ghost if the player will intersect in the next move with the ghost.
                    eatGhost(g);
                }
            }
        }
    }

    /**
     * test if Packman is above a Dot, if it is true the Dot is deleted and the dotCounter is increased
     * otherwise nothing happened
     */
    public void eatDot() {
        if (super.handler.getWorld().getPowerUpManager().getItems() == null)
            return;
        Dot removedDot = null;
        for (Item d : super.handler.getWorld().getPowerUpManager().getItems()) {
            if (d.getClass() == Dot.class) {
                if (this.collisionBOX.intersects(d.collisionBOX)) {
                    this.dotCounter += 1;
                    removedDot = (Dot) d;
                }
            }
        }
        handler.getWorld().getPowerUpManager().removeItem(removedDot);
    }

    /**
     * test if the movement is possible
     * adjust the Looking
     * apply xMove and yMove onto posX and posY
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
        //adjust looking
        super.adjustLooking();
        // change the position with xMove and yMove
        super.posX += super.xMove;
        super.posY += super.yMove;
        //adjust collisionBOX
        super.adjustCollisionBOX();
    }

    /**
     * set MoveOLD
     * translate the input in xMove and yMove
     */
    public void getMove() {
        // set MoveOLD to the current Move
        super.yMoveOLD = super.yMove;
        super.xMoveOLD = super.xMove;
        // set Move to the new Input and reset the other Move
        if (handler.getInput().keyJustPressed_PressedBased(KeyEvent.VK_W)) { // UP
            super.yMove = -super.SPEED;
            super.xMove = 0;
        }
        if (handler.getInput().keyJustPressed_PressedBased(KeyEvent.VK_S)) { // DOWN
            super.yMove = super.SPEED;
            super.xMove = 0;
        }
        if (handler.getInput().keyJustPressed_PressedBased(KeyEvent.VK_D)) { // RIGHT
            super.xMove = super.SPEED;
            super.yMove = 0;
        }
        if (handler.getInput().keyJustPressed_PressedBased(KeyEvent.VK_A)) { // LEFT
            super.xMove = -super.SPEED;
            super.yMove = 0;
        }
    }

    public void win() {
        if (handler.getWorld().getPowerUpManager().getDotCount() == 0) {
            System.out.println("help");
            handler.getGameState().setGameStatus(GameState.WIN);
        }
    }

    public void eatGhost(Ghost g) {
        if (g == null) return;
        this.killCount++;
        for (int i = 0; i < handler.getGhosts().length; i++) {
            if (handler.getGhosts()[i] == g) {
                handler.getGhosts()[i] = null;
                return;
            }
        }
    }
}
