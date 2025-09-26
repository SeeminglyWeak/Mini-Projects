# 📦 Java Terminal Mini-Projects

This repository features beginner-friendly Java projects that run entirely in the terminal—no GUI, no frameworks, just classic Java logic and clean code structure.

## 🗂️ Projects Included

### 1. Library Management System

A simple library management program for handling books and students.

**Features:**
- **Add, remove, and search books**: Maintain a dynamic list of books, with admin controls to add new books or remove existing ones by title.
- **Track issued and returned books**: Students can borrow and return books. Borrowed books are tracked with the student’s ID and name.
- **Student registration and login**: New students can sign up, while existing users can log in with their ID.
- **Admin access**: Separate admin menu for managing the library.
- **Input validation**: Checks for valid names and IDs, custom exception handling for user input.
- **Simple, menu-driven interface**: All operations are guided by prompts in the terminal.

### 2. Inventory Management System

A basic inventory manager for shops or stores, supporting both customer and manager roles.

**Features:**
- **Add and update products**: Managers can add products, set prices, update stock levels, and remove items.
- **View and buy products**: Customers can see available products, view details, and simulate purchases.
- **Stock tracking**: Each product has a quantity and price, updated with every sale or stock change.
- **Sales history and feedback**: Sales and feedback from customers are logged and viewable.
- **User roles**: Separate functionality for customers and managers, including password protection for admin actions.
- **Terminal-based input/output**: Intuitive prompts for every action, with validation for commands.

## 💻 How to Run

These projects are written in pure Java and run in any terminal.

**Steps:** 

1. Navigate into the desired project folder (e.g., `Library_Management` or `Inventory_Management`).
2. Compile the main file:
   ```bash
   javac Main.java
   ```
3. Run the program:
   ```bash
   java Main
   ```

Each project directory contains its own `Main.java` (or similarly named) file. Just compile and run from inside that folder.

## 🎯 Why This Repo?

This is a collection of basic Java projects to help you learn:
- How to structure Java code for small systems
- Handling user input/output in the terminal
- Managing collections (HashMap, LinkedList, etc.)
- Building menu-driven programs with simple logic

No fancy UI, no databases—just practical, terminal-based Java for learning and experimenting.

---
