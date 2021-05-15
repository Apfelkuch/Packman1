package EntitySystem;

import Main.Handler;

import java.awt.*;

public abstract class Entity {
    protected float posX, posY;
    protected int CBwidth, CBheight;
    protected Rectangle collisionBOX;
    protected Handler handler;

    public Entity(Handler handler, float posX, float posY, int CBwidth, int CBheight) {
        this.handler = handler;
        this.posX = posX;
        this.posY = posY;
        this.CBwidth = CBwidth;
        this.CBheight = CBheight;
        this.collisionBOX = new Rectangle((int) posX, (int) posY, CBwidth, CBheight);
    }

    public void renderCollisionBox(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.drawRect(collisionBOX.x, collisionBOX.y, collisionBOX.width, collisionBOX.height);
    }

    public abstract void render(Graphics g);

    public abstract void tick();

    // GETTER & SETTER

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public Rectangle getCollisionBOX() {
        return collisionBOX;
    }

    public int getCBwidth() {
        return CBwidth;
    }

    public int getCBheight() {
        return CBheight;
    }
}

