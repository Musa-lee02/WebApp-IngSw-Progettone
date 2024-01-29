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

/*

//Volendo questa classe si potrebbe fare più o meno così

package pattern.skillmatchbackend.controller.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pattern.skillmatchbackend.model.TokenManager;

import java.io.IOException;

@WebServlet("/ConfermaAccount")
public class ConfermaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Esempio di url:
        //http://localhost:8080/ConfermaAccount?token=123

        String token = req.getParameter("token");
        System.out.println("token is "+ token); //Qui verrà printato: "token is 123"

        HttpSession session = req.getSession(true);

        //String tokenGenerato = TokenManager.verificaToken(token);
        //System.out.println(tokenGenerato);

        //Nello switch qui sotto andrebbe "tokenGenerato", però, se qualcuno volesse testare il risultato può hard-codare la stringa, per ora
        switch("scaduto"){//tokenGenerato

            case "errore":
                System.out.println("case: errore");
                session.setAttribute("status", "errore");

                //resp.sendRedirect("/Errore");
                break;

            case  "scaduto":
                System.out.println("case: scaduto");
                session.setAttribute("status", "scaduto");

                //resp.sendRedirect("/Scaduto");
                break;

            default:
                System.out.println("case: default");
                session.setAttribute("status", "default");

                /*Lavoratore utente = DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(tokenGenerato);
                if(utente == null) {
                    resp.sendRedirect("/Errore");
                }
                else {
                    utente.setRegistrato(true);
                    DBManager.getInstance().getLavoratoreDao().saveOrUpdate(utente);
                }*/
 /*       }

RequestDispatcher dispatcher = req.getRequestDispatcher("views/conferma_account.html");
        dispatcher.forward(req, resp);
    }
            }

 */
