package LinkedList;

public class LinkedList<E> {

    private static class Node<E> {
        E e;
        Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node<E> dummyHead;
    private int size;
    public LinkedList() {
        dummyHead = new Node<>();
        size = 0;
    }

    public LinkedList(Node<E> dummyHead, int size) {
        this.dummyHead = dummyHead;
        this.size = size;
    }

    public LinkedList(E[] array) {
        if (array==null || array.length == 0){
            dummyHead = new Node<>();
            size = 0;
        }else {
            Node<E> dummyHead = new Node<>();
            Node ptr = dummyHead;
            for (E t : array) {
                ptr.next = new Node<>(t);
                ptr = ptr.next;
            }
            this.dummyHead = dummyHead;
            this.size = array.length;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e, int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed because index is invalid.");
        Node prev = dummyHead;
        //注意遍历的次数
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node<>(e, prev.next);//浓缩写法
        size++;
    }

    public void addFirst(E e) {
        add(e, 0);
    }

    public void addLast(E e) {
        add(e, size);
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed because index is invalid.");
        Node ptr = dummyHead;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return (E) ptr.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed because index is invalid.");
        Node ptr = dummyHead;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        ptr.e = e;
    }

    public boolean contains(E e) {
        Node ptr = dummyHead.next;
        //另一种写法
        while (ptr != null) {
            if (ptr.e.equals(e))//注意不用==
                return true;
            ptr = ptr.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed because index is invalid.");
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node targetNode = prev.next;
        prev.next = targetNode.next;
        targetNode.next = null;
        size--;
        return (E) targetNode.e;
    }

    public void removeAllElements(E e) {
        if (size == 0)
            return;
        Node cur = dummyHead;
        while (cur.next != null)
            if (cur.next.e.equals(e)) {
                cur.next = cur.next.next;
                size--;
            } else
                cur = cur.next;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(String.format("Size:%d  ", size));
        //第三种写法
        for (Node ptr = dummyHead.next; ptr != null; ptr = ptr.next) {
            builder.append(ptr).append("->");
        }
        builder.append("NULL");
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>(new Integer[]{1,2,1,3,1});
        linkedList.removeAllElements(1);
        System.out.println(linkedList);
    }
}
