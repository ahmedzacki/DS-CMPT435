import java.util.ArrayList;

public class LinkedObjects {
    // Object Attributes => Each Vertex is represented as an object with attributes as class variables
    int id;
    Boolean processed;
    int distance;
    LinkedObjects predecessor;
    ArrayList<LinkedObjects> neighbors;
    
    // Initialize the class/Vertex object
    LinkedObjects(int id) {
        this.id = id;
        this.distance = 0;
        this.predecessor = null;
        this.neighbors = new ArrayList<>();
    }
}