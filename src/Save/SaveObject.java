package Save;

import Main.Handler;

public class SaveObject {

    public static String getSaveString(Handler handler) {
        // construct the String
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(handler.getSound().toString() + "\n");
        return stringBuilder.toString();
    }
}
