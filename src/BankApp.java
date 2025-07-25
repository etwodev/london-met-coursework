import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import service.BankService;
import model.BankAccount;
import model.Transaction;

public class BankApp {
  private static final Scanner scanner = new Scanner(System.in);
  private static final BankService bank = new BankService();

  public static void main(String[] args) {
    while (true) {
      System.out.println("=== Main Menu ===");
      System.out.println("1. Manage Accounts");
      System.out.println("2. View Transactions");
      System.out.println("3. Exit");
      System.out.print("Choose an option: ");

      int choice;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
        continue;
      }

      switch (choice) {
        case 1:
          manageAccountsMenu();
          break;
        case 2:
          viewTransactionsMenu();
          break;
        case 3:
          System.out.println("Exiting application.");
          return;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }

  private static void manageAccountsMenu() {
    while (true) {
      System.out.println("=== Manage Accounts ===");
      System.out.println("1. Create Account");
      System.out.println("2. Deposit");
      System.out.println("3. Withdraw");
      System.out.println("4. Send Money");
      System.out.println("5. List Accounts");
      System.out.println("6. Close Account");
      System.out.println("7. List account details");
      System.out.println("8. Back to Main Menu");
      System.out.print("Choose an option: ");

      int choice;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
        continue;
      }

      switch (choice) {
        case 1:
          createAccount();
          break;
        case 2:
          deposit();
          break;
        case 3:
          withdraw();
          break;
        case 4:
          sendMoney();
          break;
        case 5:
          listAccounts();
          break;
        case 6:
          closeAccount();
          break;
        case 7:
          getAccountDetails();
          break;
        case 8:
          return;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }

  private static void getAccountDetails() {
    System.out.print("Enter account ID: ");
    int accountId;
    try {
      accountId = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid account ID.");
      return;
    }

    BankAccount account = bank.getAccount(accountId);
    if (account == null) {
      System.out.println("Account not found.");
      return;
    }

    System.out.println("Account Details:");
    System.out.println(account);
    List<Transaction> transactions = account.getTransactions();
    if (transactions.isEmpty()) {
      System.out.println("No transactions found for this account.");
    } else {
      System.out.println("Transactions:");
      for (Transaction t : transactions) {
        System.out.println(t);
      }
    }
  }

  private static void createAccount() {
    System.out.print("Enter holder name: ");
    String name = scanner.nextLine();
    System.out.print("Enter holder address: ");
    String address = scanner.nextLine();

    BankAccount account = bank.createAccount(name, address);
    System.out.println("Account created successfully: \n" + account);
  }

  private static void listAccounts() {
    List<BankAccount> accounts = bank.listAccounts();
    if (accounts.isEmpty()) {
      System.out.println("No accounts found.");
      return;
    }
    System.out.println("Existing Accounts:");
    for (BankAccount acc : accounts) {
      System.out.println(acc);
    }
  }

  private static void closeAccount() {
    System.out.print("Enter account ID to close: ");
    int accountId;
    try {
      accountId = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid account ID.");
      return;
    }

    boolean success = bank.closeAccount(accountId);
    if (success) {
      System.out.println("Account " + accountId + " closed successfully.");
    } else {
      System.out.println("Failed to close account. Ensure the account exists and balance is zero.");
    }
  }

  private static void deposit() {
    System.out.print("Enter account ID: ");
    int accountId = Integer.parseInt(scanner.nextLine());
    System.out.print("Enter amount to deposit: ");
    double amount = Double.parseDouble(scanner.nextLine());

    boolean success = bank.deposit(accountId, amount);
    System.out.println(success ? "Deposit successful." : "Deposit failed.");
  }

  private static void withdraw() {
    System.out.print("Enter account ID: ");
    int accountId = Integer.parseInt(scanner.nextLine());
    System.out.print("Enter amount to withdraw: ");
    double amount = Double.parseDouble(scanner.nextLine());

    boolean success = bank.withdraw(accountId, amount);
    System.out.println(success ? "Withdrawal successful." : "Withdrawl failed.");
  }

  private static void sendMoney() {
    System.out.print("Enter sender account ID: ");
    int fromAccountId = Integer.parseInt(scanner.nextLine());
    System.out.print("Enter receiver account ID: ");
    int toAccountId = Integer.parseInt(scanner.nextLine());
    System.out.print("Enter amount to send: ");
    double amount = Double.parseDouble(scanner.nextLine());

    boolean success = bank.send(fromAccountId, toAccountId, amount);
    System.out.println(success ? "Transfer successful." : "Transfer failed.");
  }

  private static void viewTransactionsMenu() {
    while (true) {
      System.out.println("\n=== View Transactions ===");
      System.out.println("1. List All Transactions");
      System.out.println("2. Search Transaction by Amount");
      System.out.println("3. Back to Main Menu");
      System.out.print("Choose an option: ");

      int choice;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
        continue;
      }

      switch (choice) {
        case 1:
          listAllTransactions();
          break;
        case 2:
          searchTransactionByAmount();
          break;
        case 3:
          return;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }

  private static void listAllTransactions() {
    List<Transaction> transactions = bank.getAllTransactions();
    if (transactions.isEmpty()) {
      System.out.println("No transactions found.");
      return;
    }

    transactions.sort(Comparator.comparingDouble(t -> t.getAmount()));

    System.out.println("Transactions sorted by amount:");
    for (Transaction t : transactions) {
      System.out.println(t);
    }
  }

  private static void searchTransactionByAmount() {
    System.out.print("Enter amount to search: ");
    float amount;
    try {
      amount = Float.parseFloat(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid amount entered.");
      return;
    }

    List<Transaction> transactions = bank.getAllTransactions();
    Transaction found = bank.binarySearch(transactions, amount, Transaction::getAmount);
    if (found != null) {
      System.out.println("Transaction found: " + found);
    } else {
      System.out.println("No transaction found with amount: " + amount);
    }
  }
}
