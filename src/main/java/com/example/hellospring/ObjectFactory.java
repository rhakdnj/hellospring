package com.example.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exchangeRateReader());
	}

	@Bean
	public ExchangeRateReader exchangeRateReader() {
		return new WebExchangeRateReader();
	}
}
