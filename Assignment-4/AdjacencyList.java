import java.util.ArrayList;

public class AdjacencyList {
    public static void main(String[] args) {

        // AdjacencyList AdjacencyListGraph = new AdjacencyList();
        // AdjacencyListGraph.arrayList.add(new LinkedList(0));
        // AdjacencyListGraph.arrayList.add(new LinkedList(1));
        // AdjacencyListGraph.arrayList.add(new LinkedList(2));

        // AdjacencyListGraph.arrayList.get(0).input(1);
        // AdjacencyListGraph.arrayList.get(0).input(2);
        // AdjacencyListGraph.arrayList.get(0).input(3);
        // AdjacencyListGraph.arrayList.get(0).input(4);
        // for (int i = 0; i < AdjacencyListGraph.arrayList.size(); i++) {
        //     AdjacencyListGraph.arrayList.get(i).print();
        // }
    }

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
