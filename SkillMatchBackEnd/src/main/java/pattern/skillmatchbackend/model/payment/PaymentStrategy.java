package pattern.skillmatchbackend.model.payment;

import jakarta.servlet.http.HttpServletResponse;

public interface PaymentStrategy {
    String processPayment(PaymentRequest paymentRequest);

}
