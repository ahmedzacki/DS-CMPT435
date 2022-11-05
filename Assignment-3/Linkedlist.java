public class Linkedlist {

    //Node class for stroing single elements
    static class Node {       
        String data;
        Node next;  
        Node(String val){ 
            this.data = val;
            this.next = null;
        }
    }
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
 * Now we are creating a Funtion to put elements into the hashtable using linkedlist object
 */
    public void put(String key){
        Node newNode = new Node(key);

        if (isEmpty()){
            this.head = newNode;
        } else {
            this.tail.next= newNode;
        }
        this.tail = newNode;
        this.size++;
    }

    // Performing search by returning the number of comparison of the searched element 
    public int get(String findKey){
        Node currentNode = this.head;
        int comparisonCount = 0;
        while(currentNode!=null){
            comparisonCount++;
            if(currentNode.data == findKey){
                return comparisonCount;
            }
            currentNode = currentNode.next;
        }
        return comparisonCount;
    }
}