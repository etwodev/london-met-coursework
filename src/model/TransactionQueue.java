package model;

import java.util.*;

public class TransactionQueue {
  private final int capacity;
  private LinkedList<Transaction> transactions;

  public TransactionQueue(int capacity) {
    this.capacity = capacity;
    this.transactions = new LinkedList<Transaction>();
  }

  public void enqueue(Transaction t) {
    if (transactions.size() == capacity) {
      transactions.removeLast();
    }
    transactions.addFirst(t);
  }

  public List<Transaction> getAll() {
    return new LinkedList<Transaction>(transactions);
  }

  public int size() {
    return transactions.size();
  }

  public boolean isEmpty() {
    return transactions.isEmpty();
  }
}
