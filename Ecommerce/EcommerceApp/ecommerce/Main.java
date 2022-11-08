package ecommerce;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        User loginUser = new User();

        while (!done) {
            welcome();

            System.out.println("Enter a number from the operations list above...");
            int choice = scan.nextInt();
            scan.nextLine();// prevent skipping email input

            switch (choice) {
                case 1:
                    System.out.println("You have chosen to register for a new account!");

                    System.out.println("Enter your first and last name...");
                    String enteredName = scan.nextLine();

                    System.out.println("Enter a valid Email address...");
                    String enteredEmail = scan.nextLine();

                    if (!enteredEmail.contains("@") && !enteredEmail.contains(".com")) {
                        System.out.println("Email is invalid please retry");
                        break;
                    }

                    System.out.println("Enter a password for your account...");
                    String enteredPassword = scan.nextLine();

                    if (!Character.isUpperCase(enteredPassword.charAt(0))) {
                        System.out.println("password must begin with uppcase character");
                    }
                    if (enteredPassword.length() < 8) {
                        System.out.println("password must be at least 8 characters long");
                        break;
                    }
                    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(enteredPassword);
                    if (!m.find()) {
                        System.out.println("password must include a special character");
                        break;
                    }

                    System.out.println("Confirm your password...");
                    String confirmPassword = scan.nextLine();

                    System.out.println("Enter funds for initial deposit...");
                    double enteredDeposit = scan.nextDouble();
                    scan.nextLine();

                    if (!enteredPassword.equals(confirmPassword)) {
                        System.out.println("Your passwords did not match! please try again.");
                    } else {
                        User.registerAccount(enteredName, enteredEmail, enteredPassword, enteredDeposit);
                        System.out.println("You have succesfully created a new account!");
                        User acct = User.findAccount(enteredEmail);
                        System.out.println(acct.toString());
                    }
                    break;

                case 2:
                    System.out.println("You have chosen to login to an existing account!");

                    System.out.println("Please enter your email address...");
                    String loginEmail = scan.nextLine();

                    System.out.println("Please enter your account password...");
                    String loginPassword = scan.nextLine();

                    for (User u : User.userlist) {
                        if (u.uEmail == loginEmail) {
                            if (u.uPassword == loginPassword) {
                                loginUser = u;
                                loginUser.logIn();
                            } else {
                                System.out.println("Account could not be located! please try again.");
                                break;
                            }
                        }
                    } // end for loop through userlist
                    System.out.println("You have logged in to your account");
                    break;

                case 3:
                    if (loginUser.isLoggedIn == false) {
                        System.out.println("you must log in before making a purchase!");
                        break;
                    }
                    User.createSaleItems();
                    System.out.println("Below are the items we have on sale");
                    User.displayItems();

                    boolean donePurchasing = false;

                    while (!donePurchasing) {
                        System.out.println("Enter the itemCode corresponding to the purchase you wish to make...");
                        String iCode = scan.nextLine();

                        System.out.println("Enter the quantity you wish to purchase");
                        Integer itemQ = scan.nextInt();
                        scan.nextLine();
                        loginUser.addItem(iCode, itemQ);

                        System.out.println("would you like to make another purchase?");

                        System.out.println("Enter y/n to continue...");
                        String finishedPurchase = scan.nextLine();

                        if (finishedPurchase.equals("n")) {
                            donePurchasing = true;
                        } else {
                            continue;
                        }
                    }
                    System.out.println("\n");
                    loginUser.getInvoice();
                    break;
                case 4:
                    if (loginUser.isLoggedIn == false) {
                        System.out.println("you must log in before making a purchase!");
                        break;
                    }
                    loginUser.getInvoice();
                    System.out.println("You have selected to replace a cart item.");
                    System.out.println("Enter the item code for the item in your cart you wish to replace");
                    String code1 = scan.nextLine();

                    System.out.println("Enter item code for replacement item");
                    String code2 = scan.nextLine();
                    try {
                        loginUser.switchItems(code1, code2);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    break;

                case 5:
                    System.out.println("you have chosen to add funds to your account");
                    System.out.println("Enter the amount you wish to deposit...");
                    double deposit = scan.nextDouble();
                    loginUser.addFunds(deposit);

                case 6:
                    done = true;
                    return;
            }// end switch cases
        } // end while loop
    }

    public static void welcome() {
        System.out.println(" StandAlone ecommerce Application");
        System.out.println("+-------------------------------------+");
        System.out.println("| 1: Register                         |");
        System.out.println("| 2: Login                            |");
        System.out.println("| 3: Buy an item                      |");
        System.out.println("| 4: Replace item                     |");
        System.out.println("| 5: Exit Application                 |");
        System.out.println("+-------------------------------------+");
    }

}
