import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AVLTree {
    Node root;

    public AVLTree(){}

    public AVLTree(Node root) {
        this.root = root;
    }
    public int updateHeight(Node node){
        if(node == null) return -1;
        int left = updateHeight(node.left);
        int right = updateHeight(node.right);

        return Math.max(left , right) + 1;

    }

    int height(Node node){
        if (node == null){
            return 0;
        }else {
            return node.height;
        }
    }
    int getBalance(Node node){
        if (node == null){
            return 0;
        }else {
            return updateHeight(node.right)- updateHeight(node.left);
        }
    }



    public void readFile(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                Node[] nodes = new Node[n];
                for (int i = 0; i < n; i++) {
                    nodes[i] = new Node();
                }
                for (int i = 0; i < n; i++) {
                    nodes[i].setKey(scanner.nextInt());
                    int l = scanner.nextInt();
                    int r = scanner.nextInt();
                    if (l != 0) {
                        nodes[i].setLeft(nodes[l - 1]);
                        nodes[l - 1].setParent(nodes[i]);
                    }
                    if (r != 0) {
                        nodes[i].setRight(nodes[r - 1]);
                        nodes[r - 1].setParent(nodes[i]);
                    }
                }
                root = nodes[0];
                for (int i = 0; i < n; i++) {
                    System.out.println(getBalance(nodes[i]));
                }
            } else {
                System.out.println("Invalid input file format.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
    }
    public int getHeight(Node node){
        if(node == null) return -1;
        int left = getHeight(node.left);
        int right = getHeight(node.right);

        return Math.max(left , right) + 1;

    }
    Node rotateLeft(Node node){
        Node newNode = node.right;
        node.right= newNode.left;
        newNode.left= node;

        updateHeight(node);
        updateHeight(newNode);
        return newNode;
    }
    Node rotateRight(Node node) {
        Node newNode = node.left;
        node.left = newNode.right;
        newNode.right = node;

        updateHeight(node);
        updateHeight(newNode);

        return newNode;
    }
    public Node inorderTraversal(Node node, FileWriter fileWriter) throws IOException {
        if (node != null) {
            inorderTraversal(node.left, fileWriter);
            if (fileWriter != null) {
                fileWriter.write(node.key + " " + ((node.left != null) ? node.left.key : 0) + " " + ((node.right != null) ? node.right.key : 0) + "\n");
            }
            inorderTraversal(node.right, fileWriter);
        }
        return node;
    }

    Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right))
                z = rotateRight(z);
            else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}