import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    //    a) подсчет количества вершин
    public int getNumberOfNodes(Node node) {
        if (node == null) return 0;
        return 1 + getNumberOfNodes(node.getLeft()) + getNumberOfNodes(node.getRight());
    }

    public int numberOfNodes() {
        return getNumberOfNodes(root);
    }

    //    b) нахождение высоты дерева
    public int getHeight(Node node) {
        if (node == null) return 0;
        int l = getHeight(node.getLeft());
        int r = getHeight(node.getRight());
        return Math.max(l, r) + 1;
    }

    //    c) проверка того, является ли дерево BST: для каждой вершины ключи в левом поддерева меньше, в
//    правом поддереве – больше
    public boolean isBST(Node node, int low, int high) {
        if (node == null) return true;
        if (node.getKey() <= low || node.getKey() >= high) {
            return false;
        }
        return isBST(node.getLeft(), low, node.getKey()) && isBST(node.getRight(), node.getKey(), high);
    }

    //    d) поиск по ключу
    public Node searchByKey(Node node, int key) {
        if (node == null || node.getKey() == key) {
            return node;
        }
        if (key < node.getKey()) {
            return searchByKey(node.getLeft(), key);
        }
        return searchByKey(node.getRight(), key);
    }


    //    f) поиск минимального элемента в дереве
    public Node getMin(Node node) {
        while (node.getLeft() != null)
            node = node.getLeft();
        return node;
    }

    //    e) поиск максимального элемента в дереве
    public Node getMax(Node node) {
        while (node.getRight() != null)
            node = node.getRight();
        return node;
    }

//    g) для заданного элемента поиск следующего элемента

    //    h1) удаление поддерева
    public void deleteSubTree(int key) {
        Node n = searchByKey(root, key);
        if (n == null) return;
        Node p = n.getParent();
        if (p.getLeft() != null && p.getLeft().getKey() == key)
            p.setLeft(null);
        if (p.getRight() != null && p.getRight().getKey() == key)
            p.setRight(null);
        //  count = count - calcCount(n);
    }

    //    h2) удаление элемента по ключу
    private Node deleteRecursive(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key == node.getKey()) {
            // Node to delete found
            // ... code to delete the node will go here
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            int smallestValue = getMin(node.getRight()).getKey();
            node.setKey(smallestValue);
            node.setRight(deleteRecursive(node.getRight(), smallestValue));
            return node;
        }
        if (key < node.getKey()) {
            node.setLeft(deleteRecursive(node.getLeft(), key));
            return node;
        }
        node.setRight(deleteRecursive(node.getRight(), key));
        return node;
    }

    //    i) вставка элемента
    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key, null, null);
        }
        if (key < node.getKey()) {
            node.setLeft(insert(node.getLeft(), key));
        } else if (key > node.getKey()) {
            node.setRight(insert(node.getRight(), key));
        } else { //  already exists
            return node;
        }
        return node;
    }

    public Node insert(int key) {
        return insert(root, key);
    }


    //  j) чтение данных для дерева из текстового файла
    public void readFromFile(String path) {

        try (Scanner scanner = new Scanner(new File(path))) {
            int n = scanner.nextInt();
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node();
            }
            for (int i = 0; i < n; i++) {
                nodes[i].setKey(scanner.nextInt());
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                if (left != 0) {
                    nodes[i].setLeft(nodes[left - 1]);
                    nodes[left - 1].setParent(nodes[i]);
                }
                if (right != 0) {
                    nodes[i].setRight(nodes[right - 1]);
                    nodes[right - 1].setParent(nodes[i]);
                }
            }
            root = nodes[0];
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
