package dev.aman.e_commerce_payment_service.Services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

@Service("Razorpay")
public class RazorpayPaymentGateway implements PaymentService{

    RazorpayClient razorpayClient;
    public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(Long paymentId) throws RazorpayException, JSONException {
        //Make a call to Razorpay to generate payment link
        //Below code is from Razorpay Payment APIs docs

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000); //10 Rupees i.e it is store as 10.00 format number is multiplied by 100
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 10 * 60 * 1000); //Expiry time of 10 min
        paymentLinkRequest.put("reference_id", paymentId.toString()); //Excepting our order id
        paymentLinkRequest.put("description","Payment for Product");
        JSONObject customer = new JSONObject();
        //Here to get name and phone number we should call Order service
        //OrderService orderService = restTemplate.getForObject("order service URL", OrderService.class)

        customer.put("name","+91 7290930010");
        customer.put("contact","Aman Sharma");
        customer.put("email","sharma.aman.a30@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
        //Callback URL
        //Calling Google for now in case Payment fails
        paymentLinkRequest.put("callback_url","https://google.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.get("short_url");
    }
}

