package Save;

import java.io.File;
import java.io.FileWriter;

public class Save {

    /**
     * Create the file with the path, if the file does not exists and writes the content to it.
     *
     * @param path       The file path.
     * @param saveString The content which is written to the file.
     * @return true if the process was successful, otherwise false.
     */
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
