public class Test2 {
    public static void main(String[] args) {
        // Setup LinkedList-based manager and photos
        PhotoManager manager = new PhotoManager();
        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        manager.addPhoto(photo1); manager.addPhoto(photo2);

        // LinkedList insert/delete/getPhotos tests
        LinkedList<Photo> ll = new LinkedList<>();
        System.out.println("[Test2 - LinkedList] After insert photo1 & photo2:");
        ll.insert(photo1);
        ll.insert(photo2);
        printPhotos(ll);

        ll.findFirst();
        ll.remove();
        System.out.println("[Test2 - LinkedList] After remove():");
        printPhotos(ll);
        
        ll.findFirst();
        ll.remove();
        System.out.println("[Test2 - LinkedList] After remove():");
        printPhotos(ll);
        

        // Using Album on LinkedList manager for getPhotos
        Album album = new Album("LLAlbum", "animal AND grass", manager);
        System.out.println("[Test2 - LinkedList] Album photos:");
        printPhotos(album.getPhotos());
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