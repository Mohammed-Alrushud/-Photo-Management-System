public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> current;

    public LinkedList() {
        head = current = null;
    }

    @Override
    public boolean empty() {
        return head == null;
    }

    @Override
    public boolean last() {
        return current != null && current.next == null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public void findFirst() {
        current = head;
    }

    @Override
    public void findNext() {
        if (current != null) current = current.next;
    }

    @Override
    public T retrieve() {
        return current.data;
    }

    @Override
    public void update(T e) {
        current.data = e;
    }

    @Override
    public void insert(T e) {
        if (empty() || current == null) {
            head = current = new Node<>(e);
        } else {
            Node<T> tmp = current.next;
            current.next = new Node<>(e);
            current = current.next;
            current.next = tmp;
        }
    }

    @Override
    public void remove() {
        if (empty()) return;
        if (current == head) {
            head = head.next;
            current = head;
        } else {
            Node<T> tmp = head;
            while (tmp.next != current) {
                tmp = tmp.next;
            }
            tmp.next = current.next;
            current = tmp.next != null ? tmp.next : head;
        }
    }

    public void display() {
        if (empty()) {
            System.out.println("empty list");
            return;
        }
        Node<T> p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }
}
