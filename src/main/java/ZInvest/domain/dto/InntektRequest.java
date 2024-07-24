package ZInvest.domain.dto;


public class InntektRequest {
    private String label;
    private Double belop;

    public String getLabel() {
        return label;
    }

    public Double getBelop() {
        return belop;
    }

    private InntektRequest(Builder builder) {
        this.label = builder.label;
        this.belop = builder.belop;
    }

    public static class Builder {
        private String label;
        private Double belop;

        public Builder() {
        }

        public Builder belop(Double belop) {
            this.belop = belop;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public InntektRequest build() {
            return new InntektRequest(this);
        }
    }
}
