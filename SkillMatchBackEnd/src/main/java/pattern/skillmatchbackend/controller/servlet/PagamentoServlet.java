package pattern.skillmatchbackend.controller.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import pattern.skillmatchbackend.model.TokenManager;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/Pagamento")
public class PagamentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        //String tokenGenerato = TokenManager.verificaToken(token);
        //System.out.println(tokenGenerato);

        //Nello switch qui sotto andrebbe "tokenGenerato", però, se qualcuno volesse testare il risultato può hard-codare la stringa, per ora
        List<String> metodiPagamento = List.of("PayPal", "Carta di credito", "Bonifico bancario");
        session.setAttribute("metodiPagamento", metodiPagamento);

        RequestDispatcher dispatcher = req.getRequestDispatcher("views/pagamento.html");
        dispatcher.forward(req, resp);

    }

}
