package pattern.skillmatchbackend.model.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;

/*import javax.mail.Session;

public class StripePaymentStrategy {


    public PaymentResponse pay(PaymentRequest request) {
        Stripe.apiKey = "sk_test_51J0QXeK5ZQ4Z";


    try {
        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount(request.getCustomAmount())
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName("P2P Payment")
                                .build())
                .build();

        // Create the checkout session
        SessionCreateParams params = SessionCreateParams.builder()
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setPriceData(priceData)
                        .setQuantity(1L)
                        .build())
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("your-success-url")
                .setCancelUrl("your-cancel-url")
                // Additional parameters for recipient or transfer logic
                .build();

        Session session = Session.create(params);
        return new PaymentResponse(session.getId());
    } catch (StripeException e) {
        // Handle exceptions
    }
    }
}*/

