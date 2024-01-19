package pattern.skillmatchbackend.controller.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import pattern.skillmatchbackend.model.TokenManager;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.persistenza.DBManager;

import java.io.IOException;

@CrossOrigin("http://localhost:4200")
@WebServlet("/ConfermaAccount")
public class ConfermaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Access-Control-Allow-Origin", "*");

        String token = req.getParameter("token");
        String tokenGenerato = TokenManager.verificaToken(token);
        System.out.println("token: " + tokenGenerato);

        switch(tokenGenerato){

            case "errore":
                System.out.println("ciao");
                resp.sendRedirect("/Errore");
                break;

            case  "scaduto":
                System.out.println("ciao2");
                resp.sendRedirect("/Scaduto");
                break;

            default:
                System.out.println("ciao3");
                Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(tokenGenerato);
                if (utente != null) {
                    utente.setRegistrato(true);
                    DBManager.getInstance().getUtenteDao().saveOrUpdate(utente);
                }




                if(utente == null) {
                    resp.sendRedirect("/Errore");
                }
                else {

                }
                break;

        }

    }

}

