package application.model.contenuto;

public abstract class Contenuto {

    protected String contenuto;

    public String getContenuto() {
        return contenuto;
    }

    protected Contenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public abstract String visualizza();

}
