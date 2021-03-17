package Input;

import java.awt.event.*;
import Main.Handler;

public class MListener implements MouseListener {

    private Handler handler;

    public MListener(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("[MOUSE] clicked");
        handler.getState().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("[MOUSE] pressed");
        handler.getState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("[MOUSE] released");
        handler.getState().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("[MOUSE] enter");
        handler.getState().mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("[MOUSE] exit");
        handler.getState().mouseExited(e);
    }
}