package music;

public class Song {
    private String name;
    private String path;

    public Song(String name, String path) {
        this.name = name;
        this.path = path;
    }

    // GETTER && SETTER
    public String getName() {
        return name;
    }
    public String getPath() {
        return path;
    }
}