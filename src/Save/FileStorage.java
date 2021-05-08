package Save;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileStorage {

    private File storageFile;
    private HashMap<String, Object> storageMap;

    public FileStorage (File file) throws IOException, IllegalArgumentException {
        this.storageFile = file;

        if(storageFile.isDirectory()) {
            throw new IllegalArgumentException("storageFile must not be a directory");
        }
        if(storageFile.createNewFile()) {
            storageMap = new HashMap<String, Object>();
            save();
            System.out.println("FileStorage.FileStorage: load file does not exits");
        } else {
            load();
            System.out.println("FileStorage.FileStorage: load file exists");
        }
    }

    private void save() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(storageFile));
            objectOutputStream.writeObject(storageMap);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(storageFile));
            storageMap = (HashMap<String, Object>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void store(String key, Object object) {
        storageMap.put(key, object);
        save();
    }

    public Object get(String key) {
        return storageMap.get(key);
    }

    public ArrayList<Object> getAllAsArrayList() {
        ArrayList<Object> result = new ArrayList<Object>();
        result.addAll(storageMap.values());
        return result;
    }

    public HashMap<String, Object> getAll() {
        return storageMap;
    }

    public void printlAll() {
        for(String key : storageMap.keySet()) {
            System.out.println(key + " :: " + storageMap.get(key));
        }
    }

    public void remove(String key) {
        storageMap.remove(key);
        save();
    }

    public boolean hasKey(String key) {
        return storageMap.containsKey(key);
    }

    public boolean hasObject(Object object) {
        return storageMap.containsValue(object);
    }

    public int getSize() {
        return storageMap.size();
    }

    @Override
    public String toString() {
        String s = "";
        for(String key : storageMap.keySet()) {
            s += key + " :: " + storageMap.get(key) + "\n";
        }
        return s.trim();
    }
}
