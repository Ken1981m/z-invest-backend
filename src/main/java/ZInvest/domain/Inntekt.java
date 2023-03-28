package ZInvest.domain;

public class Inntekt {
    private int id;
    private Integer leilighetId;
    private Integer inntektTypeId;
    private Double belop;
    private Integer aar;
    private Integer mnd;


    private Inntekt(Builder builder) {
        this.leilighetId = builder.leilighetId;
        this.inntektTypeId = builder.inntektTypeId;
        this.belop = builder.belop;
    }

    public static class Builder {
        private Integer leilighetId;
        private Integer inntektTypeId;
        private Double belop;
        private Integer aar;
        private Integer mnd;

        public Builder() {
        }

        public Builder leilighetId(Integer leilighetId) {
            this.leilighetId = leilighetId;
            return this;
        }

        public Builder inntektTypeId(Integer inntektTypeId) {
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
