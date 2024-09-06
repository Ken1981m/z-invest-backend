package ZInvest.domain;

public class Leilighet {
    private int id;
    private String navn;
    private String adresse;
    private String postNr;
    private String postSted;
    private String beskrivelse;

    public int getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPostNr() {
        return postNr;
    }

    public String getPostSted() {
        return postSted;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    private Leilighet(Builder builder) {
        this.id = builder.id;
        this.navn = builder.navn;
        this.adresse = builder.adresse;
        this.postNr = builder.postNr;
        this.postSted = builder.postSted;
        this.beskrivelse = builder.beskrivelse;
    }

    public static class Builder {
        private int id;
        private String navn;
        private String adresse;
        private String postNr;
        private String postSted;
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

        public Builder adresse(String adresse) {
            this.adresse = adresse;
            return this;
        }

        public Builder postNr(String postNr) {
            this.postNr = postNr;
            return this;
        }

        public Builder postSted(String postSted) {
            this.postSted = postSted;
            return this;
        }

        public Builder beskrivelse(String beskrivelse) {
            this.beskrivelse = beskrivelse;
            return this;
        }

        public Leilighet build() {
            return new Leilighet(this);
        }
    }
}
