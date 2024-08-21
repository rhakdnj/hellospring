package com.example.hellospring.payment;

import com.example.hellospring.TestPaymentConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {
	private final PaymentService paymentService;
	private final Clock clock;

	public PaymentServiceSpringTest(@Autowired PaymentService paymentService, @Autowired Clock clock) {
		this.paymentService = paymentService;
		this.clock = clock;
	}

	@Test
	void prepare() throws IOException {
		var payment = this.paymentService.prepare(1L, "USD", BigDecimal.TEN);

		assertThat(payment.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000L));
	}

	@Test
	void validUntil() throws IOException {
		var payment = this.paymentService.prepare(1L, "USD", BigDecimal.TEN);

		assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(this.clock).plusMinutes(30));
	}
}
