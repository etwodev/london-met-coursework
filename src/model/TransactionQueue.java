package model;

import java.util.*;

public class TransactionQueue {
  private final int capacity;
  private final List<Transaction> transactions;

  public TransactionQueue(int capacity) {
    this.capacity = capacity;
    this.transactions = new ArrayList<>();
  }

  public void enqueue(Transaction t) {
    if (transactions.size() == capacity) {
      transactions.remove(transactions.size() - 1);
    }
    transactions.add(0, t);
  }

  public List<Transaction> getAll() {
    return new ArrayList<>(transactions);
  }

  public int size() {
    return transactions.size();
  }

  public boolean isEmpty() {
    return transactions.isEmpty();
  }
}
