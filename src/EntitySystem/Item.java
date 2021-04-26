package EntitySystem;

import Main.Handler;

public abstract class Item extends Entity {
    private char itemType; // P für powerUp d Für dot
    public Item(Handler handler, float posX, float posY, int CBwidth, int CBheight, char itemType) {
        super(handler, posX, posY, CBwidth, CBheight);
        this.itemType = itemType;
    }

    // GETTER && SETTER
    public char getItemType() {
        return itemType;
    }
}
