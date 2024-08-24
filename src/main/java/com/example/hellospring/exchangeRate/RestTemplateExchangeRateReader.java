package com.example.hellospring.exchangeRate;

import com.example.hellospring.payment.ExchangeRateReader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;

@RequiredArgsConstructor
public class RestTemplateExchangeRateReader implements ExchangeRateReader {
	private final RestTemplate restTemplate;

	@Override
	public BigDecimal getExchangeRate(String currency) {
		var url = "https://open.er-api.com/v6/latest/" + currency;

		return this.restTemplate.getForObject(url, ExchangeRateData.class)
			.rates()
			.get("KRW");
	}
}
