package pattern.skillmatchbackend.model.payment;

import com.braintreegateway.*;
import pattern.skillmatchbackend.model.payment.PaymentOperations;
import pattern.skillmatchbackend.model.payment.PaymentRequest;
import pattern.skillmatchbackend.model.payment.PaymentStrategy;

import java.math.BigDecimal;

public class PayPalPaymentStrategy implements PaymentStrategy {

    private PaymentOperations paymentOperations;

    public PayPalPaymentStrategy() {
        this.paymentOperations = PaymentOperations.getInstance();
    }

    @Override
    public String processPayment(PaymentRequest paymentRequest) {
        // Assuming paymentRequest contains necessary info such as paymentMethodToken
        BigDecimal amount = new BigDecimal(paymentRequest.getCustomAmount());
        String paymentMethodToken = paymentRequest.getPaymentMethodToken();

        Result<Transaction> result = paymentOperations.createTransaction(paymentMethodToken, amount);

        if (result.isSuccess()) {
            return result.getTarget().getId(); // Transaction ID
        } else {
            // Handle errors or failed transactions
            return "Error processing PayPal payment";
        }
    }
}
