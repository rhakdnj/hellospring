package com.example.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {
	public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
		var exchangeRate = getExchangeRate(currency);
		var convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
		var validUntil = LocalDateTime.now().plusMinutes(30);

		return Payment.builder()
			.orderId(orderId)
			.currency(currency)
			.foreignCurrencyAmount(foreignCurrencyAmount)
			.exchangeRate(exchangeRate)
			.convertedAmount(convertedAmount)
			.validUntil(validUntil)
			.build();
	}

	private static BigDecimal getExchangeRate(String currency) throws IOException {
		var url = new URL("https://open.er-api.com/v6/latest/" + currency);
		var connection = (HttpURLConnection) url.openConnection();
		var br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		var response = br.lines().collect(Collectors.joining());
		br.close();

		var mapper = new ObjectMapper();
		var exchangeRateData = mapper.readValue(response, ExchangeRateData.class);
		return exchangeRateData.rates().get("KRW");
	}

	public static void main(String[] args) throws IOException {
		var paymentService = new PaymentService();
		var payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(1000.3));
		System.out.println(payment);
	}
}
