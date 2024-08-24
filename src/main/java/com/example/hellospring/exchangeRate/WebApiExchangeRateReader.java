package com.example.hellospring.exchangeRate;

import com.example.hellospring.api.*;
import com.example.hellospring.payment.ExchangeRateReader;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;

@RequiredArgsConstructor
public class WebApiExchangeRateReader implements ExchangeRateReader {
	private final ApiTemplate apiTemplate;

	@Override
	public BigDecimal getExchangeRate(String currency) throws IOException {
		var url = "https://open.er-api.com/v6/latest/" + currency;

		return this.apiTemplate.getForExchangeRate(url);
	}

}
