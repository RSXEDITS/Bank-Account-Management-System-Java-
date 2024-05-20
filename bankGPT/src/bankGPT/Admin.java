package bankGPT;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Admin {
    private static final Logger LOGGER = Logger.getLogger(Admin.class.getName());
    private static final Scanner SCAN = new Scanner(System.in);
    private final File accountDetailsFile = new File("accountDetail.txt");

    private static final String DEFAULT_USERNAME = "anas";
    private static final String DEFAULT_PASSWORD = "anas12";
    private String username;
    private String password;
    
    private int choice;
    private String name;
    private String accountNumber;
    private String balance;

    private final ArrayList<Account> accounts = new ArrayList<>();

    public Admin() {
        this.username = DEFAULT_USERNAME;
        this.password = DEFAULT_PASSWORD;
    }

    // Admin Login
    public boolean logIn() {
        System.out.println(" ");
        System.out.print("Enter username: ");
        String inputUsername = SCAN.nextLine();

        if (DEFAULT_USERNAME.equals(inputUsername)) {
            System.out.print("Enter password: ");
            String inputPassword = SCAN.nextLine();

            if (DEFAULT_PASSWORD.equals(inputPassword)) {
                System.out.println(" ");
                System.out.println("Login successful!");
                System.out.println(" ");
                return true;
            } else {
                System.out.println(" ");
                System.out.println("Incorrect password!");
                System.out.println(" ");
                return false;
            }
        } else {
            System.out.println(" ");
            System.out.println("User not found!");
            System.out.println(" ");
            return false;
        }
    }

    // Admin Menu 
    public void adminMenu() {
        do {
            System.out.println("Admin Menu");
            System.out.println("1) Add Account");
            System.out.println("2) Remove Account");
            System.out.println("3) View All Accounts");
            System.out.println("4) Exit");
            System.out.print("Enter Your Choice: ");        
            while (!SCAN.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                SCAN.next();
            }
            choice = SCAN.nextInt();
            SCAN.nextLine(); // consume the newline character

            switch(choice) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    deleteAccount();
                    break;
                case 3:
                    viewAccounts();
                    break;
                case 4:
                    System.out.println(" ");
                    System.out.println("Exiting");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println(" ");
                    System.out.println("Invalid Choice");
                    System.out.println(" ");
            }
        } while (choice != 4);
    }

    // Admin Methods
    private void addAccount() {
        System.out.println(" ");
        System.out.print("Enter the name of the Customer: ");
        name = SCAN.nextLine();
        System.out.print("Enter the Account Number: ");
        accountNumber = SCAN.nextLine();
        System.out.print("Enter the Balance: ");
        balance = SCAN.nextLine();

        accounts.add(new Account(name, accountNumber, balance));
        saveAccountsToFile(accounts);
        System.out.println("Account Created Successfully!");
    }

    private void deleteAccount() {
        ArrayList<Account> accountsDetails = readAccountsFromFile();
        if (accountsDetails.isEmpty()) {
            System.out.println("No Account Available");
        } else {
            System.out.print("Enter the name of the account to delete: ");
            String accountToDelete = SCAN.nextLine();
            boolean found = false;

            for (Account account : accountsDetails) {
                if (account.getName().equalsIgnoreCase(accountToDelete)) {
                    accountsDetails.remove(account);
                    saveAccountsToFile(accountsDetails);
                    System.out.println("Account deleted successfully.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Account not found!");
            }
        }
    }

    private void viewAccounts() {
        ArrayList<Account> accountsDetails = readAccountsFromFile();
        if (accountsDetails.isEmpty()) {
            System.out.println("No Account Available");
        } else {
            for (Account account : accountsDetails) {
                System.out.println(account);
            }
        }
    }

    // File Handling
    private void saveAccountsToFile(ArrayList<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(accountDetailsFile))) {
            for (Account account : accounts) {
                writer.write(account.getName() + "," + account.getAccountNumber() + "," + account.getBalance());
                writer.newLine();
            }
            System.out.println("Account saved to file successfully.");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error saving accounts to file", ex);
        }
    }

    private ArrayList<Account> readAccountsFromFile() {
        ArrayList<Account> accountsDetails = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(accountDetailsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String accountNumber = parts[1];
                    String balance = parts[2];
                    accountsDetails.add(new Account(name, accountNumber, balance));
                }
            }
            System.out.println("Accounts Details loaded from file successfully.");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error reading accounts from file", ex);
        }
        return accountsDetails;
    }
}


