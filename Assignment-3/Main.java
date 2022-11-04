import java.io.File; // importing file utility package to manage our file
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // importing the ArrayList class to store elements
import java.util.Random; // For Random Number Calculations

public class Main {

    // Linear search algorithm that return the number of comparisons performed while searching the item
    public static int search(String targetString, String[] stringArray){

        int comparCount = 0;
        int n = stringArray.length;
        for(int i=0; i<n; i++){
            comparCount++;
            if(stringArray[i]==targetString)
                return comparCount;
        }
        return comparCount;
    }

    // Binary search algorith that returns the number of comparisons
    public static int binarySearch(String targetString, String[] stringArray){
        int comparCount = 0;
        int leftIndex = 0;
        int rightIndex = stringArray.length - 1;
        while(leftIndex<=rightIndex){
            comparCount++;
            int midVal = leftIndex + (rightIndex - leftIndex) / 2;
            if (stringArray[midVal] == targetString)
                return comparCount;
            comparCount++;
            if (stringArray[midVal].compareTo(targetString)>0)
                rightIndex = midVal - 1;
            else
                leftIndex = midVal + 1;
            }
        return comparCount;
    }
    // This method calculates the average number of comparisons used for each search
    public static void average(int[] array){
        int sum = 0;
        for (Integer i : array){
            sum += i;
        }
        double avg = sum/array.length;
        System.out.printf("Average number of Search Comparisons: %.2f", avg); 
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
            while (myReader.hasNextLine()) {
                String linee = myReader.nextLine();
                strArry.add(linee.replaceAll("[^A-Za-z]", "").toLowerCase());
            }
            // closing scanner
            myReader.close();
            // Now catching to see if any errors exist in processing this program file
        } catch (Exception e) {
            e.getStackTrace();
        }

        // Converting the String ArrayList to String Array of the same length
        String[] arr = new String[strArry.size()];
        for (int i = 0; i < strArry.size(); i++) {
            arr[i] = strArry.get(i);
        }

        // Sorting the String Array using MergeSort by Calling another class with a different file  
        Mergesort.mergeSort(arr);

        // Picking 42 items randomly from the array of magic items and then storing them in a new array
        int n = 42;
        String[] pickeItems = new String[n];
        Random ran = new Random();
        for(int i=0; i<n; i++){ 
            int randomIndex = i + ran.nextInt(arr.length-i);
            String temp = arr[randomIndex];
            arr[randomIndex]=arr[i];
            arr[i]=temp;
            pickeItems[i]=temp;
        }

        //resorting the original array (arr) to use it for other operations in the program
        Mergesort.mergeSort(arr);
        // Storing the number of comparisons used for each search in the following 2 arrays respectively
        int[] linearSearchCompArray = new int[pickeItems.length];
        int[] binarySearchCompArray = new int[pickeItems.length];
        
        // Searching and storing the comparison results in the arrays
        for (int i=0; i<pickeItems.length; i++){
            int linearResult = search(pickeItems[i], arr);
            int binaryResult = binarySearch(pickeItems[i], arr);
            linearSearchCompArray[i]=linearResult;
            binarySearchCompArray[i]=binaryResult;
        }
        // Printing the number of comparisons used for each linear search and the average
        for(Integer i: linearSearchCompArray)
            System.out.print(i+" ");
        System.out.println(" ");
        average(linearSearchCompArray);
        System.out.println(" ");

        // Printing the number of comparisons used for each binary search and the average
        for(Integer i: binarySearchCompArray)
            System.out.print(i+" ");
        System.out.println(" ");
        average(binarySearchCompArray);
               
        
    }
        
}
