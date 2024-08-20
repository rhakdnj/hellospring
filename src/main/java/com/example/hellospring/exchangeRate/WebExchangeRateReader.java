package com.example.hellospring.exchangeRate;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebExchangeRateReader implements ExchangeRateReader {
	@Override
	public BigDecimal getExchangeRate(String currency) throws IOException {
		var url = new URL("https://open.er-api.com/v6/latest/" + currency);
		var connection = (HttpURLConnection) url.openConnection();
		var br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		var response = br.lines().collect(Collectors.joining());
		br.close();

		var mapper = new ObjectMapper();
		var exchangeRateData = mapper.readValue(response, ExchangeRateData.class);
		return exchangeRateData.rates().get("KRW");
	}
}
