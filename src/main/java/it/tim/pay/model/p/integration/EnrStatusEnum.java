package it.tim.pay.model.p.integration;

public enum EnrStatusEnum {
    Y("Y"), 
    N("N"),
    U("U");

    private String value;

    EnrStatusEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static EnrStatusEnum fromValue(String text) {
      for (EnrStatusEnum b : EnrStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
}