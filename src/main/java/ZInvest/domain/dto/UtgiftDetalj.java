package ZInvest.domain.dto;

public class UtgiftDetalj {
    private String navn;
    private Long belop;

    public String getNavn() {
        return navn;
    }

    public Long getBelop() {
        return belop;
    }

    private UtgiftDetalj(Builder builder) {
        this.navn = builder.navn;
        this.belop = builder.belop;
    }

    public static class Builder {
        private String navn;
        private Long belop;

        public Builder() {
        }

        public Builder navn(String navn) {
            this.navn = navn;
            return this;
        }

        public Builder belop(Long belop) {
            this.belop = belop;
            return this;
        }

        public UtgiftDetalj build() {
            return new UtgiftDetalj(this);
        }
    }
}
