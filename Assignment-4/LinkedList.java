// import java.util.ArrayList;

public class LinkedList {
    public static void main(String[] args) {
        // LinkedList mylink = new LinkedList(4);
        // LinkedList mylink2 = new LinkedList(0);
        // mylink2.input(22);
        // mylink2.input(28);
        // mylink2.input(26);


        // mylink.input(5);
        // mylink.input(6);
        // mylink.input(8);
        // mylink.input(9);
        // mylink.input(10);

        // ArrayList<LinkedList> arrlist = new ArrayList<>();

        // arrlist.add(mylink);
        // arrlist.add(mylink2);

        // for (int i = 0; i < arrlist.size(); i++) {
        //     arrlist.get(i).print();
        // }


    }
    // This Node class will be used to store elements in the linked list
    public class Node {
        int data;
        Node next;

        Node(int element) {
            this.data = element;
            this.next = null;
        }
    }
    // Initialize the root of the linkedlist
    Node head;
    Node tail;
    int size = 1;

    // Constructor for the Linked List

    LinkedList(int vertax) {
        this.head = new Node(vertax);
        this.tail = null;
    }

    /**
     * this Function returns the length of the linked List
     */
    public int len(){
        return this.size;
    } 

    /**
     * This function checks if the linked list is empty
     */
    public boolean isEmpty() {
        if (len() > 1) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Now we are creating a Funtion to put elements into the hashtable using linkedlist object
     */
    public void inputEdge(int vertax) {
        Node newNode = new Node(vertax);

        if (isEmpty()) {
            this.head.next = newNode;
        } else {
            this.tail.next = newNode;
        }
        this.tail = newNode;
        this.size++;
    }
    
// Printing elements in the adjacencyList 
    public void print(){
        Node currentNode = this.head;
        while (currentNode != null) {
            System.out.print(currentNode.data + " --> ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }
    
}