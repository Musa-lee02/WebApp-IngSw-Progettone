package pattern.skillmatchbackend.dto;

import pattern.skillmatchbackend.model.Cliente;
import pattern.skillmatchbackend.model.Lavoratore;

public class LoginClienteDto {

    String token;
    Cliente cliente;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
