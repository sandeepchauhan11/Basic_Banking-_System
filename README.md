# Basic_Banking-_System
Description
This project is a console-based Java application that simulates a fundamental banking environment. It allows users to manage a savings account by performing standard financial transactions such as depositing funds, withdrawing money, and checking their balance. The application also tracks and displays a complete transaction history for the user's session.

Key Features

Account Management: Users can initialize a custom savings account.

Financial Transactions: Securely deposit and withdraw funds.

Transaction History: Automatically logs all account activities (deposits, withdrawals, and interest applications) with updated balances.

Interest Application: Includes a specialized feature to calculate and apply a 5% interest rate to the savings account.

Robust Error Handling: Prevents invalid inputs (e.g., typing text instead of numbers) and stops users from withdrawing more money than they have available.

Core Java Concepts Demonstrated

Object-Oriented Programming (OOP): Utilizes classes to encapsulate account data.

Inheritance: Implements a base BankAccount class and an extended SavingsAccount class to demonstrate code reuse and specialization.

Exception Handling: Features a custom exception (InsufficientFundsException) to enforce banking rules safely without crashing the program.

Data Structures: Uses Java's ArrayList to dynamically store and manage the transaction history.

How to Run

Ensure you have the Java Development Kit (JDK) installed.

Open your terminal or command prompt.

Navigate to the folder containing the file.

Compile the code using: javac BankingSystemApp.java

Run the application using: java BankingSystemApp
