//This code will implement the stack data structure 

public class StackLink {

    /**
     * Stack DS uses technique called LIFO (Last in First out)
* some of the properties of stack is 
* Push() : method to insert elements into the stack
*Pop() : method to remove and return the head element of the stack
*Top() : method to only return the head element
* And some additional methods of my own. 
*/

/**
* First we need to reuse our code from the Node class 
* So we can store element into the stack 
*/

    static class Node {
        
        int data;
        Node next;
    
        Node(int val){
    
            this.data = val;
            this.next = null;
        }
    }

// Lets define the instance variable for our stack 

    Node head;
    int size = 0;

// Lets Reuse our isEmpty method to check if the Stack is empty or not 
    public boolean isEmpty(){
        if (this.size==0){
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

 /** Let's now create the Pop() method to pop the first element of the Stack
  * which is also the last element to be pushed. 
 */   

    public int pop(){

        int deletedValue = this.head.data;

        if (isEmpty()){
            System.out.println("Stack is Empty");
        } else {
            this.head = this.head.next;
            this.size--;
        }
        return deletedValue;
    }

/** Now let's create the Top() method which will return 
 * the value on the top of the stack but 
 * this will not delete the value from the stack
 */   
    public int top(){
        if (isEmpty()){
            System.out.println("Stack is Empty");
        } 
        return this.head.data;
    }


// Let's now Display the elements in our stack 
// To see if everything is working by reusing our Display method 

    public void display(){
        Node current = this.head;
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
