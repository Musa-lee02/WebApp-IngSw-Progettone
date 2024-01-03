package pattern.skillmatchbackend.model;

import java.util.List;

public class Ambiti {
    private Long idAmbito;
    private String nome;

    private List<Annuncio> annunci;
    private List<Lavoratore> lavoratori;

    public Ambiti() {
    }

    public Ambiti(Long idAmbito, String nome, List<Annuncio> annunci, List<Lavoratore> lavoratori) {
        this.idAmbito = idAmbito;
        this.nome = nome;
        this.annunci = annunci;
        this.lavoratori = lavoratori;
    }

    public Long getIdAmbito() {
        return idAmbito;
    }

    public void setIdAmbito(Long idAmbito) {
        this.idAmbito = idAmbito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome == null ? null : nome.trim();
    }

    public List<Annuncio> getAnnunci() {
        return annunci;
    }

    public void setAnnunci(List<Annuncio> annunci) {
        this.annunci = annunci;
    }

    public List<Lavoratore> getLavoratori() {
        return lavoratori;
    }

    public void setLavoratori(List<Lavoratore> lavoratori) {
        this.lavoratori = lavoratori;
    }

}
