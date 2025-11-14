import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// Transaction class to represent individual transactions
class Transaction {
    private String type;
    private double amount;
    private LocalDateTime timestamp;
    private double balanceAfter;
    
    public Transaction(String type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
    }
    
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public double getBalanceAfter() { return balanceAfter; }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%-10s | $%8.2f | %s | Balance: $%.2f", 
                           type, amount, timestamp.format(formatter), balanceAfter);
    }
}

// Account class to manage account operations
class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private List<Transaction> transactionHistory;
    private static final String BALANCE_FILE = "balance.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";
    
    public Account(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.transactionHistory = new ArrayList<>();
        loadBalance();
        loadTransactionHistory();
    }
    
    // Load balance from file
    private void loadBalance() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BALANCE_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                this.balance = Double.parseDouble(line.trim());
            } else {
                this.balance = 0.0;
            }
        } catch (FileNotFoundException e) {
            this.balance = 0.0; // Default balance if file doesn't exist
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading balance. Starting with $0.00");
            this.balance = 0.0;
        }
    }
    
    // Save balance to file
    private void saveBalance() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BALANCE_FILE))) {
            writer.printf("%.2f", balance);
        } catch (IOException e) {
            System.out.println("Error saving balance to file.");
        }
    }
    
    // Load transaction history from file
    private void loadTransactionHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse transaction line (simplified format)
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String type = parts[0].trim();
                    double amount = Double.parseDouble(parts[1].trim().replace("$", ""));
                    double balanceAfter = Double.parseDouble(parts[3].trim().split(":")[1].trim().replace("$", ""));
                    
                    Transaction transaction = new Transaction(type, amount, balanceAfter);
                    transactionHistory.add(transaction);
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, which is fine
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading transaction history.");
        }
    }
    
    // Save transaction history to file
    private void saveTransactionHistory() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTIONS_FILE))) {
            for (Transaction transaction : transactionHistory) {
                writer.println(transaction.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving transaction history.");
        }
    }
    
    // Deposit money
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return false;
        }
        
        balance += amount;
        Transaction transaction = new Transaction("DEPOSIT", amount, balance);
        transactionHistory.add(transaction);
        
        saveBalance();
        saveTransactionHistory();
        
        System.out.printf("Successfully deposited $%.2f\n", amount);
        System.out.printf("Current balance: $%.2f\n", balance);
        return true;
    }
    
    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return false;
        }
        
        if (amount > balance) {
            System.out.printf("Insufficient funds. Current balance: $%.2f\n", balance);
            return false;
        }
        
        balance -= amount;
        Transaction transaction = new Transaction("WITHDRAW", amount, balance);
        transactionHistory.add(transaction);
        
        saveBalance();
        saveTransactionHistory();
        
        System.out.printf("Successfully withdrew $%.2f\n", amount);
        System.out.printf("Current balance: $%.2f\n", balance);
        return true;
    }
    
    // Check balance
    public double getBalance() {
        return balance;
    }
    
    // Get transaction history
    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
}

// ATM class to handle user interactions
class ATM {
    private Account account;
    private Scanner scanner;
    private boolean isRunning;
    
    public ATM() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }
    
    // Start ATM session
    public void start() {
        displayWelcome();
        authenticateUser();
        
        while (isRunning) {
            displayMenu();
            int choice = getUserChoice();
            processChoice(choice);
        }
        
        System.out.println("Thank you for using our ATM. Have a great day!");
        scanner.close();
    }
    
    // Display welcome message
    private void displayWelcome() {
        System.out.println("*".repeat(50));
        System.out.println("*" + " ".repeat(12) + "WELCOME TO MINI ATM" + " ".repeat(12) + "*");
        System.out.println("*".repeat(50));
        System.out.println();
    }
    
    // Simple authentication (for demo purposes)
    private void authenticateUser() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        System.out.print("Enter Account Holder Name: ");
        String accountHolder = scanner.nextLine();
        
        // In a real system, this would verify against a database
        account = new Account(accountNumber, accountHolder);
        
        System.out.println("\nAuthentication successful!");
        System.out.println("Welcome, " + accountHolder + "!");
        System.out.println("-".repeat(40));
    }
    
    // Display main menu
    private void displayMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("           ATM MAIN MENU");
        System.out.println("=".repeat(40));
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transaction History");
        System.out.println("5. Exit");
        System.out.println("=".repeat(40));
        System.out.print("Please select an option (1-5): ");
    }
    
    // Get user choice with input validation
    private int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= 5) {
                return choice;
            } else {
                System.out.println("Invalid choice. Please select 1-5.");
                return getUserChoice();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getUserChoice();
        }
    }
    
    // Process user choice
    private void processChoice(int choice) {
        System.out.println();
        
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                withdrawMoney();
                break;
            case 4:
                showTransactionHistory();
                break;
            case 5:
                isRunning = false;
                break;
        }
        
        if (isRunning) {
            System.out.print("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    // Check balance operation
    private void checkBalance() {
        System.out.println("-".repeat(30));
        System.out.println("      BALANCE INQUIRY");
        System.out.println("-".repeat(30));
        System.out.printf("Account: %s\n", account.getAccountNumber());
        System.out.printf("Holder: %s\n", account.getAccountHolder());
        System.out.printf("Current Balance: $%.2f\n", account.getBalance());
        System.out.println("-".repeat(30));
    }
    
    // Deposit money operation
    private void depositMoney() {
        System.out.println("-".repeat(30));
        System.out.println("       DEPOSIT MONEY");
        System.out.println("-".repeat(30));
        
        try {
            System.out.print("Enter deposit amount: $");
            double amount = Double.parseDouble(scanner.nextLine());
            account.deposit(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
        
        System.out.println("-".repeat(30));
    }
    
    // Withdraw money operation
    private void withdrawMoney() {
        System.out.println("-".repeat(30));
        System.out.println("      WITHDRAW MONEY");
        System.out.println("-".repeat(30));
        System.out.printf("Current Balance: $%.2f\n", account.getBalance());
        
        try {
            System.out.print("Enter withdrawal amount: $");
            double amount = Double.parseDouble(scanner.nextLine());
            account.withdraw(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
        
        System.out.println("-".repeat(30));
    }
    
    // Show transaction history
    private void showTransactionHistory() {
        System.out.println("-".repeat(70));
        System.out.println("                    TRANSACTION HISTORY");
        System.out.println("-".repeat(70));
        
        List<Transaction> history = account.getTransactionHistory();
        
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Type       | Amount   | Date & Time         | Balance After");
            System.out.println("-".repeat(70));
            
            // Show last 10 transactions (most recent first)
            int start = Math.max(0, history.size() - 10);
            for (int i = history.size() - 1; i >= start; i--) {
                System.out.println(history.get(i));
            }
            
            if (history.size() > 10) {
                System.out.println("\n(Showing last 10 transactions)");
            }
        }
        
        System.out.println("-".repeat(70));
    }
}

// Main class to run the ATM application
public class ATMSimulation {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
