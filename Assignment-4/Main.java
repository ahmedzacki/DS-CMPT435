import java.io.File; // importing file utility package to manage our file
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // importing the ArrayList class to store elements


public class Main {


    public static void main(String[] args) {
        // Creating an ArrayList object to store lines of strings
        ArrayList<String> strArry = new ArrayList<String>();

        try {
            // creating a new file object
            File f = new File("magicitems.txt");
            // creating a Scanner Object
            Scanner myReader = new Scanner(f);
            // reading each line and removing the spaces and making it all lowercase
            while (myReader.hasNextLine()) {
                String linee = myReader.nextLine();
                strArry.add(linee.replaceAll("[^A-Za-z]", "").toLowerCase());
            }
            // closing scanner
            myReader.close();
            // Now catching to see if any errors exist in processing this program file
        } catch (Exception e) {
            e.getStackTrace();
        }

        // Converting the String ArrayList to String Array of the same length
        String[] arr = new String[strArry.size()];
        for (int i = 0; i < strArry.size(); i++) {
            arr[i] = strArry.get(i);
        }

        // Creating an object of BST class 
        BST binarySearchTree = new BST();

        // Populating the BST with the magic items 
        for (String eachstring : arr) {
            binarySearchTree.insert(eachstring);
        }

        // Printing magic items in In-Order-Traversals
        binarySearchTree.inorder(binarySearchTree.root);

    }
}