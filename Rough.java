import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

class Customer_Handle{

    void listProducts(HashMap<String,Integer> ID, LinkedList<Integer> price, LinkedList<Integer> stock){
        for(Map.Entry<String,Integer> v : ID.entrySet()){
            System.out.println("Product Name-> " + v.getKey() + " Product Price-> " + price.get(v.getValue()) + " Stock Available-> " + stock.get(v.getValue()));
        }
    }
}

class Product_Management{

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

    void removeProduct(){

    }
}

public class Rough{
    public static void main(String [] args){

        Scanner in = new Scanner(System.in);

        HashMap <String,Integer> products = new HashMap<>(); //Storing Product Name and Product ID (index)
        LinkedList <Integer> stock = new LinkedList<>(); //Storing Product Price
        LinkedList <Integer> price = new LinkedList<>(); //Storing Product stock

        //Adding products
        Product_Management obj = new Product_Management();
        boolean proceed;
        do{
            obj.addingProducts(products, price, stock, in);
            System.out.print("Add more products?\n->");
            String ans = in.next();
            in.nextLine();
            if(ans.equalsIgnoreCase("yes")){
                proceed = true;
            }else{
                proceed = false;
            }
        }while(proceed);
        System.out.println(products);
        //Changing stock
        do{
            obj.changeStock(products, stock, in);
            System.out.print("Change more stocks?\n->");
            String ans = in.next();
            in.nextLine();
            if(ans.equalsIgnoreCase("yes")){
                proceed = true;
            }else{
                proceed = false;
            }
        }while(proceed);
        //Changing price 
        do{
            obj.changePrice(products, price, in);
            System.out.print("Change more prices?\n->");
            String ans = in.next();
            in.nextLine();
            if(ans.equalsIgnoreCase("yes")){
                proceed = true;
            }else{
                proceed = false;
            }
        }while(proceed);

        Customer_Handle obj1 = new Customer_Handle();
        obj1.listProducts(products, price, stock);
     }
}