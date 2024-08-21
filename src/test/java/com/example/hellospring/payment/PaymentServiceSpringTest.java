package com.example.hellospring.payment;

import com.example.hellospring.TestObjectFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {
	@Autowired
	PaymentService paymentService;

	@Test
	void prepare() throws IOException {
		var payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

		assertThat(payment.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000L));
	}
}
