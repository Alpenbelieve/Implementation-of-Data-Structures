package Stack;

import Array.Array;

public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack() {
        array = new Array<E>();
    }

    public ArrayStack(int capacity) {
        array = new Array<E>(capacity);
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
    public void push(E element) {
        array.addLast(element);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Stack: size=%d, capacity=%d\n", array.getSize(), array.getCapacity()));
        builder.append("bottom [");
        for (int i = 0; i < array.getSize(); i++) {
            builder.append(array.get(i));
            if (i != array.getSize() - 1)
                builder.append(", ");
        }
        builder.append("] top");
        return builder.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        for (int i = 0; i < 11; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        for (int i = 0; i < 10; i++) {
            stack.pop();
            System.out.println(stack);
        }
    }
}
