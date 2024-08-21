package com.example.hellospring;

import com.example.hellospring.exchangeRate.ExchangeRateReaderStub;
import com.example.hellospring.payment.ExchangeRateReader;
import com.example.hellospring.payment.PaymentService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@TestConfiguration
public class TestPaymentConfig {
	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exchangeRateReader(), clock());
	}

	@Bean
	public ExchangeRateReader exchangeRateReader() {
		return new ExchangeRateReaderStub(BigDecimal.valueOf(1_000L));
	}

	@Bean
	public Clock clock() {
		return Clock.fixed(Instant.now(), ZoneId.systemDefault());
	}
}
