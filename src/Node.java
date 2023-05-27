public class Node {

    public int key; // data + key
    public Node left;
    public Node right;
    private Node parent;
    public int height; // balance factor

    public Node() {
    }

    public Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public Node(int key, Node left, Node right, Node parent) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setValue(int value) {
        this.key = value;
    }

    public void setBalance(int balance) {
        this.height = balance;
    }

    @Override
    public String toString() {
        return key + " ";
    }
}
