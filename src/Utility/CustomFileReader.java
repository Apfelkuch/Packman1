package Utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CustomFileReader {

    /**
     * Load the content from a given path of a file into a String. The structure of the String is identical to the file structure.
     * @param path The path to the file, which is loaded.
     * @return A String with the content of the file.
     */
    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine())!= null){
                builder.append(line+"\n");
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }
}
