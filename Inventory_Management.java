/*
Simple Inventory Management : 
# Track products with ID, name, quantity, price
# Add/remove stock, sell products
# Low stock alerts
# Sales tracking and profit calculation
# Data structures: HashMap for products, LinkedList for sales history
*/

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Scanner;

class Common_Handle{
    void listProducts(HashMap<String,Integer> ID, LinkedList<Integer> price, LinkedList<Integer> stock){
        for(Map.Entry<String,Integer> v : ID.entrySet()){
            System.out.println("Product Name-> " + v.getKey() + "Product Price->₹" + price.get(v.getValue()) + "Stock Available->" + stock.get(v.getValue()));
        }
    }
}

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

    void addingProducts(HashMap<String,Integer> ID,LinkedList<Integer> price,LinkedList<Integer> stock, Scanner in){
        System.out.print("Enter the product name : ");
        String prodName = in.nextLine();
        System.out.print("What should be the prduct ID : ");
        int ID_ = in.nextInt();
        ID.put(prodName, ID_);
        System.out.print("What is the price : ");
        int amount = in.nextInt();
        price.add(amount);
        System.out.print("What is the stock : ");
        int quantity= in.nextInt();
        stock.add(quantity);
    }

    void changeStock(HashMap<String,Integer> ID, LinkedList<Integer> stock, Scanner in){
        System.out.print("Which product's stock is be altered : ");
        String product = in.nextLine();
        int idx = ID.get(product);
        System.out.print("What is the updated stock : ");
        int updated_stock = in.nextInt();
        stock.add(idx, updated_stock);
    }

    void changePrice(HashMap<String,Integer> ID, LinkedList<Integer> price, Scanner in){
        System.out.print("Which product's price is be altered?\n");
        String product = in.nextLine();
        int idx = ID.get(product);
        System.out.print("What is the updated price : ");
        int updated_price = in.nextInt();
        price.add(idx, updated_price);
    }
}

public class Inventory_Management {
    public static void main(String [] args){

        Scanner in = new Scanner(System.in);

        //Data Structures
        HashMap <String,Integer> products = new HashMap<>(); //Storing Product ID and Product Name
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

                break;

            case "Manager":
            case "manager":
                Manager_Handle obj_1 = new Manager_Handle();
                
                if(Manager_Handle.login(in)){
                System.out.println("Access Granted!");
                }else{
                System.out.println("Access Denied!!");
                break;
                }

                System.out.println("What would you like to do?\n1.Add Products\n2.Alter Stocks\n3.Alter Price");
                String action = in.nextLine();
                switch (action) {
                    case "1":
                    case "Add Products":
                        obj_1.addingProducts(products, price, stock, in);
                        break;
                    case "2":
                    case "Alter Stocks":
                        obj_1.changeStock(products, stock, in);
                        break;
                    case "3":
                    case "Alter Price":
                        obj_1.changePrice(products, price, in);
                        break;
                
                    default:
                        System.out.println("Enter a valid command!!");
                        break;
                }
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
