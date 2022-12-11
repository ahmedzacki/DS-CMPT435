import java.util.*;
import java.io.File; // importing file utility package to manage our file
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // importing the ArrayList class to store elements

// This class uses an external module "BST"
public class Main {

    public static void main(String[] args) {

        try {
            File f = new File("graphs2.txt");

            Scanner myReader = new Scanner(f);

            // Start reading the file line by line
            while (myReader.hasNextLine()) {
                //store the data of the line 
                String data = myReader.nextLine();

                // Check if the line starts with "new graph" to create a new matrix and adjacencylist for that specific graph
                if (data.startsWith("new graph")) {
                    System.out.println("new graph");
                    System.out.println();

                    // Creating an ArrayList to store the verticies of the linked objects graph representation
                    ArrayList<LinkedObjects> linkObjGraph = new ArrayList<>();

                    // Iterate over the nex few lines to count the vertices 
                    String tempString = myReader.nextLine();

                    //count verticies variable 
                    int count = 0;

                    // Create verticies
                    while (myReader.hasNextLine() & tempString.startsWith("add vertex")) {
                        linkObjGraph.add(new LinkedObjects(getVertex(tempString)));
                        tempString = myReader.nextLine();
                        count = count + 1;
                    }

                    // Creating an ArrayList of Arraylist to store the weights of the edges of the neighboring vertices of each vertex
                    // Each index of the Arraylist will crospond to its respective vertex
                    ArrayList<ArrayList<Integer>> weights = new ArrayList<ArrayList<Integer>>();

                    initializeWeightsArrlist(count, weights);

                    // iterate over the edges and add edges to the  linked Objects
                    while (myReader.hasNextLine() & tempString.startsWith("add edge")) {
                        // this toFilterString will return an array of three number 
                        int[] edgeVerticies = toFilterString(tempString);
                        //Add edge between the two verticies and their weight
                        addEdge(edgeVerticies, linkObjGraph, weights);
                        tempString = myReader.nextLine();
                        //Edge Case => making sure the last line of the file gets exuted 
                        if (!myReader.hasNextLine() & tempString.startsWith("add edge")) {
                            int[] lastLineofFile = toFilterString(tempString);
                            addEdge(lastLineofFile, linkObjGraph, weights);
                        }
                    }
                    
                    bellmanFord(linkObjGraph, weights, linkObjGraph.get(0), count);

                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    // This function creates edges between two vertices and their weighted value
    public static void addEdge(int[] edgeVerticies, ArrayList<LinkedObjects> linkObjGraph,
            ArrayList<ArrayList<Integer>> weights) {
        // edgeverticies & weight = [v,u,w]
        //store the neighbour object into a temprorary object variable 
        LinkedObjects temObj = linkObjGraph.get(edgeVerticies[1] - 1);
        //Add vertex object 2 to vertex object 1 (edgeVerticies[0] - 1 => because Arraylist start storing at index 0 when graph starts at vertex 1)
        linkObjGraph.get(edgeVerticies[0] - 1).neighbors.add(temObj);
        // get the index of the array list crossponding to the vertex and then add weight
        //edgeVerticies[2] = weight
        weights.get(edgeVerticies[0] - 1).add(edgeVerticies[2]);

    }
    
    // This function will filter a string and return the two vertices where an edge will be created
    public static int[] toFilterString(String string) {
        //split the string at spaces and get the vertices from the string
        int[] edge = new int[3];
        String[] splitLine = string.split(" ");
        edge[0] = Integer.parseInt(splitLine[2]);
        edge[1] = Integer.parseInt(splitLine[4]);
        edge[2] = Integer.parseInt(splitLine[splitLine.length -1]);
        return edge;
    }

    // This funtion return the vertex number as an integer
    public static int getVertex(String tempString) {
        //Filter String 
        String[] stringParts = tempString.split(" ");
        int vertex = Integer.parseInt(stringParts[stringParts.length - 1]);

        return vertex;
    }

    // This function initializes the arraylist of arrays which will store the weights
    public static void initializeWeightsArrlist(int count, ArrayList<ArrayList<Integer>> weights) {
        int i = 0;
        while (i < count) {
            weights.add(new ArrayList<Integer>());
            i++;
        }
    }
    
    // Bellman Ford Algorthim (SSSP) implimentation
    public static void bellmanFord(ArrayList<LinkedObjects> linkObjGraph, ArrayList<ArrayList<Integer>> weights,
            LinkedObjects source, int count) {
                // Initialize the distance values of the verticies 
                initSingleSource(linkObjGraph, source);
                // iterating through all the verticies and relaxing each vertex in (n-1) times where n is the number of vertices 
                int c = 0; //count = number of verticeis 
                while (c < count - 1) {
                    int n = linkObjGraph.size();
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < linkObjGraph.get(i).neighbors.size(); j++) {
                            //Relax (u,v,w)
                            relax(linkObjGraph.get(i), linkObjGraph.get(i).neighbors.get(j), weights);
                        }
                    }
                    c++;
                }          
                // Check if there are any negative cycles in the graph 
                for (int i = 0; i < linkObjGraph.size(); i++) {
                    for (int j = 0; j < linkObjGraph.get(i).neighbors.size(); j++) {
                        LinkedObjects u = linkObjGraph.get(i);
                        LinkedObjects v = u.neighbors.get(j);
                        if (v.distance > u.distance + weightOf(weights, u, v)) {
                            System.out.println("Negative cycle detected");;
                            break;
                        }
                    }
                }
                // Printing the shortest path of each vertex from the source vertex 
                for (int i = 1; i < linkObjGraph.size(); i++) {
                    ArrayList<Integer> path = new ArrayList<Integer>();
                    path = getPath(linkObjGraph, source, linkObjGraph.get(i), path);
                    System.out.println(source.id + " --> " + linkObjGraph.get(i).id + " cost is "
                            + linkObjGraph.get(i).distance + "; path: "+path);
                }

    }
    
    // This function initializes the distance value of each of the verticies
    public static void initSingleSource(ArrayList<LinkedObjects> linkObjGraph, LinkedObjects source) {
        // initialize the source vertex distance to zero and the rest is infinity
        for (LinkedObjects s : linkObjGraph) {
            // all vertex.distance will be equal to infinity except 1
            double infinity = Double.POSITIVE_INFINITY;
            s.distance = (int) infinity;
        }
        source.distance = 0;
    }

    // this Function will relax on all the verticies in (n-1) times where n is the number of verticies 
    public static void relax(LinkedObjects u, LinkedObjects v, ArrayList<ArrayList<Integer>> weights) {
        // Start the relaxaztion 
        if (v.distance > u.distance + weightOf(weights, u, v)) {
            v.distance = u.distance + weightOf(weights, u, v);
            v.predecessor = u;
            v.predecessor = u;   // setting predecessor vertex to u 
        } 
    }
    
    // This function will return the weight or two verticies example w(u,v)
    public static int weightOf(ArrayList<ArrayList<Integer>> weights, LinkedObjects u, LinkedObjects v) {
        int k = 0;
        for (int i = 0; i < u.neighbors.size(); i++) {
            k = i;
            if (u.neighbors.get(i) == v) {
                break;
            }
        }
        return weights.get(u.id - 1).get(k);
    }
    
    // This function find take the source vertex and another vertex and will desplay the path 
    public static ArrayList<Integer> getPath(ArrayList<LinkedObjects> linkedObjects, LinkedObjects source,
            LinkedObjects destination, ArrayList<Integer> path) {

        while (destination.id != source.id) {
            path.add(destination.id);
            destination = destination.predecessor;
        }
        path.add(source.id);
        Collections.reverse(path);
        return path;
    }
}