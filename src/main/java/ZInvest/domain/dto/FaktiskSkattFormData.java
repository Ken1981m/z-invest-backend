package ZInvest.domain.dto;

public class FaktiskSkattFormData {
    private Integer grupperingId;
    private Integer aar;
    private Long faktiskSkattBelopForUtleieUtfyltISkattemelding;
    private Long faktiskSkattBelopEtterUtleieUtfyltISkattemelding;

    public Integer getGrupperingId() {
        return grupperingId;
    }

    public Integer getAar() {
        return aar;
    }

    public Long getFaktiskSkattBelopForUtleieUtfyltISkattemelding() {
        return faktiskSkattBelopForUtleieUtfyltISkattemelding;
    }

    public Long getFaktiskSkattBelopEtterUtleieUtfyltISkattemelding() {
        return faktiskSkattBelopEtterUtleieUtfyltISkattemelding;
    }
}
