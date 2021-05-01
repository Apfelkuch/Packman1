package Componts;

import Input.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TextField extends object{

    private final Input input;

    private StringBuilder content;
    private Font font;
    private Color color;
    private int arrangement = CENTER;
    private int textOffsetX;
    private boolean changes = false;
    private boolean editable;
    private boolean edit;

    /**
     * X is the left boarder of the text.
     */
    public static final int LEFT = 0;
    /**
     * X is in the middle of the text.
     */
    public static final int CENTER = 1;
    /**
     * X is the right boarder of the text.
     */
    public static final int RIGHT = 2;

    public TextField(Input input, int x, int y, String content, int arrangement, Font font, Color color) {
        super(x, y, 0, 0);
        this.textOffsetX = x;
        this.input = input;
        this.content = new StringBuilder(content);
        this.arrangement = arrangement;
        this.font = font;
        this.color = color;
        this.changes = true;
    }

    @Override
    public void tick() {
        // edit the content
        if(edit) {
//            System.out.println("edit");
            KeyEvent key = input.getKeyEventTyped();
            if (key == null) return;
//            System.out.println("TextField.tick: " + key.getKeyCode());
            if(key.getKeyCode() == KeyEvent.VK_DELETE | key.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
//                System.out.println("TextField.tick:delete");
                content.deleteCharAt(content.length() - 1);
                changes = true;
            } else if(key.getKeyCode() >= 44 && key.getKeyCode() <= 111) { // characters which are possible to typ
//                System.out.println("TextField.tick:add");
                content.append(key.getKeyChar());
                changes = true;
            } else if(key.getKeyCode() == KeyEvent.VK_ENTER || key.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                System.out.println("TextField.tick:exit");
                edit = false;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        // set Font and color
        g.setFont(font);
        g.setColor(color);

        // set Text position
        if(changes)
            this.setTextPosition(g);

        // draw String
        g.drawString(content.toString(), textOffsetX, y);

//        // draw box
//        g.setColor(Color.RED);
//        g.drawRect(textX, getBodyRectangle().y - height, getBodyRectangle().width, getBodyRectangle().height);

    }

    private void setTextPosition(Graphics g) {
        // set width and height
        width = g.getFontMetrics().stringWidth(content.toString());
        height = g.getFontMetrics(font).getHeight() / 2; // if characters are the normal height, ASCI index <= 128
//        height = (g.getFontMetrics(font).getMaxAscent() + g.getFontMetrics(font).getMaxDescent()) / 2; // if characters are higher then normal, ASCI index > 128

        // set offset
        switch (arrangement) {
            case CENTER -> textOffsetX = x - (width / 2);
            case RIGHT -> textOffsetX = x - width;
        }

        // changes are changed
        changes = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.print("TextField.mouseClicked");
        if(!editable) return;
        edit = intersect(new Rectangle(textOffsetX, y - height, width, height), e.getPoint());
//        System.out.println("TextField.mouseClicked: edit = " + edit);
        input.getKeyEventTyped();
    }

    // GETTER && SETTER

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
