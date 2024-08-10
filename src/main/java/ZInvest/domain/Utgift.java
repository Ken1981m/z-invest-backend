package ZInvest.domain;

public class Utgift {

    private int id;
    private Integer leilighetId;
    private Leilighet leilighet;
    private Integer utgiftTypeId;
    private String utgiftTypeNavn;
    private String utgiftTypeBeskrivelse;
    private UtgiftType utgiftType;
    private Double belop;
    private Integer aar;
    private Integer mnd;
    private String beskrivelse;
    private Boolean mndUavhengig;

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

    public String getUtgiftTypeNavn() {
        return utgiftTypeNavn;
    }

    public void setUtgiftTypeNavn(String utgiftTypeNavn) {
        this.utgiftTypeNavn = utgiftTypeNavn;
    }

    public String getUtgiftTypeBeskrivelse() {
        return utgiftTypeBeskrivelse;
    }

    public void setUtgiftTypeBeskrivelse(String utgiftTypeBeskrivelse) {
        this.utgiftTypeBeskrivelse = utgiftTypeBeskrivelse;
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

    public Boolean getMndUavhengig() {
        return mndUavhengig;
    }

    public void setMndUavhengig(Boolean mndUavhengig) {
        this.mndUavhengig = mndUavhengig;
    }

    private Utgift(Builder builder) {
        this.id = builder.id;
        this.leilighetId = builder.leilighetId;
        this.utgiftTypeId = builder.utgiftTypeId;
        this.utgiftTypeNavn = builder.utgiftTypeNavn;
        this.utgiftTypeBeskrivelse = builder.utgiftTypeBeskrivelse;
        this.belop = builder.belop;
        this.aar = builder.aar;
        this.mnd = builder.mnd;
        this.beskrivelse = builder.beskrivelse;
        this.mndUavhengig = builder.mndUavhengig;
    }

    public static class Builder {
        private int id;
        private Integer leilighetId;
        private Integer utgiftTypeId;
        private String utgiftTypeNavn;
        private String utgiftTypeBeskrivelse;
        private Double belop;
        private Integer aar;
        private Integer mnd;
        private String beskrivelse;
        private Boolean mndUavhengig;

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

        public Builder utgiftTypeId(Integer utgiftTypeId) {
            this.utgiftTypeId = utgiftTypeId;
            return this;
        }

        public Builder utgiftTypeNavn(String utgiftTypeNavn) {
            this.utgiftTypeNavn = utgiftTypeNavn;
            return this;
        }

        public Builder utgiftTypeBeskrivelse(String utgiftTypeBeskrivelse) {
            this.utgiftTypeBeskrivelse = utgiftTypeBeskrivelse;
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

        public Builder mndUavhengig(Boolean mndUavhengig) {
            this.mndUavhengig = mndUavhengig;
            return this;
        }

        public Utgift build() {
            return new Utgift(this);
        }
    }

}
