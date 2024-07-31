package ZInvest.domain.dto;


public class UtgiftRequest {
    private int id;
    private int mnd;
    private String label;
    private Double belop;
    private String beskrivelse;

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

    public String getBeskrivelse() {
        return beskrivelse;
    }

    private UtgiftRequest(Builder builder) {
        this.id = builder.id;
        this.mnd = builder.mnd;
        this.label = builder.label;
        this.belop = builder.belop;
        this.beskrivelse = builder.beskrivelse;
    }

    public static class Builder {
        private int id;
        private int mnd;
        private String label;
        private Double belop;
        private String beskrivelse;
        private Boolean mndUavhengig;

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

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder belop(Double belop) {
            this.belop = belop;
            return this;
        }

        public Builder beskrivelse(String beskrivelse) {
            this.beskrivelse = beskrivelse;
            return this;
        }

        public UtgiftRequest build() {
            return new UtgiftRequest(this);
        }
    }
}
