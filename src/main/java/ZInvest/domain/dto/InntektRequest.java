package ZInvest.domain.dto;

import ZInvest.domain.InntektType;
import ZInvest.domain.Leilighet;

public class InntektRequest {
    private Leilighet leilighet;
    private InntektType inntektType;
    private Double belop;
    private Integer aar;
    private Integer mnd;

    public Leilighet getLeilighet() {
        return leilighet;
    }

    public InntektType getInntektType() {
        return inntektType;
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

    private InntektRequest(Builder builder) {
        this.leilighet = builder.leilighet;
        this.inntektType = builder.inntektType;
        this.belop = builder.belop;
        this.aar = builder.aar;
        this.mnd = builder.mnd;
    }

    public static class Builder {
        private Leilighet leilighet;
        private InntektType inntektType;
        private Double belop;
        private Integer aar;
        private Integer mnd;

        public Builder() {
        }

        public Builder leilighet(Leilighet leilighet) {
            this.leilighet = leilighet;
            return this;
        }

        public Builder inntektType(InntektType inntektType) {
            this.inntektType = inntektType;
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

        public InntektRequest build() {
            return new InntektRequest(this);
        }
    }
}
