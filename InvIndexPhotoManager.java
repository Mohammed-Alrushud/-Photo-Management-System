public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> index;
    public PhotoManager baseManager;

    public InvIndexPhotoManager() {
        index = new BST<>();
        baseManager = new PhotoManager();
    }

    public void addPhoto(Photo p) {
        baseManager.addPhoto(p);
        LinkedList<String> tags = p.getTags();
        tags.findFirst();
        while (!tags.empty()) {
            String tag = tags.retrieve();
            if (index.findKey(tag)) index.retrieve().insert(p);
            else { LinkedList<Photo> list = new LinkedList<>(); list.insert(p); index.insert(tag,list); }
            if (tags.last()) break;
            tags.findNext();
        }
    }

    public void deletePhoto(String path) {
        Photo target = null;
        LinkedList<Photo> all = baseManager.getPhotos(); all.findFirst();
        while (!all.empty()) {
            Photo p = all.retrieve(); if (p.getPath().equals(path)) { target = p; break; }
            if (all.last()) break; all.findNext();
        }
        if (target == null) return;
        baseManager.deletePhoto(path);
        LinkedList<String> tags = target.getTags(); tags.findFirst();
        while (!tags.empty()) {
            if (index.findKey(tags.retrieve())) {
                LinkedList<Photo> list = index.retrieve(); list.findFirst();
                while (!list.empty()) {
                    if (list.retrieve().getPath().equals(path)) { list.remove(); break; }
                    if (list.last()) break; list.findNext();
                }
                if (list.empty()) index.remove_key(tags.retrieve());
            }
            if (tags.last()) break; tags.findNext();
        }
    }

    public BST<LinkedList<Photo>> getIndex() { return index; }
}
