package model;

import java.util.Date;

public class Transaction {
  private TransactionType type;
  private float amount;
  private Date date;
  private Integer fromAccountId;
  private Integer toAccountId;

  public Transaction(TransactionType type, float amount, Date date, Integer fromAccountId, Integer toAccountId) {
    this.type = type;
    this.amount = amount;
    this.date = date;
    this.fromAccountId = fromAccountId;
    this.toAccountId = toAccountId;
  }

  public float getAmount() {
    return amount;
  }

  public Integer getFromAccountId() {
    return fromAccountId;
  }

  public Integer getToAccountId() {
    return toAccountId;
  }

  public TransactionType getType() {
    return type;
  }

  public Date getDate() {
    return date;
  }

  @Override
  public String toString() {
    return type + " | Date: " + date + " | Amount: $" + amount +
        (fromAccountId != null ? " | From: " + fromAccountId : "") +
        (toAccountId != null ? " | To: " + toAccountId : "");
  }
}
