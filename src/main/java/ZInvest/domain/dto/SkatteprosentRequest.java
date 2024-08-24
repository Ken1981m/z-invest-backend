package ZInvest.domain.dto;

public class SkatteprosentRequest {
    private int id;
    private Integer aar;
    private Integer skatteprosent;

    public int getId() {
        return id;
    }

    public Integer getAar() {
        return aar;
    }

    public Integer getSkatteprosent() {
        return skatteprosent;
    }

    private SkatteprosentRequest(Builder builder) {
        this.id = builder.id;
        this.aar = builder.aar;
        this.skatteprosent = builder.skatteprosent;
    }

    public static class Builder {
        private int id;
        private Integer aar;
        private Integer skatteprosent;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder aar(Integer aar) {
            this.aar = aar;
            return this;
        }

        public Builder skatteprosent(Integer skatteprosent) {
            this.skatteprosent = skatteprosent;
            return this;
        }

        public SkatteprosentRequest build() {
            return new SkatteprosentRequest(this);
        }
    }
}