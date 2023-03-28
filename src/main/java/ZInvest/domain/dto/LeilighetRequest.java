package ZInvest.domain.dto;

public class LeilighetRequest {

    private String navn;
    private String adresse;
    private String postNr;
    private String postSted;

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

    public String getPostNr() {
        return postNr;
    }

    public void setPostNr(String postNr) {
        this.postNr = postNr;
    }

    public String getPostSted() {
        return postSted;
    }

    public void setPostSted(String postSted) {
        this.postSted = postSted;
    }
}
