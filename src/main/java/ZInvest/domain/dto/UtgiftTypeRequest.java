package ZInvest.domain.dto;

public class UtgiftTypeRequest {

    private int id;
    private String navn;
    private String beskrivelse;

    public int getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    private UtgiftTypeRequest(Builder builder) {
        this.id = builder.id;
        this.navn = builder.navn;
        this.beskrivelse = builder.beskrivelse;
    }

    public static class Builder {
        private int id;
        private String navn;
        private String beskrivelse;

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


        public UtgiftTypeRequest build() {
            return new UtgiftTypeRequest(this);
        }
    }
}
