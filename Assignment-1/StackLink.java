//This code will implement the stack data structure 

public class StackLink {



    static class Node {
        
        int data;
        Node next;
    
        Node(int val){
    
            this.data = val;
            this.next = null;
        }
    }

// Lets define the instance variable for our stack 

    Node head; //top or first element of the Stack 
    int size = 0; //keeping truck the size of the stack 

/**
 * this Function returns the length of the Stack
 */
    public int len(){
        return this.size;
    }  

// Lets Reuse our isEmpty method to check if the Stack is empty or not 
    public boolean isEmpty(){
        if (len()==0){
            return true;
        } else {
            return false; 
        }   
    }

// Let's create the Push method to insert elements into the Stack at the head 
// Using Link representation

    public void push(int key){
        Node newNode = new Node(key);

        if (isEmpty()){
            this.head = newNode;
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
        this.size++;
    }

 /** Let's now create the Pop() method to pop the first element of the Stack and remove from the stack 
  * This is also the last element that was pushed. 
 */   

    public int pop(){

        Node temp = this.head;

        if (isEmpty()){
            System.out.println("Stack is Empty");
            System.exit(-1); // Exit the funtion if the Stack is Empty
        }
        this.head = this.head.next;
        this.size--;

        return temp.data;
    }


/** Now let's create the Top() method which will return 
 * the value on the top of the stack but 
 * this will not delete the value from the stack
 */   
    public int top(){

        if (isEmpty()){
            System.out.println("Stack is Empty");
            System.exit(-1); // Here, I'm exiting this function 
                            // because it throws a "NullPointerException" when the stack is empty
                            // and the head is null
        } 
        return this.head.data;
    }

// Let's now Display the elements in our stack 
// To see if everything is working by reusing our Display method 

    public void display(){
        Node current = this.head;
        if (isEmpty()){
            System.out.println("Stack is Empty");
        }
        while (current!=null){
            System.out.print(current.data + "-->");
            current = current.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {

        StackLink S = new StackLink();

        S.push(40);
        S.push(60);
        S.push(10);
        S.push(30);
        S.display();
        System.out.println(S.pop());
        S.display();
        System.out.println(S.pop());
        S.display();
        System.out.println(S.top());
        S.display();
        
    }
}
