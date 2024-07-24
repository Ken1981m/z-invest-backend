package ZInvest.domain.dto;

public enum ConstantsMap {

    SUM_BRUTTO_INNTEKT("Sum brutto inntekt"),
    ESTIMERT_SKATT("Estimert skatt"),
    FAKTISK_SKATT("Faktisk skatt"),
    ESTIMERT_NETTO_INNTEKT("Estimert netto inntekt"),
    FAKTISK_NETTO_INNTEKT("Faktisk netto inntekt");

    private final String string;

    ConstantsMap(String string) {
        this.string = string;
    }


    public String getString() {
        return string;
    }


}
