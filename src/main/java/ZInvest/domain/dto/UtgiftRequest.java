package ZInvest.domain.dto;


public class UtgiftRequest {
    private int id;
    private int mnd;
    private String label;
    private Double belop;

    public int getId() {
        return id;
    }

    public int getMnd() {
        return mnd;
    }

    public String getLabel() {
        return label;
    }

    public Double getBelop() {
        return belop;
    }

    private UtgiftRequest(Builder builder) {
        this.id = builder.id;
        this.mnd = builder.mnd;
        this.label = builder.label;
        this.belop = builder.belop;
    }

    public static class Builder {
        private int id;
        private int mnd;
        private String label;
        private Double belop;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder mnd(int mnd) {
            this.mnd = mnd;
            return this;
        }

        public Builder belop(Double belop) {
            this.belop = belop;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public UtgiftRequest build() {
            return new UtgiftRequest(this);
        }
    }
}
