package States;

import Main.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class State {
    private static  State currentState = null;
    protected final Handler handler;
    private static boolean doneLoading = false;

    public static State getState(){
        return currentState;
    }
    public static void changeState(State state) {
        doneLoading = false;
        currentState = state;
        doneLoading = currentState.initState();
    }

    public State(Handler handler) {
        this.handler = handler;
    }
    public boolean isDoneLoading() {
        return doneLoading;
    }

    //MouseListener
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {};
    public void mouseMoved(MouseEvent e) {};

    // CLASS
    public abstract boolean initState();

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void sectick();
}