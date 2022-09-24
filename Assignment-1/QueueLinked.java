public class QueueLinked {
/**
 * Queue DS uses FIFO technique
 * Which means in Queue DS, the elements are inserted at the back of the queue 
 * and removed from the head or front of the que
 * Queue also supports following functions for implementation
 * Functions : Enqueue, Dequeue, to insert and remove elements from the queue DS
 * We also use another function to only return the head of the queue without deleting and 
 * call that function First  
 */

    // Using a node class to create the linked list of th queue

    static class Node {
        
        int data;
        Node next;
    
        Node(int val){
    
            this.data = val;
            this.next = null;
        }
    }

    // Instance Variables of the Queue DS

    Node front; // reference to the first end of the queue 
    Node back; // reference to the back end of the queue 
    int size = 0; // size of the queue 

    // Let's returnt he length of the queue 
    public int len(){
        return this.size;
    } 

    // Let's now check if the Queue is empty 
    public boolean isEmpty(){
        if (len()==0){
            return true;
        } else {
            return false; 
        }   
    }

    // Let's now create the function to insert the elements into the back of the queue

    public void enqueue(int key){

        Node newNode = new Node(key);

        if (isEmpty()){
            this.front = newNode;
        } else {
            this.back.next = newNode;
        }
        this.back = newNode;
        this.size++;

    }

    // Now, we going to retrive elements from the front fo the queue

    public int dequeue(){

        if (isEmpty()){
            System.out.println("Queue is Empty");
            System.exit(-1);
        }
        Node temp = this.front; 
        this.front = this.front.next;
        if (isEmpty()){
            this.back = null;
            System.out.println("Stack is Empty");
        }
        return temp.data;
    }

    // Let's now display the elements in the Queue 

    public void display(){
        Node current = this.front;
        if(isEmpty()){
            System.out.print("the Queue is Empty");
        }
        while (current!=null){
            System.out.print(current.data + "<--");
            current = current.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        
        QueueLinked Q = new QueueLinked();

        Q.enqueue(20);
        Q.display();
        Q.dequeue();
        Q.display();
        Q.dequeue();
    }
    
}
