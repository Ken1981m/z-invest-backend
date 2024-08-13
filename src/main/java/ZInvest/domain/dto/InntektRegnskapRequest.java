package ZInvest.domain.dto;

import java.util.List;

public class InntektRegnskapRequest {
    private String aar;
    private String label;
    private Double belop;
    private List<Double> belopList; //Brukes for å vise inntekt for flere år på regnskap siden
    private List<String> aarList; //Brukes for å hente detaljer om utgifter på regnskap siden

    public String getAar() {
        return aar;
    }

    public void setAar(String aar) {
        this.aar = aar;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getBelop() {
        return belop;
    }

    public void setBelop(Double belop) {
        this.belop = belop;
    }

    public List<Double> getBelopList() {
        return belopList;
    }

    public void setBelopList(List<Double> belopList) {
        this.belopList = belopList;
    }

    public List<String> getAarList() {
        return aarList;
    }

    public void setAarList(List<String> aarList) {
        this.aarList = aarList;
    }

    private InntektRegnskapRequest(Builder builder) {
        this.aar = builder.aar;
        this.label = builder.label;
        this.belop = builder.belop;
        this.belopList = builder.belopList;
        this.aarList = builder.aarList;
    }

    public static class Builder {
        private String aar;
        private String label;
        private Double belop;
        private List<Double> belopList;
        private List<String> aarList;

        public Builder() {
        }

        public Builder aar(String aar) {
            this.aar = aar;
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

        public Builder belopList(List<Double> belopList) {
            this.belopList = belopList;
            return this;
        }

        public Builder aarList(List<String> aarList) {
            this.aarList = aarList;
            return this;
        }

        public InntektRegnskapRequest build() {
            return new InntektRegnskapRequest(this);
        }
    }
}
