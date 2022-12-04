import java.util.ArrayList;

public class LinkedObjects {
    public static void main(String[] args) {
        
    }

    // Object Attributes => Each Vertex is represented as an object
    int id;
    Boolean processed;
    ArrayList<LinkedObjects> neighbors;
    

    // Initialize the class/Vertex object
    LinkedObjects(int id) {
        this.id = id;
        this.processed = false;
        this.neighbors = new ArrayList<>();
    }

}
