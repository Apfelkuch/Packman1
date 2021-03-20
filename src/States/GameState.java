package States;

import Componts.Button;
import EntitySystem.Ghost;
import EntitySystem.Player;
import ImageLoad.Assets;
import Main.Game;
import Main.Handler;
import Text.Text;
import Tiles.TileManager;
import Worldmanager.WorldGenerator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class GameState extends State implements ActionListener {

    private Player player;
    private WorldGenerator world;
    private Ghost[] ghosts;
    private boolean gameOver;

    //GameOverwWindow
    private int windowWidth = 300;
    private int windowHeight = 300;
    private int windowX = handler.getWindow().getCanvas().getWidth() / 2 - windowWidth / 2;
    private int windowY = handler.getWindow().getCanvas().getHeight() / 2 - windowHeight / 2;
    private int buttonWidth = 100;
    private int buttonHeight = 40;
    private int buttonXInWindow = (windowWidth / 2) - (buttonWidth / 2);
    private int buttondifYInWindow = windowHeight / 3; // usedAreas(Title, Button(play), Button(exit))
    private Button play;
    private Button exit;
    public static int WIN = 0;
    public static int LOST = 1;
    private int gameOverStatus;

    public GameState(Handler handler){
        super(handler);
    }

    @Override
    public boolean initState() {
        world = new WorldGenerator("res/worlds/World1.txt",handler);
        player = new Player(handler, world.getSpawnX(),world.getSpawnY(),4.0f);
        ghosts = new Ghost[world.getGhostCount()];
        for(int i=0;i<ghosts.length;i++) {
            ghosts[i] = new Ghost(handler, world.getGhostSpawnX(), world.getGhostSpawnY(), 3.0f);
        }
        gameOver = false;
        initGameOverWindow();
        return true;
    }

    @Override
    public void tick() {
        if(!gameOver) {
            world.tick();
            player.tick();
            for (Ghost ghost : ghosts) {
                if(ghost != null) {
                    ghost.tick();
                }
            }
        }
        System.out.println(TileManager.TILES.size());
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
        for (Ghost ghost : ghosts) {
            if(ghost != null) {
                ghost.render(g);
            }
        }
        if(gameOver) {
            g.drawImage(Assets.gameOverWindow,windowX,windowY,null);
            play.render(g);
            exit.render(g);

            //titel
            g.setColor(Color.WHITE);
            g.setFont(Text.GAMEOVERFONT);
            if(gameOverStatus == WIN) {
                int stringwidth = g.getFontMetrics().stringWidth(Text.WIN);
                g.drawString(Text.WIN,windowX + windowWidth / 2 - stringwidth / 2,windowY + buttondifYInWindow / 2 + buttonHeight);
            } else if(gameOverStatus == LOST) {
                int stringwidth = g.getFontMetrics().stringWidth(Text.LOST);
                g.drawString(Text.LOST,windowX + windowHeight / 2 -stringwidth / 2,windowY + buttondifYInWindow / 2 + buttonHeight);
            } else {
                System.out.println("[ERROR] invalid gameOverStatus");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == play) {
            State.changeState(Game.gameState);
        } else  if(e.getSource() == exit) {
            State.changeState(Game.menuState);
        }
    }

    //mouse Inpus
    @Override
    public void mousePressed(MouseEvent e) {
        play.mousePressed(e);
        exit.mousePressed(e);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        play.mouseReleased(e);
        exit.mouseReleased(e);
    }

    /**
     * initialized the gameOverWindow
     */
    private void initGameOverWindow() {
        windowWidth = 300;
        windowHeight = 300;
        windowX = handler.getWindow().getCanvas().getWidth() / 2 - windowWidth / 2;
        windowY = handler.getWindow().getCanvas().getHeight() / 2 - windowHeight / 2;
        play = new Button(this,handler,Text.ButtonPlay,windowX + buttonXInWindow,windowY + buttondifYInWindow + buttondifYInWindow / 2 - buttonHeight / 2,buttonWidth,buttonHeight);
        exit = new Button(this,handler,Text.ButtonExit,windowX + buttonXInWindow,windowY + 2 * buttondifYInWindow + buttondifYInWindow / 2 - buttonHeight / 2,buttonWidth,buttonHeight);
    }

    public void gameOver(int gameOverStatus) {
        this.gameOverStatus = gameOverStatus;
        gameOver = true;
    }

    //GETTER & SETTER
    public Player getPlayer() {
        return player;
    }
    public Ghost[] getGhosts() {
        return ghosts;
    }
    public WorldGenerator getWorld() {
        return world;
    }
}
