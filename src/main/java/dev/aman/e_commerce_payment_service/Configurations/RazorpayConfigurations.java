package dev.aman.e_commerce_payment_service.Configurations;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfigurations {

    @Value("${razorpay.key}")
    private String apiKey;
    @Value("${razorpay.value}")
    private String apiSecret;
    @Bean
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        return new RazorpayClient(apiKey, apiSecret);
    }
}
