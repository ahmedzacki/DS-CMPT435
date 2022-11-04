public class Mergesort {


    public static void mergeSort(String[] str) {
        mergesort(str, 0, str.length-1);
    }
    public static void mergesort(String[] str, int leftEndIndex, int rightEndIndex) {

        if(leftEndIndex<rightEndIndex){
            int mid = (leftEndIndex+rightEndIndex)/2;
            mergesort(str, leftEndIndex, mid);
            mergesort(str, mid+1, rightEndIndex);
            merge(str, leftEndIndex, mid, rightEndIndex);
        }      
    }
    private static void merge(String[] str, int leftEndIndex, int mid, int rightEndIndex) {
        String[] tempArray = new String[rightEndIndex+1];
        int i = leftEndIndex;
        int j = mid+1;
        int tempArrayIndex = leftEndIndex;

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
        while (i<= mid){
            tempArray[tempArrayIndex]= str[i];
            i++;
            tempArrayIndex++;
        }
        while (j<=rightEndIndex) {
            tempArray[tempArrayIndex]= str[j];
            j++;
            tempArrayIndex++;
        }
        for(int x=leftEndIndex; x <rightEndIndex+1; x++){
            str[x]=tempArray[x];
        }
    }

}
