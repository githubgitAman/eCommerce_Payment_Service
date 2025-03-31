package dev.aman.e_commerce_payment_service.Controllers;

import com.razorpay.RazorpayException;
import dev.aman.e_commerce_payment_service.DTOs.GeneratePaymentLinkDTOs;
import dev.aman.e_commerce_payment_service.Services.PaymentService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    //Passing DTOs object in parameter
    @PostMapping
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkDTOs generatePaymentLinkDTOs) throws JSONException, RazorpayException {
        return paymentService.generatePaymentLink(generatePaymentLinkDTOs.getOrderId());
    }
//    @PostMapping
    public void handleWebhookEvent(){
//        //Not written all if-else conditions
//        System.out.println("Webhook event received");
    }
}
