public class Mergesort {
    //This method gets called when sorting 
    public static void mergeSort(String[] str) {
        mergesort(str, 0, str.length-1);
    }
    //Actual merge sort method 
    public static void mergesort(String[] str, int leftEndIndex, int rightEndIndex) {

        if(leftEndIndex<rightEndIndex){
            //Calcute the mid index of the input array
            int mid = (leftEndIndex+rightEndIndex)/2;
            //Recursively calling by itself to break the arry into smaller sub array until size of 1
            mergesort(str, leftEndIndex, mid);
            mergesort(str, mid+1, rightEndIndex);
            //Call the merge method
            merge(str, leftEndIndex, mid, rightEndIndex);
        }      
    }   
    // This method merges the sub arrays by comparing and sorting 
    private static void merge(String[] str, int leftEndIndex, int mid, int rightEndIndex) {

        //Using temprorary array to perfom the merging 
        String[] tempArray = new String[rightEndIndex+1];
        int i = leftEndIndex;
        int j = mid+1;
        int tempArrayIndex = leftEndIndex;

        //Sorting two sub arrays in ascending by comparing the elements of each crosponding indicis 
        while((i<= mid) && (j<=rightEndIndex)){
            if(str[i].compareTo(str[j])<0){
                tempArray[tempArrayIndex]= str[i];
                i++;
            } else {
                tempArray[tempArrayIndex]= str[j];
                j++;
            }
            tempArrayIndex++;
        }
        //Check if there are elements left in the left sub array, if so compare them with one another and sort in ascending order
        while (i<= mid){
            tempArray[tempArrayIndex]= str[i];
            i++;
            tempArrayIndex++;
        }
        // Check if there are elements left int he right sub array, if so compare them with one another and sort in ascending order
        while (j<=rightEndIndex) {
            tempArray[tempArrayIndex]= str[j];
            j++;
            tempArrayIndex++;
        }
        // Copy the elemets back to their original array 
        for(int x=leftEndIndex; x <rightEndIndex+1; x++){
            str[x]=tempArray[x];
        }
    }
}
