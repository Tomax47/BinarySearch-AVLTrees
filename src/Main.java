import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        AVLTree tree = new AVLTree();
        tree.readFile("D:\\Java Projects\\BTS-AVL8\\src\\AVLMainInput.txt");

        FileWriter fileWriter = new FileWriter("D:\\Java Projects\\BTS-AVL8\\src\\AVLInput.txt");
        fileWriter.write(" " + "\n");
        tree.setRoot(tree.inorderTraversal(tree.getRoot(), fileWriter));
        fileWriter.close();
    }
}
