import java.io.File; // importing file utility package to manage our file
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // importing the ArrayList class to store elements
import java.util.Random;  // For Random Number Calculation

public class Main {

//Shuffling the ArraList
    public static void shuffleArrList(ArrayList<String> strList) {
        int n = strList.size();
        Random ran = new Random();
        for(int i=0; i<n; i++){
            int randomIndex = i + ran.nextInt(n-i);
            String temp = strList.get(randomIndex);
            strList.set(randomIndex, strList.get(i));
            strList.set(i, temp);
        }
    }
//Selection Sort
    public static ArrayList<String> selectionSort(ArrayList<String> str) {
        if (str == null) {
            return null;
        }
        shuffleArrList(str);
        int comparisonCount = 0;
        int n = str.size();
        for(int i=0; i<n-1; i++){
            int minPosition = i;
            for(int j= i+1; j<n; j++){
                comparisonCount++;
                int result = str.get(j).compareTo(str.get(minPosition));
                if(result<0){
                    minPosition = j;
                }
            }
            String temp = str.get(i);
            str.set(i, str.get(minPosition));
            str.set(minPosition, temp);  
        }
        System.out.println("Number of Comparisons: "+ comparisonCount);
        return str;
    }
//Insertion Sort
    public static ArrayList<String> insertionSort(ArrayList<String> str){
        if (str == null) {
            return null;
        }
        shuffleArrList(str);
        int swapComparison = 0;
        int nonSwapComparison = 0;
        int n = str.size();
        for(int i=1; i<n; i++){
            String currentValue = str.get(i);
            int currentPos = i;
            while((currentPos>0) && (str.get(currentPos-1).compareTo(str.get(currentPos))>0)){
                str.set(currentPos, str.get(currentPos-1));
                str.set(currentPos-1, currentValue);
                swapComparison++;
                currentPos--;
            }
            nonSwapComparison++;
        }
        System.out.println("Number of Comparisons: "+ (swapComparison+nonSwapComparison));
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

        System.out.println("<<Selection Sort>>");
        System.out.println("Size of the ArrayList: "+ strArry.size());
        System.out.println(selectionSort(strArry));
        System.out.println("<<Insertion Sort>>");
        System.out.println("Size of the ArrayList: "+ strArry.size());
        System.out.println(insertionSort(strArry));
    
    }
}