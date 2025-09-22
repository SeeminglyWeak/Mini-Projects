/*
Simple Inventory Management : 
# Track products with ID, name, quantity, price
# Add/remove stock, sell products
# Low stock alerts
# Sales tracking and profit calculation
# Data structures: HashMap for products, LinkedList for sales history
*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class Customer_Handle{
    
}

class Manager_Handle{
    private static int ADMIN_PASSWORD = 669;

    static Boolean login(Scanner in){
        System.out.println("Enter Password : ");
        int access = in.nextInt();
        if(access==ADMIN_PASSWORD){
            return true;
        }else{
            return false;
        }
    }


}

public class Inventory_Management {
    public static void main(String [] args){

        Scanner in = new Scanner(System.in);

        //Data Structures
        HashMap <Integer,String> products = new HashMap<>(); //Storing Product ID and Product Name
        LinkedList <Integer> stock = new LinkedList<>(); //Storing Product Price
        LinkedList <Integer> price = new LinkedList<>(); //Storing Product Quantity

        boolean proceed;

        do{
            
            System.out.println("Welcome to Inventory Management.");
            String access;

            do{
            System.out.println("Would you like to access as customer or manager?");
            access = in.nextLine();
            if(access.equalsIgnoreCase("customer")){
                proceed = true;
            }else if(access.equalsIgnoreCase("manager")){
                proceed = true;
            }else{
                System.out.println("Please enter a valid profession!!");
                proceed = false;
            }
            }while(!proceed);

            access = access.trim();

            switch(access){
            case "Customer":
            case "customer":

            case "Manager":
            case "manager":
                
                if(Manager_Handle.login(in)){
                System.out.println("Access Granted!");
                }else{
                System.out.println("Access Denied!!");
                break;
                }

                System.out.println("What would you like to do?\n1.");
            default:

            }
            
            System.out.print("Do you want to use inventory management again?\n1.Yes\n2.No\n->");
            String continuing = in.next();
            if(continuing.equalsIgnoreCase("yes")){
            proceed = true;
            }else{
            proceed = false;
            }
            in.nextLine();
        }while(proceed);
    }
}
