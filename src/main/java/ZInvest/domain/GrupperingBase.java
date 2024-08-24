package ZInvest.domain;

public class GrupperingBase {
    private int id;
    private String grupperingNavn;

    public int getId() {
        return id;
    }

    public String getGrupperingNavn() {
        return grupperingNavn;
    }

    private GrupperingBase(Builder builder) {
        this.id = builder.id;
        this.grupperingNavn = builder.grupperingNavn;
    }

    public static class Builder {
        private int id;
        private String grupperingNavn;

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

        public GrupperingBase build() {
            return new GrupperingBase(this);
        }
    }
}
