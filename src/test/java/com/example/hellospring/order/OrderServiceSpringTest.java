package com.example.hellospring.order;

import com.example.hellospring.OrderConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {
	private final OrderSpringTxService orderService;
	private final DataSource dataSource;

	public OrderServiceSpringTest(
		@Autowired OrderSpringTxService orderService,
		@Autowired DataSource dataSource
	) {
		this.orderService = orderService;
		this.dataSource = dataSource;
	}

	@Test
	void createOrder() {
		var order = orderService.createOrder(new OrderNumber("1000"), BigDecimal.ONE);

		assertThat(order.getId()).isGreaterThan(0L);
	}

	@Test
	void createOrders() {
		var orderRequests = List.of(
			new OrderRequest(new OrderNumber("1001"), BigDecimal.ONE),
			new OrderRequest(new OrderNumber("2001"), BigDecimal.TWO)
		);

		var orders = orderService.createOrders(orderRequests);

		assertThat(orders).hasSize(2);
		orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0L));
	}


	@Test
	void createDuplicatedOrders() {
		var orderRequests = List.of(
			new OrderRequest(new OrderNumber("3000"), BigDecimal.ONE),
			new OrderRequest(new OrderNumber("3000"), BigDecimal.TWO)
		);

		assertThatThrownBy(
			() -> orderService.createOrders(orderRequests)
		).isInstanceOf(DataIntegrityViolationException.class);

		var client = JdbcClient.create(this.dataSource);
		var count = client.sql("select count(*) from orders where number = '3000';")
			.query(Long.class)
			.single();

		assertThat(count).isEqualTo(0L);
	}
}
