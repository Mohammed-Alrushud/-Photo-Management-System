public class PhotoManager {
    public LinkedList<Photo> photos;

    public PhotoManager() {
        photos = new LinkedList<>();
    }

    public LinkedList<Photo> getPhotos() {
        return photos;
    }

    public void addPhoto(Photo p) {
        photos.insert(p);
    }

    public void deletePhoto(String path) {
        photos.findFirst();
        while (!photos.empty()) {
            if (photos.retrieve().getPath().equals(path)) {
                photos.remove();
                return;
            }
            if (photos.last()) break;
            photos.findNext();
        }
    }
}