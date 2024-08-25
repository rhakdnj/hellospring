package com.example.hellospring.order;

import com.example.hellospring.OrderConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {
	private final OrderService orderService;

	public OrderServiceSpringTest(@Autowired OrderService orderService) {
		this.orderService = orderService;
	}

	@Test
	void createOrder() {
		var order = orderService.createOrder(new OrderNumber("1000"), BigDecimal.ONE);

		assertThat(order.getId()).isGreaterThan(0L);
	}
}
