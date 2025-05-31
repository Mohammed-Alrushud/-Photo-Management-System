public class BST<T> {
    private BSTNode<T> root;
    private BSTNode<T> current;

    public BST() { root = current = null; }
    public boolean empty() { return root == null; }
    public boolean full() { return false; }
    public T retrieve() { return current.data; }

    public boolean findKey(String k) {
        BSTNode<T> p = root;
        while (p != null) {
            int cmp = k.compareTo(p.key);
            if (cmp == 0) { current = p; return true; }
            p = (cmp < 0) ? p.left : p.right;
        }
        return false;
    }

    public boolean insert(String k, T value) {
        if (root == null) { root = current = new BSTNode<>(k, value); return true; }
        if (findKey(k)) return false;
        BSTNode<T> tmp = new BSTNode<>(k, value);
        if (k.compareTo(current.key) < 0) current.left = tmp;
        else current.right = tmp;
        current = tmp;
        return true;
    }

    public boolean remove_key(String k) {
        BSTNode<T> p = root, q = null;
        String k1 = k;
        while (p != null) {
            int cmp = k1.compareTo(p.key);
            if (cmp < 0) { q = p; p = p.left; }
            else if (cmp > 0) { q = p; p = p.right; }
            else {
                if (p.left != null && p.right != null) {
                    BSTNode<T> min = p.right; q = p;
                    while (min.left != null) { q = min; min = min.left; }
                    p.key = min.key; p.data = min.data;
                    k1 = min.key; p = min;
                }
                BSTNode<T> child = (p.left != null) ? p.left : p.right;
                if (q == null) root = child;
                else if (k1.compareTo(q.key) < 0) q.left = child;
                else q.right = child;
                break;
            }
        }
        current = root;
        return true;
    }
}
