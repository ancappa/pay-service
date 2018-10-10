package it.tim.pay.model.w.integration;

public enum StatusEnumPiStatus {
    PENDING_SUBMISSION("PENDING_SUBMISSION"),   
    WAITING_CONFIRM_SUBMISSION("WAITING_CONFIRM_SUBMISSION"),    
    ACTIVE("ACTIVE"),   
    INACTIVE("INACTIVE"),   
    PENDING_REVOKE("PENDING_REVOKE"),    
    WAITING_CONFIRM_REVOKE("WAITING_CONFIRM_REVOKE");

    private String value;

    StatusEnumPiStatus(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnumPiStatus fromValue(String text) {
      for (StatusEnumPiStatus b : StatusEnumPiStatus.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
}