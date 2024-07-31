package ZInvest.domain.dto;

public enum ConstantsMap {

    SUM_BRUTTO_INNTEKT("Sum brutto inntekt (føres i skattemeldingen)"),
    SUM_BRUTTO_INNTEKT_MINUS_ALLE_UTGIFTER("Sum brutto inntekt (etter trekk av alle utgifter)"),
    ESTIMERT_SKATT("Estimert skatt"),
    FAKTISK_SKATT("Faktisk skatt"),
    ESTIMERT_NETTO_INNTEKT("Estimert netto inntekt (brutto inntekt trukket fra alle utgifter og estimert skatt)"),
    FAKTISK_NETTO_INNTEKT("Faktisk netto inntekt");

    private final String string;

    ConstantsMap(String string) {
        this.string = string;
    }


    public String getString() {
        return string;
    }


}
