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
@CrossOrigin(origins = "http://localhost:4200")
public class PagamentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String customAmount = req.getParameter("customAmount");
        System.out.println("Ca: " + customAmount);
        String idDest = req.getParameter("idDest");
        String idMitt = req.getParameter("idMitt");


        String idAnn = req.getParameter("idAnnuncio");

        System.out.println("idDest: " + idDest);
        System.out.println("idMitt: " + idMitt);
        HttpSession session = req.getSession();
        session.setAttribute("customAmount", customAmount);
        session.setAttribute("idDest", idDest);
        session.setAttribute("idMitt", idMitt);

        session.setAttribute("idAnnuncio", idAnn);
        //System.out.println("idAnnuncio:" + idAnn +"|");

        List<String> metodiPagamento = List.of("Carta di credito", "PayPal");
        session.setAttribute("metodiPagamento", metodiPagamento);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/pagamento.html");
        dispatcher.forward(req, resp);
    }

}
