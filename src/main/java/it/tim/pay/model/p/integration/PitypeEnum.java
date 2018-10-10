package it.tim.pay.model.p.integration;

public enum PitypeEnum {
    CC("CC"),   
    SFRT("SFRT"),   
    AMZP("AMZP"),  
    PPAL("PPAL"),   
    PPOI("PPOI"),   
    SPAY("SPAY"),  
    AIDA("AIDA"),   
    ALIP("ALIP"),  
    PPAS("PPAS"),    
    UCSC("UCSC"),  
    MYBK("MYBK"),    
    UCPO("UCPO"),   
    APAY("APAY");

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
