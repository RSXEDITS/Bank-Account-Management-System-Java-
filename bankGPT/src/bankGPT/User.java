package bankGPT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class User {
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());
    private static final Scanner SCAN = new Scanner(System.in);
    private final File accountDetailsFile = new File("accountDetail.txt");

    private Account currentUser;

    public void userMenu() {
        System.out.print("Enter your name: ");
        String name = SCAN.nextLine();
        ArrayList<Account> accounts = readAccountsFromFile();
        
        boolean userFound = false;
        for (Account account : accounts) {
            if (account.getName().equalsIgnoreCase(name)) {
                currentUser = account;
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            System.out.println("Invalid user!");
            return;
        }

        int choice;
        do {
            System.out.println("User Menu");
            System.out.println("1) View Balance");
            System.out.println("2) Withdraw Money");
            System.out.println("3) Deposit Money");
            System.out.println("4) Exit");
            System.out.print("Enter Your Choice: ");
            while (!SCAN.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                SCAN.next();
            }
            choice = SCAN.nextInt();
            SCAN.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    viewBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (choice != 4);
    }

    private void viewBalance() {
        System.out.println("Current Balance: Rs " + currentUser.getBalance());
    }

    private void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");
        while (!SCAN.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a number: ");
            SCAN.next();
        }
        double amount = SCAN.nextDouble();
        SCAN.nextLine(); // consume the newline character

        double currentBalance = Double.parseDouble(currentUser.getBalance());
        if (amount > currentBalance) {
            System.out.println("Insufficient balance!");
        } else {
            currentBalance -= amount;
            currentUser.setBalance(String.valueOf(currentBalance));
            updateAccountInFile(currentUser);
            System.out.println("Withdrawal successful. New Balance: Rs " + currentBalance);
        }
    }

    private void depositMoney() {
        System.out.print("Enter amount to deposit: ");
        while (!SCAN.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a number: ");
            SCAN.next();
        }
        double amount = SCAN.nextDouble();
        SCAN.nextLine(); // consume the newline character

        double currentBalance = Double.parseDouble(currentUser.getBalance());
        currentBalance += amount;
        currentUser.setBalance(String.valueOf(currentBalance));
        updateAccountInFile(currentUser);
        System.out.println("Deposit successful. New Balance: Rs " + currentBalance);
    }

    private void updateAccountInFile(Account updatedAccount) {
        ArrayList<Account> accounts = readAccountsFromFile();
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(updatedAccount.getAccountNumber())) {
                account.setBalance(updatedAccount.getBalance());
                break;
            }
        }
        saveAccountsToFile(accounts);
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
}
