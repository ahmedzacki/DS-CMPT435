import java.io.File; // importing file utility package to manage our file
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // importing the ArrayList class to store elements


public class Main {

    public static void main(String[] args) {
        // Creating an ArrayList object to store lines of strings
        ArrayList<String> magicItems = new ArrayList<String>();

        // Creating another ArrayList to store the magic items we are searcing 
        ArrayList<String> magicItemsFind = new ArrayList<String>();

        try {
            // creating a new file object
            File f = new File("magicitems.txt");

            // Creating another file for accessing the second magic items file
            File f2 = new File("magicitems-find-in-bst.txt");

            // creating a Scanner Object
            Scanner myReader = new Scanner(f);

            // Creating another Scanner object for the magicItemsFind file 
            Scanner myReader2 = new Scanner(f2);

            // reading each line and removing the spaces and making it all lowercase
            while (myReader.hasNextLine()) {
                String linee = myReader.nextLine();
                magicItems.add(linee.replaceAll("[^A-Za-z]", "").toLowerCase());
            }

            // Reading each line of the second magic items file and filtering it, then will store in the array List
            while (myReader2.hasNextLine()) {
                String line2 = myReader2.nextLine();
                magicItemsFind.add(line2.replaceAll("[^A-Za-z]", "").toLowerCase());
            }
            // closing scanner
            myReader.close();
            // Closing the second scanner
            myReader2.close();
            // Now catching to see if any errors exist in processing this program file
        } catch (Exception e) {
            e.getStackTrace();
        }

        // Converting the String ArrayList to String Array
        String[] magicItemsArray = new String[magicItems.size()];
        for (int i = 0; i < magicItems.size(); i++) {
            magicItemsArray[i] = magicItems.get(i);
        }

        // Converting the magicItemsFind ArrayList to String Array
        String[] magicItemsFindArray = new String[magicItemsFind.size()];
        for (int i = 0; i < magicItemsFind.size(); i++) {
            magicItemsFindArray[i] = magicItemsFind.get(i);
        }

        // Creating an object of BST class 
        BST binarySearchTree = new BST();

        System.out.println("Populating the BST with elements and printing their path");
        // Populating the BST with the magic items 
        for (String eachstring : magicItemsArray) {
            binarySearchTree.insert(eachstring);
        }

        System.out.println("Printing the elements in the tree in In-Order-Traversal");

        // Printing magic items in In-Order-Traversals
        binarySearchTree.inorder(binarySearchTree.root);

        System.out.println("Printing the path of each of the searched element in the BST");
        // Searching all the strings in the magicItemsFindArray one by one
        for (String eachString : magicItemsFindArray) {
            binarySearchTree.search(eachString);
        }

        // Calculating the Average comparison count for the searched elements 
        System.out.println("Average Comparison Count: " + binarySearchTree.avgSearchComparison(binarySearchTree.totalComparisonCount));

    }
}
