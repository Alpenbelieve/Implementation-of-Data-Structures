package Queue;

import Array.Array;

public class ArrayQueue<E> implements Queue {

    private Array<E> array;

    public ArrayQueue() {
        array = new Array<E>();
    }

    public ArrayQueue(int capacity) {
        array = new Array<E>(capacity);
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enqueue(Object o) {
        array.addLast((E) o);
    }

    //数组队列的出队时间复杂度是O(N)
    @Override
    public Object dequeue() {
        return array.removeFirst();
    }

    @Override
    public Object getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Queue.Queue: size=%d, capacity=%d\n", array.getSize(), array.getCapacity()));
        builder.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            builder.append(array.get(i));
            if (i != array.getSize() - 1)
                builder.append(", ");
        }
        builder.append("] tail");
        return builder.toString();
    }
}
