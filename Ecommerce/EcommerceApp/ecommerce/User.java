package ecommerce;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class User {
    String uName;
    String uEmail;
    String uPassword;
    int uId;
    double deposit;

    boolean isLoggedIn=false;

    static int invoiceNum=1;
    static List<User> userlist=new ArrayList<>();
    static List<Item> saleItems= new ArrayList<>();

    List<Item> userCart=new ArrayList<>();

    static int i=0;

    public User(){

    }
    public User(String name,String email,String pass,double deposit){
        this.uName= name;
        this.uEmail=email;
        this.uPassword=pass;
        this.uId= i;
        this.deposit=deposit;
        i++;
    }

    public static void createSaleItems(){
        saleItems.add(new Item("Silk T-shirt", 50.99,"st1"));
        saleItems.add(new Item("Silk SweatPants", 100.99,"ss1"));
        saleItems.add(new Item("Silk boxers", 15.99,"sb1"));
        saleItems.add(new Item("Silk pajama set", 150.99,"sp1"));
    }

    public static void displayItems(){
        for(Item i: saleItems){
            System.out.println("itemName: "+i.itemName+"  itemCode: "+ i.itemCode+"  itemPrice: "+ (i.itemPrice*100)/100);
        }
    }

    public void addItem(String itemCode,int itemQuantity){
        Item item;
        for(Item i: saleItems){
            if(i.itemCode.equals(itemCode)){
                item=i;
                item.itemQuantity=itemQuantity;
                this.userCart.add(item);
                System.out.println("Item has been added to cart");
                break;
            }
        }
    }

    public void switchItems(String inCart,String replacement){
        Item item1= null;
        Item replaceItem=null;

        for(Item i: User.saleItems){
            if(i.itemCode.equals(inCart)){
                item1= i;
            }
            if(i.itemCode.equals(replacement)){
                replaceItem= i;
            }
        }
        for(Item i: userCart){
            if(i == item1){
                i=replaceItem;
                System.out.println("Items in cart have been switched");
            }
        }
    }

    public void getInvoice(){
        double totalPrice=0;
        int iter=1;
        DateTimeFormatter formatObj1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatObj2 = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("Your account balance is "+ this.deposit +"\n");
        System.out.println(" Standalone ecommerce app invoice");
        System.out.println("+================================================================+");
        System.out.println(  "| Cusomer name: "+ this.uName +"     Time: "+LocalTime.now().format(formatObj2) +"    Date: "+ LocalDateTime.now().format(formatObj1)+" ");
        System.out.println(  "| Invoice #: "+ invoiceNum+"                                     ");
        System.out.println("|  Item              itemCode               quantity          price      ");
        for(Item i:userCart){
        System.out.println(  "|"+iter+": "+i.itemName+"         "+i.itemCode+"                   "+i.itemQuantity+"            "+i.itemPrice);
            totalPrice += (i.itemPrice * i.itemQuantity);
            iter++;
        }
        System.out.println("  Total Price = "+ totalPrice+"$                                             ");
        System.out.println("+================================================================+ \n");
        invoiceNum++;
        if(totalPrice > this.deposit){
            System.out.println("You do not have enough funds to make this purchase, please add more funds to your account");
        }
    }

    public static void registerAccount(String name,String email,String pass,double deposit){
        boolean isRegistered=false;

        for(User u: userlist){      //search userlist
            if(u.uEmail==email){    // for email
                isRegistered=true;
            }
        }
        if(!isRegistered){          //if not registered create new user and add to userlist
            User user= new User(name,email,pass,deposit);
            userlist.add(user);
            System.out.println("New user created");
        }else{
            System.out.println("A User with this email already exist, try logging in!");
        }
    }
    public static User findAccount(String email){
        User user= null;
        for(User u:userlist){
            if(u.uEmail.equals(email)){
                user=u;
            }
        }
        return user;
    }
    public void logIn(){
        this.isLoggedIn=true;
    }
    public void logOut(){
        this.isLoggedIn=false; 
    }

    public void addFunds(double additionalDeposit){
        this.deposit+=additionalDeposit;
    }
    public String getuEmail() {
        return uEmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public int getuId() {
        return uId;
    }
    
    public String getuName() {
        return uName;
    }
    public double getDeposit() {
        return deposit;
    }
    public static int getInvoiceNum() {
        return invoiceNum;
    }
    @Override
    public String toString() {
        return "User [uName=" + uName + ", uEmail=" + uEmail + ", uPassword=" + uPassword + ", uId=" + uId
                + ", deposit=" + deposit + ", isLoggedIn=" + isLoggedIn + "]";
    }
    
}
