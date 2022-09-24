import java.io.File; // importing file utility package 
import java.util.Scanner; // Importing the Scanner class to read text files
import java.util.ArrayList; // import the ArrayList class

public class Palindrome {

  //Initially creating a boolean function to check if a string is a Palindrome

  public static boolean checkPalindrome(String string) {
    int left = 0;
    int right = string.length() - 1;
    
    while(left < right)
    {
        if(string.charAt(left) != string.charAt(right))
        {
            return false;
        }
        left++;
        right--;
    }
    return true;
  }

    public static void main(String[] args) {

    try {

      // creating a new file object
      File f = new File("magicitems.txt");

      // creating an object of Scanner
      // associated with the file
      Scanner myReader = new Scanner(f);

      // Creating an ArrayList object to store lines of strings
      ArrayList<String> strArry = new ArrayList<String>(); 


      // reading each line and
      // removing the spaces and making it all lowercase

      while(myReader.hasNextLine()) {
        String linee = myReader.nextLine();
        strArry.add(linee.toLowerCase().replace(" ", ""));
      }

        // going to the array and get the strings that are Palindrome
      for (int i = 0; i< strArry.size(); i++){

        if(checkPalindrome(strArry.get(i))){
          System.out.println(strArry.get(i));
        }
      }
      System.out.println("Size of the ArrayList: "+ strArry.size());

      // closing scanner
      myReader.close();

      // Now catching to see if any errors exist in processing this program file
    } catch (Exception e) {
      e.getStackTrace();
    }
  }
}

