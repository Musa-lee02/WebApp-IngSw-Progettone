package pattern.skillmatchbackend.model.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

public class StripePaymentStrategy implements PaymentStrategy{


    @Override
    public String processPayment(PaymentRequest request) {
        Stripe.apiKey = "sk_test_51Ocx4GLbtW1BvSBIKQcnTtmjfiNpBI4m7ccOnfi0iyZMGzksGUXP0p5ZQjEqiTuX7resxoQioqipQFe3YeZJk6wa00FU8DcreM";


    try {
        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("eur")
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
                .setSuccessUrl("http://localhost:8080/succes.html")
                .setCancelUrl("http://localhost:8080/cancel.html")
                // Additional parameters for recipient or transfer logic
                .build();
        Session session = Session.create(params);
        System.out.println("url: " + session.getUrl());

        return session.getId();

    } catch (StripeException e) {
       return null;
    }
    }
}

