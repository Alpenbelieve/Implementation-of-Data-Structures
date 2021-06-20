package BST;

import java.util.*;

public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;
    private int counter;

    public BST() {
        root = null;
        size = 0;
        counter = 0;
    }

    public BST(E[] arrays){
        this();
        for(E e: arrays)
            add(e);
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(E e){
        root = add(root, e);
    }

    //比较精妙的实现方式，重点是返回值、判断条件以及size更新的位置
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (node.e.compareTo(e) < 0)
            node.right = add(node.right, e);
        else if (node.e.compareTo(e) > 0)
            node.left = add(node.left, e);
        return node;
    }

    public boolean contains(E e){
        return contains(root, e);
    }

    private boolean contains(Node node, E e){
        if (node==null)
            return false;
        if (node.e.equals(e))
            return true;
        else if (node.e.compareTo(e)>0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    //用递归和非递归方式进行前中后序遍历
    public void traverseTree(){
        System.out.println("前序遍历：");
        preOrder1(root);
        System.out.println();
        preOrder2(root);
        System.out.println();
        preOrder3(root);
        System.out.println("\n中序遍历：");
        middleOrder1(root);
        System.out.println();
        middleOrder2(root);
        System.out.println("\n后序遍历：");
        postOrder1(root);
        System.out.println();
        postOrder2(root);
        System.out.println("\n层序遍历：");
        levelOrder(root);
    }

    private class Command{
        private String cmd;
        private Node node;
        private Command(String cmd, Node node) {
            this.cmd = cmd;
            this.node = node;
        }
    }

    private void preOrder1(Node node) {
        if (node==null)
            return;
        System.out.print(node.e+" ");
        preOrder1(node.left);
        preOrder1(node.right);
    }

    //常见非递归写法
    private void preOrder2(Node node) {
        if (node==null)
            return;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()){
            Node n = stack.pop();
            System.out.print(n.e+" ");
            if (n.right!=null)
                stack.push(n.right);
            if (n.left!=null)
                stack.push(n.left);
        }
    }

    //模拟系统栈写法
    private void preOrder3(Node node) {
        if (node==null)
            return;
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("push", node));
        while (!stack.isEmpty()){
            Command cmd = stack.pop();
            if (cmd.cmd.equals("push")){
                if (cmd.node.right!=null)
                    stack.push(new Command("push", cmd.node.right));
                if (cmd.node.left!=null)
                    stack.push(new Command("push", cmd.node.left));
                stack.push(new Command("print", cmd.node));
            }else {
                System.out.print(cmd.node.e+" ");
            }
        }
    }

    private void middleOrder1(Node node) {
        if (node==null)
            return;
        middleOrder1(node.left);
        System.out.print(node.e+" ");
        middleOrder1(node.right);
    }

    private void middleOrder2(Node node) {
        if (node==null)
            return;
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("push", node));
        while (!stack.isEmpty()){
            Command cmd = stack.pop();
            if (cmd.cmd.equals("push")){
                if (cmd.node.right!=null)
                    stack.push(new Command("push", cmd.node.right));
                stack.push(new Command("print", cmd.node));
                if (cmd.node.left!=null)
                    stack.push(new Command("push", cmd.node.left));
            }else {
                System.out.print(cmd.node.e+" ");
            }
        }
    }

    private void postOrder1(Node node) {
        if (node==null)
            return;
        postOrder1(node.left);
        postOrder1(node.right);
        System.out.print(node.e+" ");
    }

    private void postOrder2(Node node) {
        if (node==null)
            return;
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("push", node));
        while (!stack.isEmpty()){
            Command cmd = stack.pop();
            if (cmd.cmd.equals("push")){
                stack.push(new Command("print", cmd.node));
                if (cmd.node.right!=null)
                    stack.push(new Command("push", cmd.node.right));
                if (cmd.node.left!=null)
                    stack.push(new Command("push", cmd.node.left));
            }else {
                System.out.print(cmd.node.e+" ");
            }
        }
    }

    private void levelOrder(Node node) {
        if (node==null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node n = queue.remove();
            System.out.print(n.e+" ");
            if (n.left!=null)
                queue.add(n.left);
            if (n.right!=null)
                queue.add(n.right);
        }
    }

    public E getMin(){
        if (size==0)
            throw new IllegalArgumentException("GetMin failed because BST is empty.");
        return getMin(root).e;
    }

    private Node getMin(Node node){
        if (node.left!=null)
            return getMin(node.left);
        else
            return node;
    }

    public E getMax(){
        if (size==0)
            throw new IllegalArgumentException("GetMax failed because BST is empty.");
        return getMax(root).e;
    }

    private Node getMax(Node node){
        if (node.right!=null)
            return getMax(node.right);
        else
            return node;
    }

    public E removeMin(){
        if (size==0)
            throw new IllegalArgumentException("GetMin failed because BST is empty.");
        E e = getMin();
        root = removeMin(root);
        return e;
    }

    private Node removeMin(Node node){
        if (node.left==null){
            //注意node.right无论是否为空都适用
            Node rightChild = node.right;
            node.right=null;
            size--;
            return rightChild;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax(){
        if (size==0)
            throw new IllegalArgumentException("GetMax failed because BST is empty.");
        E e = getMax();
        root = getMax(root);
        return e;
    }

    private Node removeMax(Node node){
        if (node.right==null){
            Node leftChild = node.left;
            node.left = null;
            size--;
            return leftChild;
        }
        node.right = removeMin(node.right);
        return node;
    }

    public void remove(E e){
        remove(root, e);
    }

    //重点方法
    private Node remove(Node node, E e){
        if (node==null)
            return null;
        if (node.e.compareTo(e)>0){
            node.right = remove(node.right, e);
            return node;
        }else if (node.e.compareTo(e)<0){
            node.left = remove(node.left, e);
            return node;
        }else {
            if (node.left==null){
                Node rightChild = node.right;
                node.right = null;
                size--;
                return rightChild;
            }
            if (node.right==null){
                Node leftChild = node.left;
                node.left = null;
                size--;
                return leftChild;
            }
            //左右子树都不为空，找右子树的最小值（当然也可以找左子树的最大值）
            //注意size在removeMin中已经更新
            Node targetNode = getMin(node.right);
            targetNode.right = removeMin(node.right);
            targetNode.left = node.left;
            node.left = null;
            node.right = null;
            return targetNode;
        }
    }

    public E floor(E e){
        if (getMin().compareTo(e)>0)
            return null;
        if (getMax().compareTo(e)<0)
            return getMax();
        return floor(root, e);
    }

    private E floor(Node node, E e){
        if (node==null)
            return null;
        if (node.e.equals(e)||node.right==null){
            return node.e;
        }else if (node.e.compareTo(e)>0){
            return floor(node.left, e);
        }else {
            if (getMin(node.right).e.compareTo(e)>0)
                return node.e;
            else
                return floor(node.right, e);
        }
    }

    public E ceil(E e){
        if (getMin().compareTo(e)>0)
            return getMin();
        if (getMax().compareTo(e)<0)
            return null;
        return ceil(root, e);
    }

    private E ceil(Node node, E e){
        if (node==null)
            return null;
        if (node.e.equals(e)||node.left==null){
            return node.e;
        }else if (node.e.compareTo(e)<0){
            return ceil(node.right, e);
        }else {
            if (getMax(node.left).e.compareTo(e)<0)
                return node.e;
            else
                return ceil(node.left, e);
        }
    }

    //返回元素的排名，从1开始
    public int rank(E e){
        if (!contains(e))
            throw new IllegalArgumentException(String.format("Rank failed because BST doesn't contains element %s.", e));
        counter = 0;
        return rank(root, e);
    }

    private int rank(Node node, E e){
        if (node==null)
            return -1;
        int left = rank(node.left, e);
        if (left!=-1)
            return left;
        counter++;
        if (node.e.equals(e))
            return counter;
        return rank(node.right, e);
    }

    public E select(int index){
        if (index<=0 || index>size)
            throw new IllegalArgumentException("Select failed because index is invalid");
        counter = index-1;
        return select(root);
    }

    private E select(Node node){
        if (node==null)
            return null;
        E left = select(node.left);
        if (left!=null)
            return left;
        if (counter==0)
            return node.e;
        counter--;
        return select(node.right);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        generateBSTString(root, 0, builder);
        return builder.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder builder) {
        if (node==null){
            builder.append(depth).append("|").append(generateDepthString(depth)).append("NULL\n");
            return;
        }
        builder.append(depth).append("|").append(generateDepthString(depth)).append(node.e).append("\n");
        generateBSTString(node.left, depth+1, builder);
        generateBSTString(node.right, depth+1, builder);
    }

    private String generateDepthString(int depth){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("——");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
//        BST<Integer> bst = new BST<Integer>();
//        Random random = new Random();
//        int n=100;
//        for (int i = 0; i < n; i++) {
//            bst.add(random.nextInt(10000));
//        }
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        while (!bst.isEmpty())
//            arrayList.add(bst.removeMin());
//        System.out.println(arrayList);
//        for (int i = 1; i < arrayList.size(); i++) {
//            if (arrayList.get(i-1)>arrayList.get(i))
//                System.out.println("ERR");
//        }

        BST<Integer> bst = new BST<Integer>(new Integer[]{5,2,8,1,3,7,9});
        System.out.println(bst.toString());
        System.out.println(bst.select(5));
        System.out.println(bst.rank(7));
        System.out.println(bst.getMin());
        System.out.println(bst.getMax());
        System.out.println(bst.ceil(5));
        System.out.println(bst.floor(4));
    }
}
