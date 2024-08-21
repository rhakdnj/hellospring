package com.example.hellospring;

import com.example.hellospring.exchangeRate.ExchangeRateReaderStub;
import com.example.hellospring.payment.ExchangeRateReader;
import com.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TestObjectFactory {
	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exchangeRateReader());
	}

	@Bean
	public ExchangeRateReader exchangeRateReader() {
		return new ExchangeRateReaderStub(BigDecimal.valueOf(1_000L));
	}
}
