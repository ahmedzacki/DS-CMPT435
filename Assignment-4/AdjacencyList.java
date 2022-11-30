import java.util.ArrayList;

public class AdjacencyList {
    // This class will have an ArrayList of LinkedLists of Nodes
    ArrayList<LinkedList> arrayList;
    AdjacencyList() {
        // Create an ArrayList of LinkedList of Nodes
        arrayList = new ArrayList<>();
    }
    // This method will print everything in the Adjacency List
    public void displayAdjacencyList() {
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).print();
        }
        System.out.println();
    }
}
