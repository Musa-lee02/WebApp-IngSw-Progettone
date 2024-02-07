package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.payment.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
@RequestMapping("/payment")

public class PaymentController {

    private final PaymentContext paymentContext = new PaymentContext();
    private final PaymentStrategyFactory paymentStrategyFactory = new PaymentStrategyFactory();


    @PostMapping("/process")
    public void processPayment(@RequestParam("customAmount") Long customAmount,
                                 @RequestParam("idDest") String idDest,
                                 @RequestParam("paymentMethod") String paymentMethod, HttpServletResponse response) throws IOException {

        PaymentRequest paymentRequest = new PaymentRequest(customAmount, idDest);
        PaymentStrategy paymentStrategy = paymentStrategyFactory.getPaymentStrategy(paymentMethod);
        paymentContext.setPaymentStrategy(paymentStrategy);
        String session = paymentContext.processPayment(paymentRequest);
        response.sendRedirect(session);

    }
}
