import java.io.File; // importing file utility package 
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // import the ArrayList class

public class Main {


    public static void main(String[] args) {


        // Creating an ArrayList object to store lines of strings
        ArrayList<String> strArry = new ArrayList<String>();
      
        try { 
            // creating a new file object
            File f = new File("magicitems.txt");

            // creating an object of Scanner
            // associated with the file
            Scanner myReader = new Scanner(f);

            // reading each line and
            // removing the spaces and making it all lowercase
            while(myReader.hasNextLine()) {
                String linee = myReader.nextLine();
                strArry.add(linee.toLowerCase().replace(" ", ""));
                }
            // closing scanner
            myReader.close();
            
            // Now catching to see if any errors exist in processing this program file
          } catch (Exception e) {
            e.getStackTrace();
          }

        // going to the array and get the strings that are Palindrome
        for (int i = 0; i< strArry.size(); i++){
            System.out.println(strArry.get(i));
        }
        System.out.println("Size of the ArrayList: "+ strArry.size());

        int[] arr = {3,4,2,1};

        Sorting.selectionSort(arr);


    
    }
}
