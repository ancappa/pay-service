package it.tim.pay.model.p.integration;

public enum TxOpEnum {
    VERIFICATION("VERIFICATION"),
    RESERVATION("RESERVATION"),
    SALE("SALE"),
    TOKENIZATION("TOKENIZATION");

    private String value;

    TxOpEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TxOpEnum fromValue(String text) {
      for (TxOpEnum b : TxOpEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
}
