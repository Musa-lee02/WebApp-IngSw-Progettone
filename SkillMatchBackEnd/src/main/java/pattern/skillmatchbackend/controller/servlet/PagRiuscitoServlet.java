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

