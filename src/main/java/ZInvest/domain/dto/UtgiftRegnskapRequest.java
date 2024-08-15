package ZInvest.domain.dto;

import java.util.List;

public class UtgiftRegnskapRequest {
    private Integer leilighetId;
    private String label;
    private Long belop;
    private String utgiftBeskrivelse;
    private String utgiftTypeNavn;
    private List<UtgiftDetalj> utgiftDetaljer;

    public Integer getLeilighetId() {
        return leilighetId;
    }

    public String getLabel() {
        return label;
    }

    public Long getBelop() {
        return belop;
    }

    public String getUtgiftBeskrivelse() {
        return utgiftBeskrivelse;
    }

    public String getUtgiftTypeNavn() {
        return utgiftTypeNavn;
    }

    public List<UtgiftDetalj> getUtgiftDetaljer() {
        return utgiftDetaljer;
    }

    public void setUtgiftDetaljer(List<UtgiftDetalj> utgiftDetaljer) {
        this.utgiftDetaljer = utgiftDetaljer;
    }

    private UtgiftRegnskapRequest(Builder builder) {
        this.leilighetId = builder.leilighetId;
        this.label = builder.label;
        this.belop = builder.belop;
        this.utgiftBeskrivelse = builder.utgiftBeskrivelse;
        this.utgiftTypeNavn = builder.utgiftTypeNavn;
        this.utgiftDetaljer = builder.utgiftDetaljer;
    }

    public static class Builder {
        private Integer leilighetId;
        private String label;
        private Long belop;
        private String utgiftBeskrivelse;
        private String utgiftTypeNavn;
        private List<UtgiftDetalj> utgiftDetaljer;

        public Builder() {
        }

        public Builder leilighetId(Integer leilighetId) {
            this.leilighetId = leilighetId;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder belop(Long belop) {
            this.belop = belop;
            return this;
        }

        public Builder utgiftBeskrivelse(String utgiftBeskrivelse) {
            this.utgiftBeskrivelse = utgiftBeskrivelse;
            return this;
        }

        public Builder utgiftTypeNavn(String utgiftTypeNavn) {
            this.utgiftTypeNavn = utgiftTypeNavn;
            return this;
        }

        public Builder utgiftDetaljer(List<UtgiftDetalj> utgiftDetaljer) {
            this.utgiftDetaljer = utgiftDetaljer;
            return this;
        }

        public UtgiftRegnskapRequest build() {
            return new UtgiftRegnskapRequest(this);
        }
    }
}
