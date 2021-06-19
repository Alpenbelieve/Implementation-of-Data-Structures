package Queue;

import java.util.Objects;

public class LoopQueue<E> implements Queue {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
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
        return size;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(Object o) {
        if ((front + 1) % data.length == tail)
            resize(getCapacity() * 2);
        data[tail] = (E) o;
        tail = (tail + 1) % data.length;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    //循环队列的出队时间复杂度是O(1)
    @Override
    public Object dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Can't dequeue because queue is empty.");
        E element = data[front];
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && size >= 5)
            resize(getCapacity() / 2);
        return element;
    }

    @Override
    public Object getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Can't getFront because queue is empty.");
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Queue: size=%d, capacity=%d\n", getSize(), getCapacity()));
        builder.append("front [");
        //下面的遍历方式和resize方法中是两种不同的方式，都可以
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            builder.append(data[i]);
            if ((i + 1) % data.length != tail)
                builder.append(", ");
        }
        builder.append("] tail");
        return builder.toString();
    }
}
