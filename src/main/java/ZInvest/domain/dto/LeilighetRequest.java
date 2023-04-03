package ZInvest.domain.dto;

public class LeilighetRequest {

    private int id;
    private String navn;
    private String adresse;
    private String postNr;
    private String postSted;

    public LeilighetRequest(String navn, String adresse, String postNr, String postSted) {
        this.navn = navn;
        this.adresse = adresse;
        this.postNr = postNr;
        this.postSted = postSted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostNr() {
        return postNr;
    }

    public void setPostNr(String postNr) {
        this.postNr = postNr;
    }

    public String getPostSted() {
        return postSted;
    }

    public void setPostSted(String postSted) {
        this.postSted = postSted;
    }



    private LeilighetRequest(Builder builder) {
        this.id = builder.id;
        this.navn = builder.navn;
        this.adresse = builder.adresse;
        this.postNr = builder.postNr;
        this.postSted = builder.postSted;
    }

    public static class Builder {
        private int id;
        private String navn;
        private String adresse;
        private String postNr;
        private String postSted;

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

        public LeilighetRequest build() {
            return new LeilighetRequest(this);
        }
    }
}
