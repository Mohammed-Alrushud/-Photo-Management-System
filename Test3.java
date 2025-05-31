public class Test3 {
    public static void main(String[] args) {
        // Setup BST inverted-index manager and photos
        InvIndexPhotoManager invMgr = new InvIndexPhotoManager();
        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        invMgr.addPhoto(photo1); invMgr.addPhoto(photo2);

        // BST insert/delete tests on index
        BST<LinkedList<Photo>> bst = invMgr.getIndex();
        System.out.println("[Test3 - BST] findKey 'animal': " + bst.findKey("animal"));
        System.out.print("[Test3 - BST] Retrieved list: "); printPhotos(bst.retrieve());

        System.out.println("[Test3 - BST] Removing key 'wind'");
        bst.remove_key("wind");
        System.out.println("[Test3 - BST] findKey 'wind' after removal: " + bst.findKey("wind"));

        // Using AlbumInvertedIndex on BST manager
        Album invIndexedAlbum = new AlbumInvertedIndex("BSTAlbum", "animal AND grass", invMgr);
        System.out.println("[Test3 - BST] IndexedAlbum photos:");
        printPhotos(invIndexedAlbum.getPhotos());
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<>();
        for (String t : tags.split("\\s*,\\s*")) result.insert(t);
        return result;
    }

    private static void printPhotos(LinkedList<Photo> photos) {
        if (photos.empty()) { System.out.println("No photos available."); return; }
        photos.findFirst();
        while (true) {
            System.out.print(photos.retrieve().getPath());
            if (photos.last()) break;
            System.out.print(", "); photos.findNext();
        }
        System.out.println();
    }
}