public class BST {

    // This Node class will be used to store elements
    public class Node {
        String data;
        Node left;
        Node right;

        Node(String element) {
            this.data = element;
            this.left = null;
            this.right = null;
        }

    }

    // Initializing the root node of the BST
    Node root;

    // Constructor for the BST where the tree is empty 
    BST() {
        root = null;
    }

    public void insert(String string) {

        Node newNode = new Node(string);

        if (this.root == null) {
            this.root = newNode;
        } else {
            // Temporary node for storing the reference of the parent of the position of the new node
            Node temp = this.root;
            Node refPositionOfNewParentNode = null;
            while (temp != null) {
                refPositionOfNewParentNode = temp;
                if (newNode.data.compareTo(temp.data) < 0) {
                    temp = temp.left;
                } else if (newNode.data.compareTo(temp.data) > 0) {
                    temp = temp.right;
                } else {
                    return;
                }
            }
            // Check if the new node is greater or less than the parent node and insert newNode 

            if (newNode.data.compareTo(refPositionOfNewParentNode.data) < 0) {
                refPositionOfNewParentNode.left = newNode;
            } else {
                refPositionOfNewParentNode.right = newNode;
            }
        }

    }
    // Printing elements in In-order-traversal (left, root, right) using Recursiosn

    public void inorder(Node root) {
        
        if (root != null) {
            inorder(root.left);
            System.out.println(root.data);
            inorder(root.right);
        }
    }

}
