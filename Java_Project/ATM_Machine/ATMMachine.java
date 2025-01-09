//===================ATM Machine Application======================//

import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private float balance;
    private int pin;
    private final int MAX_ATTEMPTS = 3;
    private final float DAILY_LIMIT = 10000.0f;
    private float dailyWithdrawal = 0;
    private ArrayList<String> transactionHistory = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // ====================Constructor to initialize balance and PIN ==========================//
    public ATM(float initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
    }

    // =========================Display ATM menu ========================//
    public void displayMenu() {
        System.out.println("\n********** Main Menu **********");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Change PIN");
        System.out.println("5. View Transaction History");
        System.out.println("6. EXIT");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.print("Enter Your Choice: ");
    }

    // ===============Validate the entered PIN========================//
    public boolean validatePin() {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your PIN: ");
            int enteredPin = sc.nextInt();
            if (this.pin == enteredPin) {
                System.out.println("PIN verified successfully.");
                return true;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }
        }
        System.out.println("Too many incorrect attempts. Exiting.");
        return false;
    }

    // ===================Check account balance===========================//
    public void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
    }

    // ===================Deposit money into the account=========================//
    public void depositMoney() {
        System.out.print("Enter the amount to deposit: ");
        float amount = sc.nextFloat();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            balance += amount;
            addTransaction("Deposited: $" + amount);
            System.out.println("You have successfully deposited: $" + amount);
            System.out.println("Your updated balance is: $" + balance);
        }
    }

    // =============Withdraw money from account============//
    public void withdrawMoney() {
        System.out.print("Enter the amount to withdraw: ");
        float amount = sc.nextFloat();
        if (dailyWithdrawal + amount > DAILY_LIMIT) {
            System.out.println("Daily withdrawal limit exceeded. You can withdraw up to $" + (DAILY_LIMIT - dailyWithdrawal));
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            balance -= amount;
            dailyWithdrawal += amount;
            addTransaction("Withdrawn: $" + amount);
            System.out.println("You have successfully withdrawn: $" + amount);
            System.out.println("Your updated balance is: $" + balance);
        }
    }

    // ============Change the user PIN===============//
    public void changePin() {
        System.out.print("Enter your current PIN: ");
        int currentPin = sc.nextInt();
        if (this.pin == currentPin) {
            System.out.print("Enter your new PIN: ");
            int newPin = sc.nextInt();
            if (newPin <= 0 || newPin == currentPin) {
                System.out.println("Invalid new PIN. Try again.");
            } else {
                this.pin = newPin;
                System.out.println("PIN changed successfully!");
            }
        } else {
            System.out.println("Incorrect current PIN. Cannot change PIN.");
        }
    }

    // =============Add transaction to the history===============//
    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    // =============View transaction history========================//
    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions to display.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // =============Main ATM process==================//
    public void start() {
        if (!validatePin()) return; 

        int choice = 0;
        while (choice != 6) {
            displayMenu();
            choice = sc.nextInt();

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
                    changePin();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

public class ATMMachine {
    public static void main(String[] args) {
        ATM atm = new ATM(1000.0f, 1234);
        atm.start();
    }
}

