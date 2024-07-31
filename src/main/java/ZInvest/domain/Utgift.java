package ZInvest.domain;

public class Utgift {

    private int id;
    private Integer leilighetId;
    private Leilighet leilighet;
    private Integer utgiftTypeId;
    private UtgiftType utgiftType;
    private Double belop;
    private Integer aar;
    private Integer mnd;
    private String beskrivelse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLeilighetId() {
        return leilighetId;
    }

    public void setLeilighetId(Integer leilighetId) {
        this.leilighetId = leilighetId;
    }

    public Leilighet getLeilighet() {
        return leilighet;
    }

    public void setLeilighet(Leilighet leilighet) {
        this.leilighet = leilighet;
    }

    public Integer getUtgiftTypeId() {
        return utgiftTypeId;
    }

    public void setUtgiftTypeId(Integer utgiftTypeId) {
        this.utgiftTypeId = utgiftTypeId;
    }

    public UtgiftType getUtgiftType() {
        return utgiftType;
    }

    public void setUtgiftType(UtgiftType utgiftType) {
        this.utgiftType = utgiftType;
    }

    public Double getBelop() {
        return belop;
    }

    public void setBelop(Double belop) {
        this.belop = belop;
    }

    public Integer getAar() {
        return aar;
    }

    public void setAar(Integer aar) {
        this.aar = aar;
    }

    public Integer getMnd() {
        return mnd;
    }

    public void setMnd(Integer mnd) {
        this.mnd = mnd;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    private Utgift(Builder builder) {
        this.id = builder.id;
        this.leilighetId = builder.leilighetId;
        this.utgiftTypeId = builder.inntektTypeId;
        this.belop = builder.belop;
        this.aar = builder.aar;
        this.mnd = builder.mnd;
        this.beskrivelse = builder.beskrivelse;
    }

    public static class Builder {
        private int id;
        private Integer leilighetId;
        private Integer inntektTypeId;
        private Double belop;
        private Integer aar;
        private Integer mnd;
        private String beskrivelse;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder leilighetId(Integer leilighetId) {
            this.leilighetId = leilighetId;
            return this;
        }

        public Builder inntektTypeId(Integer inntektTypeId) {
            this.inntektTypeId = inntektTypeId;
            return this;
        }

        public Builder belop(Double belop) {
            this.belop = belop;
            return this;
        }

        public Builder aar(Integer aar) {
            this.aar = aar;
            return this;
        }

        public Builder mnd(Integer mnd) {
            this.mnd = mnd;
            return this;
        }

        public Builder beskrivelse(String beskrivelse) {
            this.beskrivelse = beskrivelse;
            return this;
        }

        public Utgift build() {
            return new Utgift(this);
        }
    }

}
