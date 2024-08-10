package ZInvest.domain.dto;


import java.util.List;

public class InntektRequest {
    private int id;
    private int mnd;
    private String aar;
    private String label;
    private Double belop;
    private List<Double> belopList; //Brukes for å vise inntekt for flere år på regnskap siden
    private List<String> aarList; //Brukes for å hente detaljer om utgifter på regnskap siden
    private String beskrivelse;

    public int getId() {
        return id;
    }

    public int getMnd() {
        return mnd;
    }

    public String getAar() {
        return aar;
    }

    public void setAar(String aar) {
        this.aar = aar;
    }

    public String getLabel() {
        return label;
    }

    public Double getBelop() {
        return belop;
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

    public String getBeskrivelse() {
        return beskrivelse;
    }

    private InntektRequest(Builder builder) {
        this.id = builder.id;
        this.mnd = builder.mnd;
        this.aar = builder.aar;
        this.label = builder.label;
        this.belop = builder.belop;
        this.belopList = builder.belopList;
        this.aarList = builder.aarList;
        this.beskrivelse = builder.beskrivelse;
    }

    public static class Builder {
        private int id;
        private int mnd;
        private String aar;
        private String label;
        private Double belop;
        private List<Double> belopList;
        private List<String> aarList;
        private String beskrivelse;

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

        public Builder beskrivelse(String beskrivelse) {
            this.beskrivelse = beskrivelse;
            return this;
        }

        public InntektRequest build() {
            return new InntektRequest(this);
        }
    }
}
