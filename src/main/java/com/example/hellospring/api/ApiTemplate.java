package com.example.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {
	private final ApiExecutor apiExecutor;
	private final ExchangeRateExtractor exchangeRateExtractor;

	public ApiTemplate() {
		this.apiExecutor = new SimpleApiExecutor();
		this.exchangeRateExtractor = new ErApiExchangeRateExtractor();
	}

	public ApiTemplate(ApiExecutor apiExecutor, ExchangeRateExtractor exchangeRateExtractor) {
		this.apiExecutor = apiExecutor;
		this.exchangeRateExtractor = exchangeRateExtractor;
	}

	public ApiTemplate(ApiExecutor apiExecutor) {
		this.apiExecutor = apiExecutor;
		this.exchangeRateExtractor = new ErApiExchangeRateExtractor();
	}

	public ApiTemplate(ExchangeRateExtractor exchangeRateExtractor) {
		this.apiExecutor = new SimpleApiExecutor();
		this.exchangeRateExtractor = exchangeRateExtractor;
	}

	public BigDecimal getForExchangeRate(String url) {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		String response;
		try {
			response = apiExecutor.execute(uri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			return exchangeRateExtractor.extract(response);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
