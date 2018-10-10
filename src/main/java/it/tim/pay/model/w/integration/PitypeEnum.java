package it.tim.pay.model.w.integration;

public enum PitypeEnum {
    CC("CC"),
    SEPA("SEPA"),
    AMZP("AMZP"),
    PPAL("PPAL"),
	PPAS("PPAS");

    private String value;

    PitypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static PitypeEnum fromValue(String text) {
      for (PitypeEnum b : PitypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
}