package ZInvest.domain.dto;

public class UtgiftFormData {
    private Integer id;
    private String leilighetId;
    private String utgiftTypeId;
    private String formatertDato;
    private Integer mnd;
    private Double belop;
    private String beskrivelse;

    public Integer getId() {
        return id;
    }

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

    public String getBeskrivelse() {
        return beskrivelse;
    }
}
