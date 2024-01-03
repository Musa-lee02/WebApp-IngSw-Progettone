package pattern.skillmatchbackend.model;

import java.util.List;

public class Lavoratore extends Utente {


    private String provincia;
    private String provinciaDiLavoro;
    private String imgProfilo;

    private List<Ambiti> ambiti;

    public Lavoratore() {
    }

    public Lavoratore(String provincia, String provinciaDiLavoro, String imgProfilo, List<Ambiti> ambiti) {
        this.provincia = provincia;
        this.provinciaDiLavoro = provinciaDiLavoro;
        this.imgProfilo = imgProfilo;
        this.ambiti = ambiti;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getProvinciaDiLavoro() {
        return provinciaDiLavoro;
    }

    public void setProvinciaDiLavoro(String provinciaDiLavoro) {
        this.provinciaDiLavoro = provinciaDiLavoro;
    }

    public String getImgProfilo() {
        return imgProfilo;
    }

    public void setImgProfilo(String imgProfilo) {
        this.imgProfilo = imgProfilo;
    }

    public List<Ambiti> getAmbiti() {
        return ambiti;
    }

    public void setAmbiti(List<Ambiti> ambiti) {
        this.ambiti = ambiti;
    }
}
