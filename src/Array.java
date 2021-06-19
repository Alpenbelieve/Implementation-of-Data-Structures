public class Array {
    private int[] data;
    private int size;

    public Array() {
        this(10);
    }

    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(String.format("Array: size=%d, capacity=%d\n[", size, data.length));
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i!=size-1){
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

    public int get(int index){
        if (index<0 || index>=size){
            throw new IllegalArgumentException(String.format("Get failed. Index %d is invalid.)", index));
        }
        return data[index];
    }

    public void set(int index, int element){
        if (index<0 || index>=size){
            throw new IllegalArgumentException(String.format("Get failed. Index %d is invalid.)", index));
        }
        data[index] = element;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(int element, int index){
        if (size==data.length){
            throw new IllegalArgumentException("Add element failed. Array is full.");
        }
        if (index<0 || index>size){
            throw new IllegalArgumentException(String.format("Add failed. Index %d is invalid.)", index));
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        data[index] = element;
        size++;
    }

    public void addLast(int element){
        add(element, size);
    }

    public void addFirst(int element){
        add(element, 0);
    }

    public int find(int element){
        for (int i = 0; i < data.length; i++) {
            if (data[i]==element)
                return i;
        }
        return -1;
    }

    public boolean contains(int element){
        for (int i = 0; i < data.length; i++) {
            if (data[i]==element)
                return false;
        }
        return false;
    }

    public boolean removeElement(int element){
        int index = find(element);
        if (index==-1)
            return false;
        for (int i = index; i < size; i++) {
            data[i] = data[i+1];
        }
        size--;
        return true;
    }

    public int remove(int index){
        if (index<0 || index>=size){
            throw new IllegalArgumentException(String.format("Remove failed. Index %d is invalid.", index));
        }
        int result = data[index];
        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
        return result;
    }

    public int removeFirst(){
        return remove(0);
    }

    public int removeLast(){
        return remove(size-1);
    }

    public static void main(String[] args) {
        Array array = new Array(20);
        System.out.println(array);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);
        array.add(100,1);
        System.out.println(array);
        array.addFirst(-1);
        System.out.println(array);
        array.remove(2);
        System.out.println(array);
        array.removeElement(4);
        System.out.println(array);
        array.removeFirst();
        System.out.println(array);
    }
}
