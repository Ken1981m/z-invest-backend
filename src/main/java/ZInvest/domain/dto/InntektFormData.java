package ZInvest.domain.dto;

public class InntektFormData {
    private String leilighetId;
    private String inntektTypeId;
    private String formatertDato;
    private Integer mnd;
    private Double belop;

    public String getLeilighetId() {
        return leilighetId;
    }

    public String getInntektTypeId() {
        return inntektTypeId;
    }

    public Integer getMnd() {
        return mnd;
    }

    public String getFormatertDato() {
        return formatertDato;
    }

    public Double getBelop() {
        return belop;
    }
}
