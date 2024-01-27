package pattern.skillmatchbackend.model.payment;

public class PaymentRequest {
    private Long customAmount;
    private String idDest;

    private String paymentMethodToken;

    public PaymentRequest(Long customAmount, String id) {
        this.customAmount = customAmount;
        this.idDest = id;
    }

    public Long getCustomAmount() {
        return customAmount;
    }

    public void setCustomAmount(Long customAmount) {
        this.customAmount = customAmount;
    }

    public String getId() {
        return idDest;
    }

    public void setId(String id) {
        this.idDest = id;
    }

    public void setPaymentMethodToken(String paymentMethodToken) {
        this.paymentMethodToken = paymentMethodToken;
    }


    public String getPaymentMethodToken() {
        return paymentMethodToken;

    }
}
