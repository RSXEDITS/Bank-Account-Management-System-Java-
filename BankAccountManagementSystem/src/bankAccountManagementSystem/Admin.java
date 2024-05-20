package bankAccountManagementSystem;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;

class Admin {

	Scanner scan = new Scanner(System.in);
	private final File accountDetails = new File ("accountDetail.txt");
	
	
	int choice;
	private String name;
    private String accountNumber;
    private String balance;
	
    ArrayList<Account> account = new ArrayList<>();
    ArrayList<Account> order = new ArrayList<>();
    
    private String username;
    private String password;

    public Admin() {
        this.username = "anas";
        this.password = "anas12";
    }


	//Admin Login
    public boolean LogIn() {
    	System.out.println(" ");
        System.out.print("Enter username: ");
        username = scan.nextLine();

        if (username.equals("anas")) {
            System.out.print("Enter password: ");
            password = scan.nextLine();

            if (password.equals("anas12")) {
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
	
	
	//Admin Menu 
	public void AdminMenu() {
	
	do{
		System.out.println("Admin Menu");
		System.out.println("1) Add Account");
		System.out.println("2) Remove Account");
		System.out.println("3) View All Accounts");
		System.out.println("4) View Transections");
		System.out.println("5) Exit");
		System.out.print("Enter Your Choice: ");		
		choice = scan.nextInt();
		
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
				
			break;
			
			case 5:
				System.out.println(" ");
				System.out.println("Exiting");
				System.out.println(" ");
			break;
			
			default:
			System.out.println(" ");
			System.out.println("Invalid Choice");
			System.out.println(" ");
			
		}
			} while( choice != 5);
	}
	
	
	//Admin Methods
	
	void addAccount() {
        
        System.out.println(" ");
        System.out.print("Enter the name of the Customer: ");
        name = scan.next();
        System.out.print("Enter the Account Number: ");
        accountNumber = scan.next();
        System.out.print("Enter the Balance: ");
        balance = scan.next();
		System.out.println("Account Created Successfully!");
		
        account.add(new Account(name, accountNumber ,balance));
        saveAccountToFile(account);
        
    }
    
     void deleteAccount() {
        ArrayList<Account> accountsDetails= readAccountsFromFile();
        if (accountsDetails.isEmpty()) {
            System.out.println("No Account Available");
        } else {
            System.out.print("Enter cuisine name you want to delete: ");
            String accountToDelete = scan.next();
            boolean found = false;

            for (Account account : accountsDetails) {
                if (account.getName().equalsIgnoreCase(accountToDelete)) {
                    accountsDetails.remove(account);
                    saveAccountToFile(accountsDetails);
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
    
    void viewAccounts() {
        ArrayList<Account> accountsDetails= readAccountsFromFile();
        if (accountsDetails.isEmpty()) {
            System.out.println("No Account Available");
        } else {
            for (Account account: accountsDetails) {
                System.out.println(account);
            }
        }
    }
    
	
    //File Handling
    void saveAccountToFile(ArrayList<Account> account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accountDetail.txt"))) {
            for (Account x : account) {
                writer.write(x.getName() + "," + x.getaccountNumber()+ "," + x.getBalance());
                writer.newLine();
            }
            System.out.println("Account saved to file successfully.");
        } catch (IOException ex) {
   
        }
    }
    
 
	ArrayList<Account> readAccountsFromFile() {
        ArrayList<Account> accountsDetails = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("accountDetail.txt"))) {
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
        
        }
        return accountsDetails;
    }
	
	
}
