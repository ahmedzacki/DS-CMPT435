import java.io.File; // importing file utility package to manage our file
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // importing the ArrayList class to store elements
import java.util.Random;  // For Random Number Calculation

public class Main {

    private static int mergeSortComparisonsCount=0;
    private static int quickSortComparisonsCount=0;

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
    public static ArrayList<String> selectionSort(ArrayList<String> str){
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
//Merge Sort
    public static ArrayList<String> mergeSort(ArrayList<String> str) {
        if (str == null) {
            return null;
        }
        shuffleArrList(str);
        mergeSort(str, 0, str.size()-1);
        System.out.println("Number of Comparisons: "+ (mergeSortComparisonsCount));
        return str;
    }
    public static void mergeSort(ArrayList<String> str, int leftEndIndex, int rightEndIndex) {

        if(leftEndIndex<rightEndIndex){
            int mid = (leftEndIndex+rightEndIndex)/2;
            mergeSort(str, leftEndIndex, mid);
            mergeSort(str, mid+1, rightEndIndex);
            merge(str, leftEndIndex, mid, rightEndIndex);
        }      
    }

    private static void merge(ArrayList<String> str, int leftEndIndex, int mid, int rightEndIndex) {
        ArrayList<String> tempArray = new ArrayList<String>(str);
        int i = leftEndIndex;
        int j = mid+1;
        int tempArrayIndex = leftEndIndex;

        while((i<= mid) && (j<=rightEndIndex)){
            if(str.get(i).compareTo(str.get(j))<0){
                tempArray.set(tempArrayIndex, str.get(i));
                i++;
            } else {
                tempArray.set(tempArrayIndex, str.get(j));
                j++;
            }
            tempArrayIndex++;
            mergeSortComparisonsCount++;
        }
        while (i<= mid){
            tempArray.set(tempArrayIndex, str.get(i));
            i++;
            tempArrayIndex++;
        }
        while (j<=rightEndIndex) {
            tempArray.set(tempArrayIndex, str.get(j));
            j++;
            tempArrayIndex++;
        }
        for(int x=leftEndIndex; x <rightEndIndex+1; x++){
            str.set(x, tempArray.get(x));
        }
    }
// Quick Sort
    public static ArrayList<String> quickSort(ArrayList<String> str) {
        if (str == null) {
            return null;
        }
        shuffleArrList(str);
        quickSort(str, 0, str.size()-1);
        System.out.println("Number of Comparisons: "+ (quickSortComparisonsCount));
        return str;
    }
    public static void quickSort(ArrayList<String> str, int leftEndIndex, int rightEndIndex) {
        if (leftEndIndex<rightEndIndex){
            int partitionIndex = partition(str, leftEndIndex, rightEndIndex);
            quickSort(str, leftEndIndex, partitionIndex-1);
            quickSort(str, partitionIndex+1, rightEndIndex);
        }
    }
    public static int partition(ArrayList<String> str, int leftEndIndex, int rightEndIndex) {
        String pivot = str.get(leftEndIndex);
        int i = leftEndIndex+1;
        int j = rightEndIndex;
        int count = 0;

        while(true){
            while((i<=j) && ((str.get(i).compareTo(pivot))<=0)){
                i++;
                count++;
                quickSortComparisonsCount++;
            }
            while((i<=j) && ((str.get(j).compareTo(pivot))>0)){
                j--;
                count++;
                quickSortComparisonsCount++;
            }
            if(i<=j){
                String temp = str.get(i);
                str.set(i, str.get(j));
                str.set(j, temp);
            } else {
                break;
            }
        }
        String tempVar2 = str.get(leftEndIndex);
        str.set(leftEndIndex, str.get(j));
        str.set(j, tempVar2);

        return j;  
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

//Un-comment the following lines of code to run each of the sorting algorithm 

        // System.out.println("<<Selection Sort>>");
        // System.out.println("Size of the ArrayList: "+ strArry.size());
        // System.out.println(selectionSort(strArry));
        // System.out.println("<<Insertion Sort>>");
        // System.out.println("Size of the ArrayList: "+ strArry.size());
        // System.out.println(insertionSort(strArry));
        // System.out.println("<<Merge Sort>>");
        // System.out.println("Size of the ArrayList: "+ strArry.size());
        // System.out.println(mergeSort(strArry));  
        // System.out.println("<<Quick Sort>>");
        // System.out.println("Size of the ArrayList: "+ strArry.size());
        // System.out.println(quickSort(strArry));  
    }
}