import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

class student{
    private int id;
    private  String name;

    student(int id,String name){
        this.id = id;
        this.name= name;
    }

    public String toString(){
        return "ID : " + id + " Name : " + name;
    }
}

class validNameException extends Exception{
    validNameException(String message){
        super(message);
    }
}

class check{
    
    static int int_check(Scanner sc) throws InputMismatchException{

        int ID = Integer.MIN_VALUE;
        boolean flag;
        do{
            try{
                System.out.print("Enter ID : ");
                ID = sc.nextInt();
                if(ID<0){
                    System.out.println("Please enter a positive ID!!!");
                    flag = true;
                    continue;
                }
                flag = false;
            }catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter an integer!!!");
                flag = true;
            }
        }while(flag);

        return ID;  
    }

    static String str_check(Scanner sc){
        
        boolean flag;
        String name;

        do{
            System.out.print("Enter your name : ");
            name = sc.nextLine();
            try {
                for(int i = 0; i < name.length(); i++){
                    int num = (int) name.charAt(i);
                    if(num>=48 && num<=57){
                        throw new validNameException("Please do not enter numbers in the asked field!!");
                    }
                }
                flag = false;
            } catch (validNameException e) {
                System.out.println(e.getMessage());
                flag = true;
            }
        }while(flag);

        return name;
    }
}

class Books_States{
    static HashMap<String,String> list = new HashMap<>();

    Books_States(){
        
    }

    Books_States(String book,String name){
        list.put(book,name);
    }

    Books_States(String book){
        list.remove(book);
    }
    
    void print_borrows(){
        for(Map.Entry<String,String> v : list.entrySet()){
            System.out.println("Book-> " + v.getKey() + " Borrowed by -> " + v.getValue());
        }
    }
}

class admin_access{
    Scanner sc = new Scanner(System.in);
    boolean access(String book,String author){
        System.out.println("Would you like to add " + book + " to the library written by " + author + " ?");
        String command = sc.nextLine();
        if(command.equalsIgnoreCase("yes")){
            return true;
        }else{
            return false;
        }
    }
} 


class Show_Available_Book{
    private Map<String,String> list;

    Show_Available_Book(HashMap<String,String> Books){
        this.list = Books;
    }

    void print_available_books(){
        for(Map.Entry<String,String> e : list.entrySet()){
            System.out.println("Book -> " + e.getKey() + " Author -> " + e.getValue());
        }
    }
}

public class Library_Management{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        int ID;
        String NAME = null;

        //List of books
        HashMap<String,String> Books = new HashMap<>();
        Books.put("To Kill a Mockingbird",	"Harper Lee");
        Books.put("1984",	"George Orwell");
        Books.put("The Alchemist",	"Paulo Coelho");
        Books.put("The Great Gatsby",	"F. Scott Fitzgerald");
        Books.put("Sapiens: A Brief History of Humankind","Yuval Noah Harari");
        Books.put("The Catcher in the Rye",	"J.D. Salinger");
        Books.put("Pride and Prejudice",	"Jane Austen");
        Books.put("The Hobbit",	"J.R.R. Tolkien");
        Books.put("Thinking, Fast and Slow",	"Daniel Kahneman");
        HashMap<String,String> Permanent_Books = new HashMap<>();
        Permanent_Books.putAll(Books);
        HashMap<String,String> Removed_Books = new HashMap<>();

        //List of Students
        HashMap<Integer,student> Students = new HashMap<>();

        //Profession input
        boolean proceed = false;
        String access;
        boolean end = true;
        System.out.println("Welcome to the library!");
        

