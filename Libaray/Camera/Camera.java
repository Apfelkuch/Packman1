package Camera;

import EntitySystem.Entity;

import java.awt.*;

public abstract class Camera {

    public abstract void render(Graphics g);

    public abstract void renderEntities(Graphics g, Entity entity);
}
