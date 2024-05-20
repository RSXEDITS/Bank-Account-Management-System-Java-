package bankAccountManagementSystem;

public class User {
	
	String name;
	
	 public boolean LogIn() {
	    	System.out.println(" ");
	        System.out.print("Enter username: ");
	        username = scan.nextLine();

	        if (username.equals("")) {
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
	
	
}