        do{
            System.out.println("Would you like to access as a student or admin?");

            do{
                access = sc.nextLine();
                if(access.equalsIgnoreCase("student")){
                    proceed = true;
                }else if(access.equalsIgnoreCase("admin")){
                    proceed = true;
                }else{
                    System.out.println("Please enter a valid profession!");
                }
            }while(!proceed);

            //Proceeding according to profession
            switch (access) {
                //Student case 1
                case "Student":
                case "student":
                    System.out.println("Do you want to login or sign up?");
                    String command = check.str_check(sc);
                if (command.equalsIgnoreCase("sign up")) {
                    ID = check.int_check(sc);
                    sc.nextLine();
                    System.out.print("Enter your name : ");
                    NAME = check.str_check(sc);
                    Students.put(ID,new student(ID,NAME)); 
                }else if(command.equalsIgnoreCase("login")){
                    System.out.print("Enter ID : ");
                    ID = check.int_check(sc);
                    if(Students.containsKey(ID)){
                        System.out.println("Login successful!!!\nWelcome " + Students.get(ID));
                    }else {
                        System.out.println("No match found!!");
                        break;
                    }
                    sc.nextLine();
                }
                System.out.println("Enter which one of the actions to perform?");
                System.out.println("1.Borrow Book\n2.Return Book\n3.Available book list\n4.Add Book to library!");
                String action = sc.nextLine();
                switch (action) {
                    case "1":
                    case "Borrow Book":
                        String book;
                        System.out.print("Enter the name of the book you want to be issued : ");
                        book = sc.nextLine();
                        if(Books.containsKey(book)){
                            System.out.println("Please return the book in 15 days, thank you for using library!!");
                            new Books_States(book,NAME);
                            Books.remove(book);
                        }else{
                            System.out.println("There exists no such books in the libraray.\nPlease enter the name of the book correctly otherwise.");
                        }
                        break;
                    case "2":
                    case "Return Book":
                        String returning;
                        System.out.print("Enter the name of the book you wish to return : ");
                        returning = sc.nextLine();
                        if(Permanent_Books.containsKey(returning) && !Books.containsKey(returning)){
                            Books.put(returning,Permanent_Books.get(returning));
                            new Books_States(returning);
                        }else if(Removed_Books.containsKey(returning)){
                            System.out.println("No need to return the book, the book has been removed from the library. You can keep it!!!");
                        }else{
                            System.out.println("No such book was issued from the library otherwise try again with case sensitive notation.");
                        }
                        break;
                    case "3":
                    case "Available book list":
                        Show_Available_Book obj1 = new Show_Available_Book(Books);
                        obj1.print_available_books();
                        break;
                    case "4":
                    case "Add book to library":
                        System.out.println("Which book would you like to add?");
                        String add_book = sc.nextLine();
                        System.out.println("Who is the author of that book?");
                        String author = sc.nextLine();
                        System.out.println("To add a book, Admin access is required. Please enter the password : ");
                        int pass = sc.nextInt();
                        if(pass==669){
                            admin_access obj2 = new admin_access();
                            if(obj2.access(add_book,author)){
                                Books.put(add_book,author);
                                Permanent_Books.put(add_book,author);
                                System.out.println("Thank you for adding the book to the library!!!!");
                            }else{
                                System.out.println("The addition of the book has been denied by the admin.");
                            }
                        }else{
                            System.out.println("Admin access denied.");
                        }
                        break;
                    default:
                        System.out.println("Enter a valid command.");
                        break;
                }
                    break;
                //Admin case 2
                case "Admin":
                case "admin":
                    System.out.println("Enter the password to proceed : ");
                    int pass = sc.nextInt();
                    if(pass==669){
                        System.out.println("Access granted");
                    }else{
                        System.out.println("Access denied");
                        break;
                    }
                    sc.nextLine();
                    
                    System.out.println("What whould you like to do?\n1.List of borrowed books\n2.Remove book\n3.Add book\n4.Remove Student\n5.List of Students");
                    String action_2 = sc.nextLine();
                    switch (action_2) {
                        case "1":
                        case "List of borrowed books":
                            Books_States obj = new Books_States();
                            obj.print_borrows();
                            break;
                        case "2":
                        case "Remove book":
                            System.out.println("Which book to remove?");
                            String removebook = sc.nextLine();
                            Removed_Books.put(removebook,Permanent_Books.get(removebook));
                            Permanent_Books.remove(removebook);
                            Books.remove(removebook);
                            System.out.println("Successfully removed the book.");
                            break;
                        case "3":
                        case "Add book":
                            System.out.println("Which book to add?");
                            String addbook = sc.nextLine();
                            System.out.println("Who is the author of book?");
                            String addbook_author = check.str_check(sc);
                            Books.put(addbook,addbook_author);
                            Permanent_Books.put(addbook,addbook_author);
                            System.out.println("Successfully added the book.");
                            break;
                        case "4":
                        case "Remove Student":
                            System.out.print("Which student to remove? Enter ID : ");
                            int remove_id = sc.nextInt();
                            Students.remove(remove_id);
                            break;
                        case "5":
                        case "List of Students":
                            System.out.println(Students);
                            break;
                        default:
                            System.out.println("Enter a valid command.");
                            break;
                    }
                    break;
            }
            sc.nextLine();
            System.out.println("Would you like to use library again?\n1.YES\n2.NO");
            String end_1 = sc.nextLine();
            if(end_1.equalsIgnoreCase("yes")){
                end = false;
            }else{
                end = true;
            }
        }while(!end);
        System.out.println("Thank you for using the library.");
        sc.close();
    }
}