package pattern.skillmatchbackend.controller.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pattern.skillmatchbackend.model.TokenManager;

import java.io.IOException;


@WebServlet("/RecuperoPassword")
public class RecuperoPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getParameter("token");
        String tokenGenerato = TokenManager.verificaToken(token);

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
                /*Lavoratore utente = DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(tokenGenerato);
                if(utente == null) {
                    resp.sendRedirect("/Errore");
                }
                else {
                    utente.setRegistrato(true);
                    DBManager.getInstance().getLavoratoreDao().saveOrUpdate(utente);
                }*/
                break;

        }

    }

}
