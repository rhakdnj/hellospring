package com.example.hellospring;

import com.example.hellospring.api.ApiTemplate;
import com.example.hellospring.api.ErApiExchangeRateExtractor;
import com.example.hellospring.api.SimpleApiExecutor;
import com.example.hellospring.exchangeRate.CachedWebExchangeRateReader;
import com.example.hellospring.exchangeRate.WebApiExchangeRateReader;
import com.example.hellospring.payment.ExchangeRateReader;
import com.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public ApiTemplate apiTemplate() {
		return new ApiTemplate(new SimpleApiExecutor(), new ErApiExchangeRateExtractor());
	}

	@Bean
	public ExchangeRateReader exchangeRateReader() {
		return new WebApiExchangeRateReader(apiTemplate());
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}
}
