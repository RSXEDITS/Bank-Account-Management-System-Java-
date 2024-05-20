package bankAccountManagementSystem;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		int choice;
		
		Scanner scan = new Scanner(System.in);
		Admin admin = new Admin();
		
		do {
			System.out.println("Choose:");
			System.out.println("1) Admin:");
			System.out.println("2) User:");
			System.out.println("3) Exit");
			System.out.print("Enter Your Choice: ");
			choice = scan.nextInt();
	
	
			switch(choice) {
			case 1:
				admin.LogIn();
				admin.AdminMenu();
			break;
		
			case 2:
				System.out.println("user");
			break;
		
			case 3:
				System.out.println("Thank You");
			break;
		
			default:
				System.out.println(" ");
				System.out.println("Invalid Input");
				System.out.println(" ");
	
	}
		} while(choice != 3);
		
	}
	
}
