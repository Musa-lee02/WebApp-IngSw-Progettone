package pattern.skillmatchbackend.controller.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.web.bind.annotation.CrossOrigin;
import pattern.skillmatchbackend.model.Proposta;
import pattern.skillmatchbackend.model.TokenManager;
import pattern.skillmatchbackend.model.TransazionePagamento;
import pattern.skillmatchbackend.persistenza.DBManager;
import pattern.skillmatchbackend.persistenza.dao.postgres.PropostaDaoPostgres;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;


@WebServlet("/PagamentoRiuscito")
public class PagRiuscitoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        float importo = Float.parseFloat(req.getSession().getAttribute("customAmount").toString());
        Timestamp data = new Timestamp(System.currentTimeMillis());
        //String metodoDiPagamento = "Luciana";
        String metodoDiPagamento = req.getSession().getAttribute("metodiPagamento").toString();
        String cliente = req.getSession().getAttribute("idMitt").toString();
        String lavoratore = req.getSession().getAttribute("idDest").toString();
        String idStr = req.getSession().getAttribute("idAnnuncio").toString();
        System.out.println("idStr:" + idStr +"|");
        Long idAnnuncio = Long.parseLong(req.getSession().getAttribute("idAnnuncio").toString());


        TransazionePagamento transazione = new TransazionePagamento();
        transazione.setDataTransazione(data);
        transazione.setImporto(importo);
        transazione.setMetodoPagamento(metodoDiPagamento);
        transazione.setMittente(DBManager.getInstance().getClienteDao().findByPrimaryKey(cliente));
        transazione.setDestinatario(DBManager.getInstance().getLavoratoreDao().findByPrimaryKey(lavoratore));

        DBManager.getInstance().getTransazionePagamentoDao().saveOrUpdate(transazione);

        Proposta propostaDao = DBManager.getInstance().getPropostaDao().findByPrimaryKey(idAnnuncio, lavoratore);
        propostaDao.setStatoLavoro("pagato");
        DBManager.getInstance().getPropostaDao().saveOrUpdate(propostaDao);

        RequestDispatcher dispatcher = req.getRequestDispatcher("views/pagamentoRiuscito.html");
        dispatcher.forward(req, resp);

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

RequestDispatcher dispatcher = req.getRequestDispatcher("views/pagamento.html");
        dispatcher.forward(req, resp);
    }
            }

 */
