package dev.aman.e_commerce_payment_service.Services;

import com.razorpay.RazorpayException;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface PaymentService {
    public String generatePaymentLink(Long paymentId) throws RazorpayException, JSONException;

}
