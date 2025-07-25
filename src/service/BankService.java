package service;

import model.*;

import java.util.*;
import java.util.function.Function;

public class BankService {
  private final List<BankAccount> accounts = new ArrayList<>();
  private int nextAccountId = 1000;

  public BankAccount createAccount(String holderName, String holderAddress) {
    BankAccount account = new BankAccount(nextAccountId++, holderName, holderAddress);
    accounts.add(account);
    return account;
  }

  public boolean deposit(int accountId, double amount) {
    BankAccount acc = findAccount(accountId);
    if (acc != null && acc.deposit(amount)) {
      acc.addTransaction(new Transaction(TransactionType.DEPOSIT, (float) amount, new Date(), null, accountId));
      return true;
    }
    return false;
  }

  public boolean withdraw(int accountId, double amount) {
    BankAccount acc = findAccount(accountId);
    if (acc != null && acc.withdraw(amount)) {
      acc.addTransaction(new Transaction(TransactionType.WITHDRAW, (float) amount, new Date(), accountId, null));
      return true;
    }
    return false;
  }

  public List<BankAccount> listAccounts() {
    return new ArrayList<>(accounts);
  }

  public boolean closeAccount(int accountId) {
    BankAccount account = findAccount(accountId);
    if (account != null && account.getBalance() == 0) {
      accounts.remove(account);
      return true;
    }
    return false;
  }

  public boolean send(int fromAccountId, int toAccountId, double amount) {
    BankAccount from = findAccount(fromAccountId);
    BankAccount to = findAccount(toAccountId);

    if (from != null && to != null && from.withdraw(amount)) {
      to.deposit(amount);
      Date now = new Date();
      from.addTransaction(new Transaction(TransactionType.SEND, (float) amount, now, fromAccountId, toAccountId));
      to.addTransaction(new Transaction(TransactionType.RECEIVE, (float) amount, now, fromAccountId, toAccountId));
      return true;
    }

    return false;
  }

  public List<Transaction> getAllTransactions() {
    List<Transaction> all = new ArrayList<>();
    for (BankAccount account : accounts) {
      all.addAll(account.getTransactions());
    }
    return all;
  }

  public <T, K extends Comparable<K>> T binarySearch(List<T> list, K target, Function<T, K> keyExtractor) {
    list.sort(Comparator.comparing(keyExtractor));
    int left = 0, right = list.size() - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      K midValue = keyExtractor.apply(list.get(mid));
      int cmp = midValue.compareTo(target);
      if (cmp == 0)
        return list.get(mid);
      else if (cmp < 0)
        left = mid + 1;
      else
        right = mid - 1;
    }
    return null;
  }

  public void printAccounts() {
    for (BankAccount account : accounts) {
      System.out.println(account);
    }
  }

  public BankAccount getAccount(int accountId) {
    return findAccount(accountId);
  }

  private BankAccount findAccount(int accountId) {
    for (BankAccount account : accounts) {
      if (account.getAccountId() == accountId) {
        return account;
      }
    }
    return null;
  }
}
