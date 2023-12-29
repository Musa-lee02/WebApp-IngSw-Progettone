package application.model.contenuto;

public class ContenutoTesto  extends Contenuto {
    public ContenutoTesto(String contenuto) {
        super(contenuto);
    }

    @Override
    public String visualizza() {
        return contenuto;
    }
}
