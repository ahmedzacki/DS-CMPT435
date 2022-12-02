import java.io.File; // importing file utility package to manage our file
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // importing the ArrayList class to store elements

// This class uses an external module "BST"
public class Main {

    public static void main(String[] args) {

        try {
            File myObj = new File("graphs1.txt");

            Scanner myReader = new Scanner(myObj);

            // Start reading the file line by line
            while (myReader.hasNextLine()) {
                //Safing the data of the line 
                String data = myReader.nextLine();

                // Display information about the graph
                displayGraphInfo(data);

                // Check if the line starts with "new graph" to create a new matrix for that specific graph
                if (data.startsWith("new graph")) {

                    // Create an Adjancecy List 
                    AdjacencyList adjacencyList = new AdjacencyList();

                    // Iterate over the nex few lines to count the vertices 
                    String tempString = myReader.nextLine();

                    //Check if the graph starts at vertax zero which means it is at the ground level 
                    Boolean vertexStartsZero = isGroundLevel(tempString);

                    // Start counting the vertices to to create the Matrix later
                    int countVertices = 0;

                    //this wile loop will jump from one line to another to count the verticies and add verticies to adjacency list
                    while (myReader.hasNextLine() & tempString.startsWith("add vertex")) {
                        adjacencyList.addVertax(tempString);
                        countVertices++;
                        tempString = myReader.nextLine();
                    }

                    // Create the matrix with the number of vertices (countVertices X countVertices)
                    if (!vertexStartsZero) {
                        //New Matrix Object 
                        Matrix matrixGraph = new Matrix(countVertices);
                        //Create Matrix
                        matrixGraph.createMatrix();

                        // create edges for both graphs
                        while (myReader.hasNextLine() & tempString.startsWith("add edge")) {
                            // iterate over the edges and add edges to the matrix and the adjacencyList and then display
                            matrixGraph.addEdge(toFilterString(tempString));
                            adjacencyList.addEdge(toFilterString(tempString), vertexStartsZero);
                            tempString = myReader.nextLine();
                        }

                        //Printing the graph in both forms (Matrix & AdjacencyList) 
                        matrixGraph.display();
                        adjacencyList.display();

                    } else {

                        // Initialize matrix object
                        Matrix matrixGraph = new Matrix(countVertices);
                        // Create actual matrix that starts at zero
                        matrixGraph.createGroundLevelMatrix();
                        //Create edges for both graphs
                        while (myReader.hasNextLine() & tempString.startsWith("add edge")) {
                            // iterate over the edges and add edges to the matrix and the adjacencyList and then display
                            matrixGraph.addEdgeGroundLevel(toFilterString(tempString));
                            adjacencyList.addEdge(toFilterString(tempString), vertexStartsZero);
                            tempString = myReader.nextLine();
                        }
                        // Making sure the scanner reads the last line of the file
                        if (myReader.hasNextLine() == false & tempString.startsWith("add edge")) {
                            matrixGraph.addEdgeGroundLevel(toFilterString(tempString));
                            adjacencyList.addEdge(toFilterString(tempString), vertexStartsZero);
                        }

                        //Printing the graph in both forms (Matrix & AdjacencyList) 
                        matrixGraph.display();
                        adjacencyList.display();
                    }
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

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
        filterFile(magicItems, f);
        // Call the filtering function to filter the "magicitems-find-in-bst.txt" and append it to the arrayList
        filterFile(magicItemsFind, f2);

        // Converting the String ArrayList of magicitems to String Array
        String[] magicItemsArray = toArrayOfString(magicItems);
        // Converting the magicItemsFind ArrayList to String Array
        String[] magicItemsFindArray = toArrayOfString(magicItemsFind);
        //Inserting magicitems in the BST
        populateTree(magicItemsArray, binarySearchTree);
        System.out.println("-------------------------------------------");
        System.out.println("Printing the elements in the tree in In-Order-Traversal");
        // Printing magic items in In-Order-Traversals from the BST
        binarySearchTree.inorder(binarySearchTree.root);
        // Searching the selected magic items from the BST and printing their path
        searchBST(magicItemsFindArray, binarySearchTree);
        System.out.println("-------------------------------------------");
        // Calculating the Average comparison count for the searched elements 
        AvgComparisonCount(binarySearchTree);
    }
    
    //This method will convert ArrayList String to Array String
    public static String[] toArrayOfString(ArrayList<String> arrayListofStrings) {
        String[] magicItemsArray = new String[arrayListofStrings.size()];
        for (int i = 0; i < arrayListofStrings.size(); i++) {
            magicItemsArray[i] = arrayListofStrings.get(i);
        }
        return magicItemsArray;
    }
    // This function gets called from the main method and it inserts the data in the BST 
    public static void populateTree(String[] magicItems, BST binarySearchTree) {
        System.out.println("-------------------------------------------");
        System.out.println("Populating the BST with elements and printing their path");
        // Populating the BST with the magic items 
        for (String eachstring : magicItems) {
            binarySearchTree.insert(eachstring);
        }
    }
    // This function gets called from the main method and it searches gives strings from the BST
    public static void searchBST(String[] selectedMagicItems, BST binarySearchTree) {
        System.out.println("-------------------------------------------");
        System.out.println("Printing the path of each of the searched element in the BST");
        // Searching all the strings in the magicItemsFindArray one by one
        for (String eachString : selectedMagicItems) {
            binarySearchTree.search(eachString);
        }
    }
    // This function prints out the average comparison count from the BST
    public static void AvgComparisonCount(BST binarySearchTree) {
        System.out.println("Average Comparison Count: "
                + binarySearchTree.avgSearchComparison(binarySearchTree.totalComparisonCount));
    }
    // This function takes a file and an ArrayList, it filters out the file and append it to the ArrayList line by line
    public static void filterFile(ArrayList<String> magicItems, File file) {
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
    
    // This function will check if a graph has a ground level zero vertex
    public static Boolean isGroundLevel(String tempString) {
        //split string at spaces
        String[] stringParts = tempString.split(" ");

        //Converting Char to Interger
        int charConvertion = Integer.parseInt(stringParts[stringParts.length - 1]);
        if (charConvertion == 0) {
            return true;
        } else {
            return false;
        }
    }

    // This function will filter a string and return the two vertices where an edge will be created
    public static int[] toFilterString(String string) {
        //split the string at spaces and get the vertices from the string
        int[] edge = new int[2];
        String[] splitLine = string.split(" ");
        edge[0] = Integer.parseInt(splitLine[2]);
        edge[1] = Integer.parseInt(splitLine[4]);
        return edge;

    }

    // This graph dispalys information about the graph
    public static void displayGraphInfo(String data) {
        if (data.startsWith("--")) {
            System.out.println(data);
            System.out.println();
        }
    }

}

