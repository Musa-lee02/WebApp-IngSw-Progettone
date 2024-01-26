package pattern.skillmatchbackend.dto;

import pattern.skillmatchbackend.model.Lavoratore;

public class LoginLavoratoreDto {

    String token;
    Lavoratore lavoratore;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Lavoratore getLavoratore() {
        return lavoratore;
    }

    public void setLavoratore(Lavoratore lavoratore) {
        this.lavoratore = lavoratore;
    }
}
