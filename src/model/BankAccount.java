package model;

import java.util.*;

public class BankAccount {
  private int accoundId;
  private String holderName;
  private String holderAddress;
  private Date openingDate;
  private double balance;
  private TransactionQueue transactions;

  public BankAccount(int accoundId, String holderName, String holderAddress) {
    this.accoundId = accoundId;
    this.holderName = holderName;
    this.holderAddress = holderAddress;
    this.openingDate = new Date();
    this.balance = 0.0;
    this.transactions = new TransactionQueue(4);
  }

  public boolean deposit(double amount) {
    if (amount < 0) {
      return false;
    }

    this.balance += amount;
    return true;
  }

  public boolean withdraw(double amount) {
    if (amount > balance) {
      return false;
    }

    this.balance -= amount;
    return true;
  }

  public void addTransaction(Transaction t) {
    transactions.enqueue(t);
  }

  public double getBalance() {
    return balance;
  }

  public int getAccountId() {
    return accoundId;
  }

  public String getHolderName() {
    return holderName;
  }

  public String getHolderAddress() {
    return holderAddress;
  }

  public Date getOpeningDate() {
    return openingDate;
  }

  public List<Transaction> getTransactions() {
    return transactions.getAll();
  }

  @Override
  public String toString() {
    return accoundId + " | " + holderName + " | " + openingDate.toString() + " | Balance: $" + balance;
  }
}
