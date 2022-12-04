import java.util.ArrayList;

public class Queue {
    
    // This queue class will keep truck of the visited verticie

    ArrayList<LinkedObjects> queueData;

    Queue() {
        queueData = new ArrayList<>();
    }

     // Checking if the Queue is empty 
     public boolean empty() {
         if (queueData.size() == 0) {
             return true;
         } else {
             return false;
         }
     }
     // Add an element to the queue
     public void enqueue(LinkedObjects v) {
         this.queueData.add(v);
     }
     
     // Remove an element from the queue
     public LinkedObjects dequeue() {
         LinkedObjects v = this.queueData.get(0);
         this.queueData.remove(0);
         return v;
     }
}
