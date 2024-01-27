/*package pattern.skillmatchbackend.model.payment;
import com.braintreegateway.*;

import java.math.BigDecimal;

    public class PaymentOperations {
        private static final PaymentOperations instance = new PaymentOperations();

        private PaymentOperations() {}

        private static final String MerchandId = "9jhqh79ksvzgvtqk";
        private static final String Publickey = "bj7f9ndwbh5mb8m6";
        private static final String PrivateKey = "bf96da73175f3bd930ee2420c8861605";

        BraintreeGateway gateway= createSandboxGateway();
        public static PaymentOperations getInstance() {
            return instance;
        }

        public static BraintreeGateway createSandboxGateway() {
            return new BraintreeGateway(
                    Environment.SANDBOX,
                    MerchandId,
                    Publickey,
                    PrivateKey
            );
        }

        public Result<PaymentMethod> createCreditCardPaymentMethod(String customerId, String cardNumber) {
            PaymentMethodRequest paymentMethodRequest = new PaymentMethodRequest()
                    .customerId(customerId)
                    .paymentMethodNonce(cardNumber) // Inserisci il nonce della carta di credito
                    .options()
                    .verifyCard(true) // Opzionale: verifica la carta di credito
                    .done();

            return (Result<PaymentMethod>) gateway.paymentMethod().create(paymentMethodRequest);
        }

        public Result<Transaction> createTransaction(String paymentMethodToken, BigDecimal amount) {
            TransactionRequest request = new TransactionRequest()
                    .amount(amount)
                    .paymentMethodToken(paymentMethodToken)
                    .options()
                    .submitForSettlement(true)
                    .done();

            return gateway.transaction().sale(request);
        }
    }
*/