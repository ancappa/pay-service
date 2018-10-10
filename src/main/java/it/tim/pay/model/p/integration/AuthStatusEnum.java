package it.tim.pay.model.p.integration;

public enum AuthStatusEnum {
    Y("Y"),    
    N("N"),
    U("U"),
    A("A");

    private String value;

    AuthStatusEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static AuthStatusEnum fromValue(String text) {
      for (AuthStatusEnum b : AuthStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
}
