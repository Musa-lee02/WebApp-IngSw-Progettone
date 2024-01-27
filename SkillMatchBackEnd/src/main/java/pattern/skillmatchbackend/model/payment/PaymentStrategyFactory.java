package pattern.skillmatchbackend.model.payment;

public class PaymentStrategyFactory {

    public PaymentStrategy getPaymentStrategy(String paymentMethod){
        switch(paymentMethod){
            case "stripe":
                return new StripePaymentStrategy();
            case "paypal":
                //return new PaypalPaymentStrategy();
            default:
                return null;
        }
    }
}
