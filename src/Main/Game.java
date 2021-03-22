package Main;

import ImageLoad.Assets;
import Input.Input;
import Input.MListener;
import PackmanUi.Window;
import States.GameState;
import States.MenuState;
import States.State;
import Text.Text;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game {
    private Window window;
    private Graphics g;
    private BufferStrategy bs;
    private boolean running = true;
    public static MenuState menuState;
    public static GameState gameState;
    Handler handler;
    private final Input input;
    private final MListener mListener;
    private final Text text;

    public  static int fps = 60;
    //////////////////////////////////////////////////////////////////////
    public Game(){
        window = new Window();
        handler = new Handler(this);
        mListener = new MListener(handler);
        input = new Input();
        text = new Text();

        gameLoop();
    }
    public void gameLoop(){
        init();
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        long ticks = 0;
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if(delta >=1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                sectick();
                ticks = 0;
                timer = 0;

                //ÜBERPRÜFUNG
            }
        }
    }

    private void init(){
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.changeState(menuState);
        Assets.init();
        window.getCanvas().addKeyListener(input);
        window.getCanvas().addMouseListener(mListener);

        // TODO music
        // TODO graphics
        // TODO level
        // TODO screen size
    }

    public void render() {
        bs = window.getCanvas().getBufferStrategy();
        if(window.getCanvas().getBufferStrategy() == null){
            window.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0,0, window.getWidth(), window.getHeight());
        if(State.getState()!= null && State.getState().isDoneLoading()){
            State.getState().render(g);
        }
        bs.show();
        g.dispose();
    }

    public void tick() {
        if(State.getState()!= null && State.getState().isDoneLoading()){
            State.getState().tick();
        }
        input.tick();
    }

    public void sectick() {
        if(State.getState() != null && State.getState().isDoneLoading()) {
            State.getState().sectick();
        }
    }

    //GETTER & SETTER
    public Window getWindow() {
        return window;
    }
    public GameState getGameState() {
        return gameState;
    }
    public MenuState getMenuState() {
        return menuState;
    }
    public MListener getmListener() {
        return mListener;
    }
    public Input getInput() {
        return input;
    }
}
