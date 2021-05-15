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
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //// TEXT

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

    //// FONT
    private static final String fontName = "arial";

    // FONTS GAME
    public static Font GameOverFont = new Font(fontName, Font.BOLD, 50);
    public static Font BreakFont = new Font(fontName, Font.BOLD, 50);
    public static Font DotFont = new Font(fontName, Font.BOLD, 36);
    // FONTS MENU
    public static Font MenuTitleFontMain = new Font(fontName, Font.BOLD, 100);
    public static Font MenuTitleFontOption = new Font(fontName, Font.BOLD, 50);
    public static Font MenuTitleFontExit = new Font(fontName, Font.BOLD, 50);
    public static Font MenuButtonFont = new Font(fontName, Font.BOLD, 30);
    public static Font MenuTextFont = new Font(fontName, Font.BOLD, 30);

    //// PATHS

    // save path
    public static String SavePath = "res/saves/save.txt";
}
