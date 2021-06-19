package Stack;

import LinkedList.LinkedList;
import Queue.LinkedListQueue;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        this.list = new LinkedList<E>();
    }

    public LinkedListStack(LinkedList list){
        this.list = list;
    }

    public LinkedListStack(E[] array) {
        this.list = new LinkedList<E>(array);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    //之所以是addFirst而不是addLast是因为速度快
    @Override
    public void push(E o) {
        list.addFirst(o);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        return "Stack " + list;
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> linkedList = new LinkedListStack<Integer>();
        for (int i = 0; i < 5; i++) {
            linkedList.push(i);
            System.out.println(linkedList);
        }
        linkedList.pop();
        System.out.println(linkedList);
    }
}
