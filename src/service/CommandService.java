package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import model.BankAccount;
import model.Transaction;

public class CommandService {
  private static final BankService bank = new BankService();
  private static final Scanner scanner = new Scanner(System.in);

  private static String getStrInput(String message) {
    System.out.print(message);

    String value = "";
    value = scanner.nextLine();

    if (value == "") {
      System.out.println("Invalid input, got empty!");
      return null;
    }

    return value;
  }

  private static Integer getIntInput(String message) {
    System.out.print(message);
    int value;

    try {
      value = Integer.parseInt(scanner.nextLine());
    } catch (Exception e) {
      System.out.println("Invalid input, expected float!");
      return null;
    }

    return value;
  }

  private static Double getDoubleInput(String message) {
    System.out.print(message);
    double value = 0.0;

    try {
      value = Float.parseFloat(scanner.nextLine());
    } catch (Exception e) {
      System.out.println("Invalid input, expected float!");
      return null;
    }

    return value;
  }

  public static void create() {
    String name = getStrInput("Enter holder name: ");
    String address = getStrInput("Enter holder address: ");
    if (name == null || address == null) return;

    BankAccount account = bank.createAccount(name, address);
    System.out.println("Account created successfully: \n" + account);
  }

  public static void deposit() {
    Integer accountId = getIntInput("Enter account ID: ");
    Double amount = getDoubleInput("Enter amount to deposit: ");
    if (accountId == null || amount == null) return;

    boolean success = bank.deposit(accountId, amount);
    System.out.println(success ? "Deposit successful." : "Deposit failed.");
  }

  public static void withdraw() {
    Integer accountId = getIntInput("Enter account ID: ");
    Double amount = getDoubleInput("Enter amount to withdraw: ");
    if (accountId == null || amount == null) return;

    boolean success = bank.withdraw(accountId, amount);
    System.out.println(success ? "Withdrawal successful." : "Withdrawl failed.");
  }

  public static void send() {
    Integer fromAccountId = getIntInput("Enter sender account ID: ");
    Integer toAccountId = getIntInput("Enter receiver account ID: ");
    Double amount = getDoubleInput("Enter amount to send: ");
    if (fromAccountId == null || toAccountId == null || amount == null) return;

    boolean success = bank.send(fromAccountId, toAccountId, amount);
    System.out.println(success ? "Transfer successful." : "Transfer failed.");
  }

  public static void listAccounts() {
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

  public static void close() {
    Integer accountId = getIntInput("Enter account ID: ");
    boolean success = bank.closeAccount(accountId);
    if (accountId == null)
      return;

    System.out.println(success ? "Account closed." : "Unable to close account.");
  }

  public static void account() {
    Integer accountId = getIntInput("Enter account ID: ");
    BankAccount account = bank.getAccount(accountId);
    if (account == null) {
      System.out.println("Account not found.");
      return;
    }

    System.out.println("Account Details:");
    System.out.println(account);
    System.out.println("Transactions:");

    List<Transaction> transactions = new ArrayList<>(account.getTransactions());
    if (transactions.isEmpty()) {
      System.out.println("No transactions found for this account.");
      return;
    }

    for (Transaction t : transactions) {
      System.out.println(t);
    }
  }

  public static void listTransactions() {
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

  public static void searchTransactions() {
    Double amount = getDoubleInput("Enter amount to search for: ");
    if (amount == null) return;

    float amt = amount == null ? null : amount.floatValue();

    List<Transaction> transactions = bank.getAllTransactions();
    Transaction found = bank.binarySearch(transactions, amt, Transaction::getAmount);
    if (found != null) {
      System.out.println("Transaction found: " + found);
    } else {
      System.out.println("No transaction found with amount: " + amount);
    }
  }
}
