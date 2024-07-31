package ZInvest.domain.dto;

public class UtgiftTypeRequest {

    private int id;
    private String navn;
    private String beskrivelse;
    private Boolean mndUavhengig;

    public int getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public Boolean getMndUavhengig() {
        return mndUavhengig;
    }

    private UtgiftTypeRequest(Builder builder) {
        this.id = builder.id;
        this.navn = builder.navn;
        this.beskrivelse = builder.beskrivelse;
        this.mndUavhengig = builder.mndUavhengig;
    }

    public static class Builder {
        private int id;
        private String navn;
        private String beskrivelse;
        private Boolean mndUavhengig;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder navn(String navn) {
            this.navn = navn;
            return this;
        }

        public Builder beskrivelse(String beskrivelse) {
            this.beskrivelse = beskrivelse;
            return this;
        }

        public Builder mndUavhengig(Boolean mndUavhengig) {
            this.mndUavhengig = mndUavhengig;
            return this;
        }

        public UtgiftTypeRequest build() {
            return new UtgiftTypeRequest(this);
        }
    }
}
