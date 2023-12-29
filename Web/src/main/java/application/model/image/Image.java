package application.model.image;

public class Image {

    private String path;

    public Image() {
        this.path = "default";
    }

    public Image(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

