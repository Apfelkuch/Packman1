package States;

import Componts.Button;
import EntitySystem.Ghost;
import EntitySystem.Player;
import ImageLoad.Assets;
import Main.Game;
import Main.Handler;
import Text.Text;
import Worldmanager.WorldGenerator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameState extends State implements ActionListener {

    private Player player;
    private WorldGenerator world;
    private Ghost[] ghosts;
    private int ghostSpawnDelay;
    private int currentGhostSpawnDelay;
    private int ghostCount;

    //GameOverWindow
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
    private Button proceed;
    private int gameStatus;
    public static int PLAY = 0;
    public static int WIN = 1;
    public static int LOST = 2;
    public static int BREAK = 3;

    public GameState(Handler handler){
        super(handler);
    }

    @Override
    public boolean initState() {
        world = new WorldGenerator("res/worlds/World1.txt",handler);
//        System.out.println(world.getWidth() * Assets.TILEWIDTH + " , " +  world.getHeight() * Assets.TILEHEIGHT);
        handler.getWindow().setSize(new Dimension(world.getWidth() * Assets.TILEWIDTH + 16, world.getHeight() * Assets.TILEHEIGHT + 39));
        player = new Player(handler, world.getSpawnX(),world.getSpawnY(),4.0f);
        ghosts = new Ghost[world.getGhostCount()];
        ghostSpawnDelay = 2;
        currentGhostSpawnDelay = 0;
        ghostCount = ghosts.length;
        gameStatus = PLAY;
        initGameWindow();
        return true;
    }

    @Override
    public void tick() {
        if(handler.getInput().keyJustPressed_TickBased(KeyEvent.VK_ESCAPE)) {
            this.gameStatus = BREAK;
        }
        if(gameStatus == PLAY) {
            world.tick();
            player.tick();
            for (Ghost ghost : ghosts) {
                if(ghost != null) {
                    ghost.tick();
                }
            }
        }
    }

    @Override
    public void sectick() {
        if(ghostCount > 0) {
            if(currentGhostSpawnDelay > 0) {
                currentGhostSpawnDelay --;
            }
            if(currentGhostSpawnDelay == 0) {
                currentGhostSpawnDelay = ghostSpawnDelay;
                ghostCount--;
                spawnGhost(ghostCount);
            }
        }
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
        if(gameStatus == BREAK) {
            g.drawImage(Assets.gameOverWindow, windowX,windowY,null);
            proceed.render(g);
            exit.render(g);

            // title
            g.setColor(Color.WHITE);
            g.setFont(Text.BreakFont);

            int stringwidth = g.getFontMetrics().stringWidth(Text.BREAK);
            g.drawString(Text.BREAK,windowX + windowWidth / 2 - stringwidth / 2,windowY + buttondifYInWindow / 2 + buttonHeight);
        }
        if(gameStatus == WIN || gameStatus == LOST) {
            g.drawImage(Assets.gameOverWindow,windowX,windowY,null);
            play.render(g);
            exit.render(g);

            //titel
            g.setColor(Color.WHITE);
            g.setFont(Text.GameOverFont);
            
            if(gameStatus == WIN) {
                int stringwidth = g.getFontMetrics().stringWidth(Text.WIN);
                g.drawString(Text.WIN,windowX + windowWidth / 2 - stringwidth / 2,windowY + buttondifYInWindow / 2 + buttonHeight);
            } else if(gameStatus == LOST) {
                int stringwidth = g.getFontMetrics().stringWidth(Text.LOST);
                g.drawString(Text.LOST,windowX + windowHeight / 2 - stringwidth / 2,windowY + buttondifYInWindow / 2 + buttonHeight);
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
        } else if (e.getSource() == proceed) {
            gameStatus = PLAY;
        }
    }

    /**
     * mouse Input
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(gameStatus == WIN || gameStatus == LOST)
            play.mousePressed(e);
        if(gameStatus != PLAY)
           exit.mousePressed(e);
        if(gameStatus == BREAK)
            proceed.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(gameStatus == WIN || gameStatus == LOST)
            play.mouseReleased(e);
        if(gameStatus != PLAY)
            exit.mouseReleased(e);
        if(gameStatus == BREAK)
            proceed.mouseReleased(e);
    }

    /**
     * initialized the gameOverWindow
     */
    private void initGameWindow() {
        windowWidth = 300;
        windowHeight = 300;
        windowX = handler.getWindow().getCanvas().getWidth() / 2 - windowWidth / 2;
        windowY = handler.getWindow().getCanvas().getHeight() / 2 - windowHeight / 2;
        int cornerrounds = 20;
        play = new Button(this,handler,Text.ButtonPlay,windowX + buttonXInWindow,windowY + buttondifYInWindow + buttondifYInWindow / 2 - buttonHeight / 2,buttonWidth,buttonHeight);
        play.setCornerRounds(cornerrounds);
        proceed = new Button(this,handler,Text.ButtonContinue,windowX + buttonXInWindow,windowY + buttondifYInWindow +buttondifYInWindow / 2 - buttonHeight / 2, buttonWidth, buttonHeight);
        proceed.setCornerRounds(cornerrounds);
        exit = new Button(this,handler,Text.ButtonExit,windowX + buttonXInWindow,windowY + 2 * buttondifYInWindow + buttondifYInWindow / 2 - buttonHeight / 2,buttonWidth,buttonHeight);
        exit.setCornerRounds(cornerrounds);
    }

    public void spawnGhost(int index) {
        ghosts[index] = new Ghost(handler, world.getGhostSpawnX(), world.getGhostSpawnY(), 3.0f);
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
    public int getGhostCount() {
        return ghostCount;
    }
    public int getCurrentGhostSpawnDelay() {
        return currentGhostSpawnDelay;
    }
    public int getGhostSpawnDelay() {
        return ghostSpawnDelay;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }

}
