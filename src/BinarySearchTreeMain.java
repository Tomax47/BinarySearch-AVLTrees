public class BinarySearchTreeMain {

    public static void main(String[] args) {

        Node f = new Node(1, null, null);
        Node g = new Node(4, null, null);
        Node h = new Node(7, null, null);
        Node i = new Node(13, null, null);
        Node d = new Node(14, i, null);
        Node e = new Node(6, g, h);
        Node c = new Node(10, null, d);
        Node b = new Node(3, f, e);
        Node a = new Node(8, b, c);

        BinarySearchTree tree = new BinarySearchTree(a);

        System.out.println("Number of nodes in subtree: " + tree.getNumberOfNodes(b));
        System.out.println("Total number of nodes in the tree: " + tree.numberOfNodes());
        System.out.println();
        System.out.println("Height: " + tree.getHeight(a));
        System.out.println("Search by key: " + tree.searchByKey(a, 7));
        System.out.println();
        System.out.println("This is a binary search tree: " + tree.isBST(a, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println();
        System.out.println("Min value: " + tree.getMin(a));
        System.out.println("Max value: " + tree.getMax(a));

        System.out.println();
        BinarySearchTree secondTree = new BinarySearchTree();
        secondTree.readFromFile("D:\\Java Projects\\BTS-AVL8\\src\\AVLMainInput.txt");
        System.out.println("Total number of nodes in the tree: " + secondTree.numberOfNodes());

        System.out.println("Height: " + secondTree.getHeight(secondTree.getRoot()));
    }
}
