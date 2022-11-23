import java.util.ArrayList; // importing the ArrayList class to store elements

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

        // Creating an CharacterArrayList to store the path of each node
        ArrayList<Character> pathArray = new ArrayList<Character>();

        Node newNode = new Node(string);

        if (this.root == null) {
            this.root = newNode;
        } else {

            // Temporary node for storing the root of the tree
            Node temp = this.root;
            // Keeping truck of the parent node of the position where the new node will be inserted
            Node refPositionOfNewParentNode = null;

            // Find the position of the new Node 
            while (temp != null) {
                refPositionOfNewParentNode = temp;
                if (newNode.data.compareTo(temp.data) < 0) {
                    temp = temp.left;
                    pathArray.add('L');

                } else if (newNode.data.compareTo(temp.data) > 0) {
                    temp = temp.right;
                    pathArray.add('R');
                } else {
                    return;
                }
            }
            // Printing out the path of the Node in a CharacterArrayList
            System.out.println(pathArray);

            // Check if the new node is greater or less than it's parent and insert newNode in it's correct positioin
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

    // Storing the number of comparison of each look up in this arrayList 
    ArrayList<Integer> totalComparisonCount = new ArrayList<Integer>();

    // This function returns the average comparisons of each look up 
    public Double avgSearchComparison(ArrayList<Integer> comparisonCounting) {

        int sum = 0;
        for (int i = 0; i < comparisonCounting.size(); i++) {
            sum += comparisonCounting.get(i);
        }
        double avg = sum / comparisonCounting.size();

        return avg;
    }

    // Searching elements in the BST and retrun their path in a characterArrayListx
    public void search(String element) {

        int comparisonsCount = 0;

        // Creating an CharacterArrayList to store the path of each node
        ArrayList<Character> pathArray2 = new ArrayList<Character>();

        Node treeRoot = this.root;

        while (treeRoot != null) {
            if (element.compareTo(treeRoot.data) < 0) {
                treeRoot = treeRoot.left;
                pathArray2.add('L');
                comparisonsCount++;
            } else if (element.compareTo(treeRoot.data) > 0) {
                treeRoot = treeRoot.right;
                pathArray2.add('R');
                comparisonsCount++;
            } else {
                //Printing the look up path
                System.out.println("Look-up-Path: " + pathArray2);
                // Printing the number of comparison for each look up 
                System.out.println("Number of comparisons: " + comparisonsCount);
                break;
            }
        }
        // Check if the element is not in the tree 
        if (treeRoot == null) {
            System.out.println(element + " : is not in the tree");
        }

        //Adding the comparison count to the totalComparisonCount arrayList to calculate the average
        totalComparisonCount.add(comparisonsCount);
    }
}