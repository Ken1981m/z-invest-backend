package ZInvest.domain;

public class Inntekt {
    private int id;
    private int leilighetId;
    private Leilighet leilighet;
    private int inntektTypeId;
    private InntektType inntektType;
    private Double belop;
    private Integer aar;
    private Integer mnd;

    public int getId() {
        return id;
    }

    public int getLeilighetId() {
        return leilighetId;
    }

    public int getInntektTypeId() {
        return inntektTypeId;
    }

    public Leilighet getLeilighet() {
        return leilighet;
    }

    public void setLeilighet(Leilighet leilighet) {
        this.leilighet = leilighet;
    }

    public InntektType getInntektType() {
        return inntektType;
    }

    public void setInntektType(InntektType inntektType) {
        this.inntektType = inntektType;
    }

    public Double getBelop() {
        return belop;
    }

    public Integer getAar() {
        return aar;
    }

    public Integer getMnd() {
        return mnd;
    }

    private Inntekt(Builder builder) {
        this.leilighetId = builder.leilighetId;
        this.inntektTypeId = builder.inntektTypeId;
        this.belop = builder.belop;
        this.aar = builder.aar;
        this.mnd = builder.mnd;
    }

    public static class Builder {
        private int id;
        private int leilighetId;
        private int inntektTypeId;
        private Double belop;
        private Integer aar;
        private Integer mnd;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder leilighetId(int leilighetId) {
            this.leilighetId = leilighetId;
            return this;
        }

        public Builder inntektTypeId(int inntektTypeId) {
            this.inntektTypeId = inntektTypeId;
            return this;
        }

        public Builder belop(Double belop) {
            this.belop = belop;
            return this;
        }

        public Builder aar(Integer aar) {
            this.aar = aar;
            return this;
        }

        public Builder mnd(Integer mnd) {
            this.mnd = mnd;
            return this;
        }

        public Inntekt build() {
            return new Inntekt(this);
        }
    }

}
