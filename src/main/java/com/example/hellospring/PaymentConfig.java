package com.example.hellospring;

import com.example.hellospring.exchangeRate.CachedWebExchangeRateReader;
import com.example.hellospring.exchangeRate.RestTemplateExchangeRateReader;
import com.example.hellospring.payment.ExchangeRateReader;
import com.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
public class PaymentConfig {
	@Bean
	public PaymentService paymentService() {
		return new PaymentService(cachedWebExchangeRateReader(), clock());
	}

	@Bean
	public ExchangeRateReader cachedWebExchangeRateReader() {
		return new CachedWebExchangeRateReader(exchangeRateReader());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new JdkClientHttpRequestFactory());
	}

	@Bean
	public ExchangeRateReader exchangeRateReader() {
		return new RestTemplateExchangeRateReader(restTemplate());
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}
}
