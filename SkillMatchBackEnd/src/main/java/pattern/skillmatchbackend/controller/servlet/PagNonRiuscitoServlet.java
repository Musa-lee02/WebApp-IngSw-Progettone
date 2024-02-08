package pattern.skillmatchbackend.controller.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import pattern.skillmatchbackend.model.TokenManager;

import java.io.IOException;
import java.util.List;


@WebServlet("/PagamentoNonRiuscito")
public class PagNonRiuscitoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Access-Control-Allow-Origin", "*");

        String token = req.getParameter("token");
        String tokenGenerato = TokenManager.verificaToken(token);
        System.out.println("token: " + tokenGenerato);

        HttpSession session = req.getSession(true);

        //String tokenGenerato = TokenManager.verificaToken(token);
        //System.out.println(tokenGenerato);


        RequestDispatcher dispatcher = req.getRequestDispatcher("views/pagamentoNonRiuscito.html");
        dispatcher.forward(req, resp);

    }

}


