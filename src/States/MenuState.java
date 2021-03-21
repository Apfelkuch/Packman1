package States;

import Componts.Button;
import ImageLoad.Assets;
import Main.Game;
import Main.Handler;
import Text.Text;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MenuState extends State implements ActionListener {

    private int ButtonWidth = 200;
    private int ButtonHeight = 50;

    // Buttons MENU
    private Button play;
    private Button option;
    private Button exit;
    // Buttons OPTION
    private Button back;
    // Button EXIT
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
        initButton();
        this.menuStatus = MENU;
        return true;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        //background
        g.drawImage(Assets.menuBackground,0,0,null);


        if(this.menuStatus == MENU) {
            //titel
            g.setColor(Color.BLACK);
            g.setFont(Text.MENUTITLEFONTMAIN);
            int stringwidth = g.getFontMetrics().stringWidth(Text.TITLE);
            g.drawString(Text.TITLE,handler.getWindow().getCanvas().getSize().width / 2 - stringwidth / 2,handler.getWindow().getCanvas().getSize().height / 4);
            //button
            play.render(g);
            option.render(g);
            exit.render(g);
        } else if (this.menuStatus == OPTION) {
            //titel
            g.setColor(Color.BLACK);
            g.setFont(Text.MENUTITLEFONTOPTION);
            int stringwidth = g.getFontMetrics().stringWidth(Text.OPTION);
            g.drawString(Text.OPTION,handler.getWindow().getCanvas().getSize().width / 2 - stringwidth / 2,handler.getWindow().getCanvas().getSize().height / 4);
            //button
            back.render(g);
        } else if (this.menuStatus == EXIT) {
            g.setColor(Color.BLACK);
            g.setFont(Text.MENUTITLEFONTEXIT);
            int stringwidth = g.getFontMetrics().stringWidth(Text.EXITMASSAGE);
            g.drawString(Text.EXITMASSAGE,handler.getWindow().getCanvas().getSize().width / 2 - stringwidth / 2,handler.getWindow().getCanvas().getSize().height / 2);
            //button
            yes.render(g);
            no.render(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("Action Performed");
        if (e.getSource() == play) {
//            System.out.println("[Button] play");
            State.changeState(Game.gameState);
        } else if (e.getSource() == option) {
//            System.out.println("[Button] option");
            this.menuStatus = OPTION;
        } else if (e.getSource() == exit) {
//            System.out.println("[Button] exit");
            this.menuStatus = EXIT;
        } else if (e.getSource() == back) {
            this.menuStatus = MENU;
        } else if (e.getSource() == yes) {
            System.exit(1);
        } else if (e.getSource() == no) {
            this.menuStatus = MENU;
        }
    }

    // mouse input handeling
    @Override
    public void mousePressed(MouseEvent e) {
        if(menuStatus == MENU) {
            play.mousePressed(e);
            option.mousePressed(e);
            exit.mousePressed(e);
        } else if (menuStatus == OPTION) {
            back.mousePressed(e);
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
        } else if (menuStatus == EXIT) {
            yes.mouseReleased(e);
            no.mouseReleased(e);
        }
    }

    private void initButton() {
        int cornerrounds = 20;
        // MENU
        int ButtonPosX = (handler.getWindow().getCanvas().getSize().width / 2) - (ButtonWidth / 2);
        int ButtondifferenceY = ((handler.getWindow().getCanvas().getSize().height / 2) / 3);
        // Play
        play = new Button(this,handler, Text.ButtonPlay,ButtonPosX,handler.getWindow().getCanvas().getSize().height - ButtondifferenceY * 3,ButtonWidth,ButtonHeight);
        play.setFont(Text.MENUBUTTONFONT);
        play.setCornerrounds(cornerrounds);
        // Option
        option = new Button(this,handler,Text.ButtonOption,ButtonPosX,handler.getWindow().getCanvas().getSize().height - ButtondifferenceY * 2,ButtonWidth,ButtonHeight);
        option.setFont(Text.MENUBUTTONFONT);
        option.setCornerrounds(cornerrounds);
        // Exit
        exit = new Button(this,handler,Text.ButtonExit,ButtonPosX,handler.getWindow().getCanvas().getSize().height - ButtondifferenceY,ButtonWidth,ButtonHeight);
        exit.setFont(Text.MENUBUTTONFONT);
        exit.setCornerrounds(cornerrounds);

        //OPTION
        ButtonPosX = (handler.getWindow().getCanvas().getSize().width / 2) - (ButtonWidth / 2);
        ButtondifferenceY = (handler.getWindow().getCanvas().getSize().height / 2) / 3;
        back = new Button(this, handler, Text.ButtonBack, ButtonPosX, handler.getWindow().getCanvas().getSize().height - ButtondifferenceY, ButtonWidth, ButtonHeight);
        back.setFont(Text.MENUBUTTONFONT);
        back.setCornerrounds(cornerrounds);

        // EXIT
        ButtonPosX = (handler.getWindow().getCanvas().getSize().width / 3) - (ButtonWidth / 2);
        ButtondifferenceY = (handler.getWindow().getCanvas().getSize().height / 3);
        // yes
        yes = new Button(this, handler, Text.ButtonYes, ButtonPosX, handler.getWindow().getCanvas().getSize().height - ButtondifferenceY, ButtonWidth, ButtonHeight);
        yes.setFont(Text.MENUBUTTONFONT);
        yes.setCornerrounds(cornerrounds);
        // no
        no = new Button(this, handler, Text.ButtonNo, ButtonPosX * 2, handler.getWindow().getCanvas().getSize().height - ButtondifferenceY, ButtonWidth, ButtonHeight);
        no.setFont(Text.MENUBUTTONFONT);
        no.setCornerrounds(cornerrounds);

    }
}
