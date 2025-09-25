/*
Simple Inventory Management : 
# Track products with ID, name, quantity, price
# Add/remove stock, sell products
# Low stock alerts
# Sales tracking and profit calculation
# Data structures: HashMap for products, LinkedList for sales history
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.InputMismatchException;

class NegativeNumException extends Exception{
    NegativeNumException(String message){
        super(message);
    }
}

class Input_Check{

    static int int_check(Scanner in,String question){
        boolean flag;
        int check = Integer.MIN_VALUE;
        do{
            try {
                System.out.print(question);
                check = in.nextInt();
                if(check<0){
                    throw new NegativeNumException("Enter an Integer greater than or equal to 0 for the given field!");
                }
                in.nextLine();
                flag = false;
            } catch (NegativeNumException e) {
                in.nextLine();
                System.out.println(e.getMessage());
                flag = true;
                System.out.print(question);
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.println("Enter an Integer for the given field!");
                flag = true;
                System.out.print(question);
            }
        }while(flag);
        return check;
    }

}

class Common_Handle{

    void listProducts(HashMap<String,Integer> products, LinkedList<Integer> price, LinkedList<Integer> stock){
        for(Map.Entry<String,Integer> v : products.entrySet()){
            System.out.println("Product Name-> " + v.getKey() + " | Product Price-> " + price.get(v.getValue()) + " | Stock Available->" + stock.get(v.getValue()));
        }
    }

    static boolean useAgain(Scanner in, String question){
        System.out.print(question + "\n->");
        String ans = in.next();
        in.nextLine();
        boolean exit;
        do{
            if(ans.equalsIgnoreCase("yes")){
                return true;
            }else if(ans.equalsIgnoreCase("no")){
                return false;
            }else{
                System.out.println("Enter a valid Command!");
                exit = false;
            }
        }while(exit);
        return true;
    }

    void productDetail(Scanner in, HashMap<String,Integer> products, LinkedList<Integer> price){
        System.out.print("Which would you like to see the details of : ");
        String detProduct = in.nextLine();
        if(products.containsKey(detProduct)){
            System.out.println("Product Name-> " + detProduct + " | Price-> " + price.get(products.get(detProduct)));
        }else{
            System.out.println("No such product present!!");
        }
    }

}

class Customer_Handle{
    
    void addCart(Scanner in, HashMap<String, Integer> products, HashMap<String,Integer> cart, LinkedList<Integer> stock){
        System.out.print("Which product would you like to add in cart : ");
        String addProduct = in.nextLine();
        int idx = products.containsKey(addProduct) ? products.get(addProduct) : -1;
        if(idx!=-1){
            int quantity = Input_Check.int_check(in, "How many would you like to add in cart : ");
            if(quantity>stock.get(idx)){
                System.out.println("Sorry, we do not have " + quantity + " in our stock!!");
            }else{
                cart.put(addProduct,quantity);
                System.out.println("Successfully added " + addProduct + " to cart!!");
            }
        }else{
            System.out.println("No such product present!!");
        }
    }

    void removeCart(Scanner in, HashMap<String,Integer> cart){
        System.out.println("Which product whould you like to remove from the cart : ");
        String removeProduct = in.nextLine();
        if(cart.containsKey(removeProduct)){
            cart.remove(removeProduct);
            System.out.println("Successfully removed " + removeProduct + " from cart!!");
        }else{
            System.out.println("No such product present in cart!!");
        }
    }

    String showBill(HashMap<String,Integer> products, HashMap<String,Integer> cart, LinkedList<Integer> price){
        int totalBill = 0;
        for(Map.Entry<String,Integer> v : cart.entrySet()){
            for(Map.Entry<String,Integer> e : products.entrySet()){
                if(e.getKey().equals(v.getKey())){
                    int idx = e.getValue();
                    totalBill += (price.get(idx)*v.getValue());
                }
            }
        }
        return "The total bill would be " + totalBill;
    }

}

class Manager_Handle{
    private static int ADMIN_PASSWORD = 669;
    String question;

    static Boolean login(Scanner in){
        System.out.print("Enter Password : ");
        int access = Integer.MIN_VALUE;
        try{
            access = in.nextInt();
            in.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Enter an Integer for the given field!!");
        }
        if(access==ADMIN_PASSWORD){
            return true;
        }else{
            return false;
        }
    }

    void addingProducts(HashMap<String,Integer> products,LinkedList<Integer> price,LinkedList<Integer> stock, Scanner in){
        boolean proceed;
        do{
            System.out.print("Enter the product name : ");
            String prodName = in.nextLine().trim();
            products.put(prodName, stock.size());
            int amount = Input_Check.int_check(in, "What will be the price of the Product : ");
            price.add(amount);
            int quantity= Input_Check.int_check(in, "What is the stock of the Product : ");
            stock.add(quantity);
            System.out.print("Do you wanna add more products?\n->");
            String ans = in.next();
            in.nextLine();
            if(ans.equalsIgnoreCase("yes")){
                proceed = true;
            }else{
                proceed = false;
            }
        }while(proceed);
    }

    void changeStock(HashMap<String,Integer> products, LinkedList<Integer> stock, Scanner in){
        System.out.print("Which product's stock is be altered : ");
        String product = in.nextLine();
        int idx = products.get(product);
        System.out.print("What is the updated stock : ");
        int updated_stock = in.nextInt();
        stock.add(idx, updated_stock);
    }

    void changePrice(HashMap<String,Integer> products, LinkedList<Integer> price, Scanner in){
        System.out.print("Which product's price is be altered?\n");
        String product = in.nextLine();
        int idx = products.get(product);
        System.out.print("What is the updated price : ");
        int updated_price = in.nextInt();
        price.add(idx, updated_price);
    }

    void removeProduct(Scanner in ,HashMap<String,Integer> products ,LinkedList<Integer> price ,LinkedList<Integer> stock){
        System.out.println("Which product you want to remove?");
        String removeProduct = in.nextLine();
        if(products.containsKey(removeProduct)){
            int remove_idx = products.get(removeProduct);
            price.remove(remove_idx);
            stock.remove(remove_idx);
            products.remove(removeProduct, remove_idx);
            while(products.containsValue(remove_idx+1)){
                String toUpdate_Key = null;
                int to_update = remove_idx + 1;
                for(Map.Entry<String,Integer> v : products.entrySet()){
                    if(v.getValue().equals(to_update)){
                        toUpdate_Key = v.getKey();
                    }
                }
                products.replace(toUpdate_Key, remove_idx);
                remove_idx++;
            }
        }else{
            System.out.println("There is no such product in the stock.");
        }
    }
}

public class Inventory_Management {
    public static void main(String [] args){

        Scanner in = new Scanner(System.in);

        //Data Structures
        HashMap <String,Integer> products = new HashMap<>(); //Storing Product Name and Product index
        LinkedList <Integer> stock = new LinkedList<>(); //Storing Product Price
        LinkedList <Integer> price = new LinkedList<>(); //Storing Product Quantity
        HashMap <String,Integer> cart = new HashMap<>(); //Product Name and Stock bought by customer
        ArrayList <String> feedbacks = new ArrayList<>(); //Feedback by customer if not bought anything

        boolean proceed;

        Common_Handle obj_2 = new Common_Handle();

        System.out.println("Welcome to Inventory Management.");
        
        do{
            
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
                Customer_Handle obj_3 = new Customer_Handle();

                do{
                    System.out.println("What would you like to do?\n1.Browse Products\n2.List Products\n3.Show Total Bill\n4.Search Product");
                    String Action = in.nextLine();
                    switch (Action) {
                        case "1":
                        case "Browse Products":
                            obj_3.addCart(in, products, cart, stock);
                            do{
                                System.out.println("Would you like to Add Products to cart or Remove Products from cart?");
                                String action = in.nextLine();
                                if(action.equalsIgnoreCase("Add Products")){
                                    obj_3.addCart(in, products, cart, stock);
                                }else if(action.equalsIgnoreCase("Remove Products")){
                                    obj_3.removeCart(in, cart);
                                }else{
                                    System.out.println("Enter a valid command!!");
                                }
                            }while(Common_Handle.useAgain(in, "Would you like to browse more products?\n1.Yes\n2.No"));
                            break;
                        case "2":
                        case "List Products":
                            obj_2.listProducts(products, price, stock);            
                            break;
                        case "3":
                        case "Show Total Bill":
                            System.out.println(obj_3.showBill(products, cart, price));
                            break;
                        case "4":
                        case "Search Product":
                            obj_2.productDetail(in, products, price);
                            break;
                        default:
                            System.out.println("Enter a valid Command!!");
                            break;
                    }
                    if(cart.isEmpty()){
                        System.out.println("Why did you not purchase anything, please leave a feedback.");
                        int idx = 0;
                        while(idx!=feedbacks.size()){
                            idx++;
                        }
                        String feedback = in.nextLine();
                        feedback.trim();
                        feedbacks.add(idx, feedback);
                    }else{
                        System.out.println(obj_3.showBill(products, cart, price));
                        //Payment then leave
                    }
                }while(Common_Handle.useAgain(in, "Do you want to use the Customer Handle again?\n1.Yes\n2.No"));

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
                do{
                    System.out.println("What would you like to do?\n1.Add Products\n2.Alter Stocks\n3.Alter Price\n4.Remove Product\n5.List Products");
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
                        case "4":
                        case "Remove Product":
                            obj_1.removeProduct(in, products, price, stock);
                            break;
                        case "5":
                        case "List Products":
                            obj_2.listProducts(products, price, stock);
                            break;
                        case "6":
                        case "Feedbacks":
                            for(int i = 0; i < feedbacks.size(); i++){
                                System.out.println("Customer " + (i+1) + " : " + feedbacks.get(i));
                            }
                        default:
                            System.out.println("Enter a valid command!!");
                            break;
                    }
                }while(Common_Handle.useAgain(in, "Do you want to use Manager Handle again?\n1.Yes\n2.No"));
            }
        }while(Common_Handle.useAgain(in, "Do you want to use inventory management again?\n1.Yes\n2.No"));
    }
}