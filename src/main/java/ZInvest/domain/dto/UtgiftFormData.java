package ZInvest.domain.dto;

public class UtgiftFormData {
    private String leilighetId;
    private String utgiftTypeId;
    private String formatertDato;
    private Integer mnd;
    private Double belop;

    public String getLeilighetId() {
        return leilighetId;
    }

    public String getUtgiftTypeId() {
        return utgiftTypeId;
    }

    public String getFormatertDato() {
        return formatertDato;
    }

    public Integer getMnd() {
        return mnd;
    }

    public Double getBelop() {
        return belop;
    }
}
