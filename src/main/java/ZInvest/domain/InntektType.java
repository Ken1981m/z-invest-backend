package ZInvest.domain;

public class InntektType {

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


    private InntektType(Builder builder) {
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


        public InntektType build() {
            return new InntektType(this);
        }
    }
}
