package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private boolean[] keys = new boolean[256];
    private boolean[] justPressed = new boolean[keys.length];
    private boolean[] cantPressed = new boolean[keys.length];

    private KeyEvent keyEvent;

    public void tick() {
        for (int i=0;i<keys.length;i++) {
            if(cantPressed[i] && !keys[i]) {
                cantPressed[i] = false;
            } else if(justPressed[i]) {
                cantPressed[i] = true;
                justPressed[i] = false;
            } else if(!cantPressed[i] && keys[i]) {
                justPressed[i] = true;
            }
        }

    }

    /**
     * Update the key pressing on every tick and check if the key is recently pressed.
     * @param keyCode: key which is pressed
     * @return true: key just pressed & false: key not just pressed
     */
    public boolean keyJustPressed_TickBased(int keyCode){
        if(keyCode < 0 || keyCode > keys.length)
            return false;
        return justPressed[keyCode];
    }

    /**
     * Update the key pressing every time a key is pressed and check if the key is pressed.
     * @param keyCode: key which is pressed
     * @return true: key just pressed & false: key not just pressed
     */
    public boolean keyJustPressed_PressedBased(int keyCode) {
        if(keyCode < 0 || keyCode > keys.length)
            return false;
        return keys[keyCode];
    }

    public KeyEvent getKeyEventTyped() {
//        System.out.println("Input.getKeyEventTyped: " + keyEvent);
        KeyEvent kE = keyEvent;
        keyEvent = null;
        return kE;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keyEvent = e;
        if(e.getKeyCode() < 0 || e.getKeyCode() > keys.length)
            return;
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() > keys.length)
            return;
        keys[e.getKeyCode()] = false;
    }
}
