package pattern.skillmatchbackend.model.payment;

public interface PaymentStrategy {
    String processPayment(PaymentRequest paymentRequest);

}
