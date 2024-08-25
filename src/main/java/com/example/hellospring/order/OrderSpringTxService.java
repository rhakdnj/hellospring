package com.example.hellospring.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(transactionManager = "jdbcTransactionManager")
@RequiredArgsConstructor
public class OrderSpringTxService {
	private final OrderRepository orderRepository;

	public Order createOrder(OrderNumber orderNumber, BigDecimal totalPrice) {
		var order = new Order(orderNumber.value(), totalPrice);

		this.orderRepository.save(order);

		return order;
	}

	public List<Order> createOrders(List<OrderRequest> requests) {
		return requests.stream()
			.map(request -> {
				var order = new Order(request.orderNumber().value(), request.totalPrice());
				this.orderRepository.save(order);
				return order;
			})
			.toList();
	}
}
