public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    protected int nbComps;

    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        this.nbComps = 0;
    }

    public String getName() { return name; }
    public String getCondition() { return condition; }
    public PhotoManager getManager() { return manager; }

    public LinkedList<Photo> getPhotos() {
        nbComps = 0;
        LinkedList<Photo> result = new LinkedList<>();
        String[] condTags = condition.isEmpty() ? new String[0] : condition.split("\\s+AND\\s+");

        LinkedList<Photo> all = manager.getPhotos();
        all.findFirst();
        while (!all.empty()) {
            Photo p = all.retrieve();
            boolean match = true;
            for (String t : condTags) {
                LinkedList<String> tags = p.getTags();
                tags.findFirst();
                boolean found = false;
                while (!tags.empty()) {
                    nbComps++;
                    if (tags.retrieve().equals(t)) { found = true; break; }
                    if (tags.last()) break;
                    tags.findNext();
                }
                if (!found) { match = false; break; }
            }
            if (match) result.insert(p);
            if (all.last()) break;
            all.findNext();
        }
        return result;
    }

    public int getNbComps() { return nbComps; }
}
