package application.controller.servlet;


import application.persistenza.DBManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        /*Customer customer = DBManager.getInstance().getCustomerDao().findByPrimaryKey(username);
        boolean autorizzato;
        if (customer == null){
            System.out.println("Non sei autorizzato");
            autorizzato = false;
        }else{
            System.out.println("Utente " + customer.getFirstName() + " trovato");
            if (password.equals(customer.getPassword())){
                System.out.println("La password corrisponde");
                autorizzato = true;
                HttpSession session = req.getSession();
                System.out.println("ID sessione: " + session.getId());
                session.setAttribute("user", customer);
                resp.sendRedirect("/");
            }else{
                System.out.println("La password non corrisponde");
                autorizzato = false;
            }
            if (!autorizzato){
                RequestDispatcher dispatcher = req.getRequestDispatcher("/views/nonAutorizzato.html");
                dispatcher.forward(req, resp);
            }
        }*/
    }
}
