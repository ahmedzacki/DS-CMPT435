import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) {

        File f2 = new File("spice.txt");

        // Creating an ArrayList to Available spices of class Spice
        ArrayList<Spice> spices = new ArrayList<>();

        // Creating another ArrayList of Integers to store the available knapsack capacity 
        ArrayList<Integer> knapsack = new ArrayList<>();

        // This array will store the initial quantities of the spices
        ArrayList<Integer> initial_Quantities = new ArrayList<>();


        try {
            Scanner f2Reader = new Scanner(f2);

            // Start reading the file line by line
            while (f2Reader.hasNextLine()) {
                //store the data of the line 
                String data = f2Reader.nextLine();

                // Check if the line starts with spacie to create an object of class spice to store the spice details 
                if (data.startsWith("spice")) {

                    // Store the returned spice details in the temporary variable holder
                    Object[] temp = getSpice(data);
                    // converting objects to string and integeres
                    String name = (String) temp[0];
                    Integer qty = (Integer) temp[2];
                    Double totalPrice = (Double) temp[1];

                    // create a new spice and it to the spices arraylist of spices
                    spices.add(new Spice(name, totalPrice, qty));
                }

                // Creating the knapstack capacity containers
                if (data.startsWith("knapsack")) {
                    // create a knapsack container object
                    knapsack.add(getCapacity(data));
                }
            }

            // Store the initial quantities here 
            initialQty(spices, initial_Quantities);

            // // Calculating the price per quantity of each of the spices
            calculatePricePerQty(spices);

            // fill up each knapsack with the most valuable spices

            for (int i = 0; i < knapsack.size(); i++) {
                
                // Creating a HashMap object to store each of the spices and their quantity each knapstack has
                HashMap<String, Integer> result = new HashMap<String, Integer>();

                // the knapstack capacity that we are using
                int capacity = knapsack.get(i);
                //This function will do all the calculations needed and will also update the hashman with the results
                calculate(capacity, result, spices, initial_Quantities);
                
                // calculating total worth
                double worth = 0.0;
                for (String j : result.keySet()) {
                    worth = worth + (getPricePerQty(j, spices) * result.get(j));
                }
                // printing the results 
                System.out.print(
                        "knapstack of capacity " + capacity + " is worth " + worth + " quatloos and contains ");
                
                for (String j : result.keySet()) {
                    if (result.size() == 1) {
                        System.out.print(result.get(j) + " scoop of " + j + ", ");
                    } else {
                        System.out.print(result.get(j) + " scoops of " + j + ", ");
                    }
                }
                System.out.println();
            }
            
            f2Reader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    // This function will filter a string and return the two vertices where an edge will be created
    public static Object[] getSpice(String spice_info) {
        //split the string at semi-colons and get the name, total_price and qty
        Object[] spice = new Object[3];
        String[] splitLine = spice_info.split(";");
        for (String s : splitLine) {
            String[] second_split = s.split(" ");

            if (s.contains("spice")) {
                // add name to the first index of spice object
                spice[0] = (String) second_split[second_split.length - 1];
            } else if (s.contains("total_price")) {
                // Add total price to the array of objects 
                spice[1] = Double.valueOf(second_split[second_split.length - 1]);
            } else if (s.contains("qty")) {
                // Add total price to the array of objects 
                spice[2] = Integer.parseInt(second_split[second_split.length - 1]);
            }
        }
        return spice;
    }
    
    // This function will filter a sting and return an integer which represents the knapsack capacity
    public static int getCapacity(String knapCapacity) {

        int capacity = 0;

        // split the line at semi-colons
        String[] splitLine = knapCapacity.split(";");
        for (String s : splitLine) {
            //split again by space
            String[] second_split = s.split(" ");
            capacity = Integer.parseInt(second_split[second_split.length - 1]);
        }
        return capacity;
    }

    // This function will calculate the price per quantity of the spices and update that in each of the spice object
    private static void calculatePricePerQty(ArrayList<Spice> spices) {

        for (Spice s : spices) {
            s.price_per_qty = s.total_price / (double) s.qty;
        }

    }
    
    // this function retruns the item with the highest price/quantity
    private static Spice getMaxItem(ArrayList<Spice> spices) {

        Spice maxItem = spices.get(0);

        for (int i = 1; i < spices.size(); i++) {
            if (spices.get(i).price_per_qty > maxItem.price_per_qty) {
                maxItem = spices.get(i);
            }
        }
        return maxItem;
    }
    
    // This function stores the initial quantities of the spices 
    public static void initialQty(ArrayList<Spice> spices, ArrayList<Integer> initial_Quantities) {

        for (Spice s : spices) {
            initial_Quantities.add(s.qty);
        }
    }
    
    // This Function will calculate the result
    private static void calculate(int capacity, HashMap<String, Integer> result, ArrayList<Spice> spices, ArrayList<Integer> initial_Quantities) {

        // get the item with the highest price/quantity
        Spice maxItem = getMaxItem(spices);

        if (maxItem.price_per_qty != 0.0) {
            while (maxItem.remaining_quantity > 0 & capacity > 0) {
                // decriment the quantity remaining 
                maxItem.remaining_quantity = maxItem.remaining_quantity - 1;
                // add the spice and quantity into the hashmap 
                result.put(maxItem.name, result.getOrDefault(maxItem.name, 0) + 1);
                capacity--;
            }
            // Check if you have any space left in the container
            if (capacity > 0) {
                // we now konw that this capacity has used all the spices of the maximum quantity and we still have more space left
                // Making sure we don't use the same object twice for the second iteration 
                maxItem.price_per_qty = 0.0;
                // now it is time to get another spice 
                calculate(capacity, result, spices,initial_Quantities);
            }
        }
        reset(spices, initial_Quantities);
    }

    // This function resets the quantities of the objects back to default
    public static void reset(ArrayList<Spice> spices, ArrayList<Integer> initial_Quantities) {
        int i = 0;
        for (Spice s : spices) {
            s.remaining_quantity = initial_Quantities.get(i);
            i++;
        }
        // resetting the prices per quantity 
        calculatePricePerQty(spices);
    }

    // This function will take a string (the name of the spice) and will return the price per quantity of that spice
    public static double getPricePerQty(String spice_name, ArrayList<Spice> spices) {

        // initialize variable 
        double price_per_quantity = 0.0;

        for (Spice s : spices) {
            if (s.name.compareTo(spice_name) == 0) {
                price_per_quantity = s.total_price / s.qty;
            }
        }
        return price_per_quantity;

    }
}
