import java.io.File; // importing file utility package to manage our file
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // importing the ArrayList class to store elements
import java.util.Collections; // For swaping elements

public class Main {

    public static ArrayList<String> selectionSort(ArrayList<String> str) {
        if (str == null) {
            return null;
        }
        int comparisonCount = 0;
        int n = str.size();
        for(int i=0; i<n-1; i++){
            int curpos = i;
            for(int j= i+1; j<n; j++){
                countComparison++;
                int result = str.get(j).compareToIgnoreCase(str.get(curpos));
                if(result<0){
                    curpos = j;
                }
            }
            Collections.swap(str, i, curpos);   
        }
        System.out.println("Number of Comparisons: "+ comparisonCount);
        return str;
    }

    public static void main(String[] args) {
        // Creating an ArrayList object to store lines of strings
        ArrayList<String> strArry = new ArrayList<String>();       
      
        try { 
            // creating a new file object
            File f = new File("magicitems.txt");
            // creating a Scanner Object
            Scanner myReader = new Scanner(f);
            // reading each line and removing the spaces and making it all lowercase
            while(myReader.hasNextLine()) {
                String linee = myReader.nextLine();
                strArry.add(linee.replaceAll("[^A-Za-z]", "").toLowerCase());
                }
            // closing scanner
            myReader.close();    
            // Now catching to see if any errors exist in processing this program file
          } catch (Exception e) {
            e.getStackTrace();
          }

        System.out.println("Size of the ArrayList: "+ strArry.size());
        System.out.println(selectionSort(strArry));

    
    }
}
