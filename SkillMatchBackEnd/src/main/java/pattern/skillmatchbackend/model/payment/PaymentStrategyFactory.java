package pattern.skillmatchbackend.model.payment;

public class PaymentStrategyFactory {

    public PaymentStrategy getPaymentStrategy(String paymentMethod){
        switch(paymentMethod){
            case "Carta di credito":
                return new StripePaymentStrategy();
            case "PayPal":
                //return new PaypalPaymentStrategy();
            default:
                return null;
        }
    }
}
