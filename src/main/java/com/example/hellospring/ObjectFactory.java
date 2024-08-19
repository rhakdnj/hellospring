package com.example.hellospring;

public class ObjectFactory {
	public PaymentService paymentService() {
		return new PaymentService(exchangeRateReader());
	}

	public ExchangeRateReader exchangeRateReader() {
		return new WebExchangeRateReader();
	}
}
