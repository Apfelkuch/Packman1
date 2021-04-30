package Text;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Text {

    public Text() {
        // load custom Fonts
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // load all Fonts from the Font directory "res/font"
        File files[] = new File("res/font").listFiles();
        for (File file : files) {
            try {
                graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, file));
                System.out.println("Font: " + file.getName() + " is loaded.");
            } catch (FontFormatException e) {
                System.out.println("Font: " + file.getName() + " is not loaded.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Font: " + file.getName() + " is not loaded.");
                e.printStackTrace();
            }
        }
    }

    // MENU
    public static String TITLE = "Packmann";
    public static String OPTION = "Options";
    public static String ExitMassage = "Do you want to exit the Game!";
    public static String VOLUME = "volume";

    // GAME
    public static String WIN = "You Win!!";
    public static String LOST = "You Lost!";
    public static String BREAK = "Break";

    // BUTTON
    public static String ButtonPlay = "play";
    public static String ButtonExit = "exit";
    public static String ButtonOption = "option";
    public static String ButtonBack = "back";
    public static String ButtonYes = "yes";
    public static String ButtonNo = "no";
    public static String ButtonContinue = "continue";

    // FONTS GAME
    public static Font GameOverFont = new Font(Font.MONOSPACED,Font.BOLD,50);
    public static Font BreakFont = new Font(Font.MONOSPACED,Font.BOLD,50);
    public static Font DotFont = new Font(Font.MONOSPACED,Font.BOLD,36);
    // FONTS MENU
    public static Font MenuTitleFontMain = new Font(Font.MONOSPACED,Font.BOLD,100);
    public static Font MenuTitleFontOption = new Font(Font.MONOSPACED,Font.BOLD,50);
    public static Font MenuTitleFontExit = new Font(Font.MONOSPACED,Font.BOLD,50);
    public static Font MenuButtonFont = new Font(Font.MONOSPACED,Font.BOLD,30);
    public static Font MenuTextFont = new Font(Font.MONOSPACED, Font.BOLD,30);

    // save path
    public static String SavePath = "res/save/save.txt";
}
