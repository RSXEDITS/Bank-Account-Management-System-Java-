# Bank-Account-Management-System-Java-
This console-based application manages bank accounts. Administrators can add, delete, and view accounts. Users can log in to view their balance, withdraw, and deposit money, with all updates reflected in a file.

The Bank Account Management System is a console-based application designed to manage bank accounts. This system allows administrators to perform tasks such as adding new accounts, deleting existing accounts, and viewing all accounts. Regular users can log in to view their balance, withdraw money, deposit money, and have their balances updated accordingly. The application ensures secure login for administrators and validates users based on the account details stored in a file.

Language Used: Java

Key Features:

Admin Login and Menu:

Admins can log in using a predefined username and password.
Admins have access to a menu where they can:
Add new accounts.
Delete existing accounts.
View all accounts.
User Login and Menu:

Users can log in by entering their name, which is validated against the stored account details.
Users have access to a menu where they can:
View their account balance.
Withdraw money from their account.
Deposit money into their account.
File Handling:

Account details are stored in a file (accountDetail.txt).
The system reads from and writes to this file to update account information, ensuring data persistence.
Technologies Used:

Java I/O: For reading from and writing to files to manage account details.
Java Collections (ArrayList): To store account details in memory during program execution.
Java Util: For utility classes like Scanner for input handling.
Java Logging: For logging error messages and other significant events.

This project demonstrates the use of core Java features, including object-oriented programming principles, file handling, and basic user input validation. It is a practical example of how to manage simple banking operations through a console-based interface.


*Project is in bankGPT/src/bankGPT*   
<br>*See class diagram to understand the project*
