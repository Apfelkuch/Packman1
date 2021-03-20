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
        handler.getState().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        handler.getState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        handler.getState().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        handler.getState().mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        handler.getState().mouseExited(e);
    }
}