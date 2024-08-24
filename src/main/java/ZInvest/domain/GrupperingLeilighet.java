package ZInvest.domain;

public class GrupperingLeilighet {
    private int id;
    private String grupperingNavn;
    private String leilighetNavn;
    private int leilighetId;

    public int getId() {
        return id;
    }

    public String getGrupperingNavn() {
        return grupperingNavn;
    }

    public String getLeilighetNavn() {
        return leilighetNavn;
    }

    public int getLeilighetId() {
        return leilighetId;
    }

    private GrupperingLeilighet(Builder builder) {
        this.id = builder.id;
        this.grupperingNavn = builder.grupperingNavn;
        this.leilighetNavn = builder.leilighetNavn;
        this.leilighetId = builder.leilighetId;
    }

    public static class Builder {
        private int id;
        private String grupperingNavn;
        private String leilighetNavn;
        private int leilighetId;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder grupperingNavn(String grupperingNavn) {
            this.grupperingNavn = grupperingNavn;
            return this;
        }

        public Builder leilighetNavn(String leilighetNavn) {
            this.leilighetNavn = leilighetNavn;
            return this;
        }

        public Builder leilighetId(int leilighetId) {
            this.leilighetId = leilighetId;
            return this;
        }

        public GrupperingLeilighet build() {
            return new GrupperingLeilighet(this);
        }
    }
}
