
public class Spice {
    
    // This class will hold the available spices to take
    String name;
    double total_price;
    int qty;
    double price_per_qty;
    boolean isUsedAll;
    int remaining_quantity;

    Spice(String name, double total_price, int qty) {
        this.name = name;
        this.total_price = total_price;
        this.qty = qty;
        price_per_qty = 0.0;
        remaining_quantity = qty;
    }
}
