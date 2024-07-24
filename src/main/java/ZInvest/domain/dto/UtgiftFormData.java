package ZInvest.domain.dto;

public class UtgiftFormData {
    private String leilighetId;
    private String utgiftTypeId;
    private String formatertDato;
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

    public Double getBelop() {
        return belop;
    }
}
