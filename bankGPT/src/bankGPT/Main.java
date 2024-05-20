package bankGPT;

import java.util.Scanner;

public class Main {
	
	 public static void main(String[] args) {
	        Scanner scan = new Scanner(System.in);
	        Admin admin = new Admin();
	        User user = new User();
	        int choice;
	        
	       do {
	        System.out.println("1) Admin Login");
	        System.out.println("2) User Login");
	        System.out.println("3) Exit");
	        System.out.print("Enter your choice: ");
	        choice = scan.nextInt();
	        scan.nextLine(); // consume the newline character

	        if (choice == 1) {
	            if (admin.logIn()) {
	                admin.adminMenu();
	            }
	        } else if (choice == 2) {
	            user.userMenu();
	        } else if(choice == 3){
	            System.out.println("Exiting");
	        }else {
	            System.out.println("Invalid Input");
	        }
	       } while(choice != 3);
	 }   
   
}
