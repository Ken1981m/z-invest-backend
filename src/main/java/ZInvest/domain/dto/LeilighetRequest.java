package ZInvest.domain.dto;

public class LeilighetRequest {

    private int id;
    private String navn;
    private String adresse;
    private String postnr;
    private String poststed;

    public LeilighetRequest(String navn, String adresse, String postNr, String poststed) {
        this.navn = navn;
        this.adresse = adresse;
        this.postnr = postNr;
        this.poststed = poststed;
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

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getPoststed() {
        return poststed;
    }

    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }



    private LeilighetRequest(Builder builder) {
        this.id = builder.id;
        this.navn = builder.navn;
        this.adresse = builder.adresse;
        this.postnr = builder.postnr;
        this.poststed = builder.poststed;
    }

    public static class Builder {
        private int id;
        private String navn;
        private String adresse;
        private String postnr;
        private String poststed;

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
            this.postnr = postNr;
            return this;
        }

        public Builder postSted(String postSted) {
            this.poststed = postSted;
            return this;
        }

        public LeilighetRequest build() {
            return new LeilighetRequest(this);
        }
    }
}
