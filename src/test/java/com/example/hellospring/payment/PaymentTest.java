package com.example.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;

class PaymentTest {
	Clock clock;

	@BeforeEach
	void setup() {
		this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
	}

	@Test
	void createPrepared() {
		var payment = Payment.createPrepared(
			1L,
			"USD",
			BigDecimal.TEN,
			BigDecimal.valueOf(1_000L),
			LocalDateTime.now(this.clock)
		);

		Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000L));
		Assertions.assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(this.clock).plusMinutes(30L));
	}

	@Test
	void isValid() {
		var payment = Payment.createPrepared(
			1L,
			"USD",
			BigDecimal.TEN,
			BigDecimal.valueOf(1_000L),
			LocalDateTime.now(this.clock)
		);

		Assertions.assertThat(payment.isValid(this.clock)).isTrue();
		Assertions.assertThat(payment.isValid(Clock.offset(this.clock, Duration.of(30L, ChronoUnit.MINUTES)))).isFalse();
	}
}

