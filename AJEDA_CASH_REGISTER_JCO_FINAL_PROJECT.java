/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.paulgeneo.ajeda_cash_register_jco_final_project;

/**
 *
 * @author PAUL GENEO
 */
import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class AJEDA_CASH_REGISTER_JCO_FINAL_PROJECT {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> usernames = new ArrayList<>();
    static ArrayList<String> passwords = new ArrayList<>();

    static ArrayList<String> cartProductNames = new ArrayList<>();
    static ArrayList<Integer> cartProductQuantities = new ArrayList<>();
    static ArrayList<Double> cartProductPrices = new ArrayList<>();

    static String loggedInUser = "";

    public static void main(String[] args) {
        welcomePage();
        boolean authenticated = false;

        while (!authenticated) {
            System.out.println("\n[1] Sign Up");
            System.out.println("[2] Log In");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                signup();
            } else if (choice.equals("2")) {
                authenticated = login();
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        mainMenu();
    }

    public static void welcomePage() {
        System.out.println("\n=====================================================");
        System.out.println("|                                                   |");
        System.out.println("|        WELCOME TO J.CO CASH REGISTER SYSTEM       |");
        System.out.println("|                  by: Paul Ajeda                   |");
        System.out.println("|                                                   |");
        System.out.println("|     Fresh Donuts | Rich Coffee | Cool Frappe      |");
        System.out.println("|___________________________________________________|");
        System.out.println("=====================================================");
    }

    public static void signup() {
        System.out.println("\n=========================");
        System.out.println("     SIGN UP PORTAL     ");
        System.out.println("=========================");

        String username, password;
        while (true) {
            System.out.print("Enter username (5-15 Alphanumeric Characters, at least one letter and one digit): ");
            username = sc.nextLine();
            if (!username.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,15}$")) {
                System.out.println("Invalid username format.");
            } else if (usernames.contains(username)) {
                System.out.println("Username already taken.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Enter password (8-20 chars, 1 uppercase, 1 digit): ");
            password = sc.nextLine();
            if (!password.matches("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$")) {
                System.out.println("Invalid password format.");
            } else {
                break;
            }
        }

        usernames.add(username);
        passwords.add(password);
        System.out.println("Account created successfully!");
    }

    public static boolean login() {
        System.out.println("\n=========================");
        System.out.println("      LOGIN PORTAL      ");
        System.out.println("=========================");

        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        for (int i = 0; i < usernames.size(); i++) {
            if (user.equals(usernames.get(i)) && pass.equals(passwords.get(i))) {
                loggedInUser = user;
                System.out.println("Login successful!");
                return true;
            }
        }

        System.out.println("Invalid credentials. Please try again.");
        return false;
    }

    public static void mainMenu() {
        boolean running = true;
        while (running) {
            printHeader("J.CO CASH REGISTER");
            System.out.println("1. View Menu");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Update Cart Item");
            System.out.println("5. Remove Cart Item");
            System.out.println("6. Checkout");
            System.out.println("7. Log Out");

            System.out.print("Select an option: ");
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    displayMenu();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    displayCart();
                    break;
                case "4":
                    updateCartItem();
                    break;
                case "5":
                    removeCartItem();
                    break;
                case "6":
                    checkout();
                    break;
                case "7":
                    if (confirmLogout()) {
                        running = false;
                        logout();
                    }
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static boolean confirmLogout() {
        while (true) {
            System.out.print("Are you sure you want to log out? (y/n): ");
            String ans = sc.nextLine().trim().toLowerCase();
            if (ans.equals("y")) return true;
            else if (ans.equals("n")) return false;
            else System.out.println("Invalid input. Please enter 'y' or 'n'.");
        }
    }

    public static void printHeader(String title) {
        System.out.println("\n=====================================================");
        System.out.printf("%30s\n", title);
        System.out.println("=====================================================");
    }

    public static void displayMenu() {
        printHeader("J.CO MENU - DONUTS");

        System.out.printf("| %-35s | %-13s |\n", "Product", "Price (PHP)");
        System.out.println("|-------------------------------------|---------------|");
        System.out.printf("| %-35s | %-13.2f |\n", "Half Dozen Donuts", 300.00);
        System.out.printf("| %-35s | %-13.2f |\n", "1 Dozen Donuts", 490.00);
        System.out.printf("| %-35s | %-13.2f |\n", "Yin Yang 2 Dozen Donuts", 825.00);
        System.out.printf("| %-35s | %-13.2f |\n", "2 Dozen Donuts", 825.00);
        System.out.printf("| %-35s | %-13.2f |\n", "JPOPS (24 pcs Baby Donuts)", 300.00);

        printHeader("J.CO MENU - FRAPPES");

        System.out.printf("| %-30s | %-5s | %-5s | %-5s |\n", "Product", "S", "M", "L");
        System.out.println("|--------------------------------|-------|-------|-------|");
        System.out.printf("| %-30s | %-5.0f | %-5.0f | %-5.0f |\n", "Caramel Frappe", 170.0, 185.0, 205.0);
        System.out.printf("| %-30s | %-5.0f | %-5.0f | %-5.0f |\n", "Cappuccino Chip Frappe", 170.0, 185.0, 205.0);
        System.out.printf("| %-30s | %-5.0f | %-5.0f | %-5.0f |\n", "Avocado Frappe", 170.0, 185.0, 205.0);
        System.out.printf("| %-30s | %-5.0f | %-5.0f | %-5.0f |\n", "Jcoccino Frappe", 165.0, 180.0, 195.0);
        System.out.printf("| %-30s | %-5.0f | %-5.0f | %-5.0f |\n", "Mocha Espresso Frappe", 170.0, 185.0, 200.0);
        System.out.printf("| %-30s | %-5.0f | %-5.0f | %-5.0f |\n", "White Choco Frappe", 175.0, 195.0, 205.0);
        System.out.printf("| %-30s | %-5.0f | %-5.0f | %-5.0f |\n", "Chocolate Frappe", 170.0, 185.0, 200.0);
        System.out.printf("| %-30s | %-5.0f | %-5.0f | %-5.0f |\n", "Oreo Frappe", 175.0, 190.0, 205.0);
        System.out.printf("| %-30s | %-5.0f | %-5.0f | %-5.0f |\n", "Strawberry Yogurt Frappe", 175.0, 190.0, 205.0);
    }

    public static void addProduct() {
        printHeader("ADD TO CART");
        while (true) {
            System.out.print("Enter product name: ");
            String name = sc.nextLine();

            int qty = 0;
            while (true) {
                try {
                    System.out.print("Enter quantity: ");
                    qty = Integer.parseInt(sc.nextLine());
                    if (qty <= 0) {
                        System.out.println("Quantity must be a positive integer. Try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            double price = 0;
            while (true) {
                try {
                    System.out.print("Enter price: ");
                    price = Double.parseDouble(sc.nextLine());
                    if (price <= 0) {
                        System.out.println("Price must be a positive number. Try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid price.");
                }
            }

            cartProductNames.add(name);
            cartProductQuantities.add(qty);
            cartProductPrices.add(price);

            System.out.println("Product added to cart!");

            String answer;
            while (true) {
                System.out.print("Do you want to add another product? (y/n): ");
                answer = sc.nextLine().trim().toLowerCase();
                if (answer.equals("y") || answer.equals("n")) break;
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
            if (answer.equals("n")) break;
        }
    }

    public static void displayCart() {
        printHeader("CART SUMMARY");
        if (cartProductNames.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.printf("| %-4s | %-30s | %-5s | %-12s |\n", "No", "Product", "Qty", "Price (PHP)");
            System.out.println("|------|--------------------------------|-------|--------------|");
            for (int i = 0; i < cartProductNames.size(); i++) {
                System.out.printf("| %-4d | %-30s | %-5d | PHP %-8.2f |\n",
                        (i + 1),
                        cartProductNames.get(i),
                        cartProductQuantities.get(i),
                        cartProductPrices.get(i));
            }
        }
    }

    public static void updateCartItem() {
        displayCart();
        if (cartProductNames.isEmpty()) return;

        int index = -1;
        while (true) {
            try {
                System.out.print("Enter item number to update quantity: ");
                index = Integer.parseInt(sc.nextLine()) - 1;
                if (index < 0 || index >= cartProductNames.size()) {
                    System.out.println("Invalid item number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }

        int newQty = 0;
        while (true) {
            try {
                System.out.print("Enter new quantity: ");
                newQty = Integer.parseInt(sc.nextLine());
                if (newQty <= 0) {
                    System.out.println("Quantity must be a positive integer.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }

        cartProductQuantities.set(index, newQty);
        System.out.println("Quantity updated.");
    }

    public static void removeCartItem() {
        displayCart();
        if (cartProductNames.isEmpty()) return;

        int index = -1;
        while (true) {
            try {
                System.out.print("Enter item number to remove: ");
                index = Integer.parseInt(sc.nextLine()) - 1;
                if (index < 0 || index >= cartProductNames.size()) {
                    System.out.println("Invalid item number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }

        cartProductNames.remove(index);
        cartProductQuantities.remove(index);
        cartProductPrices.remove(index);
        System.out.println("Item removed from cart.");
    }

    public static void checkout() {
        if (cartProductNames.isEmpty()) {
            System.out.println("Cart is empty. Cannot checkout.");
            return;
        }

        printHeader("CHECKOUT");
        double total = 0;
        for (int i = 0; i < cartProductNames.size(); i++) {
            total += cartProductQuantities.get(i) * cartProductPrices.get(i);
        }

        System.out.printf("Total amount: PHP %.2f\n", total);

        double payment = 0;
        while (true) {
            try {
                System.out.print("Enter payment amount: PHP ");
                payment = Double.parseDouble(sc.nextLine());
                if (payment < total) {
                    System.out.println("Payment must be at least PHP " + String.format("%.2f", total));
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }

        double change = payment - total;
        System.out.printf("Change: PHP %.2f\n", change);

        logTransaction(total, payment, change);

        System.out.println("Thank you for your purchase!");

        cartProductNames.clear();
        cartProductQuantities.clear();
        cartProductPrices.clear();
    }

    public static void logTransaction(double totalPrice, double payment, double change) {
        try {
            File file = new File("transactions.txt");
            FileWriter writer = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(writer);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateTime = now.format(formatter);

            printWriter.println("========================================================");
            printWriter.println("Date & Time: " + dateTime);
            printWriter.println("User: " + loggedInUser);
            printWriter.println("--------------------------------------------------------");
            printWriter.println("Items Purchased:");

            for (int i = 0; i < cartProductNames.size(); i++) {
                double itemTotal = cartProductQuantities.get(i) * cartProductPrices.get(i);
                printWriter.printf("  %-30s x%-3d @ PHP %-6.2f = PHP %-8.2f\n",
                        cartProductNames.get(i),
                        cartProductQuantities.get(i),
                        cartProductPrices.get(i),
                        itemTotal);
            }

            printWriter.println("--------------------------------------------------------");
            printWriter.printf("Total Amount: PHP %.2f\n", totalPrice);
            printWriter.printf("Payment:      PHP %.2f\n", payment);
            printWriter.printf("Change:       PHP %.2f\n", change);
            printWriter.println("========================================================\n");

            printWriter.close();
            writer.close();

        } catch (IOException e) {
            System.out.println("Warning: Could not log transaction to file.");
        }
    }

    public static void logout() {
        loggedInUser = "";
        cartProductNames.clear();
        cartProductQuantities.clear();
        cartProductPrices.clear();

        System.out.println("You have successfully logged out.");
    }
}

