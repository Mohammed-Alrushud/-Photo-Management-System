public class Photo {
    private String path;
    private LinkedList<String> tags;

    public Photo(String path, LinkedList<String> tags) {
        this.path = path;
        this.tags = tags;
    }

    public String getPath() {
        return path;
    }

    public LinkedList<String> getTags() {
        return tags;
    }

    public void displayPhoto() {
        System.out.println("Path: " + path);
        System.out.println("Tags:");
        tags.display();
    }
}


