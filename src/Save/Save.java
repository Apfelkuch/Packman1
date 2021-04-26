package Save;

import java.io.File;
import java.io.FileWriter;

public class Save {

    public static boolean save(String path, String saveString) {
        try {
            // create the file
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();

            // write the saveString to the file
            FileWriter fw = new FileWriter(file);
            fw.write(saveString);
            fw.flush();
            fw.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
