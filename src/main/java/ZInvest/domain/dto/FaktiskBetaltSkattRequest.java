package ZInvest.domain.dto;


public class FaktiskBetaltSkattRequest {
    private int id;
    private Integer aar;
    private Long faktiskSkattBelopForUtleieUtfyltISkattemelding;
    private Long faktiskSkattBelopEtterUtleieUtfyltISkattemelding;

    public int getId() {
        return id;
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

    private FaktiskBetaltSkattRequest(Builder builder) {
        this.id = builder.id;
        this.aar = builder.aar;
        this.faktiskSkattBelopForUtleieUtfyltISkattemelding = builder.faktiskSkattBelopForUtleieUtfyltISkattemelding;
        this.faktiskSkattBelopEtterUtleieUtfyltISkattemelding = builder.faktiskSkattBelopEtterUtleieUtfyltISkattemelding;
    }

    public static class Builder {
        private int id;
        private Integer grupperingId;
        private Integer aar;
        private Long faktiskSkattBelopForUtleieUtfyltISkattemelding;
        private Long faktiskSkattBelopEtterUtleieUtfyltISkattemelding;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder aar(Integer aar) {
            this.aar = aar;
            return this;
        }

        public Builder faktiskSkattBelopForUtleieUtfyltISkattemelding(Long faktiskSkattBelopForUtleieUtfyltISkattemelding) {
            this.faktiskSkattBelopForUtleieUtfyltISkattemelding = faktiskSkattBelopForUtleieUtfyltISkattemelding;
            return this;
        }

        public Builder faktiskSkattBelopEtterUtleieUtfyltISkattemelding(Long faktiskSkattBelopEtterUtleieUtfyltISkattemelding) {
            this.faktiskSkattBelopEtterUtleieUtfyltISkattemelding = faktiskSkattBelopEtterUtleieUtfyltISkattemelding;
            return this;
        }

        public FaktiskBetaltSkattRequest build() {
            return new FaktiskBetaltSkattRequest(this);
        }
    }
}
