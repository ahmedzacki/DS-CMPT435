import java.io.File; // importing file utility package to manage the text files
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    //Graph Class == Adjacency List Clas

    public static void main(String[] args) {

        try {
            File myObj = new File("graphs1.txt");

            Scanner myReader = new Scanner(myObj);

            // Start reading the file line by line
            while (myReader.hasNextLine()) {
                //Safing the data of the line 
                String data = myReader.nextLine();

                // Display information about the graph
                if (data.startsWith("--")) {
                    System.out.println(data);
                    System.out.println("");

                }

                // Check if the line starts with "new graph" to create a new matrix for that specific graph
                if (data.startsWith("new graph")) {

                    // Iterate over the nex few lines to count the vertices 
                    String tempString = myReader.nextLine();
                    //Check if the graph starts at vertax zero
                    String[] stringParts = tempString.split(" ");
                    Boolean vertexStartsZero = isGroundLevel(stringParts[stringParts.length - 1]);

                    int countVertices = 0;
                    while (myReader.hasNextLine() & tempString.startsWith("add vertex")) {
                        countVertices++;
                        tempString = myReader.nextLine();
                    }

                    // Create the matrix with total number of vertices as well as Adjacency List

                    if (!vertexStartsZero) {
                        //New Matrix Object 
                        Matrix mygraph = new Matrix(countVertices);
                        //Create Matrix
                        mygraph.createMatrix();

                        //New AdjacencyList of Class Graph Object
                        Graph adjacencyListGraph = new Graph();

                        //Create adjacencyList with the given number of vertices (#of vertices=countVertices)
                        for (int i = 1; i <= countVertices; i++) {
                            adjacencyListGraph.addVertex(new Node(i));
                        }

                        // iterate over the edges and add edges to the matrix and the adjacencyList
                        while (myReader.hasNextLine() & tempString.startsWith("add edge")) {
                            //split the string at spaces and get the vertices from the string
                            int[] edgeVertices = adjacencyListGraph.toFilterString(tempString);
                            // Add edge to the matrix
                            mygraph.addEdge(edgeVertices[0], edgeVertices[1]);
                            //Add edge to the adjacencyList
                            adjacencyListGraph.createEdge(edgeVertices[0], edgeVertices[1]);
                            //Move the scanner to the next line
                            tempString = myReader.nextLine();
                        }
                        //Printing the graph in both forms (Matrix & AdjacencyList) 
                        displayFinalGraph(mygraph, adjacencyListGraph);

                    } else {
                        countVertices++;
                        // Initialize matrix object
                        Matrix mygraph = new Matrix(countVertices);
                        // Create actual matrix that starts at zero
                        mygraph.createGroundLevelMatrix();
                        //Initialize a New AdjacencyList of Class Graph Object
                        Graph adjacencyListGraph = new Graph();
                        //Create adjacencyList with the given number of vertices (#of vertices=countVertices)
                        for (int i = 1; i <= countVertices; i++) {
                            adjacencyListGraph.addVertex(new Node(i));
                        }

                        // iterate over the edges and add edges to the matrix 
                        while (myReader.hasNextLine() & tempString.startsWith("add edge")) {
                            //Split the string at spaces and get remove the vertices from the strings
                            int[] edgeVertices = adjacencyListGraph.toFilterString(tempString);
                            //Create edge b/w two vertices in the matrix
                            mygraph.addEdgeGroundLevel(edgeVertices[0], edgeVertices[1]);
                            //Create edge b/w two vertices in the adjacency list
                            adjacencyListGraph.createEdgeGroundLevel(edgeVertices[0], edgeVertices[1]);

                            //Move the scanner to the next line
                            tempString = myReader.nextLine();
                        }
                        //Printing the graph in both forms (Matrix & AdjacencyList) 
                        displayFinalGraph(mygraph, adjacencyListGraph);
                    }
                }

            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    // Adjacency List class implimentation
    
    // Creating a Node class for the adjacency list/graph class
    public static class Node {
        int vertex;

        Node(int vertex) {
            this.vertex = vertex;
        }
    }

    // Create arrayList of linkedList of Nodes for the adjacency List
    ArrayList<LinkedList<Node>> arrayList;

    // Class constructore for the adjacency list
    Graph() {
        // Create an ArrayList of LinkedList of Nodes
        arrayList = new ArrayList<>();
    }

    // This Function adds a vertex to the adjacencylist of the linkedlist
    public void addVertex(Node node) {
        // create a linkedlist object
        LinkedList<Node> linkedlist = new LinkedList<>();
        linkedlist.add(node);
        arrayList.add(linkedlist);
    }

    // This Funtion creates an edge between two vertices of the adjacency List if the graph starts at vertex 1
    public void createEdge(int u, int v) {
        u--;
        v--;
        //get the linkedlist of vertex u
        LinkedList<Node> linkedlist = arrayList.get(u);
        // add vertex v to the linked list of vertex u as a node by find the location of Node vertex v
        Node vertexVNode = arrayList.get(v).get(0);
        // Add vertexVNode at the end of the linked list u
        linkedlist.add(vertexVNode);
    }

    // Create edge when graph has a ground level or when verticies start at zero 
    public void createEdgeGroundLevel(int u, int v) {
        //get the linkedlist of vertex u
        LinkedList<Node> linkedlist = arrayList.get(u);
        // add vertex v to the linked list of vertex u as a node by find the location of Node vertex v
        Node vertexVNode = arrayList.get(v).get(0);
        // Add vertexVNode at the end of the linked list u
        linkedlist.add(vertexVNode);
    }

    // This function Prints all the adjacency list
    public void displayAdjacencyList() {
        // Iterate over all the linked list in the ArrayList object
        for (LinkedList<Node> everyLinkedList : arrayList) {
            // Iterate over all the nodes in each of the linked list
            for (Node everyNode : everyLinkedList) {
                System.out.print(everyNode.vertex + " --> ");
            }
            System.out.println();
        }
    }

    // This function will check if a graph has a ground level zero vertex
    public static Boolean isGroundLevel(String groundLevelVertex) {
        //Converting Char to Interger
        int charConvertion = Integer.parseInt(groundLevelVertex);
        if (charConvertion == 0) {
            return true;
        } else {
            return false;
        }

    }

    // This function will filter a string and return the two vertices where an edge will be created
    public int[] toFilterString(String string) {
        //split the string at spaces and get the vertices from the string
        int[] edge = new int[2];
        String[] splitLine = string.split(" ");
        edge[0] = Integer.parseInt(splitLine[2]);
        edge[1] = Integer.parseInt(splitLine[4]);
        return edge;

    }
    
    // This function will dispaly the graph intwo formats (Matrix & Adjacency list)
    public static void displayFinalGraph(Matrix mygraph, Graph adjacencyListGraph) {
        //Print out the matrix 
        mygraph.displayMatrix();
        System.out.println();
        //Print out the adjacency list
        adjacencyListGraph.displayAdjacencyList();
        System.out.println("------------------------------");
    }
    
}
