package Queue;

public class LinkedListQueue<E> implements Queue<E> {

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

    private Node<E> head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedListQueue(Node<E> head, Node<E> tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    public LinkedListQueue(E[] array) {
        if (array==null || array.length == 0){
            head = null;
            tail = null;
            size = 0;
        }else {
            Node<E> head = new Node<>();
            Node<E> tail;
            Node ptr = head;
            for (E t : array) {
                ptr.next = new Node<>(t);
                ptr = ptr.next;
            }
            tail = ptr;
            this.head = head;
            this.tail = tail;
            this.size = array.length;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;//注意要后移tail
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Dequeue failed because queue is empty.");
        Node targetNode = head;
        head = head.next;
        targetNode.next = null;
        if (head == null)//注意特殊情况下的判断，以及size的更新
            tail = null;
        size--;
        return (E) targetNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Dequeue failed because queue is empty.");
        return head.e;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder(String.format("Size:%d  head ", size));
        //第三种写法
        for (Node ptr = head.next; ptr != null; ptr = ptr.next) {
            builder.append(ptr).append("->");
        }
        builder.append("NULL tail");
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue(new Integer[]{1, 3, 5, 7});
        System.out.println(queue);
    }
}
