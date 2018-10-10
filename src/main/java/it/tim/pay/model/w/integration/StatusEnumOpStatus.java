package it.tim.pay.model.w.integration;


public enum StatusEnumOpStatus {
    INSERTED("INSERTED"),   
    UPDATED("UPDATED"),  
    REJECTED("REJECTED");

    private String value;

    StatusEnumOpStatus(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnumOpStatus fromValue(String text) {
      for (StatusEnumOpStatus b : StatusEnumOpStatus.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
}