package pattern.skillmatchbackend.controller;

import com.braintreegateway.util.Http;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.payment.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("/payment")

public class PaymentController {

    private final PaymentContext paymentContext = new PaymentContext();
    private final PaymentStrategyFactory paymentStrategyFactory = new PaymentStrategyFactory();


    @PostMapping("/process")
    public void processPayment(@RequestParam("customAmount") Long customAmount,
                               @RequestParam("idDest") String idDest,
                               @RequestParam("paymentMethod") String paymentMethod, HttpServletResponse response, HttpServletRequest req) throws IOException {

        req.setAttribute("MetodiPagamento", paymentMethod);

        PaymentRequest paymentRequest = new PaymentRequest(customAmount, idDest);
        PaymentStrategy paymentStrategy = paymentStrategyFactory.getPaymentStrategy(paymentMethod);
        paymentContext.setPaymentStrategy(paymentStrategy);
        String session = paymentContext.processPayment(paymentRequest);
        response.sendRedirect(session);

    }
}
