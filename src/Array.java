import java.util.ArrayList;

public class Array<E> {
    private E[] data;
    private int size;

    public Array() {
        this(10);
    }

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(String.format("Array: size=%d, capacity=%d\n[", size, data.length));
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        return builder.append("]").toString();
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(String.format("Get failed. Index %d is invalid.)", index));
        }
        return data[index];
    }

    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(String.format("Get failed. Index %d is invalid.)", index));
        }
        data[index] = element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E element, int index) {
        if (size == data.length) {
            throw new IllegalArgumentException("Add element failed. Array is full.");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(String.format("Add failed. Index %d is invalid.)", index));
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    public void addLast(E element) {
        add(element, size);
    }

    public void addFirst(E element) {
        add(element, 0);
    }

    public int find(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element))//不能用==
                return i;
        }
        return -1;
    }

    public ArrayList<Integer> findAll(int element) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element))//不能用==
                result.add(i);
        }
        return result;
    }

    public boolean contains(int element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element))//不能用==
                return false;
        }
        return false;
    }

    //只会删除第一个
    public boolean removeFirstElement(E element) {
        int index = find(element);
        if (index == -1)
            return false;
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return true;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(String.format("Remove failed. Index %d is invalid.", index));
        }
        E result = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;//这一句也可以不用
        return result;
    }

    public boolean removeAllElements(int element) {
        ArrayList<Integer> indexes = findAll(element);
        if (indexes.size() == 0)
            return false;
        int index = 0;
        int i = 0, j = 0;
        while (j < size) {
            if (j == indexes.get(index)) {
                j++;
                index++;
                if (index == indexes.size()) {
                    while (j < size)
                        data[i++] = data[j++];
                    for (int k = j - 1; k >= i; k--)
                        data[k] = null;
                    size = i;
                    return true;
                }
            } else
                data[i++] = data[j++];
        }
        for (int k = j - 1; k >= i; k--)
            data[k] = null;
        size = i;
        return true;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public static void main(String[] args) {
        Array<Integer> array = new Array<>(20);
        System.out.println(array);
        for (int i = 0; i < 10; i++) {
            array.addLast(10);
        }
        System.out.println(array);
        array.add(100, 3);
        System.out.println(array);
        array.addLast(100);
        System.out.println(array);
        array.addFirst(100);
        System.out.println(array);
        System.out.println(array.removeAllElements(10));
        System.out.println(array);
    }
}
