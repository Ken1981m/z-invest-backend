package ZInvest.domain.dto;


import java.util.List;

public class UtgiftRequest {
    private int id;
    private int mnd;
    private String label;
    private Double belop;
    private String utgiftBeskrivelse;
    private Boolean mndUavhengig;
    private Integer utgiftTypeId;
    private String utgiftTypeNavn;
    private String utgiftTypeBeskrivelse;
    private List<UtgiftDetalj> utgiftDetaljer;

    public int getId() {
        return id;
    }

    public int getMnd() {
        return mnd;
    }

    public String getLabel() {
        return label;
    }

    public Double getBelop() {
        return belop;
    }

    public String getUtgiftBeskrivelse() {
        return utgiftBeskrivelse;
    }

    public Boolean getMndUavhengig() {
        return mndUavhengig;
    }

    public Integer getUtgiftTypeId() {
        return utgiftTypeId;
    }

    public String getUtgiftTypeNavn() {
        return utgiftTypeNavn;
    }

    public String getUtgiftTypeBeskrivelse() {
        return utgiftTypeBeskrivelse;
    }

    public List<UtgiftDetalj> getUtgiftDetaljer() {
        return utgiftDetaljer;
    }

    public void setUtgiftDetaljer(List<UtgiftDetalj> utgiftDetaljer) {
        this.utgiftDetaljer = utgiftDetaljer;
    }

    private UtgiftRequest(Builder builder) {
        this.id = builder.id;
        this.mnd = builder.mnd;
        this.label = builder.label;
        this.belop = builder.belop;
        this.utgiftBeskrivelse = builder.utgiftBeskrivelse;
        this.mndUavhengig = builder.mndUavhengig;
        this.utgiftTypeId = builder.utgiftTypeId;
        this.utgiftTypeNavn = builder.utgiftTypeNavn;
        this.utgiftTypeBeskrivelse = builder.utgiftTypeBeskrivelse;
        this.utgiftDetaljer = builder.utgiftDetaljer;
    }

    public static class Builder {
        private int id;
        private int mnd;
        private String label;
        private Double belop;
        private String utgiftBeskrivelse;
        private Boolean mndUavhengig;
        private Integer utgiftTypeId;
        private String utgiftTypeNavn;
        private String utgiftTypeBeskrivelse;
        private List<UtgiftDetalj> utgiftDetaljer;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder mnd(int mnd) {
            this.mnd = mnd;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder belop(Double belop) {
            this.belop = belop;
            return this;
        }

        public Builder utgiftBeskrivelse(String utgiftBeskrivelse) {
            this.utgiftBeskrivelse = utgiftBeskrivelse;
            return this;
        }

        public Builder mndUavhengig(Boolean mndUavhengig) {
            this.mndUavhengig = mndUavhengig;
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

        public Builder utgiftDetaljer(List<UtgiftDetalj> utgiftDetaljer) {
            this.utgiftDetaljer = utgiftDetaljer;
            return this;
        }

        public UtgiftRequest build() {
            return new UtgiftRequest(this);
        }
    }
}
