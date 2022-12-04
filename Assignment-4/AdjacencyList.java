import java.util.ArrayList;

public class AdjacencyList {
    // This class will have an ArrayList of LinkedLists of Nodes
    ArrayList<LinkedList> arrayList;
    AdjacencyList() {
        // Create an ArrayList of LinkedList of Nodes
        arrayList = new ArrayList<>();
    }

    // This function will add a vertex to the adjacencylist graph represeantion
    public void addVertax(String tempString) {
        //Add a vertex to the AdjacencyList
        String[] splitString = tempString.split(" ");
        int vertex = Integer.parseInt(splitString[splitString.length - 1]);
        arrayList.add(new LinkedList(vertex));
    }
    
    public void addEdge(int[] vertexArray, Boolean vertexStartsZero) {
        //Add edge to the adjacencyList
        if (!vertexStartsZero) {
            arrayList.get(vertexArray[0] - 1).inputEdge(vertexArray[1]);
            arrayList.get(vertexArray[1] - 1).inputEdge(vertexArray[0]);
        } else {
            arrayList.get(vertexArray[0]).inputEdge(vertexArray[1]);
            arrayList.get(vertexArray[1]).inputEdge(vertexArray[0]);
        }
    }

    // This method will print everything in the Adjacency List
    public void display() {
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).print();
        }
        System.out.println();
    }
}
