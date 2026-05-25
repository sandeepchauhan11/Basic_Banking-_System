import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}


class BankAccount {

    protected String accountHolder;
    protected double balance;
    protected ArrayList<String> transactionHistory;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        recordTransaction("Account created with initial balance: $" + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            recordTransaction("Deposited: $" + amount + " | New Balance: $" + balance);
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Error: Deposit amount must be greater than zero.");
        }
    }

    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
        } else if (amount > balance) {
            throw new InsufficientFundsException("Cannot withdraw $" + amount + ". Current balance is only $" + balance);
        } else {
            balance -= amount;
            recordTransaction("Withdrew: $" + amount + " | New Balance: $" + balance);
            System.out.println("Successfully withdrew $" + amount);
        }
    }

    public void displayBalance() {
        System.out.println("Account Holder: " + accountHolder + " | Current Balance: $" + balance);
    }

    public void displayTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }


    protected void recordTransaction(String details) {
        transactionHistory.add(details);
    }
}

// SavingsAccount 
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountHolder, double initialBalance, double interestRate) {
        // 'super' calls the constructor of the parent BankAccount class
        super(accountHolder, initialBalance);
        this.interestRate = interestRate;
    }

    
    public void applyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        recordTransaction("Interest Applied (" + interestRate + "%): +$" + interest + " | New Balance: $" + balance);
        System.out.println("Interest of $" + interest + " applied successfully.");
    }
}

// --- Main Application ---
public class BankingSystemApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Java Banking System!");
        System.out.print("Enter account holder's name to open a Savings Account: ");
        String name = scanner.nextLine();
        
        // Create a SavingsAccount object with an initial deposit of $0 and an interest rate of 5%
        SavingsAccount myAccount = new SavingsAccount(name, 0.0, 5.0);
        
        boolean keepRunning = true;

        while (keepRunning) {
            displayMenu();

            try {
                
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: $");
                        double depAmount = Double.parseDouble(scanner.nextLine());
                        myAccount.deposit(depAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: $");
                        double withAmount = Double.parseDouble(scanner.nextLine());
                        myAccount.withdraw(withAmount);
                        break;
                    case 3:
                        myAccount.displayBalance();
                        break;
                    case 4:
                        myAccount.displayTransactionHistory();
                        break;
                    case 5:
                        myAccount.applyInterest();
                        break;
                    case 6:
                        System.out.println("Thank you for banking with us. Goodbye!");
                        keepRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                
                System.out.println("Error: Invalid input. Please enter numerical values.");
            } catch (InsufficientFundsException e) {
                
                System.out.println("Transaction Failed: " + e.getMessage());
            }
        }
        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("\n--- Banking Menu ---");
        System.out.println("1. Deposit Funds");
        System.out.println("2. Withdraw Funds");
        System.out.println("3. Check Balance");
        System.out.println("4. View Transaction History");
        System.out.println("5. Apply Savings Interest");
        System.out.println("6. Exit");
        System.out.print("Select an option (1-6): ");
    }
}