package model;

public enum TransactionType {
  WITHDRAW("withdraw"),
  DEPOSIT("deposit"),
  SEND("send"),
  REQUEST("request"),
  RECEIVE("receive");

  private final String value;

  TransactionType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static TransactionType fromString(String text) {
    for (TransactionType type : TransactionType.values()) {
      if (type.value.equalsIgnoreCase(text)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Invalid transaction type: " + text);
  }

  @Override
  public String toString() {
    return value;
  }
}
