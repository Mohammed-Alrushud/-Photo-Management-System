public class AlbumInvertedIndex extends Album {
    private InvIndexPhotoManager indexedManager;

    public AlbumInvertedIndex(String name, String condition, InvIndexPhotoManager mgr) {
        super(name, condition, mgr.baseManager);
        this.indexedManager = mgr;
    }

    @Override
    public LinkedList<Photo> getPhotos() {
        nbComps = 0;
        String[] condTags = getCondition().isEmpty() ? new String[0] : getCondition().split("\\s+AND\\s+");
        LinkedList<Photo> result = null;
        for (String t : condTags) {
            nbComps++;
            if (!indexedManager.getIndex().findKey(t)) {
                return new LinkedList<>();
            }
            LinkedList<Photo> list = indexedManager.getIndex().retrieve();
            if (result == null) result = cloneList(list);
            else result = intersectLists(result, list);
        }
        return result == null ? indexedManager.baseManager.getPhotos() : result;
    }

    private LinkedList<Photo> cloneList(LinkedList<Photo> src) {
        LinkedList<Photo> copy = new LinkedList<>();
        src.findFirst();
        while (!src.empty()) {
            copy.insert(src.retrieve());
            if (src.last()) break;
            src.findNext();
        }
        return copy;
    }

    private LinkedList<Photo> intersectLists(LinkedList<Photo> a, LinkedList<Photo> b) {
        LinkedList<Photo> inter = new LinkedList<>();
        a.findFirst();
        while (!a.empty()) {
            Photo p = a.retrieve();
            b.findFirst(); boolean found = false;
            while (!b.empty()) {
                if (b.retrieve().getPath().equals(p.getPath())) { found = true; break; }
                if (b.last()) break;
                b.findNext();
            }
            if (found) inter.insert(p);
            if (a.last()) break;
            a.findNext();
        }
        return inter;
    }
}
