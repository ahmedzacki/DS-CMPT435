public class Main {
/**
 * Let's create a Node class here for the Linked list  
 */

    static class Node {
        
        int data;
        Node next;
    
        Node(int val){
    
            this.data = val;
            this.next = null;
        }
    }
/**
 * Now we are creating a linked list to store our data
 */
    static class LinkedList{

//instance variables of the linkedlist

        Node head;
        Node tail;
        int size = 0;

/**
 * this Function returns the length of the linked List
 */
    public int len(){
        return this.size;
    }   

/**
 * This function returns a boolean value 
 * and checks whether the the Linkedlist is empty or not 
 */
        public boolean isEmpty(){
            if (len()==0){
                return true;
            } else {
                return false; 
            }   
        }

/**
 * Now we are creating a Funtion to insert elements 
 * into the Linkedlist
 */
        public void insert(int key){
            Node newNode = new Node(key);

            if (isEmpty()){
                this.head = newNode;
            } else {
                this.tail.next= newNode;
            }
            this.tail = newNode;
            this.size++;
        }
/**
* Lets Create a method to display the values
* in the linked list
*/
        public void display(){
            Node current = this.head;
            while (current!=null){
                System.out.print(current.data + "-->");
                current = current.next;
            }
            System.out.println();
        }

    }  

    public static void main(String[] args) {

        LinkedList L = new LinkedList();

        L.insert(1);
        L.insert(2);
        L.insert(3);
        L.insert(4);
        L.insert(5);

        L.display();


        System.out.println("hello ");

        
    }
}
