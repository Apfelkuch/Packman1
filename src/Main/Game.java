package Main;

import ImageLoad.Assets;
import Input.Input;
import Input.MListener;
import PackmanUi.Window;
import Save.Load;
import States.GameState;
import States.LoadOverlay;
import States.MenuState;
import States.State;
import Text.Text;
import music.Sound;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game {
    private Window window;
    private Graphics g;
    private BufferStrategy bs;
    private boolean running = true;
    private Handler handler;
    private Input input;
    private MListener mListener;
    private Text text;
    private Sound sound;
    private boolean loading;
    // states
    public static LoadOverlay loadOverlay;
    public static MenuState menuState;
    public static GameState gameState;

    public  static int fps = 60;
    //////////////////////////////////////////////////////////////////////
    public Game(){
        gameLoop();
    }

    /**
     * This Methode is the main gameloop and on this Methode the rest of the game is build.
     */
    public void gameLoop(){
        // TODO loading screen parallel to the loading
        init(); // if the method is used nothing else is used. No parallel loading-Screen
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        long ticks = 0;
        while(running) {
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
                secTick();
                ticks = 0;
                timer = 0;

                // Test
            }
        }
    }

    /**
     * Initialisation of all parameters for the game to function and run.
     */
    private void init(){
        // start the loading
        loading = true;

        // initialize the needed classes
        window = new Window();
        handler = new Handler(this);
        mListener = new MListener(handler);
        input = new Input();
        text = new Text();
        sound = new Sound();

        // initialize the states
        loadOverlay = new LoadOverlay(handler);
        gameState = new GameState(handler);
        menuState = new MenuState(handler);

        // set the starting state
        State.changeState(menuState);

        // load the save file
        if ((Load.load(handler, Text.SavePath))) {
            System.out.println("Game.init: load file exists");
        } else {
            System.out.println("Game.init: load file does not exists.");
        }

        // initialize the assets
        Assets.init();

        // add the listeners to the canvas
        window.getCanvas().addKeyListener(input);
        window.getCanvas().addMouseListener(mListener);
        window.getCanvas().addMouseMotionListener(mListener);

        // TODO graphics
        // TODO level
        // TODO screen size

        // loading is done
        loading = false;
    }

    /**
     * This methode is called {@value fps} (fps) times per second and repaint the screen.
     */
    public void render() {
        bs = window.getCanvas().getBufferStrategy();
        if(window.getCanvas().getBufferStrategy() == null){
            window.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0,0, window.getWidth(), window.getHeight());

        // STATE
        if(State.getState() != null && State.getState().isDoneLoading() && !loading) {
            State.getState().render(g);
        }
        if(State.getState() != null && !State.getState().isDoneLoading() && loading) {
            loadOverlay.render(g);
        }

        bs.show();
        g.dispose();
    }

    /**
     * This methode is called {@value fps} (fps) times per second and can be used in the game logic.
     */
    public void tick() {
        // MUSIC
        if(!sound.isPlay()) {
            sound.playSound(Sound.BACKGROUND_MUSIC);
        }

        // STATE
        if(State.getState() != null && State.getState().isDoneLoading() && !loading) {
            State.getState().tick();
        }
        if(State.getState() != null && !State.getState().isDoneLoading() && loading) {
            loadOverlay.tick();
        }

        // INPUT
        input.tick();
    }

    /**
     * This methode is called 1 time per second and caa be used in the game logic.
     */
    public void secTick() {
        if(State.getState() != null && State.getState().isDoneLoading()) {
            State.getState().secTick();
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
    public MListener getMListener() {
        return mListener;
    }
    public Input getInput() {
        return input;
    }
    public Sound getSound() {
        return sound;
    }
}
