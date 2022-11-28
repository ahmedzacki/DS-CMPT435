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
                        //Print out the matrix 
                        mygraph.displayMatrix();
                        System.out.println();
                        //Print out the adjacency list
                        adjacencyListGraph.displayAdjacencyList();
                        System.out.println("------------------------------");

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
                        //Print out the matrix 
                        mygraph.displayMatrix();
                        System.out.println();
                        //Print out the adjacency list
                        adjacencyListGraph.displayAdjacencyList();
                        System.out.println("------------------------------");
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

    
//Matrix class implimentaiton
    public static class Matrix {
        int vertices;
        int matrix[][];

        Matrix(int numberOfVertices) {
            this.vertices = numberOfVertices;
            // add number 1 to the numberOfVertices to make room for the matrix representation nicely 
            this.matrix = new int[numberOfVertices + 1][numberOfVertices + 1];
        }
        /*
         * [0,0,1,2,3]
         * [0,0,0,0,0]
         * [1,0,0,0,0]
         * [2,0,0,0,0]
         * [3,0,0,0,0]
         */
        // This function create a Matrix for a graph that starts at vertex zero
        public void createGroundLevelMatrix() {
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                int k = 2;
                for (int j = 0; j < n; j++) {
                    if (i < k & j < k) {
                        matrix[i][j] = 0;
                    } else if (i == 0 & j >= k) {
                        matrix[i][j] = j - 1;
                    } else if (j == 0 & i >= k) {
                        matrix[i][j] = i - 1;
                    } else {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
        // This function create a matrix for a graph that starts at vertex 1
        public void createMatrix() {
            // Length of the Matrix
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Check if the first row is zero to print the values of the vertices
                    if (i == 0) {
                        matrix[i][j] = j;
                        // check if the first column is zero to print the values of the vertices
                    } else if (j == 0) {
                        matrix[i][j] = i;
                        // else make the relationship (edge) place holder a zero
                    } else {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        //Display the matrix 
        public void displayMatrix() {
            // Length of the Matrix
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                System.out.print("[ ");
                for (int j = 0; j < n; j++) {
                    System.out.print(+this.matrix[i][j] + " ");
                }
                System.out.print("]");
                System.out.println("");
            }
        }
        
        // this function will create an edge between two vertices 
        public void addEdge(int vertexA, int vertexB) {
            // Find the location of both vertices and set their relationship to 1
            matrix[vertexA][vertexB] = 1;
        }

        // This function will add an edge to the undirected ground level graph since it starts at vertex 0
        public void addEdgeGroundLevel(int vertexA, int vertexB) {
            // Find the location of both vertices and set their relationship to 1
            matrix[vertexA + 1][vertexB + 1] = 1;
        }

    }
    
}
