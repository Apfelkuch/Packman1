package States;

import Componts.Button;
import Componts.Slider;
import ImageLoad.Assets;
import Main.Game;
import Main.Handler;
import Save.*;
import Text.Text;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MenuState extends State implements ActionListener {

    // MENU UI
    private Button play;
    private Button option;
    private Button exit;
    // OPTION UI
    private Button back;
    private Slider soundVolume;
    // EXIT UI
    private Button yes;
    private Button no;

    public int menuStatus;
    public static int MENU = 0;
    public static int OPTION = 1;
    public static int EXIT = 2;


    public MenuState(Handler handler) {
        super(handler);
    }

    @Override
    public boolean initState() {
        initUI();
        this.menuStatus = MENU;
        return true;
    }

    @Override
    public void tick() {
        if(menuStatus == MENU) {
        } else if (menuStatus == OPTION) {
            handler.getSound().setVolume(soundVolume.getValue());
        } else if (menuStatus == EXIT) {
        }
    }

    @Override
    public void sectick() {
        if(menuStatus == MENU) {
        } else if (menuStatus == OPTION) {
        } else if (menuStatus == EXIT) {
        }
    }

    @Override
    public void render(Graphics g) {
        // background
        g.drawImage(Assets.menuBackground,0,0,null);


        if(this.menuStatus == MENU) {
            // title
            g.setColor(Color.BLACK);
            g.setFont(Text.MenuTitleFontMain);
            int stringWidth = g.getFontMetrics().stringWidth(Text.TITLE);
            g.drawString(Text.TITLE,handler.getWindow().getCanvas().getSize().width / 2 - stringWidth / 2,handler.getWindow().getCanvas().getSize().height / 4);
            // button
            play.render(g);
            option.render(g);
            exit.render(g);
        } else if (this.menuStatus == OPTION) {
            // title
            g.setColor(Color.BLACK);
            g.setFont(Text.MenuTitleFontOption);
            int stringWidth = g.getFontMetrics().stringWidth(Text.OPTION);
            g.drawString(Text.OPTION,handler.getWindow().getCanvas().getSize().width / 2 - stringWidth / 2,handler.getWindow().getCanvas().getSize().height / 4);
            // button
            back.render(g);
            soundVolume.render(g);
        } else if (this.menuStatus == EXIT) {
            g.setColor(Color.BLACK);
            g.setFont(Text.MenuTitleFontExit);
            int stringWidth = g.getFontMetrics().stringWidth(Text.ExitMassage);
            g.drawString(Text.ExitMassage,handler.getWindow().getCanvas().getSize().width / 2 - stringWidth / 2,handler.getWindow().getCanvas().getSize().height / 2);
            // button
            yes.render(g);
            no.render(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            State.changeState(Game.gameState);
        } else if (e.getSource() == option) {
            this.menuStatus = OPTION;
        } else if (e.getSource() == exit) {
            this.menuStatus = EXIT;
        } else if (e.getSource() == back) {
            Save.save(Text.SavePath, SaveObject.getSaveString(handler));
            this.menuStatus = MENU;
        } else if (e.getSource() == yes) {
            System.exit(1);
        } else if (e.getSource() == no) {
            this.menuStatus = MENU;
        }
    }

    // mouse input handling
    @Override
    public void mousePressed(MouseEvent e) {
        if(menuStatus == MENU) {
            play.mousePressed(e);
            option.mousePressed(e);
            exit.mousePressed(e);
        } else if (menuStatus == OPTION) {
            back.mousePressed(e);
            soundVolume.mousePressed(e);
        } else if (menuStatus == EXIT) {
            yes.mousePressed(e);
            no.mousePressed(e);
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(menuStatus == MENU) {
            play.mouseReleased(e);
            option.mouseReleased(e);
            exit.mouseReleased(e);
        } else if (menuStatus == OPTION) {
            back.mouseReleased(e);
            soundVolume.mouseReleased(e);
        } else if (menuStatus == EXIT) {
            yes.mouseReleased(e);
            no.mouseReleased(e);
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(menuStatus == MENU) {
        } else if (menuStatus == OPTION) {
            soundVolume.mouseDragged(e);
        } else if (menuStatus == EXIT) {
        }
    }

    private void initUI() {
        int ButtonWidth = 200;
        int ButtonHeight = 50;
        int sliderWidth = 600;
        int sliderHeight = 50;
        int cornerRounds = 20;
        //// MENU
        int ButtonPosX = (handler.getWindow().getCanvas().getSize().width / 2) - (ButtonWidth / 2);
        int ButtonDifferenceY = ((handler.getWindow().getCanvas().getSize().height / 2) / 3);
        // Play
        play = new Button(this,handler, Text.ButtonPlay,ButtonPosX,handler.getWindow().getCanvas().getSize().height - ButtonDifferenceY * 3,ButtonWidth,ButtonHeight);
        play.setFont(Text.MenuButtonFont);
        play.setCornerRounds(cornerRounds);
        // Option
        option = new Button(this,handler,Text.ButtonOption,ButtonPosX,handler.getWindow().getCanvas().getSize().height - ButtonDifferenceY * 2,ButtonWidth,ButtonHeight);
        option.setFont(Text.MenuButtonFont);
        option.setCornerRounds(cornerRounds);
        // Exit
        exit = new Button(this,handler,Text.ButtonExit,ButtonPosX,handler.getWindow().getCanvas().getSize().height - ButtonDifferenceY,ButtonWidth,ButtonHeight);
        exit.setFont(Text.MenuButtonFont);
        exit.setCornerRounds(cornerRounds);

        ////OPTION
        ButtonPosX = (handler.getWindow().getCanvas().getSize().width / 2) - (ButtonWidth / 2);
        ButtonDifferenceY = (handler.getWindow().getCanvas().getSize().height / 2) / 3;
        // back
        back = new Button(this, handler, Text.ButtonBack, ButtonPosX, handler.getWindow().getCanvas().getSize().height - ButtonDifferenceY, ButtonWidth, ButtonHeight);
        back.setFont(Text.MenuButtonFont);
        back.setCornerRounds(cornerRounds);
        // sound volume slider
        int sliderPosX = (handler.getWindow().getCanvas().getSize().width / 2) - (sliderWidth / 2);
        soundVolume = new Slider(handler, Text.VOLUME, 0, sliderPosX,handler.getWindow().getCanvas().getSize().height - ButtonDifferenceY * 3,sliderWidth,sliderHeight);
        soundVolume.setCornerRounds(10);
        soundVolume.setTextFont(Text.MenuTextFont);

        //// EXIT
        ButtonPosX = (handler.getWindow().getCanvas().getSize().width / 3) - (ButtonWidth / 2);
        ButtonDifferenceY = (handler.getWindow().getCanvas().getSize().height / 3);
        // yes
        yes = new Button(this, handler, Text.ButtonYes, ButtonPosX, handler.getWindow().getCanvas().getSize().height - ButtonDifferenceY, ButtonWidth, ButtonHeight);
        yes.setFont(Text.MenuButtonFont);
        yes.setCornerRounds(cornerRounds);
        // no
        no = new Button(this, handler, Text.ButtonNo, ButtonPosX * 2, handler.getWindow().getCanvas().getSize().height - ButtonDifferenceY, ButtonWidth, ButtonHeight);
        no.setFont(Text.MenuButtonFont);
        no.setCornerRounds(cornerRounds);

    }

    // GETTER && SETTER
    public Slider getSoundVolume() {
        return soundVolume;
    }
}
