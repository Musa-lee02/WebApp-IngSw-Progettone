package pattern.skillmatchbackend.controller;

import org.springframework.web.bind.annotation.*;
import pattern.skillmatchbackend.model.payment.*;

@RestController
@RequestMapping("/payment")

public class PaymentController {

    private final PaymentContext paymentContext = new PaymentContext();
    private final PaymentStrategyFactory paymentStrategyFactory = new PaymentStrategyFactory();


    @PostMapping("/process")
    public String processPayment(@RequestBody PaymentRequest paymentRequest, @RequestParam String paymentMethod) {
        PaymentStrategy paymentStrategy = paymentStrategyFactory.getPaymentStrategy(paymentMethod);
        paymentContext.setPaymentStrategy(paymentStrategy);
        String session = paymentContext.processPayment(paymentRequest);
        System.out.println(session);
        return session;
    }
}
