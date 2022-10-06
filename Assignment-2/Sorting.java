import java.util.ArrayList; // import the ArrayList class

public class Sorting extends Main {
    //ArrayList<String> str
    static void selectionSort(int[] num) {

        int n = num.length;
        for(int i=0; i<n-1; i++){
            int curpos = i;

            for(int j= i+1; j<n; j++){

                if(num[j]<num[curpos]){
                    curpos = j;
                }
                int temp = num[i];
                num[i] = num[curpos];
                num[curpos]=temp;
            
            }
            System.out.print(num[i]);
        }
    }
}
