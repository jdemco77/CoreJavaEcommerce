package ecommerce;

public class Item {

    String itemName;
    double itemPrice;
    String itemCode;
    Integer itemQuantity;

    public Item(String itemName, double itemPrice, String itemCode){
        this.itemName=itemName;
        this.itemPrice=itemPrice;
        this.itemCode=itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    @Override
    public String toString() {
        return "Item [itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCode=" + itemCode + "]";
    }

    

}
