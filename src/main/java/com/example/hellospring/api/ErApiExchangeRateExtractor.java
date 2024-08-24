package com.example.hellospring.api;

import com.example.hellospring.exchangeRate.ExchangeRateData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public class ErApiExchangeRateExtractor implements ExchangeRateExtractor {
	@Override
	public BigDecimal extract(String response) throws JsonProcessingException {
		var mapper = new ObjectMapper();
		var data = mapper.readValue(response, ExchangeRateData.class);
		return data.rates().get("KRW");
	}
}
