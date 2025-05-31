public class BSTNode<T> {
    public String key;
    public T data;
    public BSTNode<T> left, right;

    public BSTNode(String k, T value) { key = k; data = value; left = right = null; }
    public BSTNode(String k, T value, BSTNode<T> l, BSTNode<T> r) { key = k; data = value; left = l; right = r; }
}
