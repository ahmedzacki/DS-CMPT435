import java.io.File; // importing file utility package to manage our file
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // importing the ArrayList class to store elements

// This class uses an external module "BST"
public class Main {

    //This method will convert ArrayList String to Array String
    public String[] toArrayOfString(ArrayList<String> arrayListofStrings) {
        String[] magicItemsArray = new String[arrayListofStrings.size()];
        for (int i = 0; i < arrayListofStrings.size(); i++) {
            magicItemsArray[i] = arrayListofStrings.get(i);
        }
        return magicItemsArray;
    }
    // This function gets called from the main method and it inserts the data in the BST 
    public void populateTree(String[] magicItems, BST binarySearchTree) {
        System.out.println("-------------------------------------------");
        System.out.println("Populating the BST with elements and printing their path");
        // Populating the BST with the magic items 
        for (String eachstring : magicItems) {
            binarySearchTree.insert(eachstring);
        }
    }
    // This function gets called from the main method and it searches gives strings from the BST
    public void searchBST(String[] selectedMagicItems, BST binarySearchTree) {
        System.out.println("-------------------------------------------");
        System.out.println("Printing the path of each of the searched element in the BST");
        // Searching all the strings in the magicItemsFindArray one by one
        for (String eachString : selectedMagicItems) {
            binarySearchTree.search(eachString);
        }
    }
    // This function prints out the average comparison count from the BST
    public void AvgComparisonCount(BST binarySearchTree) {
        System.out.println("Average Comparison Count: "
                + binarySearchTree.avgSearchComparison(binarySearchTree.totalComparisonCount));
    }
    // This function takes a file and an ArrayList, it filters out the file and append it to the ArrayList line by line
    public void filterFile(ArrayList<String> magicItems, File file) {
        try {
            //Create scanner object to read the file
            Scanner myreader = new Scanner(file);
            //filter out each line using the regression expression
            while (myreader.hasNextLine()) {
                String linee = myreader.nextLine();
                magicItems.add(linee.replaceAll("[^A-Za-z]", "").toLowerCase());
            }
            myreader.close();
        //Catch if there are any errors while processing the file
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        //Crearing an object of the Main class for accessing the functions above
        Main mainClass = new Main();
        // Creating an object of BST class from module BST.java in another file
        BST binarySearchTree = new BST();
        // Creating an ArrayList of String object to store lines of strings
        ArrayList<String> magicItems = new ArrayList<String>();

        // Creating another ArrayList of String Object to store the magic items we are searcing 
        ArrayList<String> magicItemsFind = new ArrayList<String>();

        // creating a new file object
        File f = new File("magicitems.txt");

        // Creating another file for accessing the second magic items file
        File f2 = new File("magicitems-find-in-bst.txt");
        // Call the filtering function to filter the magic items and append it to the arrayList
        mainClass.filterFile(magicItems, f);
        // Call the filtering function to filter the "magicitems-find-in-bst.txt" and append it to the arrayList
        mainClass.filterFile(magicItemsFind, f2);

        // Converting the String ArrayList of magicitems to String Array
        String[] magicItemsArray = mainClass.toArrayOfString(magicItems);
        // Converting the magicItemsFind ArrayList to String Array
        String[] magicItemsFindArray = mainClass.toArrayOfString(magicItemsFind);
        //Inserting magicitems in the BST
        mainClass.populateTree(magicItemsArray, binarySearchTree);
        System.out.println("-------------------------------------------");
        System.out.println("Printing the elements in the tree in In-Order-Traversal");
        // Printing magic items in In-Order-Traversals from the BST
        binarySearchTree.inorder(binarySearchTree.root);
        // Searching the selected magic items from the BST and printing their path
        mainClass.searchBST(magicItemsFindArray, binarySearchTree);
        System.out.println("-------------------------------------------");
        // Calculating the Average comparison count for the searched elements 
        mainClass.AvgComparisonCount(binarySearchTree);
    }
}
